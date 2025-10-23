import java.util.ArrayList;

class ChilledCounters extends StorageUnit {
    private double temperature;

    public ChilledCounters(int capacity) {
        super(capacity);
        this.temperature = 2.0; // Default chilled counter temp
    }

    public ChilledCounters() {
        this(3);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to: " + temperature + "°C");
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean addProductWithCheck(Products product) {
        // Check if product should be in chilled counter
        if (!product.getDisplayLocation().equals("Chilled counter")) {
            System.out.println("Warning: " + product.getName() +
                    " is meant for " + product.getDisplayLocation() +
                    ", not Chilled counter!");
        }
        return super.addProduct(product);
    }
}
