package Base.Products;

import Base.NonConsumable;
public class Stationery extends NonConsumable {
    public Stationery(String serialNumber, String name, double price) {
         super("STATIONERY", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Stationery(String serialNumber, String name) {
         super("STATIONERY", serialNumber, name);
    }
    
}
