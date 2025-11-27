/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Main supermarket class that manages the entire supermarket system.
 * Handles floor management, inventory, shopper interactions, and services.
 * Provides functionality for product search, checkout, ATM services, and floor navigation.
 * 
 * @author Gabriel
 * @version 1.0.6
 */

import Base.Equipment;
import Base.Product;
import Base.Products.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Supermarket {
    private String name;
    private Map map;
    private int currentFloor;
    private ArrayList<Shopper> shoppers;
    
    private HashMap<String, StorageUnit> storageLocations;
    private HashMap<String, Service> serviceLocations;

    /**
     * Constructs a Supermarket with specified name and dimensions.
     * Always starts at ground floor (floor 1).
     *
     * @param name the name of the supermarket
     * @param width the width of the supermarket map
     * @param height the height of the supermarket map
     */
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
    
    /**
     * Gets the current map.
     *
     * @return the current map object
     */
    public Map getMap() {
        return map;
    }
    
    /**
     * Gets the current floor number.
     *
     * @return the current floor number (1 or 2)
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Synchronizes storage and service locations from the map.
     */
    private void syncLocations() {
        storageLocations.clear();
        serviceLocations.clear();
        
        for(Table t : map.getTables()) storageLocations.put(t.getX() + "," + t.getY(), t);
        for(Shelf s : map.getShelves()) storageLocations.put(s.getX() + "," + s.getY(), s);
        for(Refrigerator r : map.getRefrigerators()) storageLocations.put(r.getX() + "," + r.getY(), r);
        for(ChilledCounter cc : map.getChilledCounters()) storageLocations.put(cc.getX() + "," + cc.getY(), cc);
        for(Service s : map.getServices()) serviceLocations.put(s.getX() + "," + s.getY(), s);
    }

    /**
     * Initializes inventory for the current floor.
     * Stock shelves with various products based on floor type.
     */
    private void initializeInventory() {
        System.out.println("Restocking shelves for floor " + currentFloor + "...");

        try {
            if (currentFloor == 1) {
                // Ground floor items - AT LEAST 3 PRODUCTS PER TYPE WITH CREATIVE NAMES
                int tableIndex = 0;
                for (Table t : map.getTables()) {
                    if (t != null) {
                        // Distribute fruits and breads across tables
                        if (tableIndex % 6 == 0) {
                            t.addProduct(new Fruit("FRU001", "AppleScript Apples", 25.0));
                            t.addProduct(new Bread("BRD001", "Bootstrap Bread", 45.0));
                        } else if (tableIndex % 6 == 1) {
                            t.addProduct(new Fruit("FRU002", "Bit Bananas", 15.0));
                            t.addProduct(new Bread("BRD002", "Whole Wheat Web", 50.0));
                        } else if (tableIndex % 6 == 2) {
                            t.addProduct(new Fruit("FRU003", "Orange Pi", 30.0));
                            t.addProduct(new Bread("BRD003", "SQL Sourdough", 65.0));
                        } else if (tableIndex % 6 == 3) {
                            t.addProduct(new Fruit("FRU001", "AppleScript Apples", 25.0));
                            t.addProduct(new Fruit("FRU002", "Bit Bananas", 15.0));
                        } else if (tableIndex % 6 == 4) {
                            t.addProduct(new Bread("BRD001", "Bootstrap Bread", 45.0));
                            t.addProduct(new Bread("BRD003", "SQL Sourdough", 65.0));
                        } else {
                            t.addProduct(new Fruit("FRU003", "Orange Pi", 30.0));
                            t.addProduct(new Bread("BRD002", "Whole Wheat Web", 50.0));
                        }
                        tableIndex++;
                    }
                }

                int shelfIndex = 0;
                for (Shelf s : map.getShelves()) {
                    if (s != null) {
                        // Distribute cereals, alcohol, and cleaning agents
                        if (shelfIndex % 9 == 0) {
                            s.addProduct(new Cereal("CER001", "Java Cereal", 120.0));
                            s.addProduct(new Alcohol("ALC001", "Binary Beer", 60.0));
                        } else if (shelfIndex % 9 == 1) {
                            s.addProduct(new Cereal("CER002", "OOPsie Oaties", 110.0));
                            s.addProduct(new Alcohol("ALC002", "Vector Wine", 350.0));
                        } else if (shelfIndex % 9 == 2) {
                            s.addProduct(new Cereal("CER003", "Barley Bytes", 130.0));
                            s.addProduct(new Alcohol("ALC003", "Variable Vodka", 450.0));
                        } else if (shelfIndex % 9 == 3) {
                            s.addProduct(new CleaningAgents("CLN001", "Debug Bleach", 85.0));
                            s.addProduct(new Cereal("CER001", "Java Cereal", 120.0));
                        } else if (shelfIndex % 9 == 4) {
                            s.addProduct(new CleaningAgents("CLN002", "Virus Disinfectant Spray", 95.0));
                            s.addProduct(new Cereal("CER002", "OOPsie Oaties", 110.0));
                        } else if (shelfIndex % 9 == 5) {
                            s.addProduct(new CleaningAgents("CLN003", "Memory Leak Floor Cleaner", 110.0));
                            s.addProduct(new Alcohol("ALC001", "Binary Beer", 60.0));
                        } else if (shelfIndex % 9 == 6) {
                            s.addProduct(new Alcohol("ALC002", "Vector Wine", 350.0));
                            s.addProduct(new CleaningAgents("CLN001", "Debug Bleach", 85.0));
                        } else if (shelfIndex % 9 == 7) {
                            s.addProduct(new Cereal("CER003", "Barley Bytes", 130.0));
                            s.addProduct(new CleaningAgents("CLN002", "Virus Disinfectant Spray", 95.0));
                        } else {
                            s.addProduct(new Alcohol("ALC003", "Variable Vodka", 450.0));
                            s.addProduct(new Cereal("CER001", "Java Cereal", 120.0));
                        }
                        shelfIndex++;
                    }
                }

                int ccIndex = 0;
                for (ChilledCounter cc : map.getChilledCounters()) {
                    if (cc != null) {
                        // Distribute chicken and beef
                        if (ccIndex % 6 == 0) {
                            cc.addProduct(new Chicken("CHK001", "Compiled Chicken Breast", 250.0));
                            cc.addProduct(new Beef("BEF001", "Ground Beef Stack", 350.0));
                        } else if (ccIndex % 6 == 1) {
                            cc.addProduct(new Chicken("CHK002", "Thread Thighs", 220.0));
                            cc.addProduct(new Beef("BEF002", "Boolean Beef Steak", 450.0));
                        } else if (ccIndex % 6 == 2) {
                            cc.addProduct(new Chicken("CHK003", "Whole Cloud Chicken", 400.0));
                            cc.addProduct(new Beef("BEF003", "Recursive Ribs", 380.0));
                        } else if (ccIndex % 6 == 3) {
                            cc.addProduct(new Chicken("CHK001", "Compiled Chicken Breast", 250.0));
                            cc.addProduct(new Chicken("CHK002", "Thread Thighs", 220.0));
                        } else if (ccIndex % 6 == 4) {
                            cc.addProduct(new Beef("BEF001", "Ground Beef Stack", 350.0));
                            cc.addProduct(new Beef("BEF003", "Recursive Ribs", 380.0));
                        } else {
                            cc.addProduct(new Chicken("CHK003", "Whole Cloud Chicken", 400.0));
                            cc.addProduct(new Beef("BEF002", "Boolean Beef Steak", 450.0));
                        }
                        ccIndex++;
                    }
                }
            } 
            else if (currentFloor == 2) {
                // Second floor items - AT LEAST 3 PRODUCTS PER TYPE WITH CREATIVE NAMES
                int tableIndex = 0;
                for (Table t : map.getTables()) {
                    if (t != null) {
                        // Distribute breads and eggs across tables
                        if (tableIndex % 6 == 0) {
                            t.addProduct(new Bread("BRD004", "Rye-thon Bread", 55.0));
                            t.addProduct(new Egg("EGG001", "Exception Eggs", 120.0));
                        } else if (tableIndex % 6 == 1) {
                            t.addProduct(new Bread("BRD005", "Byte Baguette", 70.0));
                            t.addProduct(new Egg("EGG002", "Organic Overflow Eggs", 150.0));
                        } else if (tableIndex % 6 == 2) {
                            t.addProduct(new Bread("BRD001", "Bootstrap Bread", 45.0));
                            t.addProduct(new Egg("EGG003", "Free Range RAM", 140.0));
                        } else if (tableIndex % 6 == 3) {
                            t.addProduct(new Egg("EGG001", "Exception Eggs", 120.0));
                            t.addProduct(new Egg("EGG002", "Organic Overflow Eggs", 150.0));
                        } else if (tableIndex % 6 == 4) {
                            t.addProduct(new Bread("BRD004", "Rye-thon Bread", 55.0));
                            t.addProduct(new Bread("BRD005", "Byte Baguette", 70.0));
                        } else {
                            t.addProduct(new Egg("EGG003", "Free Range RAM", 140.0));
                            t.addProduct(new Bread("BRD001", "Bootstrap Bread", 45.0));
                        }
                        tableIndex++;
                    }
                }

                int shelfIndex = 0;
                for (Shelf s : map.getShelves()) {
                    if (s != null) {
                        // Distribute cereals and snacks
                        if (shelfIndex % 6 == 0) {
                            s.addProduct(new Cereal("CER004", "Kernel Cornflakes", 110.0));
                            s.addProduct(new Snacks("SNK001", "Packet Potato Chips", 35.0));
                        } else if (shelfIndex % 6 == 1) {
                            s.addProduct(new Cereal("CER005", "Git Granola", 140.0));
                            s.addProduct(new Snacks("SNK002", "Cookie Crackers", 40.0));
                        } else if (shelfIndex % 6 == 2) {
                            s.addProduct(new Cereal("CER001", "Java Cereal", 120.0));
                            s.addProduct(new Snacks("SNK003", "Protocol Pretzels", 45.0));
                        } else if (shelfIndex % 6 == 3) {
                            s.addProduct(new Snacks("SNK001", "Packet Potato Chips", 35.0));
                            s.addProduct(new Cereal("CER004", "Kernel Cornflakes", 110.0));
                        } else if (shelfIndex % 6 == 4) {
                            s.addProduct(new Snacks("SNK002", "Cookie Crackers", 40.0));
                            s.addProduct(new Cereal("CER005", "Git Granola", 140.0));
                        } else {
                            s.addProduct(new Snacks("SNK003", "Protocol Pretzels", 45.0));
                            s.addProduct(new Cereal("CER001", "Java Cereal", 120.0));
                        }
                        shelfIndex++;
                    }
                }

                int fridgeIndex = 0;
                for (Refrigerator r : map.getRefrigerators()) {
                    if (r != null) {
                        // Distribute milk and cheese
                        if (fridgeIndex % 6 == 0) {
                            r.addProduct(new Milk("MLK001", "Fresh Framework Milk", 95.0));
                            r.addProduct(new Cheese("CHS001", "Cheddar Code Cheese", 150.0));
                        } else if (fridgeIndex % 6 == 1) {
                            r.addProduct(new Milk("MLK002", "Chocolate Cache Milk", 105.0));
                            r.addProduct(new Cheese("CHS002", "Mozilla Mozzarella", 180.0));
                        } else if (fridgeIndex % 6 == 2) {
                            r.addProduct(new Milk("MLK003", "Algorithm Almond Milk", 130.0));
                            r.addProduct(new Cheese("CHS003", "Polymorphic Parmesan", 220.0));
                        } else if (fridgeIndex % 6 == 3) {
                            r.addProduct(new Milk("MLK001", "Fresh Framework Milk", 95.0));
                            r.addProduct(new Milk("MLK002", "Chocolate Cache Milk", 105.0));
                        } else if (fridgeIndex % 6 == 4) {
                            r.addProduct(new Cheese("CHS001", "Cheddar Code Cheese", 150.0));
                            r.addProduct(new Cheese("CHS002", "Mozilla Mozzarella", 180.0));
                        } else {
                            r.addProduct(new Milk("MLK003", "Algorithm Almond Milk", 130.0));
                            r.addProduct(new Cheese("CHS003", "Polymorphic Parmesan", 220.0));
                        }
                        fridgeIndex++;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error initializing inventory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Adds a shopper to the supermarket at specified coordinates.
     *
     * @param s the shopper to add
     * @param x the x-coordinate position
     * @param y the y-coordinate position
     */
    public void addShopper(Shopper s, int x, int y) {
        s.setPosition(x, y);
        shoppers.add(s);
        map.setCell(x, y, "S");
    }

    /**
     * Displays the current map to the console.
     */
    public void displayMap() { 
        System.out.println("=== FLOOR " + currentFloor + " ===");
        map.printMap(); 
    }

    /**
     * Handles interaction for a shopper with objects in front of them.
     *
     * @param shopper the shopper attempting interaction
     */
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

    /**
     * Processes service interactions for a shopper.
     *
     * @param shopper the shopper interacting with the service
     * @param s the service being interacted with
     */
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
                    JOptionPane.showMessageDialog(null, 
                        "✓ You got a Cart! (Capacity: 30 items)",
                        "Cart Obtained", JOptionPane.INFORMATION_MESSAGE);
                } else if (shopper.getEquipment() != null) {
                    if (shopper.getEquipment().isEmpty()) {
                        shopper.removeEquipment();
                        JOptionPane.showMessageDialog(null, 
                            "✓ You returned the cart.",
                            "Cart Returned", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "⚠️ Cart is not empty. Remove items first.",
                            "Cannot Return", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "⚠️ You must be empty-handed to get equipment.",
                        "Cannot Get Equipment", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case BASKET_STATION:
                if (shopper.getEquipment() == null && shopper.getHandCarried().isEmpty()) {
                    shopper.setEquipment(new Basket());
                    JOptionPane.showMessageDialog(null, 
                        "✓ You got a Basket! (Capacity: 15 items)",
                        "Basket Obtained", JOptionPane.INFORMATION_MESSAGE);
                } else if (shopper.getEquipment() != null) {
                    if (shopper.getEquipment().isEmpty()) {
                        shopper.removeEquipment();
                        JOptionPane.showMessageDialog(null, 
                            "✓ You returned the basket.",
                            "Basket Returned", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "⚠️ Basket is not empty. Remove items first.",
                            "Cannot Return", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "⚠️ You must be empty-handed to get equipment.",
                        "Cannot Get Equipment", JOptionPane.WARNING_MESSAGE);
                }
                break;

            case PRODUCT_SEARCH:
                handleProductSearch();
                break;

            case CHECKOUT_COUNTER:
                handleCheckout(shopper);
                break;

            case ATM:  // NEW: ATM handler
                handleATM(shopper);
                break;

            case ENTRANCE:
                handleExit(shopper);
                break;
        }
    }
    
    /**
     * Handles product search interaction - GUI VERSION.
     * Allows shoppers to search for products by name.
     */
    private void handleProductSearch() {
        String query = JOptionPane.showInputDialog(null, 
            "🔍 Enter product name to search:", 
            "Product Search", 
            JOptionPane.QUESTION_MESSAGE);

        if (query == null || query.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "⚠️ No product name entered.",
                "Search Cancelled", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ArrayList<String> found = new ArrayList<>();

        for (String key : storageLocations.keySet()) {
            StorageUnit unit = storageLocations.get(key);
            for (Product p : unit.getProducts()) {
                String pname = p.getName();
                if (pname != null && pname.toLowerCase().contains(query.toLowerCase())) {
                    // Use address instead of coordinates
                    String address = unit.getAddress();
                    if (address == null || address.isEmpty()) {
                        address = "Location Unknown";
                    }
                    
                    // Avoid duplicate addresses in results
                    if (!found.contains(address)) {
                        found.add(address);
                    }
                    break;
                }
            }
        }

        if (found.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "❌ Product not found in the supermarket.",
                "Not Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder message = new StringBuilder("✓ Product found at:\n\n");
            for (String addr : found) {
                message.append("  • ").append(addr).append("\n");
            }
            JOptionPane.showMessageDialog(null, 
                message.toString(),
                "Product Locations", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Handles ATM interaction - BONUS FEATURE.
     * Allows shoppers to deposit money and check balance.
     *
     * @param shopper the shopper using the ATM
     */
    private void handleATM(Shopper shopper) {
        String[] options = {
            "Deposit ₱100",
            "Deposit ₱500",
            "Deposit ₱1,000",
            "Deposit ₱5,000",
            "Check Balance",
            "Cancel"
        };
        
        String choice = (String) JOptionPane.showInputDialog(
            null,
            "💰 ATM Machine\n\nCurrent Balance: ₱" + String.format("%.2f", shopper.getMoney()) + "\n\nSelect an option:",
            "ATM",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );
        
        if (choice == null || choice.equals("Cancel")) {
            return;
        }
        
        if (choice.equals("Check Balance")) {
            JOptionPane.showMessageDialog(null,
                "💵 Your Balance: ₱" + String.format("%.2f", shopper.getMoney()),
                "Balance", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Extract amount from choice
            String amountStr = choice.replace("Deposit ₱", "").replace(",", "");
            double amount = Double.parseDouble(amountStr);
            shopper.addMoney(amount);
            
            JOptionPane.showMessageDialog(null,
                "✅ Deposit Successful!\n\n" +
                "Deposited: ₱" + String.format("%.2f", amount) + "\n" +
                "💵 New Balance: ₱" + String.format("%.2f", shopper.getMoney()),
                "ATM", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Handles checkout counter interaction - UPDATED WITH MONEY CHECK.
     * Processes shopper's purchase and generates receipt.
     *
     * @param shopper the shopper checking out
     */
    private void handleCheckout(Shopper shopper) {
        ArrayList<Product> allProducts = shopper.getAllProducts();

        if (allProducts.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "⚠️ You have no products to checkout.",
                "No Items", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Calculate total cost
        double total = calculateTotal(shopper);
        
        // Check if shopper has enough money
        if (!shopper.hasEnoughMoney(total)) {
            double needed = total - shopper.getMoney();
            JOptionPane.showMessageDialog(null,
                "❌ Insufficient Funds!\n\n" +
                "Total Cost:      ₱" + String.format("%.2f", total) + "\n" +
                "Your Balance:    ₱" + String.format("%.2f", shopper.getMoney()) + "\n" +
                "Amount Needed:   ₱" + String.format("%.2f", needed) + "\n\n" +
                "💡 Please visit an ATM (M) to deposit more money.",
                "Cannot Checkout", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Deduct money
        shopper.deductMoney(total);

        generateReceipt(shopper);
        shopper.clearInventory();
        shopper.setCheckedOut(true);

        // DON'T remove equipment - player must return it manually
        JOptionPane.showMessageDialog(null, 
            "✓ Checkout Complete! Thank you for shopping!\n\n" +
            "💰 Total Paid:        ₱" + String.format("%.2f", total) + "\n" +
            "💵 Remaining Balance: ₱" + String.format("%.2f", shopper.getMoney()) + "\n\n" +
            "📝 Receipt saved to: receipt_" + shopper.getName() + ".txt\n\n" +
            "⚠️ Please return your " + (shopper.getEquipment() != null ? 
                (shopper.getEquipment() instanceof Cart ? "cart" : "basket") : "equipment") + 
            " before exiting!",
            "Checkout Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Handles exit interaction.
     * Validates that shopper can leave the supermarket.
     *
     * @param shopper the shopper attempting to exit
     */
    private void handleExit(Shopper shopper) {
        // Check if shopper has equipment
        if (shopper.getEquipment() != null) {
            JOptionPane.showMessageDialog(null, 
                "⚠️ You cannot leave with store equipment.\n\n" +
                "Please return your " + 
                (shopper.getEquipment() instanceof Cart ? "cart (K)" : "basket (B)") + 
                " first at the station where you got it.",
                "Cannot Exit", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Check if shopper has unchecked items
        if (!shopper.getAllProducts().isEmpty() && !shopper.hasCheckedOut()) {
            JOptionPane.showMessageDialog(null, 
                "⚠️ You have unpaid items!\n\n" +
                "Please checkout at a counter ($) first or return the items.",
                "Cannot Exit", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Allow exit
        shopper.setExited(true);
        
        // Success message
        String message = "✓ Thank you for shopping at SuperMart!\n\n";
        if (shopper.hasCheckedOut()) {
            message += "💰 Your receipt has been saved.\n";
        }
        message += "Come back soon! 🛒";
        
        JOptionPane.showMessageDialog(null, 
            message,
            "Goodbye!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Changes the current floor for a shopper.
     * Handles transitions between floor 1 and floor 2.
     *
     * @param shopper the shopper changing floors
     */
    private void changeFloor(Shopper shopper) {
        // Remember which stairs they used (left or right side)
        int currentX = shopper.getX();
        boolean usedLeftStairs = (currentX <= 10);
        
        // Clear shopper from current position
        map.setCell(shopper.getX(), shopper.getY(), " ");
        
        // Switch floor
        if (currentFloor == 1) {
            // Going UP to floor 2
            currentFloor = 2;
            map = new Map(22, 22, 2);
            
            // Spawn one row ABOVE row 15 stairs (row 14)
            if (usedLeftStairs) {
                shopper.setPosition(1, 14); // Above left stairs at row 15
            } else {
                shopper.setPosition(20, 14); // Above right stairs at row 15
            }
            
            JOptionPane.showMessageDialog(null, 
                "🔼 You went UP to Floor 2!",
                "Floor Changed", JOptionPane.INFORMATION_MESSAGE);
        } else if (currentFloor == 2) {
            // Going DOWN to floor 1
            currentFloor = 1;
            map = new Map(22, 22, 1);
            
            // Spawn one row BELOW row 15 stairs (row 16)
            if (usedLeftStairs) {
                shopper.setPosition(1, 16); // Below left stairs at row 15
            } else {
                shopper.setPosition(20, 16); // Below right stairs at row 15
            }
            
            JOptionPane.showMessageDialog(null, 
                "🔽 You went DOWN to Floor 1!",
                "Floor Changed", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Re-sync locations and inventory for new floor
        syncLocations();
        initializeInventory();
        
        // Place shopper on new floor
        map.setCell(shopper.getX(), shopper.getY(), "S");
    }

    /**
     * Processes storage unit interaction - GUI-FRIENDLY VERSION.
     * Uses JOptionPane instead of Scanner for product selection.
     *
     * @param shopper the shopper interacting with storage
     * @param storage the storage unit being accessed
     */
    private void processStorage(Shopper shopper, StorageUnit storage) {
        ArrayList<Product> products = storage.getProducts();
        
        if(products.isEmpty()) { 
            JOptionPane.showMessageDialog(null, 
                "This storage unit is empty.",
                "Empty", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }
        
        // Create array of product descriptions
        String[] productDescriptions = new String[products.size()];
        for(int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            productDescriptions[i] = String.format("%d. %s - ₱%.2f", 
                i + 1, p.getName(), p.getPrice());
        }
        
        // Show selection dialog
        String selected = (String) JOptionPane.showInputDialog(
            null,
            "Select a product to take:",
            "Products Available",
            JOptionPane.QUESTION_MESSAGE,
            null,
            productDescriptions,
            productDescriptions[0]
        );
        
        // If user cancelled
        if (selected == null) {
            return;
        }
        
        // Find which product was selected
        int selectedIndex = -1;
        for (int i = 0; i < productDescriptions.length; i++) {
            if (productDescriptions[i].equals(selected)) {
                selectedIndex = i;
                break;
            }
        }
        
        if (selectedIndex >= 0 && selectedIndex < products.size()) {
            Product p = products.get(selectedIndex);
            
            // Check if shopper can purchase this product
            if (!shopper.canPurchase(p)) {
                JOptionPane.showMessageDialog(null, 
                    "⚠️ You cannot purchase this product!\n\n" +
                    (shopper instanceof Minor ? "Minors cannot buy Alcohol or Cleaning Agents." : 
                     "This product is restricted."),
                    "Restricted Item", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Try to add product
            if (shopper.addProduct(p)) {
                storage.removeProduct(p);
                JOptionPane.showMessageDialog(null, 
                    "✓ Took: " + p.getName() + " (₱" + String.format("%.2f", p.getPrice()) + ")",
                    "Item Added", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "⚠️ Cannot add item. Your equipment may be full!",
                    "Cannot Add", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * Calculates total cost for shopper - BONUS FEATURE.
     * Applies appropriate discounts based on shopper type.
     *
     * @param shopper the shopper to calculate total for
     * @return the total cost after discounts
     */
    private double calculateTotal(Shopper shopper) {
        double total = 0.0;
        for (Product p : shopper.getAllProducts()) {
            total += shopper.getPriceFor(p);
        }
        return total;
    }

    /**
     * Generates and saves a receipt for the shopper's purchase.
     *
     * @param shopper the shopper to generate receipt for
     */
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
            System.out.println("⚠️ Error saving receipt: " + e.getMessage());
        }
    }
}