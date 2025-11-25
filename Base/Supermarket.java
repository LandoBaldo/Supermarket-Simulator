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
    private ArrayList<Shopper> shoppers;
    
    private HashMap<String, StorageUnit> storageLocations;
    private HashMap<String, Service> serviceLocations;

    public Supermarket(String name, int width, int height) {
        this.name = name;
        this.map = new Map(width, height, 1);
        this.shoppers = new ArrayList<>();
        this.storageLocations = new HashMap<>();
        this.serviceLocations = new HashMap<>();

        syncLocations();
        initializeInventory();
    }
    private int currentFloor = 1;
    
    // --- FIXED: ADDED GETTER FOR MAIN ---
    public Map getMap() {
        return map;
    }

    private void syncLocations() {
        // Clear existing maps before re-syncing (prevents stale entries across floors)
        storageLocations.clear();
        serviceLocations.clear();

        for(Table t : map.getTables()) storageLocations.put(t.getX() + "," + t.getY(), t);
        for(Shelf s : map.getShelves()) storageLocations.put(s.getX() + "," + s.getY(), s);
        for(Refrigerator r : map.getRefrigerators()) storageLocations.put(r.getX() + "," + r.getY(), r);
        for(Service s : map.getServices()) serviceLocations.put(s.getX() + "," + s.getY(), s);
    }

    private void initializeInventory() {
        System.out.println("Restocking shelves...");
        
        // 1. Add Fruits/Bread to Tables
        for (Table t : map.getTables()) {
            // Use concrete product subclasses instead of abstract Product
            t.addProduct(new Fruit("FRU01", "Apple", 25.0));
            t.addProduct(new Bread("BRD01", "Loaf", 45.0));
        }

        // 2. Add Cereal/Alcohol to Shelves
        for (Shelf s : map.getShelves()) {
            s.addProduct(new Cereal("CER01", "Oats", 120.0));
            // Alcohol is a Beverage subclass
            s.addProduct(new Alcohol("ALC01", "Beer", 60.0));
        }

        // 3. Add Milk to Fridges
        for (Refrigerator r : map.getRefrigerators()) {
            // Milk is a Beverage subclass
            r.addProduct(new Milk("MLK01", "Fresh Milk", 95.0));
        }
    }

    public void addShopper(Shopper s, int x, int y) {
        s.setPosition(x, y);
        shoppers.add(s);
        map.setCell(x, y, "S");
    }

    public void displayMap() { map.printMap(); }

    public void handleInteraction(Shopper shopper) {
        FrontCell front = MovementController.getCellInFront(shopper, map);
        if (front == null) { System.out.println("Nothing in front."); return; }

        String key = front.getX() + "," + front.getY();
        
        Service s = serviceLocations.get(key);
        if (s != null) { processService(shopper, s); return; }

        StorageUnit u = storageLocations.get(key);
        if (u != null) { processStorage(shopper, u); return; }
        
        System.out.println("Cannot interact with that.");
    }
    

    public int getCurrentFloor() {
        return currentFloor;
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

    private void processService(Shopper shopper, Service s) {
        String result = s.interact();

        // Handle floor change for stairs
        if (result.equals("FLOOR_CHANGE")) {
            changeFloor(shopper);
            return;
        }

        System.out.println(result);

        // Product search interaction: ask shopper for product name and show addresses
        if (s.getType() == Service.ServiceType.PRODUCT_SEARCH) {
            java.util.Scanner sc = new java.util.Scanner(System.in);
            System.out.print("Product name: ");
            String query = sc.nextLine().trim();
            if (query.isEmpty()) { System.out.println("No product name entered."); return; }

            java.util.ArrayList<String> found = new java.util.ArrayList<>();
            for (String key : storageLocations.keySet()) {
                StorageUnit unit = storageLocations.get(key);
                for (Product p : unit.getProducts()) {
                    String pname = p.getName();
                    if (pname != null && pname.toLowerCase().contains(query.toLowerCase())) {
                        found.add(key);
                        break;
                    }
                }
            }

            if (found.isEmpty()) {
                System.out.println("Product not found anywhere in the supermarket.");
            } else {
                System.out.println("Product found at the following display addresses:");
                for (String addr : found) System.out.println(addr);
            }

            return;
        }

        if (s.getType() == Service.ServiceType.CART_STATION && shopper.getEquipment() == null) {
            shopper.setEquipment(new Cart());
            System.out.println("You took a Cart.");
        } else if (s.getType() == Service.ServiceType.CHECKOUT_COUNTER) {
            generateReceipt(shopper);
            shopper.clearInventory();
            shopper.setCheckedOut(true);
        }
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
        try (FileWriter fw = new FileWriter("receipt_" + shopper.getName() + ".txt")) {
            fw.write("RECEIPT\n");
            double total = 0;
            for(Product p : shopper.getAllProducts()) {
                fw.write(p.getName() + " - " + p.getPrice() + "\n");
                total += p.getPrice();
            }
            fw.write("TOTAL: " + total);
            System.out.println("Receipt saved!");
        } catch (IOException e) { System.out.println("Error saving receipt."); }
    }
}
