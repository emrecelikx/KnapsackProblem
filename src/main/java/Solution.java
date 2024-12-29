import java.util.HashSet;
import java.util.Set;

class Solution {
    private Set<Item> selectedItems;
    private long totalProfit;
    private long totalWeight;

    public Solution() {
        this.selectedItems = new HashSet<>();
        this.totalProfit = 0;
        this.totalWeight = 0;
    }

    public void addItem(Item item) {
        selectedItems.add(item);
        totalProfit += item.profit();
        totalWeight += item.weight();
    }

    public long getTotalProfit() {
        return totalProfit;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public boolean isFeasible(long capacity) {
        return totalWeight <= capacity;
    }

    public void print() {
        System.out.println("Selected Items: " + selectedItems);
        System.out.println("Total Profit: " + totalProfit);
        System.out.println("Total Weight: " + totalWeight);
    }
}
