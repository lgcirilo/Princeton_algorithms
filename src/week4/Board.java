package week4;

import java.util.ArrayList;

class Board {

    private int[][] board;
    private int dimension;

    public Board(int[][] tiles) {

        this.board = tiles.clone();
        this.dimension = this.board.length;

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
    }

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
                    manhattan += Math.abs(x-i) + Math.abs(y-j);
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() {

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
    }

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

    }

    public Iterable<Board> neighbors() {
        ArrayList<Board> boards = new ArrayList<>();
        // Free tile coordinates
        int x0 = -10;
        int y0 = -10;
        // Free tile coordinates
        Board tempBoard;

        // Gets empty tile position
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (this.board[i][j] == 0) {
                    x0 = i;
                    y0 = j;
                }
            }
        }

        // Defines neighbors
        if (x0 - 1 >= 0) { // x-1, y
            int[][] seedArray = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    seedArray[i][j] = this.board[i][j];
                }
            }
            System.out.println("entrou");
            seedArray[x0][y0] = seedArray[x0 - 1][y0];
            seedArray[x0 - 1][y0] = 0;
            boards.add(new Board(seedArray));

        }

        if (x0 + 1 < dimension) { // x + 1, y
            int[][] seedArray = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    seedArray[i][j] = this.board[i][j];
                }
            }

            seedArray[x0][y0] = seedArray[x0 + 1][y0];
            seedArray[x0 + 1][y0] = 0;
            boards.add(new Board(seedArray));
        }

        if (y0 + 1 < dimension) { // x, y + 1
            int[][] seedArray = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    seedArray[i][j] = this.board[i][j];
                }
            }

            seedArray[x0][y0] = seedArray[x0][y0 + 1];
            seedArray[x0][y0 + 1] = 0;
            boards.add(new Board(seedArray));
        }

        if (y0 - 1 >= 0) { // x, y - 1
            int[][] seedArray = new int[dimension][dimension];
            for (int i = 0; i < dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    seedArray[i][j] = this.board[i][j];
                }
            }

            seedArray[x0][y0] = seedArray[x0][y0 - 1];
            seedArray[x0][y0 - 1] = 0;
            boards.add(new Board(seedArray));
        }

        for (Board board: boards) {
            System.out.println(board.toString());
        }

        return boards;
    }

    public Board twin() {

        int[][] twin = new int[dimension][dimension];
        int temp;
        int x1;
        int y1;
        int x2;
        int y2;

        // chooses a pair of random board tiles
        x1 = (int) Math.floor((Math.random() * this.dimension));
        y1 = (int) Math.floor((Math.random() * this.dimension));
        do {
            x2 = (int) Math.floor((Math.random() * this.dimension));
            y2 = (int) Math.floor((Math.random() * this.dimension));
        } while (x1 == x2 && y1 == y2);

        // clones a board. refactor to use clone method from Object.
        // In order to clone an Object we must implement the java.lang.Cloneable interface
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                twin[i][j] = this.board[i][j];
            }
        }

        // exchanges the two chosen tiles
        temp = twin[x1][y1];
        twin[x1][y1] = twin[x2][y2];
        twin[x2][y2] = temp;

        return new Board(twin);

    }


    public static void main(String[] args) {

        int[][] boardArray = {{1,3,5},{4,6,2},{7,8,0}};
        int[][] boardArray2 = {{1,3,5},{0,6,2},{7,8,4}};
        int[][] goalBoard = {{1,2,3},{4,5,6},{7,8,0}};
        int[][] goalBoard4By4 = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        int[][] hammingManhattanTest = {{8,1,3,9},{10,4,0,2},{7,6,11,5},{14,12,15,13}};
        Board myBoard = new Board(boardArray);
        Board myBoard2 = new Board(boardArray2);
        Board myBoardGoal = new Board(goalBoard);
        Board myGoalBoard4By4 = new Board(goalBoard4By4);
        Board myBoardHammingManhattan = new Board(hammingManhattanTest);
//        System.out.println(myBoardHammingManhattan.manhattanTest());
//        System.out.println(myBoardHammingManhattan.toString());

//        System.out.println("myBoard.isGoal(): " + myBoard.isGoal());
//        System.out.println("myBoard2.isGoal(): " + myBoard2.isGoal());
//        System.out.println("myBoardGoal.isGoal(): " + myBoardGoal.isGoal());
//        System.out.println("myBoardHammingManhattan.isGoal(): " + myBoardHammingManhattan.isGoal());
//        System.out.println("myGoalBoard4By4.isGoal(): " + myGoalBoard4By4.isGoal());
//        System.out.println("myBoardHamming.toString():\n" + myBoardHammingManhattan.toString());
//        System.out.println("Hamming distance: " + myBoardHammingManhattan.hamming());
          System.out.println("Manhattan distance: " + myBoardHammingManhattan.manhattan());
//        System.out.println("myBoard.toString():\n" + myBoard.toString());
//        System.out.println("myBoard.equals(myBoard2): " + myBoard.equals(myBoard2));
//        for (int i = 0; i < 20; i++) {
//            System.out.println("Manhattan distance: " + myBoardHammingManhattan.twin().manhattan());
//            System.out.println(myBoardHammingManhattan.twin().toString());
//        }
        System.out.println(myBoard.toString());
        myBoard.neighbors();
        System.out.println("======================");
        System.out.println(myBoard2.toString());
        myBoard2.neighbors();
        System.out.println("======================");
        System.out.println(myBoardHammingManhattan.toString());
        myBoardHammingManhattan.neighbors();
    }
}
