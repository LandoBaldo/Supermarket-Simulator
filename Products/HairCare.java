package Base.Products;

import Base.NonConsumable;

/**
 * Represents hair care products that are non-consumable.
 * Extends the NonConsumable base class with product type "HAIR_CARE".
 * 
 * 
 * @version 1.0.6
 */
public class HairCare extends NonConsumable {
    /**
     * Constructs a HairCare product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the hair care product
     * @param name the name of the hair care product
     * @param price the price of the hair care product
     */
    public HairCare(String serialNumber, String name, double price) {
         super("HAIR_CARE", serialNumber, name, price);
    }
    
    /**
     * Constructs a HairCare product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the hair care product
     * @param name the name of the hair care product
     */
    public HairCare(String serialNumber, String name) {
         super("HAIR_CARE", serialNumber, name);
    }
}