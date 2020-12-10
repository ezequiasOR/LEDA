package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if (this.head.isNIL()) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		int count = 0;
		SingleLinkedListNode<T> aux = head;
		while (aux != null && aux.getData() != null) {
            aux = aux.next;
            count++;
        }
		return count; 
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = head;
		while (aux != null && !(aux.getData().equals(element))) {
			aux = aux.next;
		}
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, null); 
			
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
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.head.getData().equals(element)) {
				this.head = this.head.next;
			}
			
			SingleLinkedListNode<T> prev = null;
			SingleLinkedListNode<T> aux = head;
			
			while (aux != null && !(aux.getData().equals(element))) {
                prev = aux;
                aux = aux.next;
			}
			
			if (aux != null) {
				prev.next = aux.next;
			}
			
		}
	}

	@Override
	public T[] toArray() {
		Object[] array = new Object[this.size()];
		SingleLinkedListNode<T> aux = head;
		int count = 0;
		while (aux != null && !(aux.getData().equals(null))) {
			array[count] = aux.getData();
			aux = aux.next;
			count++;
		}
		return (T[]) array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
