package Base.Products;

import Base.Consumable;

/**
 * Represents fruit products that are consumable.
 * Extends the Consumable base class with product type "FRUIT".
 * 
 * 
 * @version 1.0.6
 */
public class Fruit extends Consumable {
    /**
     * Constructs a Fruit product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the fruit product
     * @param name the name of the fruit product
     * @param price the price of the fruit product
     */
    public Fruit(String serialNumber, String name, double price) {
         super("FRUIT", serialNumber, name, price);
    }
    
    /**
     * Constructs a Fruit product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the fruit product
     * @param name the name of the fruit product
     */
    public Fruit(String serialNumber, String name) {
         super("FRUIT", serialNumber, name);
    }
}