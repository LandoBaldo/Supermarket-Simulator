import java.util.ArrayList;

// Represents a shopper in the supermarket simulation
public class Shopper {
    private String name;
    private int age;
    private Equipment equipment;
    private ArrayList<Products> handCarried;
    private boolean checkedOut = false;
    private boolean exited = false;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private int x = -1;
    private int y = -1;
    private Direction facing = Direction.DOWN;

    // Constructor
    public Shopper(String name, int age) {
        this.name = name;
        this.age = age;
        this.handCarried = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public ArrayList<Products> getHandCarried() {
        return handCarried;
    }

    public boolean hasCheckedOut() {
        return checkedOut;
    }

    public boolean hasExited() {
        return exited;
    }

    // Position and Direction
    public void setPostion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setFacing(Direction d) {
        this.facing = d;
    }

    public Direction getFacing() {
        return facing;
    }

    public boolean setEquipment(Equipment equipment) {
        if (this.equipment == null) {
            this.equipment = equipment;
            return true;
        }
        return false;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public void setExited(boolean exited) {
        this.exited = exited;
    }

    public Equipment removeEquipment() {
        Equipment temp = this.equipment;
        this.equipment = null;
        return temp;
    }

    public boolean addToHandCarried(Products product) {
        if (handCarried.size() >= 2)
            return false;
        handCarried.add(product);
        return true;
    }

    public boolean removeFromHandCarried(Products product) {
        return handCarried.remove(product);
    }

    public Products removeFromHandCarriedByIndex(int index) {
        if (index < 0 || index >= handCarried.size())
            return null;
        return handCarried.remove(index);
    }

    public boolean addProduct(Products product) {
        if (this.equipment != null && equipment.addProduct(product)) {
            return true;
        } else if (handCarried.size() < 2) {
            return addToHandCarried(product);
        }
        return false;
    }

    public boolean removeProduct(Products product) {
        if (equipment != null && equipment.removeProduct(product)) {
            return true;
        }
        return removeFromHandCarried(product);
    }

    public ArrayList<Products> getAllProducts() {
        ArrayList<Products> allProducts = new ArrayList<>(handCarried);
        if (equipment != null) {
            allProducts.addAll(equipment.getProducts());
        }
        return allProducts;
    }

    public int getTotalProductCount() {
        int count = handCarried.size();
        if (equipment != null) {
            count += equipment.getProducts().size();
        }
        return count;
    }

    public boolean canPurchase(Products product) {
        if (age < 18) {
            String productType = product.getProductType();
            if (productType.equals("ALCOHOL") || productType.equals("CLEANING")) {
                return false;
            }
        }
        return true;
    }

    public double applyDiscount(Products product) {
        double originalPrice = product.getPrice();
        if (age >= 60 && product.isConsumable() && !product.getProductType().equals("ALCOHOL")) {
            if (product.isBeverage()) {
                return originalPrice * 0.90;
            } else {
                return originalPrice * 0.80;
            }
        }
        return originalPrice;
    }

    public void clearAllProducts() {
        if (equipment != null) {
            equipment.getProducts().clear();
        }
        handCarried.clear();
    }

    public boolean isEquipmentEmpty() {
        return equipment == null || equipment.isEmpty();
    }
}
