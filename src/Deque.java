import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by cyfa on 13/08/2018.
 */
//Use linked lists - constant time worst-case
// Change implementation to resizing arrays.

public class Deque<Item> implements Iterable<Item> {

    private Node first;

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
        int size = 0;
        for (Item i: this) {
            size++;
        }
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        Node newNode = new Node();
        newNode.item = item;
        Node current = newNode;
        current.next = first;
        first = current;
    }

    // add the item to the end
    public void addLast(Item item) {
        Node current = first;
        Node newNode = new Node();
        newNode.item = item;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.next = null;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        Item removedElement = first.item;
        first = first.next;
        return removedElement;
    }

    // remove and return the item from the end
    public Item removeLast() {
        Item removedElement;
        Node current = first;
        while (current.next.next != null) {
            current = current.next;
        }
        removedElement = current.next.item;
        current.next = null;
        return removedElement;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
         private Node current = first;

        public boolean hasNext () { return current != null;}

        public void remove() {
            throw new UnsupportedOperationException("Operation not supported");
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
        Deque<String> myDeque = new Deque<String>();
        myDeque.addFirst("primeiro");
        myDeque.addFirst("segundo");
        myDeque.addFirst("terceiro");
        myDeque.addLast("second to last");
        myDeque.addLast("last");
        System.out.println(myDeque.size());
        for (String i: myDeque) {
            System.out.println(i);
        }
        System.out.println("removed " + myDeque.removeFirst());
        System.out.println(myDeque.size());
        for (String i: myDeque) {
            System.out.println(i);
        }
        System.out.println("removed " + myDeque.removeLast());
        System.out.println(myDeque.size());
        for (String i: myDeque) {
            System.out.println(i);
        }
    }
}

