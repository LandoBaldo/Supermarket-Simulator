package Base.Products;

import Base.Beverage;

/**
 * Represents an alcoholic beverage product.
 * Extends the Beverage base class with product type "ALCOHOL".
 * 
 *
 * @version 1.0.6
 */
public class Alcohol extends Beverage {
    /**
     * Constructs an Alcohol product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the alcohol product
     * @param name the name of the alcohol product
     * @param price the price of the alcohol product
     */
    public Alcohol(String serialNumber, String name, double price) {
         super("ALCOHOL", serialNumber, name, price);
    }
    
    /**
     * Constructs an Alcohol product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the alcohol product
     * @param name the name of the alcohol product
     */
    public Alcohol(String serialNumber, String name) {
         super("ALCOHOL", serialNumber, name);
    }
}