package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, last, null); 
			
			if(isEmpty()) {
				this.head = newNode;
			} else {
				SingleLinkedListNode<T> aux = head;
				while (aux.next != null) {
					aux = aux.next;
				}
				aux.next = newNode;
			}
		}
        
	}

	@Override
	public void removeFirst() {
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
		T data = head.data;
	    head = head.next;
	    
	    if (isEmpty()) {
	    	last = null;
	    } else {
	    	last.previous = null;
	    }
	}

	@Override
	public void removeLast() {
		if (isEmpty()) {
			throw new RuntimeException("Empty list");
		}
	    T data = last.data;
	    last = last.previous;

	    if (isEmpty()) head = null;

	    else last.next = null;
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
