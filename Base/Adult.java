/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */
/**
 * Represents an adult shopper (18-59 years old).
 * Has no purchase restrictions or special discounts.
 * 
 * @author Gabriel
 * @version 1.0
 */
public class Adult extends Shopper {
    /**
     * Creates a new Adult shopper
     * 
     * @param name Shopper's name
     * @param age Shopper's age (should be 18-59)
     */
    public Adult(String name, int age) {
        super(name, age);
    }
    
    @Override
    public boolean canPurchase(Product p) {
        // Adults can purchase everything
        return true;
    }
    
    /**
     * Gets the price for this shopper (no discount)
     * 
     * @param p Product to get price for
     * @return Original product price
     */
    public double getPriceFor(Product p) {
        return p.getPrice();
    }
}
