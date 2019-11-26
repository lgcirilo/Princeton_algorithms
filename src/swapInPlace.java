public class swapInPlace {
    public static void swap(int a, int b) {
        System.out.println("a=" + a + ", b=" + b);

        a = a - b;
        b = a + b;
        a = b - a;

        System.out.println("after swap: a=" + a + ", b=" + b);
    }

    public static void main(String[] args) {
        swap(1,2);
        swap(3,24);
        swap(21,22);
        swap(14,26);
        swap(26,14);
        swap(-26,-3);
    }
}
