public interface IBoard {

    String toString();

    int dimension();

    int hamming();

    int manhattan();

    boolean isGoal();

    boolean equals(Object y);

    Iterable<Board> neighbors();

    Board twin();
}
