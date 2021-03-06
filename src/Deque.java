/**
 * Created by cyfa on 13/08/2018.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

// Use linked lists - constant time worst-case

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }
    // construct an empty deque
    public Deque() {
        first = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node newNode = new Node();
        newNode.item = item;
        Node current = newNode;
        current.next = first;
        first = current;
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException(); }
        Node newNode = new Node();
        newNode.item = item;
        if (first == null) {
            first = newNode;
        } else {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = null;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item removedElement = first.item;
        first = first.next;
        size--;
        return removedElement;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item removedElement;
        Node current = first;
        if (current.next == null) {
            removedElement = current.item;
            first = null;
        } else {
            while (current.next.next != null) {
                current = current.next;
            }
            removedElement = current.next.item;
            current.next = null;
        }
        size--;
        return removedElement;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { return current != null; }

        public void remove() {
            throw new UnsupportedOperationException("Operation not supported");
        }

        public Item next() {
            if (current == null) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) { }
}

