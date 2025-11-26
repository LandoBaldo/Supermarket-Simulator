package Base.Products;

import Base.NonConsumable;
public class DentalCare extends NonConsumable {
    public DentalCare(String serialNumber, String name, double price) {
         super("DENTAL_CARE", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public DentalCare(String serialNumber, String name) {
         super("DENTAL_CARE", serialNumber, name);
    }
    
}
