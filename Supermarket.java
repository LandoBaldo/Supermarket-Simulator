import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main Supermarket class that manages the entire simulation
 */
public class Supermarket {
    private String name;
    private Map map;
    private ArrayList<Shopper> shoppers;
    private ArrayList<Service> services;

    // Storage units organized by type
    private ArrayList<Table> tables;
    private ArrayList<ChilledCounters> chilledCounters;
    private ArrayList<Shelf> shelves;
    private ArrayList<Refrigerator> refrigerators;

    // HashMap to track which storage unit is at which position
    private HashMap<String, StorageUnit> storageLocations;
    private HashMap<String, Service> serviceLocations;

    public Supermarket(String name, int width, int height) {
        this.name = name;
        this.map = new Map(width, height);
        this.shoppers = new ArrayList<>();
        this.services = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.chilledCounters = new ArrayList<>();
        this.shelves = new ArrayList<>();
        this.refrigerators = new ArrayList<>();
        this.storageLocations = new HashMap<>();
        this.serviceLocations = new HashMap<>();

        initializeSupermarket();
    }

    /**
     * Initialize the supermarket with perimeter walls
     */
    private void initializeSupermarket() {
        map.addPerimeterWalls();
        System.out.println("Supermarket '" + name + "' initialized!");
    }

    // ==================== STORAGE UNIT MANAGEMENT ====================

    /**
     * Add a table to the supermarket
     */
    public boolean addTable(int x, int y, int capacity) {
        Table table = new Table(capacity);
        map.addTable(table, x, y);
        tables.add(table);
        storageLocations.put(x + "," + y, table);
        return true;
    }

    /**
     * Add a chilled counter to the supermarket
     */
    public boolean addChilledCounter(int x, int y, int capacity) {
        ChilledCounters cc = new ChilledCounters(capacity);
        map.addChilledCounter(cc, x, y);
        chilledCounters.add(cc);
        storageLocations.put(x + "," + y, cc);
        return true;
    }

    /**
     * Add a refrigerator to the supermarket
     */
    public boolean addRefrigerator(int x, int y, int capacity) {
        Refrigerator ref = new Refrigerator(capacity);
        map.addRefrigerator(ref, x, y);
        refrigerators.add(ref);
        storageLocations.put(x + "," + y, ref);
        return true;
    }

    /**
     * Add a shelf to the supermarket
     */
    public boolean addShelf(int x, int y, int capacity) {
        Shelf shelf = new Shelf(capacity);
        map.addShelf(shelf, x, y);
        shelves.add(shelf);
        storageLocations.put(x + "," + y, shelf);
        return true;
    }

    // ==================== SERVICE MANAGEMENT ====================

    /**
     * Add a service to the supermarket
     */
    public boolean addService(Service.ServiceType type, int x, int y) {
        String location = "(" + x + ", " + y + ")";
        Service service = new Service(type, location);
        services.add(service);
        serviceLocations.put(x + "," + y, service);

        // Mark service location on map with appropriate symbol
        String symbol = getServiceSymbol(type);
        if (map.isValidPosition(x, y) && map.isWalkable(x, y)) {
            map.setCell(x, y, symbol);
            return true;
        }
        return false;
    }

    /**
     * Get symbol for service type
     */
    private String getServiceSymbol(Service.ServiceType type) {
        switch (type) {
            case ENTRANCE:
                return "E";
            case CART_STATION:
                return "K";
            case BASKET_STATION:
                return "B";
            case PRODUCT_SEARCH:
                return "?";
            case STAIRS:
                return "^";
            case CHECKOUT_COUNTER:
                return "$";
            case EXIT:
                return "X";
            default:
                return "S";
        }
    }

    // ==================== SHOPPER MANAGEMENT ====================

    /**
     * Add a shopper to the supermarket at entrance
     */
    public boolean addShopper(Shopper shopper, int x, int y) {
        if (!map.isValidPosition(x, y) || !map.isWalkable(x, y)) {
            System.out.println("Cannot place shopper at invalid position!");
            return false;
        }

        shopper.setPostion(x, y);
        shoppers.add(shopper);
        map.setCell(x, y, "S");
        System.out.println("Shopper " + shopper.getName() + " added at (" + x + ", " + y + ")");
        return true;
    }

    /**
     * Remove shopper from supermarket
     */
    public boolean removeShopper(Shopper shopper) {
        if (shoppers.remove(shopper)) {
            int x = shopper.getX();
            int y = shopper.getY();
            if (map.isValidPosition(x, y)) {
                map.setCell(x, y, " ");
            }
            return true;
        }
        return false;
    }

    // ==================== PRODUCT MANAGEMENT ====================

    /**
     * Add a product to a storage unit at specific location
     */
    public boolean addProductToLocation(Products product, int x, int y) {
        String key = x + "," + y;
        StorageUnit storage = storageLocations.get(key);

        if (storage != null) {
            return storage.addProduct(product);
        }

        System.out.println("No storage unit found at (" + x + ", " + y + ")");
        return false;
    }

    /**
     * Search for a product by name across all storage units
     */
    public ArrayList<String> searchProduct(String productName) {
        ArrayList<String> locations = new ArrayList<>();

        // Search in all storage units
        searchInStorageList(tables, "Table", locations, productName);
        searchInStorageList(chilledCounters, "Chilled Counter", locations, productName);
        searchInStorageList(shelves, "Shelf", locations, productName);
        searchInStorageList(refrigerators, "Refrigerator", locations, productName);

        return locations;
    }

    /**
     * Helper method to search in a list of storage units
     */
    private <T extends StorageUnit> void searchInStorageList(ArrayList<T> storageList,
            String storageType, ArrayList<String> locations, String productName) {
        for (int i = 0; i < storageList.size(); i++) {
            StorageUnit storage = storageList.get(i);
            for (Products product : storage.getProducts()) {
                if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                    locations.add(storageType + " #" + (i + 1));
                }
            }
        }
    }

    // ==================== INTERACTION METHODS ====================

    /**
     * Handle shopper interaction with cell in front
     */
    public String handleInteraction(Shopper shopper) {
        FrontCell front = MovementController.getCellInFront(shopper, map);

        if (front == null) {
            return "Nothing in front.";
        }

        String token = front.getToken();
        int x = front.getX();
        int y = front.getY();
        String key = x + "," + y;

        // Check if it's a service
        Service service = serviceLocations.get(key);
        if (service != null) {
            return service.interact(shopper, this);
        }

        // Check if it's a storage unit
        StorageUnit storage = storageLocations.get(key);
        if (storage != null) {
            return handleStorageInteraction(shopper, storage);
        }

        return "Cannot interact with " + token;
    }

    /**
     * Handle interaction with storage unit
     */
    private String handleStorageInteraction(Shopper shopper, StorageUnit storage) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== Storage Unit ===");
        System.out.println("Products available:");
        ArrayList<Products> products = storage.getProducts();

        if (products.isEmpty()) {
            return "This storage unit is empty.";
        }

        for (int i = 0; i < products.size(); i++) {
            Products p = products.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " - $" + p.getPrice());
        }

        System.out.print("Select product number to take (0 to cancel): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice > 0 && choice <= products.size()) {
            Products selectedProduct = products.get(choice - 1);

            // Check if shopper can purchase this product
            if (!shopper.canPurchase(selectedProduct)) {
                return "You cannot purchase " + selectedProduct.getName() + " (age restriction).";
            }

            // Try to add to shopper's inventory
            if (shopper.addProduct(selectedProduct)) {
                storage.removeProduct(selectedProduct);
                return "Added " + selectedProduct.getName() + " to your inventory.";
            } else {
                return "Your inventory is full! Get a cart or basket.";
            }
        }

        return "No product selected.";
    }

    // ==================== DISPLAY METHODS ====================

    /**
     * Display the supermarket map
     */
    public void displayMap() {
        System.out.println("\n=== " + name + " Map ===");
        map.printMap();
    }

    /**
     * Display supermarket statistics
     */
    public void displayStats() {
        System.out.println("\n=== SUPERMARKET STATISTICS ===");
        System.out.println("Name: " + name);
        System.out.println("Map Size: " + map.getWidth() + " x " + map.getHeight());
        System.out.println("\n--- Storage Units ---");
        System.out.println("Tables: " + tables.size());
        System.out.println("Chilled Counters: " + chilledCounters.size());
        System.out.println("Refrigerators: " + refrigerators.size());
        System.out.println("Shelves: " + shelves.size());
        System.out.println("\n--- Services ---");
        System.out.println("Total Services: " + services.size());
        for (Service service : services) {
            System.out.println("  - " + service.getType() + " at " + service.getLocation());
        }
        System.out.println("\n--- Shoppers ---");
        System.out.println("Active Shoppers: " + shoppers.size());
        for (Shopper shopper : shoppers) {
            System.out.println("  - " + shopper.getName() + " at (" +
                    shopper.getX() + ", " + shopper.getY() + ")");
        }
        System.out.println("===============================");
    }

    /**
     * Display legend for map symbols
     */
    public void displayLegend() {
        System.out.println("\n=== MAP LEGEND ===");
        System.out.println("█ - Wall");
        System.out.println("S - Shopper");
        System.out.println("T - Table");
        System.out.println("C - Chilled Counter");
        System.out.println("R - Refrigerator");
        System.out.println("H - Shelf");
        System.out.println("E - Entrance");
        System.out.println("K - Cart Station");
        System.out.println("B - Basket Station");
        System.out.println("? - Product Search");
        System.out.println("^ - Stairs");
        System.out.println("$ - Checkout Counter");
        System.out.println("X - Exit");
        System.out.println("  - Empty Space");
        System.out.println("==================");
    }

    // ==================== GAME LOOP ====================

    /**
     * Run the supermarket simulation
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   WELCOME TO " + name.toUpperCase() + "   ║");
        System.out.println("╚════════════════════════════════════════╝");

        displayLegend();

        while (true) {
            displayMap();

            System.out.println("\n=== COMMANDS ===");
            System.out.println("1. Move (W/A/S/D)");
            System.out.println("2. Interact (I)");
            System.out.println("3. View Inventory");
            System.out.println("4. View Stats");
            System.out.println("5. Quit");
            System.out.print("Enter command: ");

            String input = scanner.nextLine().trim().toLowerCase();

            if (shoppers.isEmpty()) {
                System.out.println("No shoppers in the supermarket!");
                break;
            }

            Shopper currentShopper = shoppers.get(0); // Control first shopper

            switch (input) {
                case "w":
                case "a":
                case "s":
                case "d":
                    boolean moved = MovementController.handleInput(input, currentShopper, map);
                    if (moved) {
                        System.out.println("✓ Moved " + input.toUpperCase());
                        System.out.println("Position: (" + currentShopper.getX() + ", " +
                                currentShopper.getY() + ")");
                        System.out.println("Facing: " + currentShopper.getFacing());
                    } else {
                        System.out.println("✗ Cannot move in that direction!");
                    }
                    break;

                case "i":
                case "interact":
                    String result = handleInteraction(currentShopper);
                    System.out.println(result);
                    break;

                case "3":
                case "inventory":
                    displayInventory(currentShopper);
                    break;

                case "4":
                case "stats":
                    displayStats();
                    break;

                case "5":
                case "quit":
                case "q":
                    System.out.println("Thank you for visiting " + name + "!");
                    return;

                default:
                    System.out.println("Invalid command!");
            }

            // Check if shopper has exited
            if (currentShopper.hasExited()) {
                System.out.println("\nShopper has left the supermarket!");
                removeShopper(currentShopper);
            }
        }
    }

    /**
     * Display shopper's inventory
     */
    private void displayInventory(Shopper shopper) {
        System.out.println("\n=== INVENTORY ===");
        System.out.println("Shopper: " + shopper.getName());
        System.out.println("Age: " + shopper.getAge());

        if (shopper.getEquipment() != null) {
            System.out.println("Equipment: " + shopper.getEquipment().getClass().getSimpleName());
            System.out.println("  Capacity: " + shopper.getEquipment().getProducts().size() +
                    "/" + shopper.getEquipment().getCapacity());
        }

        System.out.println("\nHand Carried: " + shopper.getHandCarried().size() + "/2");

        System.out.println("\nAll Products:");
        ArrayList<Products> allProducts = shopper.getAllProducts();
        if (allProducts.isEmpty()) {
            System.out.println("  (empty)");
        } else {
            double total = 0;
            for (Products p : allProducts) {
                double price = shopper.applyDiscount(p);
                System.out.println("  - " + p.getName() + " ($" + price + ")");
                total += price;
            }
            System.out.println("Total: $" + String.format("%.2f", total));
        }
        System.out.println("=================");
    }

    // ==================== GETTERS ====================

    public String getName() {
        return name;
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Shopper> getShoppers() {
        return shoppers;
    }

    public ArrayList<Service> getServices() {
        return services;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<ChilledCounters> getChilledCounters() {
        return chilledCounters;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public ArrayList<Refrigerator> getRefrigerators() {
        return refrigerators;
    }
}