import java.util.ArrayList;
public class Basket extends Equipment{
    private int capacity;
    private ArrayList<Product> products;

    public Basket(int capacity) {
       super(capacity, new ArrayList<Product>());

    }
    public Basket() {
        this(15);
    }
}
