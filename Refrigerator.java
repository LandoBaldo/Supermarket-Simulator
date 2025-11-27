/**
 * Represents a refrigerator storage unit with 3 tiers and 3 capacity per tier.
 * Includes validation for appropriate products.
 * 
 * @author Gabriel
 * @version 1.0.6
 * @see StorageUnit
 */
package Base;
public class Refrigerator extends StorageUnit {
    
    /**
     * Constructs a new refrigerator storage unit.
     * Initializes with 3 tiers and 3 capacity per tier.
     */
    public Refrigerator() {
        super(3, 3);
    }
    
    /**
     * Adds a product to the refrigerator with location validation.
     * 
     * @param p product to add
     * @return true if successful, false otherwise
     */
    @Override
    public boolean addProduct(Product p) {
        if (!p.getDisplayLocation().equalsIgnoreCase("Refrigerator")) {
            System.out.println("Warning: " + p.getName() + " belongs in " + p.getDisplayLocation());
        }
        return super.addProduct(p);
    }
}