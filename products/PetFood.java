package products;
import Base.Consumable;
public class PetFood extends Consumable {
    public PetFood(String serialNumber, String name, double price) {
         super("PET_FOOD", serialNumber, name, price);
    }
    //Constructor without the price parameter
    public PetFood(String serialNumber, String name) {
         super("PET_FOOD", serialNumber, name);
    }
    
}
