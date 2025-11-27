/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Abstract base class representing consumable products.
 * Extends Product class and provides consumable-specific functionality
 * including senior citizen discounts for food items.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
public abstract class Consumable extends Product {
    
    /**
     * Constructor for consumable products without explicit price.
     * Sets the consumableType to CONSUMABLE by default.
     *
     * @param productType the type of consumable product
     * @param serialNumber the unique identifier for the consumable
     * @param name the name of the consumable
     */
    protected Consumable(String productType, String serialNumber, String name) {
        super(productType, serialNumber, name);
        this.consumableType = ConsumableType.CONSUMABLE;
    }

    /**
     * Constructor for consumable products with explicit price.
     * Sets the consumableType to CONSUMABLE by default.
     *
     * @param productType the type of consumable product
     * @param serialNumber the unique identifier for the consumable
     * @param name the name of the consumable
     * @param price the price of the consumable
     */
    protected Consumable(String productType, String serialNumber, String name, double price) {
        super(productType, serialNumber, name, price, ConsumableType.CONSUMABLE);
    }

    /**
     * Check if this product qualifies for senior citizen discount.
     * Non-alcoholic consumables qualify for 20% discount for seniors.
     *
     * @param age The age of the shopper
     * @return The discount percentage (0.20 for non-alcoholic consumables, 0.0 if not applicable)
     */
    public double getSeniorDiscount(int age) {
        if (age >= 60 && !this.getProductType().equalsIgnoreCase("ALCOHOL")) {
            return 0.20; // 20% discount for consumable food items
        }
        return 0.0;
    }

    /**
     * Get the price after applying senior discount if applicable.
     *
     * @param age The age of the shopper
     * @return The discounted price
     */
    public double getDiscountedPrice(int age) {
        double discount = getSeniorDiscount(age);
        return getPrice() * (1 - discount);
    }

    /**
     * Returns a string representation of the consumable product.
     *
     * @return formatted string containing consumable information
     */
    @Override
    public String toString() {
        return String.format("[CONSUMABLE] %s - %s (SN: %s, Location: %s, $%.2f)",
                getProductType(), getName(), getSerialNumber(), getDisplayLocation(), getPrice());
    }
}