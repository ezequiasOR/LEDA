package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
	}
	
	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (this.isEmpty()) { return 0; }
		
		else { return 1 + this.next.size(); }
	}

	@Override
	public T search(T element) {
		if (element == null || this.isEmpty()) { return null; }
		
		else if (this.getData().equals(element)) { return this.getData(); }
		
		else { return this.next.search(element); }
	}

	@Override
	public void insert(T element) {
		if (element == null) { return; }
		if (this.isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>());
		}
		
		else { this.next.insert(element); }
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty() || element == null) { return; }
		
		if (this.getData().equals(element)) {
			this.setData(next.getData());
			this.setNext(next.next);
		}
		else { this.next.remove(element); }
	}
	
	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.size()];
		
		this.arrayRecursive(array, this, 0);
		return array;
	}
	
	public void arrayRecursive(T[] array, RecursiveSingleLinkedListImpl<T> node, int cont) {
		if (node.isEmpty()) { return; }
		else {
			array[cont] = node.getData();
			cont++;
			arrayRecursive(array, node.next, cont);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
