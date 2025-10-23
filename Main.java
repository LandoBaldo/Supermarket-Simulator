import java.util.Scanner;

/**
 * Main class for the Supermarket Simulation
 * Provides a menu-driven interface to set up and run the simulation
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Supermarket supermarket;

    public static void main(String[] args) {
        displayWelcomeBanner();

        // Main menu loop
        while (true) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    createNewSupermarket();
                    break;
                case "2":
                    loadDemoSupermarket();
                    break;
                case "3":
                    if (supermarket != null) {
                        setupSupermarket();
                    } else {
                        System.out.println("Please create or load a supermarket first!");
                    }
                    break;
                case "4":
                    if (supermarket != null) {
                        supermarket.run();
                    } else {
                        System.out.println("Please create or load a supermarket first!");
                    }
                    break;
                case "5":
                    System.out.println("\nThank you for using the Supermarket Simulation!");
                    System.out.println("Goodbye!\n");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    /**
     * Display welcome banner
     */
    private static void displayWelcomeBanner() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║      SUPERMARKET SIMULATION SYSTEM             ║");
        System.out.println("║      Version 1.0                               ║");
        System.out.println("║                                                ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }

    /**
     * Display main menu
     */
    private static void displayMainMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║           MAIN MENU                    ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Create New Supermarket             ║");
        System.out.println("║  2. Load Demo Supermarket (Ground Floor)║");
        System.out.println("║  3. Setup Supermarket                  ║");
        System.out.println("║  4. Start Simulation                   ║");
        System.out.println("║  5. Exit                               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Enter your choice: ");
    }

    /**
     * Create a new custom supermarket
     */
    private static void createNewSupermarket() {
        System.out.println("\n=== CREATE NEW SUPERMARKET ===");

        System.out.print("Enter supermarket name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter map width (recommended 20-50): ");
        int width = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter map height (recommended 15-30): ");
        int height = Integer.parseInt(scanner.nextLine().trim());

        supermarket = new Supermarket(name, width, height);

        System.out.println("\n✓ Supermarket '" + name + "' created successfully!");
        System.out.println("Map size: " + width + " x " + height);
    }

    /**
     * Load a pre-configured demo supermarket matching the ground floor layout
     */
    private static void loadDemoSupermarket() {
        System.out.println("\n=== LOADING GROUND FLOOR LAYOUT ===");

        // Create 22x22 map to match the layout
        supermarket = new Supermarket("Ground Floor (GF)", 22, 22);

        System.out.println("Setting up Ground Floor layout...");

        // ========== TABLES (GREEN) ==========
        // Row 5-7, Columns 1-2
        supermarket.addTable(1, 5, 4);
        supermarket.addTable(2, 5, 4);
        supermarket.addTable(1, 6, 4);
        supermarket.addTable(2, 6, 4);
        supermarket.addTable(1, 7, 4);
        supermarket.addTable(2, 7, 4);

        // Row 9-11, Columns 9-10
        supermarket.addTable(9, 9, 4);
        supermarket.addTable(10, 9, 4);
        supermarket.addTable(9, 10, 4);
        supermarket.addTable(10, 10, 4);
        supermarket.addTable(9, 11, 4);
        supermarket.addTable(10, 11, 4);

        // ========== CHILLED COUNTERS (BLUE) ==========
        // Row 1-2, Columns 1-6
        for (int x = 1; x <= 6; x++) {
            supermarket.addChilledCounter(x, 1, 3);
            supermarket.addChilledCounter(x, 2, 3);
        }

        // Row 1-2, Columns 8-13
        for (int x = 8; x <= 13; x++) {
            supermarket.addChilledCounter(x, 1, 3);
            supermarket.addChilledCounter(x, 2, 3);
        }

        // Row 1-2, Columns 16-20
        for (int x = 16; x <= 20; x++) {
            supermarket.addChilledCounter(x, 1, 3);
            supermarket.addChilledCounter(x, 2, 3);
        }

        // ========== REFRIGERATORS (DARK BLUE) ==========
        // Row 17, Columns 1-6
        for (int x = 1; x <= 6; x++) {
            supermarket.addRefrigerator(x, 17, 9);
        }

        // Row 17, Columns 8-13
        for (int x = 8; x <= 13; x++) {
            supermarket.addRefrigerator(x, 17, 9);
        }

        // Row 17, Columns 16-20
        for (int x = 16; x <= 20; x++) {
            supermarket.addRefrigerator(x, 17, 9);
        }

        // ========== SHELVES (ORANGE/YELLOW) ==========
        // Row 5-7, Columns 4-5
        for (int y = 5; y <= 7; y++) {
            supermarket.addShelf(4, y, 8);
            supermarket.addShelf(5, y, 8);
        }

        // Row 5-7, Columns 12-13
        for (int y = 5; y <= 7; y++) {
            supermarket.addShelf(12, y, 8);
            supermarket.addShelf(13, y, 8);
        }

        // Row 5-7, Columns 16-17
        for (int y = 5; y <= 7; y++) {
            supermarket.addShelf(16, y, 8);
            supermarket.addShelf(17, y, 8);
        }

        // Row 9-11, Columns 4-5
        for (int y = 9; y <= 11; y++) {
            supermarket.addShelf(4, y, 8);
            supermarket.addShelf(5, y, 8);
        }

        // Row 9-11, Columns 12-13
        for (int y = 9; y <= 11; y++) {
            supermarket.addShelf(12, y, 8);
            supermarket.addShelf(13, y, 8);
        }

        // Row 9-11, Columns 16-17
        for (int y = 9; y <= 11; y++) {
            supermarket.addShelf(16, y, 8);
            supermarket.addShelf(17, y, 8);
        }

        // ========== SERVICES ==========
        System.out.println("Setting up services...");

        // Entrances (E) - Row 20, Columns 1 and 20
        supermarket.addService(Service.ServiceType.ENTRANCE, 1, 20);
        supermarket.addService(Service.ServiceType.ENTRANCE, 20, 20);

        // Cart/Basket Stations (I) - Row 15, Columns 1, 9, 13, 20
        supermarket.addService(Service.ServiceType.CART_STATION, 1, 15);
        supermarket.addService(Service.ServiceType.BASKET_STATION, 9, 15);
        supermarket.addService(Service.ServiceType.BASKET_STATION, 13, 15);
        supermarket.addService(Service.ServiceType.CART_STATION, 20, 15);

        // Checkout counters - Row 18, Columns 2, 4, 6, 9, 11, 13, 16, 18, 20
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 2, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 4, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 6, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 9, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 11, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 13, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 16, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 18, 18);
        supermarket.addService(Service.ServiceType.CHECKOUT_COUNTER, 20, 18);

        // Stairs (U) - Row 21, Columns 8 and 13
        supermarket.addService(Service.ServiceType.STAIRS, 8, 21);
        supermarket.addService(Service.ServiceType.STAIRS, 13, 21);

        // Add sample products
        System.out.println("Stocking products...");
        addDemoProducts();

        // Add a demo shopper at entrance
        System.out.println("Adding demo shopper...");
        Shopper customer = new Shopper("Alex Smith", 28);
        supermarket.addShopper(customer, 2, 19);

        System.out.println("\n✓ Ground Floor layout loaded successfully!");
        System.out.println("Layout: 22 x 22 grid");
        supermarket.displayStats();
    }

    /**
     * Add demo products to the supermarket
     */
    private static void addDemoProducts() {
        // Fresh produce on tables
        supermarket.addProductToLocation(new Products("BREAD", "BRD0001", "Baguette"), 1, 5);
        supermarket.addProductToLocation(new Products("BREAD", "BRD0002", "Whole Wheat"), 2, 5);
        supermarket.addProductToLocation(new Products("EGGS", "EGG0001", "Free-range eggs"), 1, 6);
        supermarket.addProductToLocation(new Products("FRUIT", "FRU0001", "Apples"), 2, 6);
        supermarket.addProductToLocation(new Products("VEGETABLE", "VEG0001", "Cabbage"), 1, 7);
        supermarket.addProductToLocation(new Products("VEGETABLE", "VEG0002", "Carrots"), 2, 7);
        supermarket.addProductToLocation(new Products("FRUIT", "FRU0002", "Bananas"), 9, 9);
        supermarket.addProductToLocation(new Products("VEGETABLE", "VEG0003", "Tomatoes"), 10, 10);

        // Meat and seafood in chilled counters
        supermarket.addProductToLocation(new Products("BEEF", "BEF0001", "Rib steak"), 1, 1);
        supermarket.addProductToLocation(new Products("CHICKEN", "CHK0001", "Breast fillet"), 2, 1);
        supermarket.addProductToLocation(new Products("SEAFOOD", "SEA0001", "Tilapia"), 3, 1);
        supermarket.addProductToLocation(new Products("BEEF", "BEF0002", "Ground beef"), 4, 1);
        supermarket.addProductToLocation(new Products("CHICKEN", "CHK0002", "Whole chicken"), 5, 2);
        supermarket.addProductToLocation(new Products("SEAFOOD", "SEA0002", "Salmon"), 8, 1);
        supermarket.addProductToLocation(new Products("BEEF", "BEF0003", "Sirloin"), 16, 1);
        supermarket.addProductToLocation(new Products("CHICKEN", "CHK0003", "Chicken wings"), 17, 2);

        // Dairy and frozen in refrigerators
        supermarket.addProductToLocation(new Products("MILK", "MLK0001", "Fresh milk"), 1, 17);
        supermarket.addProductToLocation(new Products("CHEESE", "CHS0001", "Mozzarella"), 2, 17);
        supermarket.addProductToLocation(new Products("FROZEN", "FRZ0001", "Chicken nuggets"), 3, 17);
        supermarket.addProductToLocation(new Products("MILK", "MLK0002", "Chocolate milk"), 4, 17);
        supermarket.addProductToLocation(new Products("CHEESE", "CHS0002", "Cheddar"), 8, 17);
        supermarket.addProductToLocation(new Products("FROZEN", "FRZ0002", "Ice cream"), 9, 17);
        supermarket.addProductToLocation(new Products("MILK", "MLK0003", "Almond milk"), 16, 17);
        supermarket.addProductToLocation(new Products("FROZEN", "FRZ0003", "Pizza"), 17, 17);

        // Packaged goods on shelves
        supermarket.addProductToLocation(new Products("CEREAL", "CER0001", "Oatmeal"), 4, 5);
        supermarket.addProductToLocation(new Products("NOODLES", "NDL0001", "Instant ramen"), 5, 5);
        supermarket.addProductToLocation(new Products("SNACKS", "SNK0001", "Cookies"), 4, 6);
        supermarket.addProductToLocation(new Products("CANNED", "CAN0001", "Canned tuna"), 5, 7);
        supermarket.addProductToLocation(new Products("CONDIMENTS", "CON0001", "Salt"), 4, 9);
        supermarket.addProductToLocation(new Products("CEREAL", "CER0002", "Cornflakes"), 12, 5);
        supermarket.addProductToLocation(new Products("SNACKS", "SNK0002", "Chips"), 13, 6);
        supermarket.addProductToLocation(new Products("SOFTDRINK", "SFT0001", "Sparkling water"), 12, 10);
        supermarket.addProductToLocation(new Products("JUICE", "JUC0001", "Orange juice"), 13, 11);
        supermarket.addProductToLocation(new Products("CANNED", "CAN0002", "Beans"), 16, 5);
        supermarket.addProductToLocation(new Products("CONDIMENTS", "CON0002", "Pepper"), 17, 7);
        supermarket.addProductToLocation(new Products("ALCOHOL", "ALC0001", "Beer"), 16, 9);
    }

    /**
     * Setup supermarket interactively
     */
    private static void setupSupermarket() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║        SUPERMARKET SETUP               ║");
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║  1. Add Storage Unit                   ║");
            System.out.println("║  2. Add Service                        ║");
            System.out.println("║  3. Add Product to Storage             ║");
            System.out.println("║  4. Add Shopper                        ║");
            System.out.println("║  5. View Map                           ║");
            System.out.println("║  6. View Stats                         ║");
            System.out.println("║  7. Back to Main Menu                  ║");
            System.out.println("╚════════════════════════════════════════╝");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addStorageUnit();
                    break;
                case "2":
                    addService();
                    break;
                case "3":
                    addProduct();
                    break;
                case "4":
                    addShopper();
                    break;
                case "5":
                    supermarket.displayMap();
                    supermarket.displayLegend();
                    break;
                case "6":
                    supermarket.displayStats();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /**
     * Add storage unit interactively
     */
    private static void addStorageUnit() {
        System.out.println("\n=== ADD STORAGE UNIT ===");
        System.out.println("1. Table");
        System.out.println("2. Chilled Counter");
        System.out.println("3. Refrigerator");
        System.out.println("4. Shelf");
        System.out.print("Select type: ");

        String type = scanner.nextLine().trim();

        System.out.print("Enter X position: ");
        int x = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Y position: ");
        int y = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter capacity: ");
        int capacity = Integer.parseInt(scanner.nextLine().trim());

        boolean success = false;
        switch (type) {
            case "1":
                success = supermarket.addTable(x, y, capacity);
                break;
            case "2":
                success = supermarket.addChilledCounter(x, y, capacity);
                break;
            case "3":
                success = supermarket.addRefrigerator(x, y, capacity);
                break;
            case "4":
                success = supermarket.addShelf(x, y, capacity);
                break;
            default:
                System.out.println("Invalid type!");
                return;
        }

        if (success) {
            System.out.println("✓ Storage unit added successfully!");
        } else {
            System.out.println("✗ Failed to add storage unit!");
        }
    }

    /**
     * Add service interactively
     */
    private static void addService() {
        System.out.println("\n=== ADD SERVICE ===");
        System.out.println("1. Entrance");
        System.out.println("2. Cart Station");
        System.out.println("3. Basket Station");
        System.out.println("4. Product Search");
        System.out.println("5. Stairs");
        System.out.println("6. Checkout Counter");
        System.out.println("7. Exit");
        System.out.print("Select type: ");

        String type = scanner.nextLine().trim();

        System.out.print("Enter X position: ");
        int x = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter Y position: ");
        int y = Integer.parseInt(scanner.nextLine().trim());

        Service.ServiceType serviceType = null;
        switch (type) {
            case "1":
                serviceType = Service.ServiceType.ENTRANCE;
                break;
            case "2":
                serviceType = Service.ServiceType.CART_STATION;
                break;
            case "3":
                serviceType = Service.ServiceType.BASKET_STATION;
                break;
            case "4":
                serviceType = Service.ServiceType.PRODUCT_SEARCH;
                break;
            case "5":
                serviceType = Service.ServiceType.STAIRS;
                break;
            case "6":
                serviceType = Service.ServiceType.CHECKOUT_COUNTER;
                break;
            case "7":
                serviceType = Service.ServiceType.EXIT;
                break;
            default:
                System.out.println("Invalid type!");
                return;
        }

        if (supermarket.addService(serviceType, x, y)) {
            System.out.println("✓ Service added successfully!");
        } else {
            System.out.println("✗ Failed to add service!");
        }
    }

    /**
     * Add product interactively
     */
    private static void addProduct() {
        System.out.println("\n=== ADD PRODUCT ===");

        System.out.print("Enter product type (e.g., BEEF, BREAD, MILK): ");
        String type = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter serial number: ");
        String serial = scanner.nextLine().trim();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter storage X position: ");
        int x = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter storage Y position: ");
        int y = Integer.parseInt(scanner.nextLine().trim());

        Products product = new Products(type, serial, name);

        if (supermarket.addProductToLocation(product, x, y)) {
            System.out.println("✓ Product added successfully!");
            product.displayInfo();
        } else {
            System.out.println("✗ Failed to add product!");
        }
    }

    /**
     * Add shopper interactively
     */
    private static void addShopper() {
        System.out.println("\n=== ADD SHOPPER ===");

        System.out.print("Enter shopper name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter shopper age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter spawn X position: ");
        int x = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter spawn Y position: ");
        int y = Integer.parseInt(scanner.nextLine().trim());

        Shopper shopper = new Shopper(name, age);

        if (supermarket.addShopper(shopper, x, y)) {
            System.out.println("✓ Shopper added successfully!");
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("Position: (" + x + ", " + y + ")");
        } else {
            System.out.println("✗ Failed to add shopper!");
        }
    }
}