/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */
public class Refrigerator extends StorageUnit {
    public Refrigerator() {
        super(3, 3); // 3 Tiers, 3 Capacity
    }
    
    // Validation check for products
    @Override
    public boolean addProduct(Product p) {
        if (!p.getDisplayLocation().equalsIgnoreCase("Refrigerator")) {
            System.out.println("Warning: " + p.getName() + " belongs in " + p.getDisplayLocation());
        }
        return super.addProduct(p);
    }
}