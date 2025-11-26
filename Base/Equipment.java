/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */
import java.util.ArrayList;

public abstract class Equipment {
    protected int capacity;
    protected ArrayList<Product> contents;
    
    /**
     * Creates new Equipment with specified capacity
     * 
     * @param capacity Maximum number of products this equipment can hold
     */
    public Equipment(int capacity) {
        this.capacity = capacity;
        this.contents = new ArrayList<>();
    }
    
    /**
     * Gets the maximum capacity of this equipment
     * 
     * @return Maximum number of products
     */
    public int getCapacity() {
        return capacity;
    }
    
    /**
     * Gets the current number of products in this equipment
     * 
     * @return Current number of products
     */
    public int getCurrentLoad() {
        return contents.size();
    }
    
    /**
     * Calculates total weight of all products in equipment.
     * For simplicity, each product is assumed to weigh 0.5 kg.
     * 
     * @return Total weight in kilograms
     */
    public double getTotalWeight() {
        // Simple weight calculation: 0.5 kg per product
        return contents.size() * 0.5;
    }
    
    /**
     * Adds a product to this equipment
     * 
     * @param p Product to add
     * @return true if product was added, false if equipment is full
     */
    public boolean addProduct(Product p) {
        if (contents.size() < capacity) {
            contents.add(p);
            return true;
        }
        System.out.println("Equipment is full! (Max " + capacity + ")");
        return false;
    }
    
    /**
     * Removes a product from this equipment
     * 
     * @param p Product to remove
     */
    public void removeProduct(Product p) {
        contents.remove(p);
    }
    
    /**
     * Clears all products from this equipment
     */
    public void clear() {
        contents.clear();
    }
    
    /**
     * Gets all products currently in this equipment
     * 
     * @return ArrayList of products
     */
    public ArrayList<Product> getContents() {
        return contents;
    }
    
    /**
     * Checks if this equipment is empty
     * 
     * @return true if no products are in equipment, false otherwise
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }
}