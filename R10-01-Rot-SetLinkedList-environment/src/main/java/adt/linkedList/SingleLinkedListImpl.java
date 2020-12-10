package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head == null;
	}

	@Override
	public int size() {
		if (isEmpty()) { return 0; }
		else {
			int count = 0;
			SingleLinkedListNode<T> aux = this.head;
			while (!aux.isNIL()) {
				count++;
				aux = aux.next;
			}
			return count;
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()) { return null; }
		else {
			boolean guard = true;
			T result = null;
			SingleLinkedListNode<T> aux = this.head;
			while (guard && !aux.isNIL()) {
				if (aux.equals(element)) {
					guard = false;
					result = aux.getData();
				}
				aux = aux.next;
			}
			return result;
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.head.setData(element);
				this.head.setNext(new SingleLinkedListNode<T>());
			} else {
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.isNIL()) {
					aux = aux.getNext();
				}
				aux.setData(element);
				aux.setNext(new SingleLinkedListNode<T>());
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null || this.isEmpty()) {
			if (this.head.getData().equals(element)) {
				this.head = this.head.getNext();
			}
			else {
				boolean removed = false;
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.isNIL() && !removed) {
					if (aux.getNext().getData().equals(element)) {
						aux.setNext(aux.getNext().getNext());
						removed = true;
					}
					aux = aux.getNext();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		Object[] array = new Object[this.size()];
		SingleLinkedListNode<T> aux = head;
		int count = 0;
		while (!aux.isNIL()) {
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
