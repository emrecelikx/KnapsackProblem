import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class KnapsackSolver {
    private KnapsackProblem problem;

    public KnapsackSolver(KnapsackProblem problem) {
        this.problem = problem;
    }

    public Solution generateRandomSolution() {
        Solution solution = new Solution();
        List<Item> items = new ArrayList<>(problem.getItems());
        Collections.shuffle(items);

        for (Item item : items) {
            if (solution.getTotalWeight() + item.weight() <= problem.getCapacity()) {
                solution.addItem(item);
            }
        }
        return solution;
    }

    public Solution generateGreedySolution() {
        List<Item> items = new ArrayList<>(problem.getItems());
        items.sort((a, b) -> Double.compare(
                (double) b.profit() / b.weight(),
                (double) a.profit() / a.weight()
        ));

        Solution solution = new Solution();
        for (Item item : items) {
            if (solution.getTotalWeight() + item.weight() <= problem.getCapacity()) {
                solution.addItem(item);
            }
        }
        return solution;
    }

    public Solution findBestRandomSolution(int iterations) {
        long maxProfit = Long.MIN_VALUE;
        Solution bestSolution = null;

        for (int i = 0; i < iterations; i++) {
            Solution randomSolution = generateRandomSolution();
            if (randomSolution.getTotalProfit() > maxProfit) {
                maxProfit = randomSolution.getTotalProfit();
                bestSolution = randomSolution;
            }
        }
        return bestSolution;
    }
}