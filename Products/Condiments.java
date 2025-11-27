package Base.Products;

import Base.Consumable;

/**
 * Represents condiments that are consumable.
 * Extends the Consumable base class with product type "CONDIMENTS".
 * 
 * @version 1.0.6
 * 
 */
public class Condiments extends Consumable {
    /**
     * Constructs a Condiments product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the condiments product
     * @param name the name of the condiments product
     * @param price the price of the condiments product
     */
    public Condiments(String serialNumber, String name, double price) {
         super("CONDIMENTS", serialNumber, name, price);
    }
    
    /**
     * Constructs a Condiments product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the condiments product
     * @param name the name of the condiments product
     */
    public Condiments(String serialNumber, String name) {
         super("CONDIMENTS", serialNumber, name);
    }
}