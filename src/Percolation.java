import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF unionObject;
    private boolean[] opened;
    private int n; // row/column size
    private int virtualTop;
    private int virtualBottom;
    private int openedSites;

    // Create n-by-n grid, with all sites blocked, plus two extra virtual elements.
    public Percolation(int n) {
        if (n <= 0){
            throw new IllegalArgumentException("Size must be greater than 0");
        } else {
            this.n = n;
            unionObject = new WeightedQuickUnionUF(n * n + 2);
            virtualTop = n * n;
            virtualBottom = n * n + 1;
            opened = new boolean[n * n + 2];
            opened[virtualTop] = true;
            opened[virtualBottom] = true;
        }
    }

    // Open site (row, col) if it is not open already.
    public void open(int row, int col) {

        int index = xyTo1dIndex(row, col);
        if (!opened[index]) {
            // Checks if sites belongs to top or bottom rows. If so connect to corresponding virtual site.
            opened[index] = true;
            openedSites++;
            if (row == 1) {
                if (!unionObject.connected(index, virtualTop)) {
                    unionObject.union(index, virtualTop);
                }
            }
            if (row == n) {
                if (!unionObject.connected(index, virtualBottom)) {
                    unionObject.union(index, virtualBottom);
                }
            }
            // Check neighbouring sites (top, bottom, left, right) and connect if opened.
            // Checking (row, col - 1)
            if (row >= 1 && row <= n && col - 1 >= 1 && col - 1 <= n) {
                int neighborIndex = xyTo1dIndex(row, col -1);

                if (isOpen(row, col -1)) {
                    if (!unionObject.connected(index, neighborIndex)) {
                        unionObject.union(index, neighborIndex);
                    }
                }
            }

            // Checking (row, col + 1)
            if (row >= 1 && row <= n && col + 1 >= 1 && col + 1 <= n) {

                int neighborIndex = xyTo1dIndex(row, col + 1);
                if (isOpen(row, col + 1)) {
                    if (!unionObject.connected(index, neighborIndex)) {
                        unionObject.union(index, neighborIndex);
                    }
                }
            }

            // Checking (row - 1, col)
            if (row - 1 >= 1 && row - 1 <= n && col >= 1 && col <= n) {

                int neighborIndex = xyTo1dIndex(row - 1, col);
                if (isOpen(row - 1, col)) {
                    if (!unionObject.connected(index, neighborIndex)) {
                        unionObject.union(index, neighborIndex);
                    }
                }
            }

            // Checking (row + 1 , col)
            if (row + 1 >= 1 && row + 1 <= n && col >= 1 && col <= n) {

                int neighborIndex = xyTo1dIndex(row + 1, col);
                if (isOpen(row + 1, col)) {
                    if (!unionObject.connected(index, neighborIndex)) {
                        unionObject.union(index, neighborIndex);
                    }
                }
            }
        }
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = xyTo1dIndex(row, col);
        return opened[index];
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
        int OneDCoordinate = xyTo1dIndex(row,col);
        if ( isOpen(row, col) && unionObject.connected(OneDCoordinate, virtualTop)) return true;
        return false;
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return openedSites;
    }

    // Does the system percolate?
    public boolean percolates() {
        return unionObject.connected(virtualBottom, virtualTop);
    }

    // Converts from x,y coordinates to WeightedQuickUnionUF 1d array index.
    private int xyTo1dIndex(int row, int col) {
        int index;
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("index (" + row + "," + col + ") is not between (1,1) and " + "(" + n + "," + n + ")");
        } else {
            index = (this.n * (row - 1)) + (col - 1);
        }
        return index;
    }

    public static void main(String[] args) {
        // Main method not needed for assignment
    }
}
