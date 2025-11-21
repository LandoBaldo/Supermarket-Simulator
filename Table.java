
import Base.Product;
import Base.StorageUnit;

import java.util.ArrayList;

class Table extends StorageUnit {
    public Table(int capacity) {
        super(capacity);
    }

    public Table() {
        this(4);
    }

    // Display Product specifically for table
    public void arrangeProduct() {
        System.out.println("\n--- Arranging Product on Table ---");
        if (products.isEmpty()) {
            System.out.println("Table is empty.");
        } else {
            for (Product p : products) {
                System.out.println("  " + p.getName() + " placed on table at position");
            }
        }
    }
}
