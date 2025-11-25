/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Gabriel
 */
import Base.Product;
public class ChilledCounter extends StorageUnit {
    public ChilledCounter() {
        super(1, 3); // 1 Tier, 3 Capacity
    }
    
    @Override
    public boolean addProduct(Product p) {
        if (!p.getDisplayLocation().equalsIgnoreCase("Chilled counter")) {
            System.out.println("Warning: " + p.getName() + " belongs in " + p.getDisplayLocation());
        }
        return super.addProduct(p);
    }
}
