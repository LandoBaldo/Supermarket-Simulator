/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Utility class for displaying shopper inventory and equipment information.
 * Provides both detailed inventory display and compact status information.
 * 
 * @author unknown
 * @version 1.0.6
 */
import java.util.ArrayList;

public class InventoryDisplay {
    
    /**
     * Displays the shopper's equipped item and inventory in a formatted console output.
     * Shows equipment type, capacity, weight, and all products with total cost.
     *
     * @param shopper the shopper whose inventory to display
     */
    public static void displayInventory(Shopper shopper) {
        System.out.println("\n========== INVENTORY ==========");
        
        // Display equipped item (Cart or Basket)
        Equipment equip = shopper.getEquipment();
        if (equip != null) {
            String equipType = equip instanceof Cart ? "Cart" : "Basket";
            System.out.println("Equipped: [" + equipType + "]");
            System.out.println("Capacity: " + equip.getCurrentLoad() + "/" + equip.getCapacity());
            System.out.println("Weight: " + String.format("%.2f", equip.getTotalWeight()) + " kg");
        } else {
            System.out.println("Equipped: [None]");
            System.out.println("(Visit Cart Station 'K' or Basket Station 'B')");
        }
        
        System.out.println("-------------------------------");
        
        // Display products in inventory
        ArrayList<Product> products = shopper.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No items in inventory.");
        } else {
            System.out.println("Items (" + products.size() + "):");
            double total = 0.0;
            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                System.out.println("  " + (i + 1) + ". " + p.getName() + 
                                   " - ₱" + String.format("%.2f", p.getPrice()));
                total += p.getPrice();
            }
            System.out.println("-------------------------------");
            System.out.println("Total: ₱" + String.format("%.2f", total));
        }
        
        System.out.println("===============================\n");
    }
    
    /**
     * Displays just the equipment status (for HUD display).
     * Provides a compact string representation of equipment information.
     *
     * @param shopper the shopper whose equipment status to get
     * @return formatted string containing equipment status information
     */
    public static String getEquipmentStatus(Shopper shopper) {
        Equipment equip = shopper.getEquipment();
        if (equip == null) {
            return "Equipment: None";
        }
        
        String type = equip instanceof Cart ? "Cart" : "Basket";
        return String.format("Equipment: %s [%d/%d items, %.1fkg]", 
                           type, 
                           equip.getCurrentLoad(), 
                           equip.getCapacity(),
                           equip.getTotalWeight());
    }
}