/**
 * Created by cyfa on 13/08/2018.
 */

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Use resizing arrays - constant amortized time.

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        arr = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() { return n == 0; }

    // return the number of items on the randomized queue
    public int size() { return n; }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            if (arr.length == n) {
                resize(arr.length*2);
            }
            arr[n++] = item;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        int index = (int) Math.floor(StdRandom.uniform() * n);
        Item dequeuedItem = arr[index];
        arr[index] = null;
        if (index != arr.length - 1) {
            arr[index] = arr[n - 1];
            arr[n - 1] =  null;
        }
        n--;
        if (n > 0 && n == arr.length / 4) {
            resize(arr.length/2);
        }
        return dequeuedItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        int index = (int) Math.floor(StdRandom.uniform() * n);
        return arr[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }


    // resizes the array as needed. Called when we add an element to a full array or when we remove an elemente from an
    // array at 25% capacity. Check these conditions in methods enqueue() and dequeue().
    private void resize(int capacity) {
        if (capacity >= n) {
            Item[] temp =  (Item[]) new Object[capacity];
            for (int i = 0; i < n; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;
        private Item[] iteratorArray = (Item[]) new Object[n];

        public RandomizedQueueIterator() {
            StdRandom.shuffle(arr, 0, n);
            for (int j = 0; j < n; j++) {
                iteratorArray[j] = arr[j];
            }
        }

        public Item next() {
            if (i >= n) { throw new NoSuchElementException(); }
            return iteratorArray[i++];
        }

        public boolean hasNext() { return i < n; }

        public void remove() { throw new UnsupportedOperationException("Operation not supported."); }
    }

    // unit testing (optional)
    public static void main(String[] args) { }
}
