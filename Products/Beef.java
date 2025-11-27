package Base.Products;

import Base.Consumable;

/**
 * Represents a beef product that is consumable.
 * Extends the Consumable base class with product type "BEEF".
 * 
 * 
 * @version 1.0.6
 */
public class Beef extends Consumable {

    /**
     * Constructs a Beef product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the beef product
     * @param name the name of the beef product
     * @param price the price of the beef product
     */
    public Beef(String serialNumber, String name, double price) {
         super("BEEF", serialNumber, name, price);
    }
    
    /**
     * Constructs a Beef product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the beef product
     * @param name the name of the beef product
     */
    public Beef(String serialNumber, String name) {
         super("BEEF", serialNumber, name);
    }
}