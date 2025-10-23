import java.util.ArrayList;

class Refrigerator extends StorageUnit {
    private double temperature;

    public Refrigerator(int capacity) {
        super(capacity);
        this.temperature = 4.0; // Default refrigerator temp
    }

    public Refrigerator() {
        this(9);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to: " + temperature + "°C");
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean addProductWithCheck(Products product) {
        // Check if product should be in refrigerator
        if (!product.getDisplayLocation().equals("Refrigerator")) {
            System.out.println("Warning: " + product.getName() +
                    " is meant for " + product.getDisplayLocation() +
                    ", not Refrigerator!");
        }
        return super.addProduct(product);
    }
}
