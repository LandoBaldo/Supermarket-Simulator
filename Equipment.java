import java.util.ArrayList;
public class Equipment {
    protected int capacity;
    protected ArrayList<Product> products;

    public Equipment(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

}