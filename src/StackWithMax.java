// TODO - move to separate project. Not part of princeton's algorithms. Check if part of an online judge.
// This probably came from geek for geeks.

import java.util.Stack;

class StackWithMax<Item> extends Stack{
    private Stack<Integer> maxStack = new Stack<Integer>();
    private int max;

    public void push(int a) {
        super.push(a);
        if (empty()) {
            maxStack.push(a);
            max = a;
        } else {
            if (a > max) {
                max = a;
            }
            maxStack.push(max);
        }
    }

    public Item pop(){
        maxStack.pop();
        return (Item)super.pop();
    }

    public Integer max() {
        return maxStack.peek();
    }

    public static void main(String[] args) {
        StackWithMax<Integer> myStack = new StackWithMax<Integer>();
        myStack.push(7);
        myStack.push(1);
        myStack.push(7);
        System.out.println(myStack.max());
        myStack.pop();
        System.out.println(myStack.max());
    }
}

