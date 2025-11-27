/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Abstract base class representing beverage products.
 * Extends Product class and provides beverage-specific functionality
 * including senior citizen discounts for non-alcoholic beverages.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
public abstract class Beverage extends Product {

    /**
     * Constructor for beverage products without explicit price.
     * Sets the consumableType to BEVERAGE by default.
     *
     * @param productType the type of beverage product
     * @param serialNumber the unique identifier for the beverage
     * @param name the name of the beverage
     */
    protected Beverage(String productType, String serialNumber, String name) {
        super(productType, serialNumber, name);
        this.consumableType = ConsumableType.BEVERAGE;
    }

    /**
     * Constructor for beverage products with explicit price.
     * Sets the consumableType to BEVERAGE by default.
     *
     * @param productType the type of beverage product
     * @param serialNumber the unique identifier for the beverage
     * @param name the name of the beverage
     * @param price the price of the beverage
     */
    protected Beverage(String productType, String serialNumber, String name, double price) {
        super(productType, serialNumber, name, price, ConsumableType.BEVERAGE);
    }
    
    /**
     * Check if this product qualifies for senior citizen discount.
     * Non-alcoholic beverages qualify for 10% discount for seniors.
     *
     * @param age The age of the shopper
     * @return The discount percentage (0.10 for non-alcoholic beverages, 0.0 if not applicable)
     */
    public double getSeniorDiscount(int age) {
        if (age >= 60 && !this.getProductType().equalsIgnoreCase("ALCOHOL")) {
            return 0.10; // 10% discount for non-alcoholic beverage items
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
     * Returns a string representation of the beverage product.
     *
     * @return formatted string containing beverage information
     */
    @Override
    public String toString() {
        return String.format("[CONSUMABLE] %s - %s (SN: %s, Location: %s, $%.2f)",
                getProductType(), getName(), getSerialNumber(), getDisplayLocation(), getPrice());
    }
}