/**
 * Represents various service points in the supermarket.
 * 
 * @author Gabriel
 * @version 1.0.6
 */
package Base;
public class Service {
    private ServiceType type;
    private int x, y;
    
    /**
     * Service types available in the supermarket.
     */
    public enum ServiceType {
        ENTRANCE, CART_STATION, BASKET_STATION, 
        PRODUCT_SEARCH, STAIRS, CHECKOUT_COUNTER, EXIT, ATM
    }
    
    /**
     * Constructs a new service point.
     * 
     * @param type the type of service
     * @param x x-coordinate position
     * @param y y-coordinate position
     */
    public Service(ServiceType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public ServiceType getType() { return type; }
    public int getX() { return x; }
    public int getY() { return y; }
    
    /**
     * Handles interaction with the service point.
     * 
     * @return interaction result message
     */
    public String interact() {
        switch(type) {
            case CART_STATION: return "Cart acquired!";
            case BASKET_STATION: return "Basket acquired!";
            case PRODUCT_SEARCH: return "Search active.";
            case CHECKOUT_COUNTER: return "Checkout started.";
            case STAIRS: return "FLOOR_CHANGE";
            default: return "You are at the " + type;
        }
    }
}