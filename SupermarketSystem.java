
abstract class Product {
    protected String serialNumber;
    protected String name;
    protected String displayLocation;

    public Product(String serialNumber, String name, String displayLocation) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.displayLocation = displayLocation;
    }

    public abstract String getSerialNumberPrefix();

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getDisplayLocation() {
        return displayLocation;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (SN: %s, Location: %s)",
                getClass().getSimpleName(), name, serialNumber, displayLocation);
    }
}

class BeefProduct extends Product {
    public BeefProduct(String serialNumber, String name) {
        super(serialNumber, name, "Chilled counter");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "BEF";
    }
}

class SeafoodProduct extends Product {
    public SeafoodProduct(String serialNumber, String name) {
        super(serialNumber, name, "Chilled counter");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "SEA";
    }
}

class BreadProduct extends Product {
    public BreadProduct(String serialNumber, String name) {
        super(serialNumber, name, "Table");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "BRD";
    }
}

class CerealProduct extends Product {
    public CerealProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "CER";
    }
}

class NoodlesProduct extends Product {
    public NoodlesProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "NDL";
    }
}

class SnacksProduct extends Product {
    public SnacksProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "SNK";
    }
}

class CannedGoodsProduct extends Product {
    public CannedGoodsProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "CAN";
    }
}

class CondimentsProduct extends Product {
    public CondimentsProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "CON";
    }
}

class EggsProduct extends Product {
    public EggsProduct(String serialNumber, String name) {
        super(serialNumber, name, "Table");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "EGG";
    }
}

class SoftDrinkProduct extends Product {
    public SoftDrinkProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "SFT";
    }
}

class JuiceProduct extends Product {
    public JuiceProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "JUC";
    }
}

class AlcoholProduct extends Product {
    public AlcoholProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "ALC";
    }
}

class CleaningAgentsProduct extends Product {
    public CleaningAgentsProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "CLE";
    }
}

class HomeEssentialsProduct extends Product {
    public HomeEssentialsProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "HOM";
    }
}

class HairCareProduct extends Product {
    public HairCareProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "HAR";
    }
}

class BodyCareProduct extends Product {
    public BodyCareProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "BOD";
    }
}

class DentalCareProduct extends Product {
    public DentalCareProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "DEN";
    }
}

class ClothesProduct extends Product {
    public ClothesProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "CLO";
    }
}

class StationeryProduct extends Product {
    public StationeryProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "STN";
    }
}

class PetFoodProduct extends Product {
    public PetFoodProduct(String serialNumber, String name) {
        super(serialNumber, name, "Shelf");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "PET";
    }
}

// Special Food Products (from second table)
class FruitProduct extends Product {
    public FruitProduct(String serialNumber, String name) {
        super(serialNumber, name, "Table");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "FRU";
    }
}

class VegetableProduct extends Product {
    public VegetableProduct(String serialNumber, String name) {
        super(serialNumber, name, "Table");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "VEG";
    }
}

class MilkProduct extends Product {
    public MilkProduct(String serialNumber, String name) {
        super(serialNumber, name, "Refrigerator");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "MLK";
    }
}

class FrozenFoodProduct extends Product {
    public FrozenFoodProduct(String serialNumber, String name) {
        super(serialNumber, name, "Refrigerator");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "FRZ";
    }
}

class CheeseProduct extends Product {
    public CheeseProduct(String serialNumber, String name) {
        super(serialNumber, name, "Refrigerator");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "CHS";
    }
}

class ChickenProduct extends Product {
    public ChickenProduct(String serialNumber, String name) {
        super(serialNumber, name, "Chilled counter");
    }

    @Override
    public String getSerialNumberPrefix() {
        return "CHK";
    }
}

// Product Factory
class ProductFactory {
    public static Product createProduct(String type, String serialNumber, String name) {
        switch (type.toUpperCase()) {
            // General products
            case "BEEF":
                return new BeefProduct(serialNumber, name);
            case "SEAFOOD":
                return new SeafoodProduct(serialNumber, name);
            case "BREAD":
                return new BreadProduct(serialNumber, name);
            case "CEREAL":
                return new CerealProduct(serialNumber, name);
            case "NOODLES":
                return new NoodlesProduct(serialNumber, name);
            case "SNACKS":
                return new SnacksProduct(serialNumber, name);
            case "CANNED":
                return new CannedGoodsProduct(serialNumber, name);
            case "CONDIMENTS":
                return new CondimentsProduct(serialNumber, name);
            case "EGGS":
                return new EggsProduct(serialNumber, name);
            case "SOFTDRINK":
                return new SoftDrinkProduct(serialNumber, name);
            case "JUICE":
                return new JuiceProduct(serialNumber, name);
            case "ALCOHOL":
                return new AlcoholProduct(serialNumber, name);
            case "CLEANING":
                return new CleaningAgentsProduct(serialNumber, name);
            case "HOME":
                return new HomeEssentialsProduct(serialNumber, name);
            case "HAIRCARE":
                return new HairCareProduct(serialNumber, name);
            case "BODYCARE":
                return new BodyCareProduct(serialNumber, name);
            case "DENTAL":
                return new DentalCareProduct(serialNumber, name);
            case "CLOTHES":
                return new ClothesProduct(serialNumber, name);
            case "STATIONERY":
                return new StationeryProduct(serialNumber, name);
            case "PETFOOD":
                return new PetFoodProduct(serialNumber, name);

            // Special food products
            case "FRUIT":
                return new FruitProduct(serialNumber, name);
            case "VEGETABLE":
                return new VegetableProduct(serialNumber, name);
            case "MILK":
                return new MilkProduct(serialNumber, name);
            case "FROZEN":
                return new FrozenFoodProduct(serialNumber, name);
            case "CHEESE":
                return new CheeseProduct(serialNumber, name);
            case "CHICKEN":
                return new ChickenProduct(serialNumber, name);

            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
}

// Main demonstration class
public class SupermarketSystem {
    public static void main(String[] args) {
        // Create some sample products
        Product beef = ProductFactory.createProduct("BEEF", "BEF0001", "Rib steak");
        Product seafood = ProductFactory.createProduct("SEAFOOD", "SEA0001", "Tilapia");
        Product bread = ProductFactory.createProduct("BREAD", "BRD0001", "Baguette");
        Product fruit = ProductFactory.createProduct("FRUIT", "FRU0001", "Apples");
        Product milk = ProductFactory.createProduct("MILK", "MLK0001", "Fresh milk");
        Product frozen = ProductFactory.createProduct("FROZEN", "FRZ0001", "Chicken nuggets");
        Product cheese = ProductFactory.createProduct("CHEESE", "CHS0001", "Mozzarella");
        Product chicken = ProductFactory.createProduct("CHICKEN", "CHK0001", "Breast fillet");

        // Display products
        System.out.println("=== Supermarket Products ===\n");
        System.out.println(beef);
        System.out.println(seafood);
        System.out.println(bread);
        System.out.println(fruit);
        System.out.println(milk);
        System.out.println(frozen);
        System.out.println(cheese);
        System.out.println(chicken);

        // Group by location
        System.out.println("\n=== Products by Location ===");
        Product[] products = { beef, seafood, bread, fruit, milk, frozen, cheese, chicken };

        System.out.println("\nChilled Counter:");
        for (Product p : products) {
            if (p.getDisplayLocation().equals("Chilled counter")) {
                System.out.println("  - " + p.getName());
            }
        }

        System.out.println("\nRefrigerator:");
        for (Product p : products) {
            if (p.getDisplayLocation().equals("Refrigerator")) {
                System.out.println("  - " + p.getName());
            }
        }

        System.out.println("\nTable:");
        for (Product p : products) {
            if (p.getDisplayLocation().equals("Table")) {
                System.out.println("  - " + p.getName());
            }
        }
    }
}