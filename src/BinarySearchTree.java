// TODO - move to separate project. Not part of princeton's algorithms. Check if part of an online judge.

import java.util.ArrayList;

public class BinarySearchTree {

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

    public void inOrderTraversal(Node root, ArrayList<Integer> arr ) {
        if (root == null) { return; }
        inOrderTraversal(root.left, arr);
        arr.add(root.val);
        inOrderTraversal(root.right, arr);
    }

    public void preOrderTraversal(Node root, ArrayList<Integer> arr ) {
        if (root == null) { return; }
        arr.add(root.val);
        inOrderTraversal(root.left, arr);
        inOrderTraversal(root.right, arr);
    }

    public void postOrderTraversal(Node root, ArrayList<Integer> arr ) {
        if (root == null) { return; }
        inOrderTraversal(root.left, arr);
        inOrderTraversal(root.right, arr);
        arr.add(root.val);
    }



    private BinarySearchTree createBinaryTree() {
        BinarySearchTree bt = new BinarySearchTree();

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

        BinarySearchTree bt = new BinarySearchTree();
        bt = bt.createBinaryTree();
        ArrayList<Integer> inOrder = new ArrayList<>();
        ArrayList<Integer> preOrder = new ArrayList<>();
        ArrayList<Integer> postOrder = new ArrayList<>();
        bt.inOrderTraversal(bt.root, inOrder);
        System.out.println("In order traversal");
        for (Integer n: inOrder) {
            System.out.println(n);
        }
        bt.preOrderTraversal(bt.root, preOrder);
        System.out.println("Pre order traversal");
        for (Integer n: preOrder) {
            System.out.println(n);
        }
        bt.postOrderTraversal(bt.root, postOrder);
        System.out.println("Post order traversal");
        for (Integer n: postOrder) {
            System.out.println(n);
        }

//        System.out.println("");
//        System.out.println(bt.rangeSumBST(bt.root, 7, 15));
    }

    class Node {
        int val;
        Node left;
        Node right;

        Node(int value) {
            this.val = value;
//            right = null;
//            left = null;
        }
    }
}