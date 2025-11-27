package Base.Products;

import Base.Consumable;

/**
 * Represents a cereal product that is consumable.
 * Extends the Consumable base class with product type "CEREAL".
 * 
 * 
 * @version 1.0.6
 */
public class Cereal extends Consumable {
    /**
     * Constructs a Cereal product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the cereal product
     * @param name the name of the cereal product
     * @param price the price of the cereal product
     */
    public Cereal(String serialNumber, String name, double price) {
         super("CEREAL", serialNumber, name, price);
    }
    
    /**
     * Constructs a Cereal product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the cereal product
     * @param name the name of the cereal product
     */
    public Cereal(String serialNumber, String name) {
         super("CEREAL", serialNumber, name);
    }
}