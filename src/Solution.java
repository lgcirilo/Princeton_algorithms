class Solution {

    public int minTimeToVisitAllPoints(int[][] points) {
        int moves = 0;
        for (int i = 1; i < points.length; i++) {
            int x = Math.abs(points[i - 1][0] - points[i][0]);
            int y = Math.abs(points[i - 1][1] - points[i][1]);
            moves = moves + Math.max(x,y);
        }
        return moves;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] ps = {{1,1},{3,4},{-1,0}};
        System.out.println(s.minTimeToVisitAllPoints(ps));
    }

}

////////////////////////////////NAIVE SOLUTION///////////////////////////////
//    public int minTimeToVisitAllPoints(int[][] points) {
//        int moves = 0;
//        boolean moved = false;
//        for (int i = 1; i < points.length; i++) {
//            int x1 = points[i - 1][0];
//            int y1 = points[i - 1][1];
//            int x2 = points[i][0];
//            int y2 = points[i][1];
//            while (x1 != x2 || y1 != y2) {
//                if (x1 != x2) {
//                    if (x1 < x2) {
//                        x1 += 1;
//                    } else {
//                        x1 -= 1;
//                    }
//                    moved = true;
//                }
//                if (y1 != y2) {
//                    if (y1 < y2) {
//                        y1 += 1;
//                    } else {
//                        y1 -= 1;
//                    }
//                    moved = true;
//                }
//                if (moved == true) {
//                    moves++;
//                    moved = false;
//                }
//            }
//        }
//        return moves;
//    }
//
//    public static void main(String[] args) {
//        Solution s = new Solution();
//        int[][] ps = {{1,1},{3,4},{-1,0}};
//        System.out.println(s.minTimeToVisitAllPoints(ps));
//    }