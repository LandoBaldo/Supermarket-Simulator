public class ChilledCounters {
    private int capacity;
    private List<String> products;

    public ChilledCounters(int capacity) {
        this.capacity = capacity;
        this.products = new ArrayList<>();
    }

    public ChilledCounters() {
        this(3);
    }

}
