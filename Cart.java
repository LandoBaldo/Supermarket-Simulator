import java.util.ArrayList;
public class Cart extends Equipment{
    protected int capacity;
    protected ArrayList<Product> products;

    public Cart(int capacity) {
       super(capacity, new ArrayList<Product>());

    }
    public Cart() {
        this(30);
    }
}
