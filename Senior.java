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
 * Represents a senior citizen shopper (60+ years old).
 * Entitled to 20% discount on food items and 10% discount on beverages (except alcohol).
 * 
 * @author Gabriel
 * @version 1.0
 */
public class Senior extends Shopper {
    private static final double FOOD_DISCOUNT = 0.20;  // 20% off
    private static final double BEVERAGE_DISCOUNT = 0.10;  // 10% off
    
    /**
     * Creates a new Senior shopper
     * 
     * @param name Shopper's name
     * @param age Shopper's age (should be 60 or above)
     */
    public Senior(String name, int age) {
        super(name, age);
    }
    
    @Override
    public boolean canPurchase(Product p) {
        // Seniors can purchase everything
        return true;
    }
    
    /**
     * Gets the discounted price for this senior shopper
     * 
     * @param p Product to get price for
     * @return Discounted price (or original if not eligible)
     */
    public double getPriceFor(Product p) {
        String type = p.getProductType().toUpperCase();
        double originalPrice = p.getPrice();
        
        // No discount on alcohol
        if (type.equals("ALCOHOL")) {
            return originalPrice;
        }
        
        // 10% off beverages
        if (isBeverage(type)) {
            return originalPrice * (1 - BEVERAGE_DISCOUNT);
        }
        
        // 20% off food items
        if (isFood(type)) {
            return originalPrice * (1 - FOOD_DISCOUNT);
        }
        
        // No discount on non-consumables
        return originalPrice;
    }
    
    /**
     * Checks if a product type is a beverage
     * 
     * @param type Product type string
     * @return true if beverage, false otherwise
     */
    private boolean isBeverage(String type) {
        return type.equals("MILK") ||
               type.equals("SOFT DRINK") ||
               type.equals("JUICE");
    }
    
    /**
     * Checks if a product type is food
     * 
     * @param type Product type string
     * @return true if food, false otherwise
     */
    private boolean isFood(String type) {
        return type.equals("FRUIT") ||
               type.equals("VEGETABLE") ||
               type.equals("CHICKEN") ||
               type.equals("BEEF") ||
               type.equals("SEAFOOD") ||
               type.equals("FROZEN FOOD") ||
               type.equals("CHEESE") ||
               type.equals("BREAD") ||
               type.equals("CEREAL") ||
               type.equals("NOODLES") ||
               type.equals("SNACKS") ||
               type.equals("CANNED GOODS") ||
               type.equals("CONDIMENTS") ||
               type.equals("EGGS");
    }
}
