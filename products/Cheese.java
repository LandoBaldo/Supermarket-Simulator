package products;
import Base.Consumable;

public class Cheese extends Consumable {
    public Cheese(String serialNumber, String name, double price) {
         super("CHEESE", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Cheese(String serialNumber, String name) {
         super("CHEESE", serialNumber, name);
    }
}
