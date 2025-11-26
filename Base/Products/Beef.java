package Base.Products;

import Base.Consumable;

public class Beef extends Consumable {

    public Beef(String serialNumber, String name, double price) {
         super("BEEF", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Beef(String serialNumber, String name) {
         super("BEEF", serialNumber, name);
    }
}
