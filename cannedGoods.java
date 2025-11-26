package Base.Products;

import Base.Consumable;
public class cannedGoods extends Consumable {
    public cannedGoods(String serialNumber, String name, double price) {
         super("CANNEDGOODS", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public cannedGoods(String serialNumber, String name) {
         super("CANNEDGOODS", serialNumber, name);
    }
    
}
