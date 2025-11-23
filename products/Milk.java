package products;
import Base.Beverage;

public class Milk extends Beverage {
    public Milk(String serialNumber, String name, double price) {
         super("MILK", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Milk(String serialNumber, String name) {
         super("MILK", serialNumber, name);
    }
}
