package Base.Products;

import Base.NonConsumable;

/**
 * Represents dental care products that are non-consumable.
 * Extends the NonConsumable base class with product type "DENTAL_CARE".
 * 
 * 
 * @version 1.0.6
 */
public class DentalCare extends NonConsumable {
    /**
     * Constructs a DentalCare product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the dental care product
     * @param name the name of the dental care product
     * @param price the price of the dental care product
     */
    public DentalCare(String serialNumber, String name, double price) {
         super("DENTAL_CARE", serialNumber, name, price);
    }
    
    /**
     * Constructs a DentalCare product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the dental care product
     * @param name the name of the dental care product
     */
    public DentalCare(String serialNumber, String name) {
         super("DENTAL_CARE", serialNumber, name);
    }
}