package products;
import Base.Consumable;

public class Frozen extends Consumable {
    public Frozen(String serialNumber, String name, double price) {
         super("FROZEN", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Frozen(String serialNumber, String name) {
         super("FROZEN", serialNumber, name);
    }
}
