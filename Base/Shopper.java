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
    
        /**
     * Applies discount to a product based on shopper type
     * 
     * @param p Product to apply discount to
     * @return Final price after discount
     */
    public double applyDiscount(Product p) {
        // Only seniors get discounts
        if (age >= 60) {
            String type = p.getProductType().toUpperCase();
            double originalPrice = p.getPrice();

            // No discount on alcohol
            if (type.equals("ALCOHOL")) {
                return originalPrice;
            }

            // 10% off beverages
            if (p.getConsumableType() == Product.ConsumableType.BEVERAGE) {
                return originalPrice * 0.90; // 10% off
            }

            // 20% off consumables (food)
            if (p.getConsumableType() == Product.ConsumableType.CONSUMABLE) {
                return originalPrice * 0.80; // 20% off
            }
        }

        // No discount for adults and minors
        return p.getPrice();
    }

    public void displayInventory() {
        System.out.println("\n╔════ CURRENT INVENTORY ════╗");
        System.out.println("║ Shopper: " + name);
        
        double total = 0.0;

        // 1. Show Hand-Carried Items
        System.out.println("╠══ Hand Carried (" + handCarried.size() + "/2) ══");
        if (handCarried.isEmpty()) {
            System.out.println("║   (Empty)");
        } else {
            for (Product p : handCarried) {
                System.out.printf("║ - %-15s $%.2f\n", p.getName(), p.getPrice());
                total += p.getPrice();
            }
        }

        // 2. Show Equipment Items
        if (equipment != null) {
            // Uses Java reflection to get simple name "Cart" or "Basket"
            String type = equipment.getClass().getSimpleName(); 
            System.out.println("╠══ " + type + " (" + 
                               equipment.getContents().size() + "/" + 
                               equipment.getCapacity() + ") ══");
            
            if (equipment.getContents().isEmpty()) {
                System.out.println("║   (Empty)");
            } else {
                for (Product p : equipment.getContents()) {
                    System.out.printf("║ - %-15s $%.2f\n", p.getName(), p.getPrice());
                    total += p.getPrice();
                }
            }
        } else {
            System.out.println("╠══ Equipment: None");
        }

        // 3. Total
        System.out.println("╠═══════════════════════════");
        System.out.printf("║ TOTAL VALUE:      $%.2f\n", total);
        System.out.println("╚═══════════════════════════╝");
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
