import java.util.ArrayList;

class Cart extends Equipment {
    public Cart(int capacity) {
        super(capacity);
    }

    public Cart() {
        this(30);
    }

    public void checkout() {
        System.out.println("\n=== Checkout ===");
        System.out.println("Total items: " + products.size());
        displayProducts();
    }
}
