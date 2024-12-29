import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class KnapsackProblem {
    private List<Item> items;
    private long capacity;

    public KnapsackProblem(List<Item> items, long capacity) {
        this.items = items;
        this.capacity = capacity;
    }

    public List<Item> getItems() {
        return items;
    }

    public long getCapacity() {
        return capacity;
    }

    public static KnapsackProblem parseInput(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int n = Integer.parseInt(lines.get(0));
        List<Item> items = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            String[] parts = lines.get(i).split(" ");
            long id = Long.parseLong(parts[0]);
            long profit = Long.parseLong(parts[1]);
            long weight = Long.parseLong(parts[2]);
            items.add(new Item(id, profit, weight));
        }

        long capacity = Long.parseLong(lines.get(n + 1));
        return new KnapsackProblem(items, capacity);
    }

    public static Map<String, KnapsackProblem> parseProblemInstances(String baseDir) throws IOException {
        Map<String, KnapsackProblem> problemInstances = new HashMap<>();
        File baseDirectory = new File(baseDir);

        if (baseDirectory.isDirectory()) {
            for (File folder : baseDirectory.listFiles()) {
                if (folder.isDirectory() && folder.getName().startsWith("n_")) {
                    File testFile = new File(folder, "test.in");

                    if (testFile.exists()) {
                        KnapsackProblem problem = parseInput(testFile.getAbsolutePath());
                        problemInstances.put(folder.getName(), problem);
                    }
                }
            }
        }

        return problemInstances;
    }

    public boolean isTrivial() {
        long totalWeight = items.stream().mapToLong(Item::weight).sum();
        return totalWeight <= capacity;
    }

    public static void main(String[] args) throws IOException {
        String baseDir = "E:\\Users\\YOUR_USERNAME\\Desktop\\problemInstances"; // Update to your actual directory
        Map<String, KnapsackProblem> problems = parseProblemInstances(baseDir);

        // Check if there are any problem instances
        if (problems.isEmpty()) {
            System.out.println("No problem instances found in the specified directory: " + baseDir);
            System.out.println("Ensure the directory exists and contains the correct structure (e.g., subdirectories starting with 'n_' and test.in files).");
            return;
        }

        // Select a random problem instance
        List<String> keys = new ArrayList<>(problems.keySet());
        Random random = new Random();
        String randomKey = keys.get(random.nextInt(keys.size()));
        KnapsackProblem problem = problems.get(randomKey);

        System.out.println("Selected Problem Instance: " + randomKey);

        KnapsackSolver solver = new KnapsackSolver(problem);
        Solution greedySolution = solver.generateGreedySolution();
        Solution bestRandomSolution = solver.findBestRandomSolution(1_000_000);

        System.out.println("Greedy Solution:");
        greedySolution.print();

        System.out.println("Best Random Solution:");
        bestRandomSolution.print();

        // Compare results
        if (bestRandomSolution.getTotalProfit() > greedySolution.getTotalProfit()) {
            System.out.println("Random solution outperformed the greedy solution.");
        } else {
            System.out.println("Greedy solution is better or equal to the random solutions.");
        }
    }
}