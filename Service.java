import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a service amenity in the supermarket that the shopper can interact with.
 */
public class Service {
    private String type;
    private String description;
    private String location;
    
    public enum ServiceType {
        ENTRANCE,
        CART_STATION,
        BASKET_STATION,
        PRODUCT_SEARCH,
        STAIRS,
        CHECKOUT_COUNTER,
        EXIT
    }
    
    private ServiceType serviceType;

    public Service(ServiceType serviceType, String location) {
        this.serviceType = serviceType;
        this.location = location;
        this.type = serviceType.toString();
        this.description = determineDescription(serviceType);
    }

    private String determineDescription(ServiceType type) {
        switch (type) {
            case ENTRANCE: return "Shoppers in the simulation spawn here.";
            case CART_STATION: return "Allows shoppers to get or return carts.";
            case BASKET_STATION: return "Allows shoppers to get or return baskets.";
            case PRODUCT_SEARCH: return "Allows shoppers to search for products in the supermarket.";
            case STAIRS: return "Allows shoppers to ascend or descend to the other floor.";
            case CHECKOUT_COUNTER: return "Where shoppers pay for their products.";
            case EXIT: return "Allows shoppers to leave the simulation.";
            default: return "Unknown service.";
        }
    }

    // Getters
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public ServiceType getServiceType() { return serviceType; }

    /**
     * Interacts with the service based on its type.
     */
    public String interact(Shopper shopper, Supermarket supermarket) {
        switch (serviceType) {
            case ENTRANCE: return handleEntrance(shopper);
            case CART_STATION: return handleCartStation(shopper);
            case BASKET_STATION: return handleBasketStation(shopper);
            case PRODUCT_SEARCH: return handleProductSearch(shopper, supermarket);
            case STAIRS: return handleStairs(shopper);
            case CHECKOUT_COUNTER: return handleCheckout(shopper);
            case EXIT: return handleExit(shopper);
            default: return "Unknown service type.";
        }
    }

    private String handleEntrance(Shopper shopper) {
        return "Shopper spawned at entrance. Welcome to the supermarket!";
    }

    private String handleCartStation(Shopper shopper) {
        if (shopper.getEquipment() == null && shopper.getHandCarried().isEmpty() && !shopper.hasCheckedOut()) {
            shopper.setEquipment(new Cart());
            return "Cart acquired successfully! You can now carry up to 30 products.";
        }
        else if (shopper.getEquipment() instanceof Cart && shopper.getEquipment().isEmpty()) {
            shopper.removeEquipment();
            return "Cart returned successfully.";
        }
        return "Cannot get or return cart. Make sure you have no hand-carried products and haven't checked out yet.";
    }

    private String handleBasketStation(Shopper shopper) {
        if (shopper.getEquipment() == null && shopper.getHandCarried().isEmpty() && !shopper.hasCheckedOut()) {
            shopper.setEquipment(new Basket());
            return "Basket acquired successfully! You can now carry up to 15 products.";
        }
        else if (shopper.getEquipment() instanceof Basket && shopper.getEquipment().isEmpty()) {
            shopper.removeEquipment();
            return "Basket returned successfully.";
        }
        return "Cannot get or return basket. Make sure you have no hand-carried products and haven't checked out yet.";
    }

    private String handleProductSearch(Shopper shopper, Supermarket supermarket) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String productName = scanner.nextLine();
        
        ArrayList<String> locations = supermarket.searchProduct(productName);
        
        if (locations.isEmpty()) {
            return "Product '" + productName + "' not found in the supermarket.";
        } else {
            String result = "Product '" + productName + "' found at:\n";
            for (String location : locations) {
                result = result + "  - " + location + "\n";
            }
            return result;
        }
    }

    private String handleStairs(Shopper shopper) {
        return "Using stairs to move to another floor...";
    }

    private String handleCheckout(Shopper shopper) {
        if (shopper.getHandCarried().isEmpty() && 
            (shopper.getEquipment() == null || shopper.getEquipment().isEmpty())) {
            return "No products to checkout. Please add some products first.";
        }
        
        // Create receipt using the Receipt class
        Receipt receipt = new Receipt(shopper);
        receipt.saveToFile();
        
        // Clear products and equipment
        shopper.clearAllProducts();
        shopper.setCheckedOut(true);
        
        return "Checkout completed successfully! Receipt has been saved.";
    }

    private String handleExit(Shopper shopper) {
        if (shopper.getEquipment() != null) {
            return "Cannot exit with equipment. Please return your equipment first.";
        }
        
        if (!shopper.getHandCarried().isEmpty()) {
            return "Cannot exit with hand-carried products. Please checkout first.";
        }
        
        shopper.setExited(true);
        return "Shopper has exited the supermarket. Thank you for shopping!";
    }

    @Override
    public String toString() {
        return "Service: " + type + " at " + location + " - " + description;
    }

    public void displayInfo() {
        System.out.println("Service Type: " + type);
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);
        System.out.println("-------------------");
    }
}
