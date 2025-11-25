package Base.Products;

import Base.Consumable;
public class Snacks extends Consumable {
    public Snacks(String serialNumber, String name, double price) {
         super("SNACKS", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Snacks(String serialNumber, String name) {
         super("SNACKS", serialNumber, name);
    }
    
}
