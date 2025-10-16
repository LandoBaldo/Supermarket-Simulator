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

}
