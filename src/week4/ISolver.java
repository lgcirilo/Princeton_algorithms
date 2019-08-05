package week4;

public interface ISolver {

    boolean isSolvable();

    int moves();

    Iterable<Board> solution();

}
