package Base.Products;

import Base.Consumable;

/**
 * Represents a bread product that is consumable.
 * Extends the Consumable base class with product type "BREAD".
 * 
 * 
 * @version 1.0.6
 */
public class Bread extends Consumable {
    /**
     * Constructs a Bread product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the bread product
     * @param name the name of the bread product
     * @param price the price of the bread product
     */
    public Bread(String serialNumber, String name, double price) {
         super("BREAD", serialNumber, name, price);
    }
    
    /**
     * Constructs a Bread product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the bread product
     * @param name the name of the bread product
     */
    public Bread(String serialNumber, String name) {
         super("BREAD", serialNumber, name);
    }
}