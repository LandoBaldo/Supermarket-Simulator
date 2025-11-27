/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Represents a minor shopper (under 18 years old).
 * Cannot purchase Alcohol or Cleaning Agent products.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
public class Minor extends Shopper {
    /**
     * Creates a new Minor shopper.
     *
     * @param name Shopper's name
     * @param age Shopper's age (should be under 18)
     */
    public Minor(String name, int age) {
        super(name, age);
    }
    
    /**
     * Checks if the minor can purchase a product.
     * Minors cannot purchase alcohol or cleaning agents.
     *
     * @param p the product to check
     * @return true if the product can be purchased, false if restricted
     */
    @Override
    public boolean canPurchase(Product p) {
        String type = p.getProductType().toUpperCase();
        
        // Minors cannot purchase alcohol or cleaning agents
        if (type.equals("ALCOHOL") || type.equals("CLEANING_AGENTS")) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets the price for this shopper (no discount for minors).
     *
     * @param p Product to get price for
     * @return Original product price without any discount
     */
    public double getPriceFor(Product p) {
        return p.getPrice();
    }
}