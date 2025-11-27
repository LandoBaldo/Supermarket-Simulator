package Base.Products;

import Base.Consumable;

/**
 * Represents a chicken product that is consumable.
 * Extends the Consumable base class with product type "CHICKEN".
 * 
 *
 * @version 1.0.6
 */
public class Chicken extends Consumable {
    /**
     * Constructs a Chicken product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the chicken product
     * @param name the name of the chicken product
     * @param price the price of the chicken product
     */
    public Chicken(String serialNumber, String name, double price) {
         super("CHICKEN", serialNumber, name, price);
    }
    
    /**
     * Constructs a Chicken product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the chicken product
     * @param name the name of the chicken product
     */
    public Chicken(String serialNumber, String name) {
         super("CHICKEN", serialNumber, name);
    }
}