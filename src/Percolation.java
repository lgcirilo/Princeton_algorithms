import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF unionObject;
    private boolean[] opened;
    private int n; //row/column size
    private int virtualTop;
    private int virtualBottom;

    //Create n-by-n grid, with all sites blocked, plus two extra virtual elements.
    public Percolation (int n) {
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

    //Open site (row, col) if it is not open already.
    public void open(int row, int col) {

        int index = xyTo1dIndex(row, col);
        if (!opened[index]) {
            //checks if sites belongs to top or bottom rows. If so connect to corresponding virtual site.
            opened[index] = true;
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
            //check neighbouring sites (top, bottom, left, right) and connect if opened.
            //checking (row, col - 1)
            if (row >= 1 && row <= n && col - 1 >= 1 && col - 1 <= n) {
                int neighborIndex = xyTo1dIndex(row, col -1);

                if (isOpen(row, col -1)) {
                    if (!unionObject.connected(index, neighborIndex)) {
                        unionObject.union(index, neighborIndex);
                    }
                }
            }

            //checkking (row, col + 1)
            if (row >= 1 && row <= n && col + 1 >= 1 && col + 1 <= n) {

                int neighborIndex = xyTo1dIndex(row, col + 1);
                if (isOpen(row, col + 1)) {
                    if (!unionObject.connected(index, neighborIndex)) {
                        unionObject.union(index, neighborIndex);
                    }
                }
            }

            //chekcking (row - 1, col)
            if (row - 1 >= 1 && row - 1 <= n && col >= 1 && col <= n) {

                int neighborIndex = xyTo1dIndex(row - 1, col);
                if (isOpen(row - 1, col)) {
                    if (!unionObject.connected(index, neighborIndex)) {
                        unionObject.union(index, neighborIndex);
                    }
                }
            }

            //checking (row + 1 , col)
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

    //Is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = xyTo1dIndex(row, col);
        return opened[index];
    }

    //Is site (row, col) full?
    public boolean isFull(int row, int col) {
        int OneDCoordinate = xyTo1dIndex(row,col);
        for (int i = 0; i < n; i++) { //loops through top row
            if ( unionObject.connected(OneDCoordinate, i)) return true;
        }
        return false;
    }

    //Number of open sites.
    public int numberOfOpenSites() {
        int result = 0;
        for (int i = 0; i < n*n; i++) {
            if (opened[i]) result++;
        }

        return result;
    }

    //Does the system percolate?
    public boolean percolates() {
        for (int i = 1; i <= n; i++) { //loops through bottom row
            if (isFull(n,i)) { return true; }
        }
        return false;
    }

    //Converts from x,y coordinates to WeightedQuickUnionUF 1d array index.
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


    }
}
