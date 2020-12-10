package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		
		T element = this.head();
		SingleLinkedListNode<T> prev = null;
		SingleLinkedListNode<T> head = (SingleLinkedListNode<T>) head();
		SingleLinkedListNode<T> aux = head;
		
		while (aux != null && !(aux.getData().equals(element))) {
            prev = aux;
            aux = aux.getNext();
		}
		return element;
	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			Object[] array = list.toArray();
			return (T) array[0];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.list.size() == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

}
