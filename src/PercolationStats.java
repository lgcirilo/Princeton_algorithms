/**
 * Created by cyfa on 26/12/2017.
 */

import edu.princeton.cs.algs4.*;


public class PercolationStats {

    double[] openedSitesPercentagePerTrial;
    int gridSize;
    int numTrials;

    //TODO - not yet implemented
    public PercolationStats(int n, int trials) {   // perform trials independent experiments on an n-by-n grid
        gridSize = n;
        numTrials = trials;
        openedSitesPercentagePerTrial = new double[trials];

        for (int i = 0; i < numTrials; i++) {
            Percolation p = new Percolation(gridSize);
            while (!p.percolates()) {
                int rowToOpen = (int) Math.ceil(StdRandom.random() * gridSize);
                int colToOpen = (int) Math.ceil(StdRandom.random() * gridSize);
                p.open(rowToOpen, colToOpen);
            }
            openedSitesPercentagePerTrial[i] = p.numberOfOpenSites() / Math.pow(gridSize, 2);
        }
    }


    //TODO - not yet implemented
    public double mean() {  // sample mean of percolation threshold
        return StdStats.mean(openedSitesPercentagePerTrial);
    }


    //TODO - not yet implemented
    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(openedSitesPercentagePerTrial);
    }

    //TODO - not yet implemented
    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return mean() - (1.960 * (stddev() / Math.sqrt(numTrials)));
    }


    //TODO - not yet implemented
    public double confidenceHi() { // high endpoint of 95% confidence interval
        return mean() + (1.960 * (stddev() / Math.sqrt(numTrials)));
    }



    public static void main(String[] args) {     // test client
        PercolationStats pS = new PercolationStats(2, 100000);
        System.out.println("mean                    = " + pS.mean());
        System.out.println("stddev                  = " + pS.stddev());
        System.out.println("95% confidence interval = [" + pS.confidenceLo() + ", " + pS.confidenceHi() + "]");
    }
}
