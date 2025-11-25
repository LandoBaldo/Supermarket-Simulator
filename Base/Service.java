/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 *
 * @author Gabriel
 */

public class Service {
    private ServiceType type;
    private int x, y;
    
    public enum ServiceType {
        ENTRANCE, CART_STATION, BASKET_STATION, 
        PRODUCT_SEARCH, STAIRS, CHECKOUT_COUNTER, EXIT
    }
    
    public Service(ServiceType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public ServiceType getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
    
    public String interact() {
        switch(type) {
            case CART_STATION: return "Cart acquired!";
            case BASKET_STATION: return "Basket acquired!";
            case PRODUCT_SEARCH: return "Search active.";
            case CHECKOUT_COUNTER: return "Checkout started.";
            case STAIRS: return "FLOOR_CHANGE"; // Special return value for stairs
            default: return "You are at the " + type;
        }
    }
}