class LeetCodeSolutions {

    public int minTimeToVisitAllPoints(int[][] points) {
        int moves = 0;
        for (int i = 1; i < points.length; i++) {
            int x = Math.abs(points[i - 1][0] - points[i][0]);
            int y = Math.abs(points[i - 1][1] - points[i][1]);
            moves = moves + Math.max(x,y);
        }
        return moves;
    }

    public int balancedStringSplit(String s) {
        int r = 0;
        int l = 0;
        int strNum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'R') {
                r++;
            } else {
                l++;
            }

            if(r == l) {
                strNum++;
                r = 0;
                l = 0;
            }
        }

        return strNum;
    }

    public static void main(String[] args) {
        LeetCodeSolutions s = new LeetCodeSolutions();
        int[][] ps = {{1,1},{3,4},{-1,0}};
        String str = "RLRLRLRLRLLLLRRRRLLR";
        System.out.println(s.minTimeToVisitAllPoints(ps));
        System.out.println(s.balancedStringSplit(str));
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
//    public int balancedStringSplit(String s) {
//
//        Stack<Character> lStk = new Stack<>();
//        Stack<Character> rStk = new Stack<>();
//        char currentChar;
//        int strNum = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            currentChar = s.charAt(i);
//            if (currentChar == 'R') {
//                rStk.push(currentChar);
//            } else {
//                lStk.push(currentChar);
//            }
//
//            if(rStk.size() == lStk.size()) {
//                strNum++;
//                rStk = new Stack<>();
//                lStk = new Stack<>();
//            }
//        }
//
//        return strNum;
//
//    }
//
//    //same running time as naive but with one stack only
//    public int balancedStringSplit(String s) {
//
//        Stack<Character> stk = new Stack<>();
//        char currentChar;
//        int strNum = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            currentChar = s.charAt(i);
//            if (stk.size() > 0 && currentChar != stk.peek())  {
//                stk.pop();
//                if (stk.size() == 0) {
//                    strNum++;
//                }
//            } else {
//                stk.push(currentChar);
//            }
//        }
//
//        return strNum;
//
//    }
//
//    //best running time. Using only primitives. No stacks.
//    public int balancedStringSplit(String s) {
//
//        int r = 0;
//        int l = 0;
//        int strNum = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == 'R') {
//                r++;
//            } else {
//                l++;
//            }
//
//            if(r == l) {
//                strNum++;
//                r = 0;
//                l = 0;
//            }
//        }
//
//        return strNum;
//    }