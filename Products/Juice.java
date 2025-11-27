package Base.Products;

import Base.Beverage;

/**
 * Represents a juice beverage product.
 * Extends the Beverage base class with product type "JUICE".
 * 
 * 
 * @version 1.0.6
 */
public class Juice extends Beverage {
    /**
     * Constructs a Juice product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the juice product
     * @param name the name of the juice product
     * @param price the price of the juice product
     */
    public Juice(String serialNumber, String name, double price) {
         super("JUICE", serialNumber, name, price);
    }
    
    /**
     * Constructs a Juice product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the juice product
     * @param name the name of the juice product
     */
    public Juice(String serialNumber, String name) {
         super("JUICE", serialNumber, name);
    }
}