package Base.Products;

import Base.NonConsumable;

public class BodyCare extends NonConsumable {
    public BodyCare(String serialNumber, String name, double price) {
         super("BODY_CARE", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public BodyCare(String serialNumber, String name) {
         super("BODY_CARE", serialNumber, name);
    }
}
