package Base.Products;

import Base.Consumable;

/**
 * Represents a cheese product that is consumable.
 * Extends the Consumable base class with product type "CHEESE".
 * 
 * 
 * @version 1.0.6
 */
public class Cheese extends Consumable {
    /**
     * Constructs a Cheese product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the cheese product
     * @param name the name of the cheese product
     * @param price the price of the cheese product
     */
    public Cheese(String serialNumber, String name, double price) {
         super("CHEESE", serialNumber, name, price);
    }
    
    /**
     * Constructs a Cheese product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the cheese product
     * @param name the name of the cheese product
     */
    public Cheese(String serialNumber, String name) {
         super("CHEESE", serialNumber, name);
    }
}