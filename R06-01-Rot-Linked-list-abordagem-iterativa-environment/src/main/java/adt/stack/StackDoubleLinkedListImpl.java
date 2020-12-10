package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (top == null) {  
            throw new StackUnderflowException();
        } else {
        	return (T) top; // TODO
        }  
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		} else {
			return (T) top.toString();
		}
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented!");
	}

}
