/**
 * Base package containing core classes for supermarket simulation system.
 * 
 * @version 1.0.6
 */
package Base;

/**
 * Represents non-consumable products in the supermarket.
 * Non-consumable items do not qualify for senior citizen discounts
 * and some may have age restrictions.
 * 
 * @author Gabriel
 * @version 1.0.6
 * @see Product
 * @see ConsumableType
 */
public abstract class NonConsumable extends Product {
    
    /**
     * Constructs a non-consumable product with default price.
     * Sets consumableType to NON_CONSUMABLE automatically.
     * 
     * @param productType the type of product
     * @param serialNumber unique identifier for the product
     * @param name display name of the product
     */
    protected NonConsumable(String productType, String serialNumber, String name) {
        super(productType, serialNumber, name);
        this.consumableType = ConsumableType.NON_CONSUMABLE;
    }

    /**
     * Constructs a non-consumable product with specified price.
     * 
     * @param productType the type of product
     * @param serialNumber unique identifier for the product
     * @param name display name of the product
     * @param price price of the product
     */
    protected NonConsumable(String productType, String serialNumber, String name, double price) {
        super(productType, serialNumber, name, price, ConsumableType.NON_CONSUMABLE);
    }

    /**
     * Non-consumable products do not qualify for senior citizen discount.
     * 
     * @param age the age of the shopper
     * @return always returns 0.0 (no discount)
     */
    public double getSeniorDiscount(int age) {
        return 0.0;
    }

    /**
     * Gets the price without any discount applied.
     * 
     * @param age the age of the shopper
     * @return the regular price without discount
     */
    public double getDiscountedPrice(int age) {
        return getPrice();
    }

    /**
     * Checks if this product is restricted for minors.
     * Cleaning agents are restricted for shoppers under 18.
     * 
     * @param age the age of the shopper
     * @return true if the product is restricted for this age, false otherwise
     */
    public boolean isRestrictedForAge(int age) {
        if (age < 18 && this.getProductType().equalsIgnoreCase("CLEANING")) {
            return true;
        }
        return false;
    }

    /**
     * Returns string representation of the non-consumable product.
     * 
     * @return formatted string containing product details
     */
    @Override
    public String toString() {
        return String.format("[NON-CONSUMABLE] %s - %s (SN: %s, Location: %s, $%.2f)",
                getProductType(), getName(), getSerialNumber(), getDisplayLocation(), getPrice());
    }
}