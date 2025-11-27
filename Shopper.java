/**
 * Represents a shopper in the supermarket simulation.
 * Handles movement, inventory management, discounts, and purchasing logic.
 * 
 * @author Gabriel
 * @version 1.0.6
 * @see Senior
 * @see Product
 * @see Equipment
 */
package Base;
import java.util.ArrayList;
public class Shopper {
    private String name;
    private int age;
    private double money;
    private int x;
    private int y;
    private Direction facingDirection;
    private Equipment equipment;
    private ArrayList<Product> handCarried;
    private boolean checkedOut = false;
    private boolean exited = false;

    /**
     * Direction enum for shopper orientation.
     */
    public enum Direction { NORTH, SOUTH, WEST, EAST }

    /**
     * Constructs a new shopper with specified name and age.
     * Initializes money based on age group.
     * 
     * @param name the shopper's name
     * @param age the shopper's age
     */
    public Shopper(String name, int age) {
        this.name = name;
        this.age = age;
        this.handCarried = new ArrayList<>();
        this.facingDirection = Direction.NORTH;
        
        if (age < 18) {
            this.money = 300.0;
        } else if (age >= 60) {
            this.money = 700.0;
        } else {
            this.money = 500.0;
        }
    }
    
    /**
     * Applies discount to a product based on shopper type.
     * Only seniors get discounts on eligible items.
     * 
     * @param p product to apply discount to
     * @return final price after discount
     */
    public double applyDiscount(Product p) {
        if (age >= 60) {
            String type = p.getProductType().toUpperCase();
            double originalPrice = p.getPrice();

            if (type.equals("ALCOHOL")) {
                return originalPrice;
            }

            if (p.getConsumableType() == Product.ConsumableType.BEVERAGE) {
                return originalPrice * 0.90;
            }

            if (p.getConsumableType() == Product.ConsumableType.CONSUMABLE) {
                return originalPrice * 0.80;
            }
        }

        return p.getPrice();
    }
    
    /**
     * Gets the price this shopper pays for a product with discounts applied.
     * 
     * @param p product to get price for
     * @return price after any applicable discounts
     */
    public double getPriceFor(Product p) {
        return applyDiscount(p);
    }

    /**
     * Displays the shopper's current inventory including hand-carried items,
     * equipment contents, and total value.
     */
    public void displayInventory() {
        System.out.println("\n╔════ CURRENT INVENTORY ════╗");
        System.out.println("║ Shopper: " + name);
        System.out.printf("║ Balance: ₱%.2f\n", money);
        
        double total = 0.0;

        System.out.println("╠══ Hand Carried (" + handCarried.size() + "/2) ══");
        if (handCarried.isEmpty()) {
            System.out.println("║   (Empty)");
        } else {
            for (Product p : handCarried) {
                double price = getPriceFor(p);
                System.out.printf("║ - %-15s ₱%.2f\n", p.getName(), price);
                total += price;
            }
        }

        if (equipment != null) {
            String type = equipment.getClass().getSimpleName();
            System.out.println("╠══ " + type + " (" + 
                               equipment.getContents().size() + "/" + 
                               equipment.getCapacity() + ") ══");
            
            if (equipment.getContents().isEmpty()) {
                System.out.println("║   (Empty)");
            } else {
                for (Product p : equipment.getContents()) {
                    double price = getPriceFor(p);
                    System.out.printf("║ - %-15s ₱%.2f\n", p.getName(), price);
                    total += price;
                }
            }
        } else {
            System.out.println("╠══ Equipment: None");
        }

        System.out.println("╠═══════════════════════════");
        System.out.printf("║ TOTAL VALUE:      ₱%.2f\n", total);
        System.out.println("╚═══════════════════════════╝");
    }

    // Movement and position methods
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
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

    public void setFacingDirection(Direction dir) { this.facingDirection = dir; }
    public Direction getFacingDirection() { return facingDirection; }

    // Inventory management methods
    public boolean addProduct(Product p) {
        if (equipment != null) {
            if (equipment.addProduct(p)) 
                return true;
            else 
                return false;
        }
        
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
    
    public void clearInventory() {
        if (equipment != null) equipment.clear();
        handCarried.clear();
    }

    /**
     * Checks if the shopper can purchase a product based on age restrictions.
     * 
     * @param p the product to check
     * @return true if purchase is allowed, false otherwise
     */
    public boolean canPurchase(Product p) {
        if (age < 18) {
            String type = p.getProductType().toUpperCase();
            if (type.equals("ALCOHOL") || type.equals("CLEANING_AGENTS")) {
                return false;
            }
        }
        return true;
    }

    // Getters and setters
    public String getName() { return name; }
    public int getAge() { return age; }
    public boolean hasCheckedOut() { return checkedOut; }
    public void setCheckedOut(boolean b) { this.checkedOut = b; }
    public boolean hasExited() { return exited; }
    public void setExited(boolean b) { this.exited = b; }
    
    // Money management methods
    public double getMoney() { return money; }
    public void setMoney(double money) { this.money = money; }
    
    /**
     * Adds money to the shopper's balance.
     * 
     * @param amount amount to add (must be positive)
     */
    public void addMoney(double amount) {
        if (amount > 0) {
            this.money += amount;
        }
    }
    
    /**
     * Deducts money from the shopper's balance.
     * 
     * @param amount amount to deduct
     * @return true if successful, false if insufficient funds
     */
    public boolean deductMoney(double amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        return false;
    }
    
    /**
     * Checks if shopper has enough money for a purchase.
     * 
     * @param amount amount to check
     * @return true if shopper has enough money
     */
    public boolean hasEnoughMoney(double amount) {
        return money >= amount;
    }
}