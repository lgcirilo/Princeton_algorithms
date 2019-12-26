import java.util.ArrayList;

public class Solver {

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
    }

    public boolean isSolvable() {            // is the initial board solvable?
        return true; //dummy value
    }

    public int moves() {                     // min number of moves to solve initial board; -1 if u
        return 1; //dummy value
    }

    public Iterable<Board> solution() {      // sequence of boards in a shortest solution; null if unsolvable
        return new ArrayList<>(); //dummy value
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {}
}
