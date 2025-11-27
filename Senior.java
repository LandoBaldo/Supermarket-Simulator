/**
 * Represents a senior citizen shopper (60+ years old).
 * Entitled to discounts on eligible items.
 * 
 * @author Gabriel
 * @version 1.0.6
 * @see Shopper
 */
package Base;
public class Senior extends Shopper {
    private static final double FOOD_DISCOUNT = 0.20;
    private static final double BEVERAGE_DISCOUNT = 0.10;
    
    /**
     * Creates a new Senior shopper.
     * 
     * @param name shopper's name
     * @param age shopper's age (should be 60 or above)
     */
    public Senior(String name, int age) {
        super(name, age);
    }
    
    /**
     * Seniors can purchase all products without restrictions.
     * 
     * @param p product to check
     * @return always true for seniors
     */
    @Override
    public boolean canPurchase(Product p) {
        return true;
    }
    
    /**
     * Gets the discounted price for this senior shopper.
     * 
     * @param p product to get price for
     * @return discounted price (or original if not eligible)
     */
    public double getPriceFor(Product p) {
        String type = p.getProductType().toUpperCase();
        double originalPrice = p.getPrice();
        
        if (type.equals("ALCOHOL")) {
            return originalPrice;
        }
        
        if (isBeverage(type)) {
            return originalPrice * (1 - BEVERAGE_DISCOUNT);
        }
        
        if (isFood(type)) {
            return originalPrice * (1 - FOOD_DISCOUNT);
        }
        
        return originalPrice;
    }
    
    private boolean isBeverage(String type) {
        return type.equals("MILK") ||
               type.equals("SOFT DRINK") ||
               type.equals("JUICE");
    }
    
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
