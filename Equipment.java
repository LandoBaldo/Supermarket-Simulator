import java.util.ArrayList;

class Equipment {
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
        // Placeholder - you can add price field to Products later
        return products.size() * 10.0;
    }
}
