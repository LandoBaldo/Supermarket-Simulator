package Base;

public class Beverage extends Product {

    /**
     * Constructor for consumable products.
     * Sets the consumableType to CONSUMABLE by default.
     */
    protected Beverage(String productType, String serialNumber, String name) {
        super(productType, serialNumber, name);
        this.consumableType = ConsumableType.BEVERAGE;
    }

        protected Beverage(String productType, String serialNumber, String name, double price) {
        super(productType, serialNumber, name, price, ConsumableType.BEVERAGE);
    }
    /**
     * Check if this product qualifies for senior citizen discount.
     * @param age The age of the shopper
     * @return The discount percentage (0.20 for food, 0.0 if not applicable)
     */
    public double getSeniorDiscount(int age) {
        if (age >= 60 && !this.getProductType().equalsIgnoreCase("ALCOHOL")) {
            return 0.10; // 20% discount for consumable (eated or drank) food items
        }
        return 0.0;
    }

    /**
     * Get the price after applying senior discount if applicable.
     * @param age The age of the shopper
     * @return The discounted price
     */
    public double getDiscountedPrice(int age) {
        double discount = getSeniorDiscount(age);
        return price * (1 - discount);
    }

    @Override
    public String toString() {
        return String.format("[CONSUMABLE] %s - %s (SN: %s, Location: %s, $%.2f)",
                productType, name, serialNumber, displayLocation, price);
    }
}
