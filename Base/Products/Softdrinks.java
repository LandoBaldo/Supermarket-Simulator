package Base.Products;

import Base.Beverage;
public class Softdrinks extends Beverage {
    public Softdrinks(String serialNumber, String name, double price) {
         super("SOFTDRINKS", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Softdrinks(String serialNumber, String name) {
         super("SOFTDRINKS", serialNumber, name);
    }
}
