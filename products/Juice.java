package products;
import Base.Beverage;
public class Juice extends Beverage {
    public Juice(String serialNumber, String name, double price) {
         super("JUICE", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Juice(String serialNumber, String name) {
         super("JUICE", serialNumber, name);
    }
}
