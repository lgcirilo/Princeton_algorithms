package week4;

import java.util.Arrays;

class Board {

    private int[][] board;
    private int dimension;
    private int[][] goal;

    public Board(int[][] tiles) {

        this.board = tiles.clone();
        this.dimension = this.board.length;
        this.getGoal();

    }

    private void getGoal() {
        this.goal = new int[dimension][dimension];
        int tile = 1;

        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                this.goal[i][j] = tile;
                tile++;
            }
        }

        this.goal[dimension - 1][dimension - 1] = 0;
    }

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

    }

    public int dimension() {
        return this.dimension;
    }

    public int hamming() {
        return 2; //not yet implemented
    }

    public int manhattan() {
        return 2; //not yet implemented
    }

    public boolean isGoal() { // wrong
        if(this.board.equals(this.goal)) {
            return true;
        }
        return false;
    }

    public boolean equals(Object y) {

        if(y.dimension != this.dimension) {
            return false;
        } else {
            for(int i = 0; i < dimension; i++) {
                for(int j = 0; j < dimension; j++) {
                    if(this.board[i][j] != this.board[i][j]) {
                        return false;
                    }
                }
            }
        }

        return true;

    }

//    public Iterable<week4.Board> neighbors() {
//
//    }

    // a board that is obtained by exchanging any pair of tiles
//    public Board twin() {
//        return new Board();
//    }

    public static void main(String[] args) {

        int[][] boardArray = {{1,3,5},{4,2,6},{7,8,9}};
        int[][] boardArray2 = {{1,3,5},{4,2,6},{7,8,9}};
        int[][] goalBoard = {{1,2,3},{4,5,6},{7,8,0}};
        Board myBoard = new Board(boardArray);
        Board myBoard2 = new Board(boardArray2);
        Board myBoard3 = new Board(goalBoard);
        System.out.println(myBoard3.isGoal());
//        System.out.println(myBoard.toString());
//        System.out.println(myBoard.equals(myBoard2));
    }
}
