/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */
import Base.Product;
import java.util.ArrayList;

public abstract class StorageUnit {
    protected int numTiers;
    private String address;
    protected int capacityPerTier;
    protected ArrayList<ArrayList<Product>> tiers;
    
    // Coordinates (Crucial for Map)
    protected int x;
    protected int y;

    public StorageUnit(int numTiers, int capacityPerTier) {
        this.numTiers = numTiers;
        this.capacityPerTier = capacityPerTier;
        this.tiers = new ArrayList<>();
        
        // Initialize tiers
        for (int i = 0; i < numTiers; i++) {
            tiers.add(new ArrayList<>());
        }
    }

    // --- Adding Products ---
    
    // Auto-add to first available spot (Used for initializing store)
    public boolean addProduct(Product p) {
        for (ArrayList<Product> tier : tiers) {
            if (tier.size() < capacityPerTier) {
                tier.add(p);
                return true;
            }
        }
        return false;
    }

    // --- Removing Products ---
    
    public boolean removeProduct(Product p) {
        for (ArrayList<Product> tier : tiers) {
            if (tier.contains(p)) {
                tier.remove(p);
                return true;
            }
        }
        return false;
    }

    // --- Getters/Setters ---
    
    public ArrayList<Product> getProducts() {
        ArrayList<Product> all = new ArrayList<>();
        for (ArrayList<Product> tier : tiers) all.addAll(tier);
        return all;
    }
    
    public void setAddress(String address) {
       this.address = address;
    }
    
    public void setX(int x) { 
        this.x = x; 
    }
    
    public void setY(int y) { 
        this.y = y; 
    }
    
    public int getX() { 
        return x; 
    }
    
    public int getY() { 
        return y; 
    }
    
    public String getAddress() {
       return address;
    }
    
    
}