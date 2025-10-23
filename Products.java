import java.util.*;

public class Products {
    private String serialNumber;
    private String name;
    private String displayLocation;
    private String productType;
    private double price;
    
    public enum ConsumableType {CONSUMABLE, NON_CONSUMABLE, BEVERAGE}

    private ConsumableType consumableType;

    // Constructor
    public Products(String productType, String serialNumber, String name) {
        this.productType = productType;
        this.serialNumber = serialNumber;
        this.name = name;
        this.displayLocation = determineLocation(productType);
        this.consumableType = determineConsumableType(productType);
        this.price = determineDefaultPrice(productType);
    }

    public Products(String productType, String serialNumber, String name, double price,
        ConsumableType consumableType) {
        this.productType = productType;
        this.serialNumber = serialNumber;
        this.name = name;
        this.displayLocation = determineLocation(productType);
        this.price = price;
        this.consumableType = consumableType;
    }

    // Determine location based on product type
    private String determineLocation(String type) {
        switch (type.toUpperCase()) {
            // Chilled counter products
            case "BEEF":
            case "SEAFOOD":
            case "CHICKEN":
                return "Chilled counter";

            // Table products
            case "BREAD":
            case "EGGS":
            case "FRUIT":
            case "VEGETABLE":
                return "Table";

            // Refrigerator products
            case "MILK":
            case "FROZEN":
            case "CHEESE":
                return "Refrigerator";

            // Shelf products (default)
            case "CEREAL":
            case "NOODLES":
            case "SNACKS":
            case "CANNED":
            case "CONDIMENTS":
            case "SOFTDRINK":
            case "JUICE":
            case "ALCOHOL":
            case "CLEANING":
            case "HOME":
            case "HAIRCARE":
            case "BODYCARE":
            case "DENTAL":
            case "CLOTHES":
            case "STATIONERY":
            case "PETFOOD":
            default:
                return "Shelf";
        }
    }

    // Get serial number prefix based on product type
    public String getSerialNumberPrefix() {
        switch (productType.toUpperCase()) {
            case "BEEF": return "BEF";
            case "SEAFOOD": return "SEA";
            case "BREAD": return "BRD";
            case "CEREAL": return "CER";
            case "NOODLES": return "NDL";
            case "SNACKS": return "SNK";
            case "CANNED": return "CAN";
            case "CONDIMENTS": return "CON";
            case "EGGS": return "EGG";
            case "SOFTDRINK": return "SFT";
            case "JUICE": return "JUC";
            case "ALCOHOL": return "ALC";
            case "CLEANING": return "CLE";
            case "HOME": return "HOM";
            case "HAIRCARE": return "HAR";
            case "BODYCARE": return "BOD";
            case "DENTAL": return "DEN";
            case "CLOTHES": return "CLO";
            case "STATIONERY": return "STN";
            case "PETFOOD": return "PET";
            case "FRUIT": return "FRU";
            case "VEGETABLE": return "VEG";
            case "MILK": return "MLK";
            case "FROZEN": return "FRZ";
            case "CHEESE": return "CHS";
            case "CHICKEN": return "CHK";
            default: return "UNK";
        }
    }

    private ConsumableType determineConsumableType(String type) {
        switch (type.toUpperCase()) {
            case "SOFTDRINK":
            case "JUICE":
            case "ALCOHOL":
                return ConsumableType.BEVERAGE;
            case "BEEF":
            case "SEAFOOD":
            case "BREAD":
            case "CEREAL":
            case "NOODLES":
            case "SNACKS":
            case "CANNED":
            case "CONDIMENTS":
            case "EGGS":
            case "FRUIT":
            case "VEGETABLE":
            case "MILK":
            case "FROZEN":
            case "CHEESE":
            case "CHICKEN":
                return ConsumableType.CONSUMABLE;
            default:
                return ConsumableType.NON_CONSUMABLE;
        }
    }

    private double determineDefaultPrice(String type) {
        switch (type.toUpperCase()) {
            case "BEEF": return 350.0;
            case "SEAFOOD": return 460.0;
            case "BREAD": return 35.0;
            case "CEREAL": return 150.0;
            case "NOODLES": return 85.0;
            case "SNACKS": return 15.5;
            case "CANNED": return 35.5;
            case "CONDIMENTS": return 49.0;
            case "EGGS": return 120.0;
            case "SOFTDRINK": return 50.0;
            case "JUICE": return 20.0;
            case "ALCOHOL": return 60.0;
            case "MILK": return 50.5;
            case "FROZEN": return 70.0;
            case "CHEESE": return 60.0;
            case "CHICKEN": return 250.0;
            default: return 0.0;
        }
    }

    // Getters
    public String getSerialNumber() { return serialNumber; }
    public String getName() { return name; }
    public String getDisplayLocation() { return displayLocation; }
    public String getProductType() { return productType; }
    public double getPrice() { return price; }
    public ConsumableType getConsumableType() { return consumableType; }
    public boolean isConsumable() { return consumableType == ConsumableType.CONSUMABLE || consumableType == ConsumableType.BEVERAGE; }
    public boolean isBeverage() { return consumableType == ConsumableType.BEVERAGE; }
    public boolean isNonConsumable() { return consumableType == ConsumableType.NON_CONSUMABLE; }

    // Setters
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setConsumableType(ConsumableType consumableType) { this.consumableType = consumableType; }

    public void setProductType(String productType) {
        this.productType = productType;
        this.displayLocation = determineLocation(productType);
        this.consumableType = determineConsumableType(productType);
        this.price = determineDefaultPrice(productType);
    }

    // Display product information
    public void displayInfo() {
        System.out.println("Product Type: " + productType);
        System.out.println("Name: " + name);
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Display Location: " + displayLocation);
        System.out.println("Price: $" + price);
        System.out.println("Consumable Type: " + consumableType);
        System.out.println("Serial Prefix: " + getSerialNumberPrefix());
        System.out.println("-------------------");
    }

    @Override
    public String toString() {
        return String.format("%s - %s (SN: %s, Location: %s, $%.2f)",
                productType, name, serialNumber, displayLocation, price);
    }

    // Main method for demonstration
    public static void main(String[] args) {
        System.out.println("=== Supermarket Product Management System ===\n");

        // Create sample products from first table
        Products beef = new Products("BEEF", "BEF0001", "Rib steak");
        Products seafood = new Products("SEAFOOD", "SEA0001", "Tilapia");
        Products bread = new Products("BREAD", "BRD0001", "Baguette");
        Products cereal = new Products("CEREAL", "CER0001", "Oatmeal");
        Products noodles = new Products("NOODLES", "NDL0001", "Instant ramen");
        Products snacks = new Products("SNACKS", "SNK0001", "Cookies");
        Products canned = new Products("CANNED", "CAN0001", "Canned tuna");
        Products condiments = new Products("CONDIMENTS", "CON0001", "Salt");
        Products eggs = new Products("EGGS", "EGG0001", "Free-range eggs");
        Products softdrink = new Products("SOFTDRINK", "SFT0001", "Sparkling water");
        Products juice = new Products("JUICE", "JUC0001", "Orange juice");
        Products alcohol = new Products("ALCOHOL", "ALC0001", "Beer");

        // Create sample products from second table
        Products fruit = new Products("FRUIT", "FRU0001", "Apples");
        Products vegetable = new Products("VEGETABLE", "VEG0001", "Cabbage");
        Products milk = new Products("MILK", "MLK0001", "Fresh milk");
        Products frozen = new Products("FROZEN", "FRZ0001", "Chicken nuggets");
        Products cheese = new Products("CHEESE", "CHS0001", "Mozzarella");
        Products chicken = new Products("CHICKEN", "CHK0001", "Breast fillet");

        // Display all products
        System.out.println("--- All Products ---\n");
        beef.displayInfo();
        seafood.displayInfo();
        bread.displayInfo();
        fruit.displayInfo();
        vegetable.displayInfo();
        milk.displayInfo();
        frozen.displayInfo();
        cheese.displayInfo();
        chicken.displayInfo();

        // Group by location
        Products[] allProducts = { beef, seafood, bread, cereal, noodles, snacks,
                canned, condiments, eggs, softdrink, juice,
                alcohol, fruit, vegetable, milk, frozen,
                cheese, chicken };

        System.out.println("\n=== Products by Location ===\n");

        System.out.println("CHILLED COUNTER:");
        for (Products p : allProducts) {
            if (p.getDisplayLocation().equals("Chilled counter")) {
                System.out.println("  - " + p.getName() + " (" + p.getSerialNumber() + ")");
            }
        }

        System.out.println("\nREFRIGERATOR:");
        for (Products p : allProducts) {
            if (p.getDisplayLocation().equals("Refrigerator")) {
                System.out.println("  - " + p.getName() + " (" + p.getSerialNumber() + ")");
            }
        }

        System.out.println("\nTABLE:");
        for (Products p : allProducts) {
            if (p.getDisplayLocation().equals("Table")) {
                System.out.println("  - " + p.getName() + " (" + p.getSerialNumber() + ")");
            }
        }

        System.out.println("\nSHELF:");
        for (Products p : allProducts) {
            if (p.getDisplayLocation().equals("Shelf")) {
                System.out.println("  - " + p.getName() + " (" + p.getSerialNumber() + ")");
            }
        }
    }
}
