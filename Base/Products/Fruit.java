package Base.Products;

import Base.Consumable;

public class Fruit extends Consumable {
    public Fruit(String serialNumber, String name, double price) {
         super("FRUIT", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Fruit(String serialNumber, String name) {
         super("FRUIT", serialNumber, name);
    }
}
