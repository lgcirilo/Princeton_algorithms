import java.util.ArrayList;

public class Solver {
    public Solver(Board initial) {           // find a solution to the initial board (using the A* algorithm)
    }
    public boolean isSolvable() {            // is the initial board solvable?
        return true; //dummy value
    }
    public int moves() {                     // min number of moves to solve initial board; -1 if unsolvable
        return 1; //dummy value
    }
    public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if unsolvable
        return new ArrayList<>(); //dummy value
    }
    public static void main(String[] args) {} // solve a slider puzzle (given below)
}
