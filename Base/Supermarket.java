/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */

import Base.Equipment;
import Base.Product;
import Base.Products.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Supermarket {
    private String name;
    private Map map;
    private int currentFloor;
    private ArrayList<Shopper> shoppers;
    
    private HashMap<String, StorageUnit> storageLocations;
    private HashMap<String, Service> serviceLocations;

    // Modified constructor - always start at ground floor (floor 1)
    public Supermarket(String name, int width, int height) {
        this.name = name;
        this.currentFloor = 1; // Start at ground floor
        this.map = new Map(width, height, 1);
        this.shoppers = new ArrayList<>();
        this.storageLocations = new HashMap<>();
        this.serviceLocations = new HashMap<>();

        syncLocations();
        initializeInventory();
    }
    
    public Map getMap() {
        return map;
    }
    
    public int getCurrentFloor() {
        return currentFloor;
    }

    private void syncLocations() {
        storageLocations.clear();
        serviceLocations.clear();
        
        for(Table t : map.getTables()) storageLocations.put(t.getX() + "," + t.getY(), t);
        for(Shelf s : map.getShelves()) storageLocations.put(s.getX() + "," + s.getY(), s);
        for(Refrigerator r : map.getRefrigerators()) storageLocations.put(r.getX() + "," + r.getY(), r);
        for(Service s : map.getServices()) serviceLocations.put(s.getX() + "," + s.getY(), s);
    }

    private void initializeInventory() {
        System.out.println("Restocking shelves for floor " + currentFloor + "...");

        try {
            if (currentFloor == 1) {
                // Ground floor items
                for (Table t : map.getTables()) {
                    if (t != null) {
                        t.addProduct(new Fruit("FRU00001", "Apple", 25.0));
                        t.addProduct(new Bread("BRD00001", "Loaf", 45.0));
                    }
                }

                for (Shelf s : map.getShelves()) {
                    if (s != null) {
                        s.addProduct(new Cereal("CER00001", "Oats", 120.0));
                        s.addProduct(new Alcohol("ALC00001", "Beer", 60.0));
                    }
                }

                for (ChilledCounter cc : map.getChilledCounters()) {
                    if (cc != null) {
                        cc.addProduct(new Chicken("CHK00001", "Chicken", 250.0));
                        cc.addProduct(new Beef("BEF00001", "Beef", 350.0));
                    }
                }
            } 
            else if (currentFloor == 2) {
                // Second floor items
                for (Table t : map.getTables()) {
                    if (t != null) {
                        t.addProduct(new Bread("BRD00002", "Wheat Bread", 50.0));
                        t.addProduct(new Egg("EGG00001", "Eggs", 120.0));
                    }
                }

                for (Shelf s : map.getShelves()) {
                    if (s != null) {
                        s.addProduct(new Cereal("CER00002", "Cornflakes", 110.0));
                        s.addProduct(new Snacks("SNK00001", "Chips", 35.0));
                    }
                }

                for (Refrigerator r : map.getRefrigerators()) {
                    if (r != null) {
                        r.addProduct(new Milk("MLK00001", "Milk", 95.0));
                        r.addProduct(new Cheese("CHS00001", "Cheese", 150.0));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error initializing inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addShopper(Shopper s, int x, int y) {
        s.setPosition(x, y);
        shoppers.add(s);
        map.setCell(x, y, "S");
    }

    public void displayMap() { 
        System.out.println("=== FLOOR " + currentFloor + " ===");
        map.printMap(); 
    }

    public void handleInteraction(Shopper shopper) {
        FrontCell front = MovementController.getCellInFront(shopper, map);
        if (front == null) { System.out.println("Nothing in front."); return; }

        String key = front.getX() + "," + front.getY();
        
        Service s = serviceLocations.get(key);
        if (s != null) { 
            processService(shopper, s); 
            return; 
        }

        StorageUnit u = storageLocations.get(key);
        if (u != null) { processStorage(shopper, u); return; }
        
        System.out.println("Cannot interact with that.");
    }

    private void processService(Shopper shopper, Service s) {
        String result = s.interact();

        // Handle floor change for stairs
        if (result.equals("FLOOR_CHANGE")) {
            changeFloor(shopper);
            return;
        }

        System.out.println(result);

        // Handle different service types
        switch (s.getType()) {
            case CART_STATION:
                if (shopper.getEquipment() == null && shopper.getHandCarried().isEmpty()) {
                    shopper.setEquipment(new Cart());
                    System.out.println("✓ You got a Cart! (Capacity: 30 items)");
                } else if (shopper.getEquipment() != null) {
                    if (shopper.getEquipment().isEmpty()) {
                        shopper.removeEquipment();
                        System.out.println("✓ You returned the cart.");
                    } else {
                        System.out.println("⚠️  Cart is not empty. Remove items first.");
                    }
                } else {
                    System.out.println("⚠️  You must be empty-handed to get equipment.");
                }
                break;

            case BASKET_STATION:
                if (shopper.getEquipment() == null && shopper.getHandCarried().isEmpty()) {
                    shopper.setEquipment(new Basket());
                    System.out.println("✓ You got a Basket! (Capacity: 15 items)");
                } else if (shopper.getEquipment() != null) {
                    if (shopper.getEquipment().isEmpty()) {
                        shopper.removeEquipment();
                        System.out.println("✓ You returned the basket.");
                    } else {
                        System.out.println("⚠️  Basket is not empty. Remove items first.");
                    }
                } else {
                    System.out.println("⚠️  You must be empty-handed to get equipment.");
                }
                break;

            case PRODUCT_SEARCH:
                handleProductSearch();
                break;

            case CHECKOUT_COUNTER:
                handleCheckout(shopper);
                break;

            case EXIT:
                handleExit(shopper);
                break;
        }
    }
    
    /**
    * Handles product search interaction
    */
    
    private void handleProductSearch() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("\n🔍 Product name to search: ");
        String query = sc.nextLine().trim();

        if (query.isEmpty()) {
            System.out.println("⚠️  No product name entered.");
            return;
        }

        java.util.ArrayList<String> found = new java.util.ArrayList<>();

        for (String key : storageLocations.keySet()) {
            StorageUnit unit = storageLocations.get(key);
            for (Product p : unit.getProducts()) {
                String pname = p.getName();
                if (pname != null && pname.toLowerCase().contains(query.toLowerCase())) {
                    found.add(key + " - " + unit.getClass().getSimpleName());
                    break;
                }
            }
        }

        if (found.isEmpty()) {
            System.out.println("❌ Product not found in the supermarket.");
        } else {
            System.out.println("\n✓ Product found at:");
            for (String addr : found) {
                System.out.println("  • " + addr);
            }
        }
    }
    
    /**
    * Handles checkout counter interaction
    */
    private void handleCheckout(Shopper shopper) {
        java.util.ArrayList<Product> allProducts = shopper.getAllProducts();

        if (allProducts.isEmpty()) {
            System.out.println("⚠️  You have no products to checkout.");
            return;
        }

        generateReceipt(shopper);
        shopper.clearInventory();
        shopper.setCheckedOut(true);

        // Remove equipment if any
        if (shopper.getEquipment() != null) {
            shopper.removeEquipment();
            System.out.println("✓ Your equipment has been collected.");
        }

        System.out.println("\n✓ Checkout complete! Thank you for shopping!");
    }

    /**
     * Handles exit interaction
     */
    private void handleExit(Shopper shopper) {
        // Check if shopper has equipment
        if (shopper.getEquipment() != null) {
            System.out.println("⚠️  You cannot leave with store equipment.");
            System.out.println("   Please return your " + 
                (shopper.getEquipment() instanceof Cart ? "cart" : "basket") + 
                " first.");
            return;
        }

        // Check if shopper has unchecked items
        if (!shopper.getAllProducts().isEmpty() && !shopper.hasCheckedOut()) {
            System.out.println("⚠️  You have unpaid items!");
            System.out.println("   Please checkout first or return the items.");
            return;
        }

        // Allow exit
        shopper.setExited(true);
        System.out.println("\n✓ You have left the supermarket. Come again!");
    }
    
    private void changeFloor(Shopper shopper) {
        // Clear shopper from current position
        map.setCell(shopper.getX(), shopper.getY(), " ");
        
        // Switch floor
        if (currentFloor == 1) {
            currentFloor = 2;
            map = new Map(22, 22, 2);
            // Position at stairs on floor 2 (row 20, column 1 or 20)
            shopper.setPosition(1, 20);
            System.out.println("You went UP to Floor 2!");
        } else if (currentFloor == 2) {
            currentFloor = 1;
            map = new Map(22, 22, 1);
            // Position at stairs on floor 1 (row 15, column 1 or 20)
            shopper.setPosition(1, 15);
            System.out.println("You went DOWN to Floor 1!");
        }
        
        // Re-sync locations and inventory for new floor
        syncLocations();
        initializeInventory();
        
        // Place shopper on new floor
        map.setCell(shopper.getX(), shopper.getY(), "S");
    }

    private void processStorage(Shopper shopper, StorageUnit storage) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> products = storage.getProducts();
        
        if(products.isEmpty()) { System.out.println("Empty."); return; }
        
        System.out.println("--- Products ---");
        for(int i=0; i<products.size(); i++) System.out.println((i+1) + ". " + products.get(i));
        
        System.out.print("Select #: ");
        try {
            String in = sc.nextLine().trim();
            if(in.isEmpty()) return;
            int choice = Integer.parseInt(in);
            
            if(choice > 0 && choice <= products.size()) {
                Product p = products.get(choice-1);
                if(shopper.canPurchase(p)) {
                    if(shopper.addProduct(p)) {
                        storage.removeProduct(p);
                        System.out.println("Took " + p.getName());
                    }
                } else System.out.println("Restricted!");
            }
        } catch (Exception e) { System.out.println("Invalid input."); }
    }

    private void generateReceipt(Shopper shopper) {
        try {
            Receipt receipt = new Receipt(shopper);
            String receiptContent = receipt.generateReceipt();

            // Save to file
            FileWriter fw = new FileWriter("receipt_" + shopper.getName() + ".txt");
            fw.write(receiptContent);
            fw.close();

            // Also display to console
            receipt.displayReceipt();

            System.out.println("\n✓ Receipt saved to: receipt_" + shopper.getName() + ".txt");
        } catch (IOException e) {
            System.out.println("⚠️  Error saving receipt: " + e.getMessage());
        }
    }
}