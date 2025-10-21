import java.util.ArrayList;

public class StorageUnit {
    protected int capacity;
    protected ArrayList<Products> products;

    public StorageUnit(int capacity) {
        this.capacity = capacity;
        this.products = new ArrayList<>();
    }

    public boolean addProduct(Products product) {
        if (products.size() < capacity) {
            products.add(product);
            System.out.println("Product " + product.getName() + " added to storage.");
            return true;
        }
        System.out.println("Storage full! Cannot add more products. Capacity: " + capacity);
        return false;
    }

    public boolean removeProduct(Products product) {
        if (products.contains(product)) {
            products.remove(product);
            System.out.println("Product " + product.getName() + " removed from storage.");
            return true;
        }
        System.out.println("Product not found in storage.");
        return false;
    }

    public ArrayList<Products> displayProducts() {
        System.out.println("Products in storage:");
        for (Products product : products) {
            System.out.println("  - " + product);
        }
        return products;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    public int getCurrentCount() {
        return products.size();
    }

    public boolean isFull() {
        return products.size() >= capacity;
    }
}
