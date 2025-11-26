package Base.Products;

import Base.NonConsumable;
public class HomeEssentials extends NonConsumable {
    public HomeEssentials(String serialNumber, String name, double price) {
         super("HOME_ESSENTIALS", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public HomeEssentials(String serialNumber, String name) {
         super("HOME_ESSENTIALS", serialNumber, name);
    }
    
}
