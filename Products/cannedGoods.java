package Base.Products;

import Base.Consumable;

/**
 * Represents canned goods that are consumable.
 * Extends the Consumable base class with product type "CANNEDGOODS".
 * 
 * 
 * @version 1.0.6
 */
public class cannedGoods extends Consumable {
    /**
     * Constructs a CannedGoods product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the canned goods product
     * @param name the name of the canned goods product
     * @param price the price of the canned goods product
     */
    public cannedGoods(String serialNumber, String name, double price) {
         super("CANNEDGOODS", serialNumber, name, price);
    }
    
    /**
     * Constructs a CannedGoods product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the canned goods product
     * @param name the name of the canned goods product
     */
    public cannedGoods(String serialNumber, String name) {
         super("CANNEDGOODS", serialNumber, name);
    }
}