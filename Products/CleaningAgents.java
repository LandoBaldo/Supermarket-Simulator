package Base.Products;

import Base.NonConsumable;

/**
 * Represents cleaning agents that are non-consumable.
 * Extends the NonConsumable base class with product type "CLEANING_AGENTS".
 * 
 * 
 * @version 1.0.6
 */
public class CleaningAgents extends NonConsumable {
    /**
     * Constructs a CleaningAgents product with specified serial number, name, and price.
     *
     * @param serialNumber the unique identifier for the cleaning agent product
     * @param name the name of the cleaning agent product
     * @param price the price of the cleaning agent product
     */
    public CleaningAgents(String serialNumber, String name, double price) {
         super("CLEANING_AGENTS", serialNumber, name, price);
    }
    
    /**
     * Constructs a CleaningAgents product with specified serial number and name.
     * The price will be set to a default value.
     *
     * @param serialNumber the unique identifier for the cleaning agent product
     * @param name the name of the cleaning agent product
     */
    public CleaningAgents(String serialNumber, String name) {
         super("CLEANING_AGENTS", serialNumber, name);
    }
}