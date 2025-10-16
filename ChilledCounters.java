import java.util.ArrayList;
public class ChilledCounters extends StorageUnit{
    private int capacity;
    private ArrayList<Product> product;

    public ChilledCounters(int capacity) {
        super(capacity);
    }

    public ChilledCounters() {
        this(3);
    }
    public void setTemperature(double temperature) {
    this.temperature = temperature;
    System.out.println("Temperature set to: " + temperature + "°C");
        
    }

}

