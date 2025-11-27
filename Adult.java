/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Represents an adult shopper (18-59 years old).
 * Has no purchase restrictions or special discounts.
 * 
 * @author unknown
 * @version 1.0.6
 */
public class Adult extends Shopper {
    /**
     * Creates a new Adult shopper.
     *
     * @param name Shopper's name
     * @param age Shopper's age (should be 18-59)
     */
    public Adult(String name, int age) {
        super(name, age);
    }
    
    /**
     * Checks if the adult can purchase a product.
     * Adults have no restrictions and can purchase everything.
     *
     * @param p the product to check
     * @return true always, as adults have no restrictions
     */
    @Override
    public boolean canPurchase(Product p) {
        // Adults can purchase everything
        return true;
    }
    
    /**
     * Gets the price for this shopper (no discount for adults).
     *
     * @param p Product to get price for
     * @return Original product price without any discount
     */
    public double getPriceFor(Product p) {
        return p.getPrice();
    }
}