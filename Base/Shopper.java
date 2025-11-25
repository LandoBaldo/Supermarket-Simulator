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

public class Shopper {
    private String name;
    private int age;
    
    // Position
    private int x;
    private int y;
    
    // Direction Enum (Used for Vision)
    public enum Direction { NORTH, SOUTH, WEST, EAST }
    private Direction facingDirection;

    // Inventory
    private Equipment equipment;
    private ArrayList<Product> handCarried;
    
    // Status Flags
    private boolean checkedOut = false;
    private boolean exited = false;

    public Shopper(String name, int age) {
        this.name = name;
        this.age = age;
        this.handCarried = new ArrayList<>();
        this.facingDirection = Direction.NORTH; // Default facing
    }

    // --- Movement & Vision ---
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // Helper setters for MovementController
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

    public void setFacingDirection(Direction dir) { this.facingDirection = dir; }
    public Direction getFacingDirection() { return facingDirection; }

    // --- Inventory Logic ---
    
    // The "Brain" of adding items: Tries Equipment first, then Hands
    public boolean addProduct(Product p) {
        // 1. Try Equipment
        if (equipment != null) {
            if (equipment.addProduct(p)) 
                return true;
            else 
                return false; // Equipment full
        }
        
        // 2. Try Hands (Max 2 items) [cite: 81]
        if (handCarried.size() < 2) {
            handCarried.add(p);
            return true;
        }
        
        System.out.println("Hands are full! Get a cart or basket.");
        return false;
    }

    public void setEquipment(Equipment e) { 
        this.equipment = e; 
    }
    public Equipment getEquipment() { 
        return equipment; 
    }
    
    public void removeEquipment() { 
        this.equipment = null; 
    }
    
    public ArrayList<Product> getHandCarried() { 
        return handCarried; 
    }
    
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> all = new ArrayList<>(handCarried);
        if (equipment != null) {
            all.addAll(equipment.getContents());
        }
        return all;
    }
    
    // Clears everything (Used after Checkout)
    public void clearInventory() {
        if (equipment != null) equipment.clear();
        handCarried.clear();
    }

    // --- Status & Rules ---
    
    public boolean canPurchase(Product p) {
        if (age < 18) {
            String type = p.getProductType().toUpperCase();
            // Restrictions for minors [cite: 29]
            if (type.equals("ALCOHOL") || type.equals("CLEANING") || type.equals("CLEANING AGENT")) {
                return false;
            }
        }
        return true;
    }

    public String getName() { 
        return name; 
    }
    public int getAge() {
        return age; 
    }
   
    
    public boolean hasCheckedOut() { 
        return checkedOut; 
    }
    
    public void setCheckedOut(boolean b) { 
        this.checkedOut = b; 
    }
    
    public boolean hasExited() { 
        return exited; 
    }
    
    public void setExited(boolean b) { 
        this.exited = b; 
    }
}
