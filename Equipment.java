import java.util.ArrayList;

public class Equipment {
    protected int capacity;
    protected ArrayList<Products> products;

    public Equipment(int capacity) {
        this.capacity = capacity;
        this.products = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean addProduct(Products product) {
        if (products.size() < capacity) {
            products.add(product);
            System.out.println("Product " + product.getName() + " added to equipment.");
            return true;
        }
        System.out.println("Equipment full! Cannot add more products. Capacity: " + capacity);
        return false;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public void displayProducts() {
        System.out.println("Products in equipment:");
        for (Products product : products) {
            System.out.println("  - " + product);
        }
    }

    public double calculateTotalValue() {
        double total = 0;
        for (Products product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public boolean removeProduct(Products product) {
        return products.remove(product);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
