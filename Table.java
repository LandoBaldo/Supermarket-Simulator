import java.util.ArrayList;

class Table extends StorageUnit {
    public Table(int capacity) {
        super(capacity);
    }

    public Table() {
        this(4);
    }

    // Display products specifically for table
    public void arrangeProducts() {
        System.out.println("\n--- Arranging products on Table ---");
        if (products.isEmpty()) {
            System.out.println("Table is empty.");
        } else {
            for (Products p : products) {
                System.out.println("  " + p.getName() + " placed on table at position");
            }
        }
    }
}
