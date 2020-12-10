package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> aux = this.head;
		int index = 0;
		
		while (!aux.isNIL()) {
			if (inArray(aux.getData(), array)) {
				remove(aux.getData(), index);
			} else {				
				array[index] = aux.getData();
			}
			index ++;
			aux = aux.getNext();
		}
		
	}

	private void remove(T data, int index) {
		int i = 0;
		boolean guard = true;
		SingleLinkedListNode<T> aux = this.head;
		while (guard && i < this.size()) {
			if (i == index && aux.getData().equals(data)) {
				aux.setNext(aux.getNext().getNext());
				guard = false;
			}
			i++;
			aux = aux.getNext();
		}
	}

	private boolean inArray(T data, T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(data)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		@SuppressWarnings("unchecked")
		SetLinkedList<T> setLinkedList = (SetLinkedList<T>) new SingleLinkedListNode<T>();
		SingleLinkedListNode<T> aux = this.head;
		while (aux.isNIL()) {
			if (!inLista(setLinkedList, aux.getData())) {
				setLinkedList.insert(aux.getData());
			}
			aux = aux.getNext();
		}
		
		return setLinkedList;
	}

	private boolean inLista(SetLinkedList<T> setLinkedList, T data) {
		@SuppressWarnings("unchecked")
		SingleLinkedListNode<T> aux = ((SingleLinkedListImpl<T>) setLinkedList).getHead();
		while (!aux.isNIL()) {
			if (aux.getData().equals(data)) {
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		@SuppressWarnings("unchecked")
		SetLinkedList<T> setLinkedList = (SetLinkedList<T>) new SingleLinkedListNode<T>();
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			if (inLista(otherSet, aux.getData())) {
				if (!inLista(setLinkedList, aux.getData())) {
					setLinkedList.insert(aux.getData());
				}
			}
		}
		return setLinkedList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			if (aux.getNext().isNIL()) {
				aux.setNext(((SingleLinkedListImpl<T>) otherSet).getHead());
			}
		}
	}

}
