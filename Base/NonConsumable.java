/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */
public abstract class NonConsumable extends Product {
    
    /**
     * Constructor for non-consumable products.
     * Sets the consumableType to NON_CONSUMABLE by default.
     */
    protected NonConsumable(String productType, String serialNumber, String name) {
        super(productType, serialNumber, name);
        this.consumableType = ConsumableType.NON_CONSUMABLE;
    }

    /**
     * Constructor with explicit price.
     */
    protected NonConsumable(String productType, String serialNumber, String name, double price) {
        super(productType, serialNumber, name, price, ConsumableType.NON_CONSUMABLE);
    }

    /**
     * Non-consumable products do not qualify for senior citizen discount.
     * @param age The age of the shopper
     * @return Always returns 0.0 (no discount)
     */
    public double getSeniorDiscount(int age) {
        return 0.0; // No discount for non-consumable items
    }

    /**
     * Get the price (no discount for non-consumables).
     * @param age The age of the shopper
     * @return The regular price
     */
    public double getDiscountedPrice(int age) {
        return getPrice(); // No discount applied
    }

    /**
     * Check if this product is restricted for minors.
     * Cleaning agents are restricted for shoppers under 18.
     * @param age The age of the shopper
     * @return true if the product is restricted for this age, false otherwise
     */
    public boolean isRestrictedForAge(int age) {
        if (age < 18 && this.getProductType().equalsIgnoreCase("CLEANING")) {
            return true; // Cleaning agents restricted for minors
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[NON-CONSUMABLE] %s - %s (SN: %s, Location: %s, $%.2f)",
                getProductType(), getName(), getSerialNumber(), getDisplayLocation(), getPrice());
    }
}