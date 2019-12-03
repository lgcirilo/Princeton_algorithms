class LeetCodeSolutions {

    // 1266. Minimum Time Visiting All Points - 27/11/2019
    public int minTimeToVisitAllPoints(int[][] points) {
        int moves = 0;
        for (int i = 1; i < points.length; i++) {
            int x = Math.abs(points[i - 1][0] - points[i][0]);
            int y = Math.abs(points[i - 1][1] - points[i][1]);
            moves = moves + Math.max(x,y);
        }
        return moves;
    }

    // 1221. Split a String in Balanced Strings - 27/11/2019
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

    // 1252. Cells with Odd Values in a Matrix - 28/11/2019
    public int oddCells(int n, int m, int[][] indices) {

        int[] rows = new int[n];
        int[] cols = new int[m];
        int odds = 0;

        for (int i = 0; i < indices.length; i++) {
            rows[indices[i][0]]++;
            cols[indices[i][1]]++;
        }

        for (int j = 0; j < rows.length; j++) {
            for (int k = 0; k < cols.length; k++) {
                if ((rows[j] + cols[k]) % 2 != 0) {
                    odds++;
                }
            }
        }

        return odds;
    }

    // 709. To Lower Case - 28/11/2019
    public String toLowerCase(String str) {

        StringBuilder upperLower = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if ((int) str.charAt(i) >= 65 && (int) str.charAt(i) < 91 ) {
                upperLower.append((char) (str.charAt(i) + 32));
            } else {
                upperLower.append(str.charAt(i));
            }
        }

        return upperLower.toString();
    }

    // 938. Range Sum of BST
    public int rangeSumBST(BinaryTree.Node root, int L, int R) {

        if (root == null) {
            return 0;
        }

        if (root.val >= L && root.val <=R) {
            return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);

        }

        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }

        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }

        return 0;

    }


    // 1021. Remove Outermost Parentheses
    // solve without using stacks


    static int oneDIndex(int x, int y, int cols) {
        return x*cols + y;
    }

    public static void main(String[] args) {
//        LeetCodeSolutions s = new LeetCodeSolutions();
//        int[][] ps = {{1,1},{3,4},{-1,0}};
//        String str = "RLRLRLRLRLLLLRRRRLLR";
//        System.out.println(s.minTimeToVisitAllPoints(ps));
//        System.out.println(s.balancedStringSplit(str));
        StringBuilder upperLower = new StringBuilder();
        for (int i = 65; i < 91; i++) {
            upperLower.append((char) i);
            upperLower.append((char) (i + 32));
        }
        System.out.println(upperLower.toString());
    }

}

////////////////////////////////NAIVE SOLUTIONS///////////////////////////////
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
//
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
//
//    public int oddCells(int n, int m, int[][] indices) {
//
//        int odds = 0;
//
//        int[][] matrix = new int[n][m];
//        for (int i = 0; i < indices.length; i++) {
//            for (int row = 0; row < m; row++) {
//                matrix[indices[i][0]][row]++;
//            }
//
//            for (int col = 0; col < n; col++) {
//                matrix[col][indices[i][1]]++;
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (matrix[i][j] % 2 != 0) {
//                    odds++;
//                }
//            }
//        }
//
//        return odds;
//    }