class Solution {

    public int[] countBits(int num) {
        int[] result = new int[num +1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= num; i++) {
            String str = Integer.toBinaryString(i);
            sb.append("number: " + num + ", ");
            sb.append("binary representation: " + str + ", ");
            sb.append("bit count: " + Integer.bitCount(i) + "\n");
        }
        System.out.println(sb);
        return result;

    }
    public static void main(String[] args) {
        Solution s = new Solution();
        s.countBits(2);
    }

}
