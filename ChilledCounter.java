/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Represents a chilled counter storage unit for temperature-sensitive products.
 * Extends StorageUnit class and validates that products belong in chilled counters.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
import Base.Product;
public class ChilledCounter extends StorageUnit {
    /**
     * Constructs a ChilledCounter with 1 tier and 3 capacity.
     */
    public ChilledCounter() {
        super(1, 3); // 1 Tier, 3 Capacity
    }
    
    /**
     * Adds a product to the chilled counter with location validation.
     * Warns if the product doesn't belong in a chilled counter.
     *
     * @param p the product to add
     * @return true if product was added successfully, false otherwise
     */
    @Override
    public boolean addProduct(Product p) {
        if (!p.getDisplayLocation().equalsIgnoreCase("Chilled counter")) {
            System.out.println("Warning: " + p.getName() + " belongs in " + p.getDisplayLocation());
        }
        return super.addProduct(p);
    }
}