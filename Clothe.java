package Base.Products;

import Base.NonConsumable;
public class Clothe extends NonConsumable {
    public Clothe(String serialNumber, String name, double price) {
         super("CLOTHE", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public Clothe(String serialNumber, String name) {
         super("CLOTHE", serialNumber, name);
    }
    
}
