package products;
import Base.Consumable;
public class Seafood extends Consumable {
    public Seafood(String serialNumber, String name, double price) {
         super("SEAFOOD", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Seafood(String serialNumber, String name) {
         super("SEAFOOD", serialNumber, name);
    }
}
