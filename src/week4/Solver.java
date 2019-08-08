package week4;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.Comparator;

public class Solver {

    private int moves = 0;
    private MinPQ<Board> boardMinPQ = new MinPQ<>(new BoardComparatorHamming());
    private ArrayList<Board> solution = new ArrayList<>();


    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){

        if (initial == null) {
            throw new IllegalArgumentException();
        }

        boardMinPQ.insert(initial);

        for (Board board: initial.neighbors()) {
            boardMinPQ.insert(board);
        }

        for (Board board: boardMinPQ) {
            System.out.println(board);
        }

        // this is just to test the hamming and manhattan comparators. Delete after done.
        int[][] boardA = {{1,0,3},{4,2,5},{7,8,6}};
        int[][] boardB = {{4,1,3},{0,2,5},{7,8,6}};
        Board a = new Board(boardA);
        Board b = new Board(boardB);
        System.out.println(new BoardComparatorHamming().compare(a, b));
        System.out.println(new BoardComparatorManhattan().compare(a, b));
        // this is just to test the hamming and manhattan comparators Delete after done.

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
