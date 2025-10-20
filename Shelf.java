import java.util.ArrayList;

class Shelf extends StorageUnit {
    public Shelf(int capacity) {
        super(capacity);
    }

    public Shelf() {
        this(8);
    }
}