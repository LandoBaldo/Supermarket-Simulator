package Base.Products;

import Base.NonConsumable;
public class CleaningAgents extends NonConsumable {
    public CleaningAgents(String serialNumber, String name, double price) {
         super("CLEANING_AGENTS", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public CleaningAgents(String serialNumber, String name) {
         super("CLEANING_AGENTS", serialNumber, name);
    }
    
}
