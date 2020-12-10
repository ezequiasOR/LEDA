package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}
	
	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (this.isEmpty()) { this.insert(element); }
			
			else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<T>(this.getData(), this.getNext(), this);
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(newNode);
				this.setNext(newNode);
				this.setData(element);
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (getPrevious().isEmpty()) {
				setData(getNext().getData());
				setNext(getNext().getNext());
			
				if (getNext() != null) { ((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this); }
			
			} else { getPrevious().removeFirst(); }
		}
	}
	
	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);
				
				if (this.getPrevious().isEmpty()) { this.setPrevious(null); }

			} else { ((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast(); }
		}
	}
	
	@Override
	public void insert(T element) {

		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveDoubleLinkedListImpl<T>(null, null, this));
				if (getPrevious() == null)
					setPrevious(new RecursiveDoubleLinkedListImpl<T>(null, this, null));
			} else {
				next.insert(element);
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
