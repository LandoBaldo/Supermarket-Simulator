/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * Abstract base class representing a product in the inventory system.
 * Provides common functionality for all product types including serial number management,
 * pricing, display location determination, and consumable type classification.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
import java.util.*;

// Renamed to "Product" (Singular) to match standard conventions
public abstract class Product {
    private String serialNumber;
    private String name;
    private String displayLocation;
    private String productType;
    private double price;
    
    /**
     * Enum representing the different types of consumable products.
     * 
     * @author unknown
     * @version 1.0.6
     */
    public enum ConsumableType {CONSUMABLE, NON_CONSUMABLE, BEVERAGE}

    public ConsumableType consumableType;

    /**
     * Constructs a Product with specified product type, serial number, and name.
     * Default price and consumable type are determined automatically.
     *
     * @param productType the type of product
     * @param serialNumber the unique identifier for the product
     * @param name the name of the product
     */
    public Product(String productType, String serialNumber, String name) {
        this.productType = productType;
        this.serialNumber = serialNumber;
        this.name = name;
        this.displayLocation = determineLocation(productType);
        this.consumableType = determineConsumableType(productType);
        this.price = determineDefaultPrice(productType);
    }

    /**
     * Constructs a Product with all specified parameters.
     *
     * @param productType the type of product
     * @param serialNumber the unique identifier for the product
     * @param name the name of the product
     * @param price the price of the product
     * @param consumableType the consumable type of the product
     */
    public Product(String productType, String serialNumber, String name, double price,
        ConsumableType consumableType) {
        this.productType = productType;
        this.serialNumber = serialNumber;
        this.name = name;
        this.displayLocation = determineLocation(productType);
        this.price = price;
        this.consumableType = consumableType;
    }
    
    /**
     * Determines the display location based on product type.
     *
     * @param type the product type
     * @return the display location for the product
     */
    private String determineLocation(String type) {
        switch (type.toUpperCase()) {
            // Chilled counter products
            case "BEEF":
            case "SEAFOOD":
            case "CHICKEN":
                return "Chilled counter";

            // Table products
            case "BREAD":
            case "EGGS":
            case "FRUIT":
            case "VEGETABLE":
                return "Table";

            // Refrigerator products
            case "MILK":
            case "FROZEN":
            case "CHEESE":
                return "Refrigerator";

            // Shelf products (default)
            case "CEREAL":
            case "NOODLES":
            case "SNACKS":
            case "CANNED":
            case "CONDIMENTS":
            case "SOFTDRINK":
            case "JUICE":
            case "ALCOHOL":
            case "CLEANING":
            case "HOME":
            case "HAIRCARE":
            case "BODYCARE":
            case "DENTAL":
            case "CLOTHES":
            case "STATIONERY":
            case "PETFOOD":
            default:
                return "Shelf";
        }
    }

    /**
     * Gets the serial number prefix based on product type.
     *
     * @return the three-letter prefix for serial numbers
     */
    public String getSerialNumberPrefix() {
        switch (productType.toUpperCase()) {
            case "BEEF": return "BEF";
            case "SEAFOOD": return "SEA";
            case "BREAD": return "BRD";
            case "CEREAL": return "CER";
            case "NOODLES": return "NDL";
            case "SNACKS": return "SNK";
            case "CANNED": return "CAN";
            case "CONDIMENTS": return "CON";
            case "EGGS": return "EGG";
            case "SOFTDRINK": return "SFT";
            case "JUICE": return "JUC";
            case "ALCOHOL": return "ALC";
            case "CLEANING": return "CLE";
            case "HOME": return "HOM";
            case "HAIRCARE": return "HAR";
            case "BODYCARE": return "BOD";
            case "DENTAL": return "DEN";
            case "CLOTHES": return "CLO";
            case "STATIONERY": return "STN";
            case "PETFOOD": return "PET";
            case "FRUIT": return "FRU";
            case "VEGETABLE": return "VEG";
            case "MILK": return "MLK";
            case "FROZEN": return "FRZ";
            case "CHEESE": return "CHS";
            case "CHICKEN": return "CHK";
            default: return "UNK";
        }
    }

    /**
     * Determines the consumable type based on product type.
     *
     * @param type the product type
     * @return the consumable type classification
     */
    private ConsumableType determineConsumableType(String type) {
        switch (type.toUpperCase()) {
            case "SOFTDRINK":
            case "JUICE":
            case "ALCOHOL":
                return ConsumableType.BEVERAGE;
            case "BEEF":
            case "SEAFOOD":
            case "BREAD":
            case "CEREAL":
            case "NOODLES":
            case "SNACKS":
            case "CANNED":
            case "CONDIMENTS":
            case "EGGS":
            case "FRUIT":
            case "VEGETABLE":
            case "MILK":
            case "FROZEN":
            case "CHEESE":
            case "CHICKEN":
                return ConsumableType.CONSUMABLE;
            default:
                return ConsumableType.NON_CONSUMABLE;
        }
    }

    /**
     * Determines the default price based on product type.
     *
     * @param type the product type
     * @return the default price for the product type
     */
    private double determineDefaultPrice(String type) {
        switch (type.toUpperCase()) {
            case "BEEF": return 350.0;
            case "SEAFOOD": return 460.0;
            case "BREAD": return 35.0;
            case "CEREAL": return 150.0;
            case "NOODLES": return 85.0;
            case "SNACKS": return 15.5;
            case "CANNED": return 35.5;
            case "CONDIMENTS": return 49.0;
            case "EGGS": return 120.0;
            case "SOFTDRINK": return 50.0;
            case "JUICE": return 20.0;
            case "ALCOHOL": return 60.0;
            case "MILK": return 50.5;
            case "FROZEN": return 70.0;
            case "CHEESE": return 60.0;
            case "CHICKEN": return 250.0;
            case "FRUIT": return 90.0;
            case "VEGETABLE": return 40.0;
            case "CLEANING": return 75.0;
            case "HOME": return 100.0;
            case "HAIRCARE": return 85.0;
            case "BODYCARE": return 65.0;
            case "DENTAL": return 55.0;
            case "CLOTHES": return 250.0;
            case "STATIONERY": return 30.0;
            case "PETFOOD": return 120.0;
            default: return 0.0;
        }
    }

    // Getters
    /**
     * Gets the serial number of the product.
     *
     * @return the serial number
     */
    public String getSerialNumber() { return serialNumber; }
    
    /**
     * Gets the name of the product.
     *
     * @return the product name
     */
    public String getName() { return name; }
    
    /**
     * Gets the display location of the product.
     *
     * @return the display location
     */
    public String getDisplayLocation() { return displayLocation; }
    
    /**
     * Gets the product type.
     *
     * @return the product type
     */
    public String getProductType() { return productType; }
    
    /**
     * Gets the price of the product.
     *
     * @return the product price
     */
    public double getPrice() { return price; }
    
    /**
     * Gets the consumable type of the product.
     *
     * @return the consumable type
     */
    public ConsumableType getConsumableType() { return consumableType; }
    
    /**
     * Checks if the product is consumable.
     *
     * @return true if the product is consumable or a beverage, false otherwise
     */
    public boolean isConsumable() { return consumableType == ConsumableType.CONSUMABLE || consumableType == ConsumableType.BEVERAGE; }
    
    /**
     * Checks if the product is a beverage.
     *
     * @return true if the product is a beverage, false otherwise
     */
    public boolean isBeverage() { return consumableType == ConsumableType.BEVERAGE; }
    
    /**
     * Checks if the product is non-consumable.
     *
     * @return true if the product is non-consumable, false otherwise
     */
    public boolean isNonConsumable() { return consumableType == ConsumableType.NON_CONSUMABLE; }

    // Setters
    /**
     * Sets the serial number of the product.
     *
     * @param serialNumber the new serial number
     */
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    
    /**
     * Sets the name of the product.
     *
     * @param name the new product name
     */
    public void setName(String name) { this.name = name; }
    
    /**
     * Sets the price of the product.
     *
     * @param price the new product price
     */
    public void setPrice(double price) { this.price = price; }
    
    /**
     * Sets the consumable type of the product.
     *
     * @param consumableType the new consumable type
     */
    public void setConsumableType(ConsumableType consumableType) { this.consumableType = consumableType; }

    /**
     * Sets the product type and updates related properties.
     *
     * @param productType the new product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
        this.displayLocation = determineLocation(productType);
        this.consumableType = determineConsumableType(productType);
        this.price = determineDefaultPrice(productType);
    }

    /**
     * Displays product information to the console.
     */
    public void displayInfo() {
        System.out.println("Product Type: " + productType);
        System.out.println("Name: " + name);
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Display Location: " + displayLocation);
        System.out.println("Price: $" + price);
        System.out.println("Consumable Type: " + consumableType);
        System.out.println("Serial Prefix: " + getSerialNumberPrefix());
        System.out.println("-------------------");
    }

    /**
     * Returns a string representation of the product.
     *
     * @return a formatted string containing product information
     */
    @Override
    public String toString() {
        return String.format("%s - %s (SN: %s, Location: %s, $%.2f)",
                productType, name, serialNumber, displayLocation, price);
    }
}