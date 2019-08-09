package week4;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;

public class Solver {

    private Board previous = null;
    private int moves = 0;
    private MinPQ<Board> boardMinPQ = new MinPQ<>(new BoardComparatorHamming());
//    private ArrayList<Board> solution = new ArrayList<>();
    private ArrayList<Board> boardsOnPreviousPass = new ArrayList<>();
//    private ArrayList<Board> temp = new ArrayList<>();
//    int[][] arr1 = {{1,0,3},{4,2,5},{7,8,6}};
//    int[][] arr2 = {{4,1,3},{0,2,5},{7,8,6}};
//    int[][] arr3 = {{2,4,0},{1,3,7},{6,8,5}};
//    int[][] arr4 = {{1,4,7},{2,5,8},{3,6,0}};
//    Board b1 = new Board(arr1);
//    Board b2 = new Board(arr2);
//    Board b3 = new Board(arr3);
//    Board b4 = new Board(arr4);

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        if (initial == null) {
            throw new IllegalArgumentException();
        }

        boardMinPQ.insert(initial);
//        boardsOnPreviousPass.add(initial);
        previous = boardMinPQ.delMin();
        System.out.println(previous.toString());


//        while (!foundGoal()) {
//            for (Board board : boardsOnPreviousPass) {
//                for (Board b : board.neighbors()) {
//                    temp.add(b);
//                }
//            }
//            for (Board board: temp) {
//                boardsOnPreviousPass.add(board);
//                boardMinPQ.insert(board);
//            }
//
//            temp = new ArrayList<>();
//        }




//        for (Board parentBoard: boardsOnPreviousPass) {
//            for (Board neighborBoard: parentBoard.neighbors())
//            boardMinPQ.insert(neighborBoard);
//        }
//
        for (Board board : boardMinPQ) {
            System.out.println(board);
        }


//        boardMinPQ.insert(b1);
//        boardMinPQ.insert(b3);
//        boardMinPQ.insert(b2);
//        boardMinPQ.insert(b4);
//
//        solution.add(boardMinPQ.delMin());
//        solution.add(boardMinPQ.delMin());
//        System.out.println(solution.get(0));
//        System.out.println(solution.get(1));


    }

    private boolean foundGoal() {
        for (Board board: boardsOnPreviousPass) {
            if (board.isGoal()) {return true;}
        }
        return false;
    }

    // is the initial board solvable? (see below){
    public boolean isSolvable(){
        return true;
    }

    // min number of moves to solve initial board
    public int moves(){
        return solution.size() - 1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
        return solution;
    }

    private class BoardComparatorHamming implements Comparator<Board> {
        public int compare(Board a, Board b) {
            return (a.hamming() + moves) - (b.hamming() + moves);
        }
    }

    private class BoardComparatorManhattan implements Comparator<Board> {
        public int compare(Board a, Board b) {
            return (a.manhattan() + moves) - (b.manhattan() + moves);
        }
    }


    public static void main(String[] args){

    }
}
