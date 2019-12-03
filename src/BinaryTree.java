public class BinaryTree {

    Node root;

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.val) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.val) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(10);
        bt.add(5);
        bt.add(15);
        bt.add(3);
        bt.add(7);
        bt.add(18);

        return bt;
    }

    public int rangeSumBST(Node root, int L, int R) {

        int sum = 0;

        if (root == null) {
            return 0;
        }

        if (root.val >= L && root.val <=R) {
            sum = root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
            return sum;

        }

        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }

        if (root.val > R) {
            return rangeSumBST(root.left, L, R);
        }

        return sum;

    }


    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt = bt.createBinaryTree();
        System.out.println("");
        System.out.println(bt.rangeSumBST(bt.root, 7, 15));
    }

    class Node {
        int val;
        Node left;
        Node right;

        Node(int value) {
            this.val = value;
            right = null;
            left = null;
        }
    }
}