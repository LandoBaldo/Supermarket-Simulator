package Base.Products;

import Base.Consumable;
public class Bread extends Consumable {
    public Bread(String serialNumber, String name, double price) {
         super("BREAD", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Bread(String serialNumber, String name) {
         super("BREAD", serialNumber, name);
    }
}
