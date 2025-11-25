package Base.Products;

import Base.Consumable;
public class Condiments extends Consumable {
    public Condiments(String serialNumber, String name, double price) {
         super("CONDIMENTS", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Condiments(String serialNumber, String name) {
         super("CONDIMENTS", serialNumber, name);
    }
}
