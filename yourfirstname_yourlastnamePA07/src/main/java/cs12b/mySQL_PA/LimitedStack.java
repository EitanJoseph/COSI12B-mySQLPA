package cs12b.mySQL_PA;

import java.util.NoSuchElementException;

public class LimitedStack<T> {

	private static final int MIN_LIMIT = 2;
	
	private Node<T> head;
	private Node<T> tail;
	private int limit;
	private int size;
	
	public LimitedStack(int limit) {
		if (limit < MIN_LIMIT) {
			throw new IllegalArgumentException(limit + " is too low. Must be at least " + MIN_LIMIT);
		}
		this.limit = limit;
		head = null;
		tail = null;
		size = 0;
	}
	
	public void push(T x) {  	
		if (size == limit) {
			head.getNext().setPrev(null);
			head = head.getNext();
			size--;
		}
		Node<T> newNode = new Node<T>(x);
		if (size == 0) {
			head = newNode;
			tail = head;
		}
		else {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		size++;
	}
	
	public T pop() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty stack");
		}
		T oldTop = tail.getData();
		if (size == 1) {
			head = null;
			tail = null;
		}
		else {
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
		}
		size--;
		return oldTop;
	}
	
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Empty stack");
		}
		return tail.getData();
	}
	
	public boolean isEmpty() {
		return size==0;
	}
}
