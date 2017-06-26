import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
	
	private int size;
	private Node first;
	private Node last;
	
	private class Node {
		private Item item;
		private Node previous;
		private Node next;
	}
	
	public Deque() {
		size = 0;
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return (size == 0);
	}
	
	public int size() {
		return size;
	}
	
	public void addFirst(Item item) {
		if (item == null) throw new IllegalArgumentException("Null added");
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		if (oldfirst != null) oldfirst.previous = first;
		first.next = oldfirst;
		if (size == 0) {
			last = first;
		}
		else if (size == 1) {
			last = oldfirst;
			last.next = null;
		}
		size++;
	}
	
	public void addLast(Item item) {
		if (item == null) throw new IllegalArgumentException("Null added");
		Node oldlast = last;
		last = new Node();
		last.item = item;
		if (oldlast != null) oldlast.next = last;
		last.previous = oldlast;
		if (size == 0) {
			first = last;
		}
		else if (size == 1) {
			first = oldlast;
			first.previous = null;
		}
		size++;
	}
	
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException("Deque is empty");
		Item item = first.item;
		if (first.next != null) {
			first = first.next;
			first.previous = null;
		}
		else first = null;
		size--;
		return item;
	}
	
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException("Deque is empty");
		Item item = last.item;
		if (last.previous != null) {
			last = last.previous;
			last.next = null;
		}
		else last = null;		
		size--;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		public boolean hasNext() {
			return current != null;
		}
		public void remove() {
			throw new UnsupportedOperationException("Not implemented");
		}
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	public static void main(String[] args) {
		Deque<String> testdeck = new Deque<String>();
		testdeck.addFirst("first");
		testdeck.addFirst("morefirst");
		testdeck.addFirst("themostfirst");
		testdeck.addLast("last");
		testdeck.addLast("almosttheend");
		testdeck.addLast("theveryend");
		StdOut.println(testdeck.removeFirst());
		StdOut.println(testdeck.removeFirst());
		StdOut.println(testdeck.removeFirst());
		StdOut.println(testdeck.removeLast());
		StdOut.println(testdeck.removeLast());
		StdOut.println(testdeck.removeLast());
	}

}
