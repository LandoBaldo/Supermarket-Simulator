package Base.Products;

import Base.NonConsumable;

/**
 * Represents home essential products that are non-consumable.
 * Extends the NonConsumable base class with product type "HOME_ESSENTIALS".
 * 
 *
 * @version 1.0.6
 */
public class HomeEssentials extends NonConsumable {
    /**
     * Constructs a HomeEssentials product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the home essentials product
     * @param name the name of the home essentials product
     * @param price the price of the home essentials product
     */
    public HomeEssentials(String serialNumber, String name, double price) {
         super("HOME_ESSENTIALS", serialNumber, name, price);
    }
    
    /**
     * Constructs a HomeEssentials product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the home essentials product
     * @param name the name of the home essentials product
     */
    public HomeEssentials(String serialNumber, String name) {
         super("HOME_ESSENTIALS", serialNumber, name);
    }
}