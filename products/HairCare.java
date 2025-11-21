package products;
import Base.NonConsumable;
public class HairCare extends NonConsumable {
    public HairCare(String serialNumber, String name, double price) {
         super("HAIR_CARE", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public HairCare(String serialNumber, String name) {
         super("HAIR_CARE", serialNumber, name);
    }
}
