package Base.Products;

import Base.NonConsumable;

/**
 * Represents clothing products that are non-consumable.
 * Extends the NonConsumable base class with product type "CLOTHE".
 * 
 * 
 * @version 1.0.6
 */
public class Clothe extends NonConsumable {
    /**
     * Constructs a Clothe product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the clothing product
     * @param name the name of the clothing product
     * @param price the price of the clothing product
     */
    public Clothe(String serialNumber, String name, double price) {
         super("CLOTHE", serialNumber, name, price);
    }
    
    /**
     * Constructs a Clothe product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the clothing product
     * @param name the name of the clothing product
     */
    public Clothe(String serialNumber, String name) {
         super("CLOTHE", serialNumber, name);
    }
}