import java.util.ArrayList;
public class Refrigerator extends StorageUnit{
    private int capacity;
    private ArrayList<Product> product;

    public Refrigerator(int capacity) {
        super(capacity);
    }
    public Refrigerator() {
        this(9);
    }
    
}
