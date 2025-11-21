import java.util.ArrayList;
import Base.Product;
import Base.StorageUnit;
class ChilledCounter extends StorageUnit {
    protected double temperature;

    public ChilledCounter(int capacity) {
        super(capacity);
        this.temperature = 4.0;
    }

    public ChilledCounter() {
        this(3);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        System.out.println("Temperature set to: " + temperature + "°C");
    }
    public double getTemperature() {
    return temperature;
    }

    public boolean addProductWithCheck(Product product) {
        // Check if product should be in chilled counter
        if (!product.getDisplayLocation().equals("Chilled counter")) {
            System.out.println("Warning: " + product.getName() +
                    " is meant for " + product.getDisplayLocation() +
                    ", not Chilled counter!");
        }
        return super.addProduct(product);
    }
}
