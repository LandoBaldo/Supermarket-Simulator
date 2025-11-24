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

public class Equipment {
    protected int capacity;
    protected ArrayList<Product> contents;

    public Equipment(int capacity) {
        this.capacity = capacity;
        this.contents = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    // Matches Shopper.java call: equipment.addProduct(p)
    public boolean addProduct(Product p) {
        if (contents.size() < capacity) {
            contents.add(p);
            return true;
        }
        System.out.println("Equipment is full! (Max " + capacity + ")");
        return false;
    }

    public void removeProduct(Product p) {
        contents.remove(p);
    }
    
    public void clear() {
        contents.clear();
    }

    public ArrayList<Product> getContents() {
        return contents;
    }
    
    public boolean isEmpty() {
        return contents.isEmpty();
    }
}