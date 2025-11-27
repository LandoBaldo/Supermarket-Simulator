package Base.Products;

import Base.NonConsumable;

/**
 * Represents a body care product that is non-consumable.
 * Extends the NonConsumable base class with product type "BODY_CARE".
 * 
 * 
 * @version 1.0.6
 */
public class BodyCare extends NonConsumable {
    /**
     * Constructs a BodyCare product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the body care product
     * @param name the name of the body care product
     * @param price the price of the body care product
     */
    public BodyCare(String serialNumber, String name, double price) {
         super("BODY_CARE", serialNumber, name, price);
    }
    
    /**
     * Constructs a BodyCare product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the body care product
     * @param name the name of the body care product
     */
    public BodyCare(String serialNumber, String name) {
         super("BODY_CARE", serialNumber, name);
    }
}