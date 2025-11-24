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
        this.map = new Map(width, height);
        this.shoppers = new ArrayList<>();
        this.storageLocations = new HashMap<>();
        this.serviceLocations = new HashMap<>();

        syncLocations();
        initializeInventory();
    }
    
    // --- FIXED: ADDED GETTER FOR MAIN ---
    public Map getMap() {
        return map;
    }

    private void syncLocations() {
        for(Table t : map.getTables()) storageLocations.put(t.getX() + "," + t.getY(), t);
        for(Shelf s : map.getShelves()) storageLocations.put(s.getX() + "," + s.getY(), s);
        for(Refrigerator r : map.getRefrigerators()) storageLocations.put(r.getX() + "," + r.getY(), r);
        for(Service s : map.getServices()) serviceLocations.put(s.getX() + "," + s.getY(), s);
    }

    private void initializeInventory() {
        System.out.println("Restocking shelves...");
        
        // 1. Add Fruits/Bread to Tables
        for (Table t : map.getTables()) {
            // FIXED: Added the 5th argument (ConsumableType)
            t.addProduct(new Product("FRUIT", "FRU01", "Apple", 25.0, Product.ConsumableType.CONSUMABLE));
            t.addProduct(new Product("BREAD", "BRD01", "Loaf", 45.0, Product.ConsumableType.CONSUMABLE));
        }

        // 2. Add Cereal/Alcohol to Shelves
        for (Shelf s : map.getShelves()) {
            s.addProduct(new Product("CEREAL", "CER01", "Oats", 120.0, Product.ConsumableType.CONSUMABLE));
            // FIXED: Alcohol is BEVERAGE
            s.addProduct(new Product("ALCOHOL", "ALC01", "Beer", 60.0, Product.ConsumableType.BEVERAGE));
        }

        // 3. Add Milk to Fridges
        for (Refrigerator r : map.getRefrigerators()) {
            // FIXED: Milk is BEVERAGE
            r.addProduct(new Product("MILK", "MLK01", "Fresh Milk", 95.0, Product.ConsumableType.BEVERAGE));
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

    private void processService(Shopper shopper, Service s) {
        System.out.println(s.interact());
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