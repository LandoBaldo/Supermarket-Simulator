/**
 * Abstract base class for storage units in the supermarket.
 * Handles product storage with multiple tiers and capacity management.
 * 
 * @author Gabriel
 * @version 1.0.6
 * @see Shelf
 * @see Table
 * @see Refrigerator
 */
package Base;
import java.util.ArrayList;
public abstract class StorageUnit {
    protected int numTiers;
    private String address;
    protected int capacityPerTier;
    protected ArrayList<ArrayList<Product>> tiers;
    protected int x;
    protected int y;

    /**
     * Constructs a new storage unit with specified tiers and capacity.
     * 
     * @param numTiers number of tiers in the storage unit
     * @param capacityPerTier maximum products per tier
     */
    public StorageUnit(int numTiers, int capacityPerTier) {
        this.numTiers = numTiers;
        this.capacityPerTier = capacityPerTier;
        this.tiers = new ArrayList<>();
        
        for (int i = 0; i < numTiers; i++) {
            tiers.add(new ArrayList<>());
        }
    }

    /**
     * Automatically adds product to first available spot.
     * 
     * @param p product to add
     * @return true if successful, false if full
     */
    public boolean addProduct(Product p) {
        for (ArrayList<Product> tier : tiers) {
            if (tier.size() < capacityPerTier) {
                tier.add(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a product from the storage unit.
     * 
     * @param p product to remove
     * @return true if successful, false if product not found
     */
    public boolean removeProduct(Product p) {
        for (ArrayList<Product> tier : tiers) {
            if (tier.contains(p)) {
                tier.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Gets all products from all tiers.
     * 
     * @return list of all products in the storage unit
     */
    public ArrayList<Product> getProducts() {
        ArrayList<Product> all = new ArrayList<>();
        for (ArrayList<Product> tier : tiers) all.addAll(tier);
        return all;
    }
    
    public void setAddress(String address) { this.address = address; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
    public String getAddress() { return address; }
}