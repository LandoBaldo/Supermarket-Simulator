package Base.Products;

import Base.Consumable;

public class Chicken extends Consumable {
    public Chicken(String serialNumber, String name, double price) {
         super("CHICKEN", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Chicken(String serialNumber, String name) {
         super("CHICKEN", serialNumber, name);
    }
}
