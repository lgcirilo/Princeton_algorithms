import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] openedSitesPercentagePerTrial;
    private int gridSize;
    private int numTrials;

    // Constructor. Performs independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        gridSize = n;
        numTrials = trials;
        openedSitesPercentagePerTrial = new double[trials];

        for (int i = 0; i < numTrials; i++) {
            Percolation p = new Percolation(gridSize);
            while (!p.percolates()) {
                int rowToOpen = (int) Math.ceil(StdRandom.uniform() * gridSize);
                int colToOpen = (int) Math.ceil(StdRandom.uniform() * gridSize);
                p.open(rowToOpen, colToOpen);
            }
            openedSitesPercentagePerTrial[i] = p.numberOfOpenSites() / Math.pow(gridSize, 2);
        }
    }

    public double mean() {  // Sample mean of percolation threshold
        return StdStats.mean(openedSitesPercentagePerTrial);
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(openedSitesPercentagePerTrial);
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return mean() - (1.960 * (stddev() / Math.sqrt(numTrials)));
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return mean() + (1.960 * (stddev() / Math.sqrt(numTrials)));
    }

    public static void main(String[] args) {
        int grid = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats pS = new PercolationStats(grid, trials);
        System.out.println("mean                    = " + pS.mean());
        System.out.println("stddev                  = " + pS.stddev());
        System.out.println("95% confidence interval = [" + pS.confidenceLo() + ", " + pS.confidenceHi() + "]");
    }
}
