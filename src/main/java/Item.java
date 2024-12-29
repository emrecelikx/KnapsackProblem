class Item {
    private final long id;
    private final long profit;
    private final long weight;

    public Item(long id, long profit, long weight) {
        this.id = id;
        this.profit = profit;
        this.weight = weight;
    }

    public long id() {
        return id;
    }

    public long profit() {
        return profit;
    }

    public long weight() {
        return weight;
    }

    public String toString() {
        return String.format("Item(%d, %d, %d)", id, profit, weight);
    }
}