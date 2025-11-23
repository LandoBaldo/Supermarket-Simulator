package products;
import Base.Consumable;

public class Vegetable extends Consumable {
    public Vegetable(String serialNumber, String name, double price) {
         super("VEGETABLE", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Vegetable(String serialNumber, String name) {
         super("VEGETABLE", serialNumber, name);
    }    
}
