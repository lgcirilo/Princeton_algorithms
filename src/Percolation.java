/**
 * Created by cyfa on 26/12/2017.
 */

//Throw a java.lang.IllegalArgumentException if any argument to open(), isOpen(), or isFull()

import edu.princeton.cs.algs4.*;

public class Percolation extends WeightedQuickUnionUF{

    private boolean[] opened;
    private int n; //row/column size

    public Percolation (int n) { // create n-by-n grid, with all sites blocked, plus two extra virtual elements
        super(n * n + 2);
        if (n <= 0){
            throw new IllegalArgumentException("Size must be greater than 0");
        } else {
            this.n = n;
            opened = new boolean[n * n + 2];
            for (int i = 1; i <= n; i++) {
                int virtualTop = n * n;
                int virtualBottom = n * n + 1;
                int currentTopRowSite = xyTo1dIndex(1, i);
                int currentBottomRowSite = xyTo1dIndex(n, i);
                union(virtualTop, currentTopRowSite);
                union(virtualBottom, currentBottomRowSite);
            }
        }
    }

    //TODO - check for array out of bounds???
    public void open(int row, int col) { // open site (row, col) if it is not open already
//        System.out.println("abrindo (" + row + "," + col + ")");

        int index = xyTo1dIndex(row, col);
        if (!opened[index]) { //substituir condicao por metodo isOpen()
            opened[index] = true;
            //check neighbouring sites (top, bottom, left, right) and connect if opened.
            //checking (row, col - 1)
            if (row >= 1 && row <= n && col - 1 >= 1 && col - 1 <= n) {
                int neighborIndex = xyTo1dIndex(row, col -1);

                if (isOpen(row, col -1)) {
                  if (!connected(index, neighborIndex)) {
                      union(index, neighborIndex);
                  }
                }
            }

            //checkking (row, col + 1)
            if (row >= 1 && row <= n && col + 1 >= 1 && col + 1 <= n) {

                int neighborIndex = xyTo1dIndex(row, col + 1);
                if (isOpen(row, col + 1)) {
                    if (!connected(index, neighborIndex)) {
                        union(index, neighborIndex);
                    }
                }
            }

            //chekcking (row - 1, col)
            if (row - 1 >= 1 && row - 1 <= n && col >= 1 && col <= n) {

                int neighborIndex = xyTo1dIndex(row - 1, col);
                if (isOpen(row - 1, col)) {
                    if (!connected(index, neighborIndex)) {
                        union(index, neighborIndex);
                    }
                }
            }

            //checking (row + 1 , col)
            if (row + 1 >= 1 && row + 1 <= n && col >= 1 && col <= n) {

                int neighborIndex = xyTo1dIndex(row + 1, col);
                if (isOpen(row + 1, col)) {
                    if (!connected(index, neighborIndex)) {
                        union(index, neighborIndex);
                    }
                }
            }
        }
//        if (isFull(row,col)) {
//            System.out.println("(" + row + "," + col + ") IS FULL!");
//        }
    }


    public boolean isOpen(int row, int col) { // is site (row, col) open?
        int index = xyTo1dIndex(row, col);
        return opened[index];
    }


    //TODO - not yet implemented
    public boolean isFull(int row, int col) { // is site (row, col) full?
        int OneDCoordinate = xyTo1dIndex(row,col);
        for (int i = 0; i < n; i++) { //loops through top row
            if ( connected(OneDCoordinate, i)) return true;
        }
        return false;
    }


    public int numberOfOpenSites() {     // number of open sites
        int result = 0; // dummy value. check what to initialize it with in finished project
        for (int i = 0; i < n*n; i++) {
            if (opened[i]) result++;
        }

        return result;
    }


    //TODO - not yet implemented
    public boolean percolates() {      // does the system percolate?
        for (int i = 1; i <= n; i++) { //loops through bottom row
            if (isFull(n,i)) { return true; }
        }
        return false;
    }

    //accessory methods from now on. Should all be private.
    private int xyTo1dIndex(int row, int col) { // converts from x,y coordinates to WeightedQuickUnionUF 1d array index
        int index;
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("index (" + row + "," + col + ") is not between (1,1) and " + "(" + n + "," + n + ")");
        } else {
            index = (this.n * (row - 1)) + (col - 1);
        }
        return index;
    }

    // test client (optional)
    public static void main(String[] args) {
        //tests constructor
        int total = 0;
        int numTrials = 35;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < numTrials; i++) {
            int gridSize = 20;
            float temp = 0;
            Percolation p = new Percolation(gridSize);
            while (!p.percolates()) {
                int rowToOpen = (int) Math.ceil(Math.random() * gridSize);
                int colToOpen = (int) Math.ceil(Math.random() * gridSize);
                p.open(rowToOpen, colToOpen);
            }
//            System.out.println("open site count: " + p.numberOfOpenSites());
            total += p.numberOfOpenSites();
            temp += p.numberOfOpenSites();
            System.out.println("percentual de open sites: " + (temp / (gridSize*gridSize)));

        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("elapsedTime " + (float)elapsedTime / 1000 + "s");
//        System.out.println("elapsedTime " + elapsedTime + "ms");
        System.out.println("media: " + (total / numTrials));



//        In in = new In(args[0]);      // input file
//        int N = in.readInt();         // N-by-N percolation system
//        Percolation p = new Percolation(N);
//
//        while (!p.percolates()) {
//            int i = in.readInt();
//            int j = in.readInt();
//            p.open(i, j);
//        }
//        System.out.println("open site count: " + p.numberOfOpenSites());








//        int gridSize = 5;
//        Percolation p = new Percolation(gridSize);
//        while (!p.percolates()) {
//            int rowToOpen = (int) Math.ceil(Math.random() * gridSize);
//            int colToOpen = (int) Math.ceil(Math.random() * gridSize);
//            p.open(rowToOpen, colToOpen);
//        }
//        System.out.println("open site count: " + p.numberOfOpenSites());


//        p.size();
        //tests xyTo1dIndex(int row, int col)
//        System.out.println(p.xyTo1dIndex(1,1));
//        System.out.println(p.xyTo1dIndex(10,10));
//        System.out.println(p.xyTo1dIndex(2,2));
//        System.out.println(p.xyTo1dIndex(5,3));
//        tests open(int row, int col)
//        p.open(1,1);
//        p.open(10,10);
//        p.open(2,2);
//        p.open(1,2);
//        p.open(5, 3);
//        p.open(0,0);
//        p.open(0,1);
//        p.open(1,0);
//        p.open(11,1);
//        p.open(1,11);
//        p.open(1,1);
//        p.open(1,2);
//        p.open(2,2);
//        p.open(3,2);
//        p.open(4,2);
//        p.open(1, 1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(2,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(3,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(4,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(5,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(6,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(7,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(8,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(9,1);
//        System.out.println("percolates? " + p.percolates());
//        p.open(10,1);
//        System.out.println("percolates? " + p.percolates());



//        System.out.println("open site count: " + p.numberOfOpenSites());

//        tests isOpen(int row, int col)
//        System.out.println(p.isOpen(1,1));
//        System.out.println(p.isOpen(10,10));
//        System.out.println(p.isOpen(5,3));
//        System.out.println(p.isOpen(3,4));
//        System.out.println(p.isOpen(9,8));
        //1dcoords
//        int first = p.xyTo1dIndex(1,1);
//        int second = p.xyTo1dIndex(4,2);
//        int first = 101;
//        int second = 100;
//        System.out.println("pontos conectados? " + p.connected(first,second));
//        System.out.println("isFull?: " + p.isFull(6,1));
//        System.out.println("isFull?: " + p.isFull(5,5));


    }
}
