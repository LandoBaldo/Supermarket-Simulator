package Base.Products;

import Base.Consumable;

public class Cereal extends Consumable {
    public Cereal(String serialNumber, String name, double price) {
         super("CEREAL", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Cereal(String serialNumber, String name) {
         super("CEREAL", serialNumber, name);
    }
}