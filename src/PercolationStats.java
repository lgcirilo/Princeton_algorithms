import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StopwatchCPU;

public class PercolationStats {

    private double[] openedSitesPercentagePerTrial;
    private int gridSize;
    private int numTrials;
    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;
    private double temp;
    private double totalSites;
    public static final double confidence95 = 1.960;

    //Constructor. Performs independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <=0 || trials <= 0) {
            throw new IllegalArgumentException("grid size and number of trials cannot be smaller than one");
        } else {
            gridSize = n;
            totalSites = Math.pow(gridSize, 2);
            numTrials = trials;
            openedSitesPercentagePerTrial = new double[trials];

            for (int i = 0; i < numTrials; i++) {
                Percolation p = new Percolation(gridSize);
                while (!p.percolates()) {
                    int rowToOpen = (int) Math.ceil(StdRandom.uniform() * gridSize);
                    int colToOpen = (int) Math.ceil(StdRandom.uniform() * gridSize);
                    p.open(rowToOpen, colToOpen);
                }
                openedSitesPercentagePerTrial[i] = p.numberOfOpenSites() / totalSites;
            }
            mean = StdStats.mean(openedSitesPercentagePerTrial);
            stddev = StdStats.stddev(openedSitesPercentagePerTrial);
            temp = stddev / Math.sqrt(numTrials);
            confidenceLo = mean - (confidence95 * temp);
            confidenceHi = mean + (confidence95 * temp);
        }
    }

    public double mean() {  // sample mean of percolation threshold
        return mean;
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return confidenceLo;
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return confidenceHi;
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
