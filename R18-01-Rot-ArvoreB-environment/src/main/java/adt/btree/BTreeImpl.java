package adt.btree;

import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}
	
	private int height(BNode<T> node) {
		if (isEmpty())
			return -1;
		
		int result = 0;
		if (!node.isLeaf()) {
			result += 1 + height(node.getChildren().getFirst());
		}
		return result;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		LinkedList<BNode<T>> list = new LinkedList<BNode<T>>();
		depthLeftOrderRec(list, root);
		@SuppressWarnings("unchecked")
		BNode<T>[] arrayAux = (BNode<T>[]) new BNode[list.size()];
		return list.toArray(arrayAux);
	}

	private void depthLeftOrderRec(LinkedList<BNode<T>> auxList, BNode<T> node) {
		auxList.add(node);
		for (BNode<T> e : node.getChildren()) {
			depthLeftOrderRec(auxList, e);
		}
	}

	@Override
	public int size() {
		return size(root);
	}

	private int size(BNode<T> node) {
		int result = node.size();
		for (BNode<T> element : node.getChildren()) {
			result += size(element);
		}
		return result;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(root, element);
	}

	private BNodePosition<T> search(BNode<T> node, T element) {
		BNodePosition<T> result = new BNodePosition<T>();
		int index = node.getElements().indexOf(element);
		if (index != -1) {
			result = new BNodePosition<T>(node, index);
		} else {
			for (BNode<T> e : node.getChildren()) {
				BNodePosition<T> aux = search(e, element);
				if (aux.node != null) {
					result = aux;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null && this.search(element).node == null) {
			insert(this.root, element);
		}
	}

	private void insert(BNode<T> node, T element) {
		if (node.isFull()) {
			split(node);
			if (node.getParent() != null) {
				node = node.getParent();
			}
		}
		if (node.getChildren().isEmpty()) {
			node.addElement(element);
		} else {
			int i;
			for (i = 0; i < node.size(); i++) {
				T e = node.getElements().get(i);
				if (e.compareTo(element) > 0) {
					insert(node.getChildren().get(i), element);
					break;
				}
			}
			if (i == node.size()) {
				while (node.getChildren().size() <= i) {
					BNode<T> newNode = new BNode<T>(order);
					newNode.setParent(node);
					node.getChildren().add(newNode);
				}
				insert(node.getChildren().get(i), element);
			}
		}
	}

	private void split(BNode<T> node) {
		BNode<T> rightChild = new BNode<T>(order);
		BNode<T> leftChild = new BNode<T>(order);

		T middle = node.getElements().get((order - 1) / 2);
		boolean addInLeft = true;
		for (T e : node.getElements()) {
			if (e.equals(middle))
				addInLeft = false;
			else if (addInLeft)
				leftChild.addElement(e);
			else
				rightChild.addElement(e);
		}

		if (!node.equals(root)) {
			promote(node, leftChild, rightChild);
		} else {
			for (int i = 0; i < node.getChildren().size(); i++) {
				if (i <= (order - 1) / 2)
					leftChild.addChild(i, node.getChildren().get(i));
				else
					rightChild.addChild(i - ((order - 1) / 2) - 1, node.getChildren().get(i));
			}
			leftChild.setParent(node);
			rightChild.setParent(node);

			node.getChildren().clear();
			node.addChild(0, leftChild);
			node.addChild(1, rightChild);

			node.getElements().clear();
			node.addElement(middle);
		}
	}

	private void promote(BNode<T> node, BNode<T> leftChild, BNode<T> rightChild) {
		BNode<T> parent = node.getParent();
		T element = node.getElementAt((order - 1) / 2);
		parent.addElement(element);

		int position = parent.getElements().indexOf(element);
		parent.removeChild(node);

		leftChild.setParent(parent);
		rightChild.setParent(parent);

		node.parent.removeChild(node);
		node.parent.addChild(position, rightChild);
		node.parent.addChild(position, leftChild);
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
