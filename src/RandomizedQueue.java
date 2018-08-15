import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/**
 * Created by cyfa on 13/08/2018.
 */



// Use resizing arrays - constant amortized time.
// Change implementation to linked lists.

public class RandomizedQueue<Item> implements Iterable<Item> {

//    private Item[] arr;
//    private int N = 0;
    // fields below are public for test purposes. Should be private for finished project.
    public Item[] arr;
    public int N = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() { return N == 0; }

    // return the number of items on the randomized queue
    public int size() { return arr.length; }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            if (arr.length == N) {
                resize(arr.length*2);
            }
            arr[N++] = item;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        int index = (int) Math.floor(StdRandom.uniform() * N);
        Item dequeuedItem = arr[index];
        arr[index] = null;
        N--;
        for (int i = 0; i < arr.length - 1 ; i++) {
            if (arr[i] == null) {
                arr[i] = arr[i+1];
                arr[i+1] = null;
            }
        }
        if (N > 0 && N == arr.length / 4) {
            resize(arr.length/2);
        }
        return dequeuedItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int index = (int) Math.floor(StdRandom.uniform() * N);
        return arr[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomizedQueueIterator();}


    // resizes the array as needed. Called when we add an element to a full array or when we remove an elemente from an
    // array at 25% capacity. Check these conditions in methods enqueue() and dequeue().
    private void resize(int capacity) {
        if (capacity >= N) {
            Item[] temp =  (Item[]) new Object[capacity];
            for (int i = 0; i < arr.length; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;
        public Item next() { return arr[i++]; }
        public boolean hasNext() { return i < N; }
        public void remove() { throw new UnsupportedOperationException("Operation not supported."); }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<Integer> myRD = new RandomizedQueue<Integer>();
//        System.out.println(myRD.size());
        myRD.enqueue(1);
//        System.out.println(myRD.size());
        myRD.enqueue(22);
//        System.out.println(myRD.size());
        myRD.enqueue(333);
//        System.out.println(myRD.size());
        myRD.enqueue(444);
//        System.out.println(myRD.size());
        myRD.enqueue(555);
//        System.out.println(myRD.size());
        System.out.println("Dequeueing " + myRD.dequeue());
        System.out.println("Dequeueing " + myRD.dequeue());
        System.out.println("Array after dequeue");
        System.out.println("-------------------");
        for (Integer i: myRD) {
            System.out.println(i);
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
