// TODO - move to separate project. Not part of princeton's algorithms. Check if part of an online judge.

import java.util.Arrays;

public class Median {
    public static void main(String[] args) {
        int median;
        int[] arr = {3,5,24,15,63,566,45,2,3455,234553,645,7687,4687};
//        int[] arr = {5,6,3,1,2};
        Arrays.sort(arr);
        if (arr.length % 2 == 0) {
            median = (arr[arr.length/2] + arr[(arr.length/2) + 1]) / 2;
        } else {
            median = arr[(int) Math.floor(arr.length / 2)];
        }
        System.out.println(median);
    }
}
