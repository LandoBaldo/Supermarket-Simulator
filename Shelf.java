import java.util.ArrayList;

import Base.StorageUnit;

class Shelf extends StorageUnit {
    public Shelf(int capacity) {
        super(capacity);
    }

    public Shelf() {
        this(8);
    }
}
