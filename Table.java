public class Table {
    private int capacity;
    private List<String> products;

    public Table(int capacity) {
        this.capacity = capacity;
        this.products = new ArrayList<>();
    }

    public Table() {
        this(4);
    }

    
}
