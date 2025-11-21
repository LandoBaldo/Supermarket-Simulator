package products;
import Base.Beverage;
public class Alcohol extends Beverage {
    public Alcohol(String serialNumber, String name, double price) {
         super("ALCOHOL", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Alcohol(String serialNumber, String name) {
         super("ALCOHOL", serialNumber, name);
    }
}
