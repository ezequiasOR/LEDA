package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;
import adt.linkedList.RecursiveSingleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) { throw new StackOverflowException(); }
		
		if (element == null) { return; }
		else { this.top.insert(element); }
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) { throw new StackUnderflowException(); }
		else {
			T top_value = top.toArray()[this.top.size() - 1];
			this.top.removeLast();
			return top_value;
		}
	}

	@Override
	public T top() {
		T value;
		if (this.isEmpty()) { value = null; }
		else {
			value = this.top.toArray()[this.top.size() - 1];
		}
		return value;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.top.size() == this.size;
	}

}
