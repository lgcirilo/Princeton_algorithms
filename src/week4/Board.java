package week4;

import java.util.ArrayList;
import java.util.Arrays;

class Board {

    private int[][] board;
    private int dimension;

    public Board(int[][] tiles) {

        this.board = tiles.clone();
        this.dimension = this.board.length;

    } // ok

    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append(this.dimension);
        str.append("\n");
        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                str.append(this.board[i][j] + " ");
            }
            str.append("\n");
        }

        return str.toString();

    } // ok

    public int dimension() {
        return this.dimension;
    } // ok

    public int hamming() {
        int hamming = 0;
        int orderedTile = 1;

        for(int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.board[i][j] !=0 && this.board[i][j] != orderedTile) {
                    hamming++;
                }

                if ((i + 1) * (j + 1)  < (dimension * dimension)) {
                    orderedTile++;
                } else {
                    orderedTile = 0;
                }
            }
        }

        return hamming;
    } // ok

    public int manhattan() {

        int tile;
        int manhattan = 0;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.board[i][j] != 0) {
                    tile = this.board[i][j];
                    int x = tile % dimension == 0 ? (int) Math.floor(tile / dimension) - 1 : (int) Math.floor(tile / dimension);
                    int y = tile % dimension > 0 ? (tile % dimension) - 1 : dimension -1;
                    int d = Math.abs(x-i) + Math.abs(y-j);
                    System.out.println("distance from (" + i + "," + j + ") " + "to " + "(" + x + "," + y + ")");
                    manhattan += Math.abs(x-i) + Math.abs(y-j);
                }
            }
        }
        return manhattan;
    } // giving wrong result for myBoardHammingManhattan. Should be 33.

    public boolean isGoal() { // testar de novo

        int orderedTile = 1;

        for(int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if ( ((i + 1) * (j + 1)  < (dimension * dimension) ) && (this.board[i][j] != orderedTile)  ) {
                    return false;
                }

                orderedTile++;
            }
        }

        return true;
    } // ok

    public boolean equals(Object y) {

        Board y1 = (Board) y;
        if(y1.dimension != this.dimension) {
            return false;
        } else {
            for(int i = 0; i < dimension; i++) {
                for(int j = 0; j < dimension; j++) {
                    if(y1.board[i][j] != this.board[i][j]) {
                        return false;
                    }
                }
            }
        }

        return true;

    } // ok

    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        return boards;
    } //not yet implemented

    public Board twin() {


        int[][] twin = new int[dimension][dimension];
        int x1 = (int) Math.floor((Math.random() * this.dimension));
        int y1 = (int) Math.floor((Math.random() * this.dimension));
        int x2;
        int y2;
        do {
            x2 = (int) Math.floor((Math.random() * this.dimension));
            y2 = (int) Math.floor((Math.random() * this.dimension));
        } while (x1 == x2 && y1 == y2);



        ///// there's stuff left to do here
        ///// in order to clone an Object we must implement the java.lang.Cloneable interface
        int[][] temp = {{1,2},{1,2}}; // this is just here so that there are no errors. remove when finished.
        return new Board(temp);

    } //not yet implemented

    private void message(int i, int j, int x, int y, int expectedX, int expectedY) {
        System.out.println("failed for tile " + this.board[i][j] + ". Is (" + x + "," + y + "). " +
                        "Should be (" + expectedX + "," + expectedY + ")");
    }

    public static void main(String[] args) {

        int[][] boardArray = {{1,3,5},{4,6,2},{7,8,9}};
        int[][] boardArray2 = {{1,3,5},{4,6,2},{7,8,9}};
        int[][] goalBoard = {{1,2,3},{4,5,6},{7,8,0}};
        int[][] hammingManhattanTest = {{8,1,3,9},{10,4,0,2},{7,6,11,5},{14,12,15,13}};
        Board myBoard = new Board(boardArray);
//        Board myBoard2 = new Board(boardArray2);
//        Board myBoardGoal = new Board(goalBoard);
        Board myBoardHammingManhattan = new Board(hammingManhattanTest);
//        System.out.println(myBoardHammingManhattan.manhattanTest());
        System.out.println(myBoardHammingManhattan.toString());

//        System.out.println("myBoardGoal.isGoal(): " + myBoardGoal.isGoal());
//        System.out.println("myBoardHamming.toString():\n" + myBoardHammingManhattan.toString());
//        System.out.println("Hamming distance: " + myBoardHammingManhattan.hamming());
        System.out.println("Manhattan distance: " + myBoardHammingManhattan.manhattan());
//        System.out.println("myBoard.toString():\n" + myBoard.toString());
//        System.out.println("myBoard.equals(myBoard2): " + myBoard.equals(myBoard2));
//        for (int i = 0; i < 20; i++) {
//            System.out.println(myBoardHammingManhattan.twin().toString());
//        }
    }
}
