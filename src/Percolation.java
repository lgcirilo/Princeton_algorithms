/**
 * Created by cyfa on 26/12/2017.
 */

import edu.princeton.cs.algs4.*;

public class Percolation extends WeightedQuickUnionUF{

    boolean[] opened;

    public Percolation (int n) { // create n-by-n grid, with all sites blocked
        super(n*n);
        opened = new boolean[n*n];
        for (int i = 0; i < n*n; i++) {
                opened[i] = false;
        }
    }

    //TODO - check for array out of bounds
    public void open(int row, int col) { // open site (row, col) if it is not open already
        int index = xyTo1dIndex(row, col);
        if (!opened[index]) {
            opened[index] = true;
        }
    }


    public boolean isOpen(int row, int col) { // is site (row, col) open?
        int index = xyTo1dIndex(row, col);
        return opened[index];
    }


    //TODO - not yet implemented
    public boolean isFull(int row, int col) { // is site (row, col) full?
        boolean result = true; // dummy value. check what to initialize it with in finished project

        return result;
    }


    public int numberOfOpenSites() {     // number of open sites
        int result = 0; // dummy value. check what to initialize it with in finished project
        for (int i = 0; i < count(); i++) {
            if (opened[i]) result++;
        }

        return result;
    }


    //TODO - not yet implemented
    public boolean percolates() {      // does the system percolate?
        boolean result;
        int rowLength = (int)Math.sqrt(this.count());
        int startIndex = count() - rowLength;


        return result;
    }


    private int xyTo1dIndex(int row, int col) { // converts from x,y coordinates to WeightedQuickUnionUF 1d array index
        int index;
        int rowLength = (int)Math.sqrt(this.count());
        index = (rowLength * (row - 1)) + (col - 1);
        return index;

    }

    // test client (optional)
    public static void main(String[] args) {
        //tests constructor
        Percolation p = new Percolation(10);
        //tests xyTo1dIndex(int row, int col)
        System.out.println(p.xyTo1dIndex(1,1));
        System.out.println(p.xyTo1dIndex(10,10));
        System.out.println(p.xyTo1dIndex(2,2));
        System.out.println(p.xyTo1dIndex(5,3));
        //tests open(int row, int col)
        p.open(1,1);
        p.open(10,10);
        p.open(2,2);
        p.open(5, 3);
        System.out.println(p.numberOfOpenSites());
        //tests isOpen(int row, int col)
        System.out.println(p.isOpen(1,1));
        System.out.println(p.isOpen(10,10));
        System.out.println(p.isOpen(5,3));
        System.out.println(p.isOpen(3,4));
        System.out.println(p.isOpen(9,8));

    }
}
