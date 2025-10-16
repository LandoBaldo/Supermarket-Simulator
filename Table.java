import java.util.ArrayList;
public class Table extends StorageUnit {
    private int capacity;
    private ArrayList<Product> products;

    public Table(int capacity) {
        super(capacity);
    }

    public Table() {
        this(4);
    }

    
}
