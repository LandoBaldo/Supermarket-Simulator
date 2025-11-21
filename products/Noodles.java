package products;
import Base.Consumable;
public class Noodles extends Consumable {
    public Noodles(String serialNumber, String name, double price) {
         super("NOODLES", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Noodles(String serialNumber, String name) {
         super("NOODLES", serialNumber, name);
    }
}
