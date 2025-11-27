package Base.Products;

import Base.Consumable;

/**
 * Represents an egg product that is consumable.
 * Extends the Consumable base class with product type "EGG".
 * 
 * @version 1.0.6
 */
public class Egg extends Consumable {
    /**
     * Constructs an Egg product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the egg product
     * @param name the name of the egg product
     * @param price the price of the egg product
     */
    public Egg(String serialNumber, String name, double price) {
         super("EGG", serialNumber, name, price);
    }
    
    /**
     * Constructs an Egg product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the egg product
     * @param name the name of the egg product
     */
    public Egg(String serialNumber, String name) {
         super("EGG", serialNumber, name);
    }
}