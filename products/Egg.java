package products;
import Base.Consumable;
public class Egg extends Consumable {
    public Egg(String serialNumber, String name, double price) {
         super("EGG", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Egg(String serialNumber, String name) {
         super("EGG", serialNumber, name);
    }
    
}
