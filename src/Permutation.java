
import edu.princeton.cs.algs4.StdIn;

/**
 * Created by cyfa on 13/08/2018.
 */
public class Permutation {

    public Permutation() {

    }
    public static void main(String[] args) {
        int k;
        RandomizedQueue<String> myRD = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
         String value = StdIn.readString();
         myRD.enqueue(value);
        }
        if (args.length == 1) {
            k = Integer.parseInt(args[0]);
            for (int i = 0; i < k; i++) {
                System.out.println(myRD.dequeue());
            }
        } else {
            throw new IllegalArgumentException("Wrong number of arguments.");
        }


    }
}
