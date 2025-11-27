package Base.Products;

import Base.Consumable;

/**
 * Represents frozen food products that are consumable.
 * Extends the Consumable base class with product type "FROZEN".
 * 
 *
 * @version 1.0.6
 */
public class Frozen extends Consumable {
    /**
     * Constructs a Frozen product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the frozen product
     * @param name the name of the frozen product
     * @param price the price of the frozen product
     */
    public Frozen(String serialNumber, String name, double price) {
         super("FROZEN", serialNumber, name, price);
    }
    
    /**
     * Constructs a Frozen product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the frozen product
     * @param name the name of the frozen product
     */
    public Frozen(String serialNumber, String name) {
         super("FROZEN", serialNumber, name);
    }
}