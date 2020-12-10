package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() { return height(this.root, -1); }

	private int height(BSTNode<T> node, int currentHeight) {
		if (!node.isEmpty()) {
			int left = height((BSTNode<T>) node.getLeft(), currentHeight + 1);
			int right = height((BSTNode<T>) node.getRight(), currentHeight + 1);

			currentHeight = Math.max(left, right);
		}
		return currentHeight;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (!isEmpty()) { return search(this.root, element); }
		else { return new BSTNode.Builder().build(); }
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> nodeResult;

		if (node.isEmpty()) { nodeResult = new BSTNode.Builder().build(); }

		else if (element.compareTo(node.getData()) == 0) { nodeResult = node; }
		else if (element.compareTo(node.getData()) > 0) { nodeResult = search((BSTNode<T>) node.getRight(), element); }
		else { nodeResult = search((BSTNode<T>) node.getLeft(), element); }

		return nodeResult;
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode.Builder().parent(node).build());
			node.setRight(new BSTNode.Builder().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) > 0) { insert((BSTNode<T>) node.getRight(), element); }
			else if (element.compareTo(node.getData()) < 0) { insert((BSTNode<T>) node.getLeft(), element); }
		}	
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) { return null; }
		else { return maximum(this.root); }
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) { return maximum((BSTNode<T>) node.getRight()); }
		else { return node;}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) { return null; }
		else { return minimum(this.root); }
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) { return minimum((BSTNode<T>) node.getLeft()); }
		else { return node; }
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) { return minimum((BSTNode<T>) node.getRight()); }
			else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) < 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) { return maximum((BSTNode<T>) node.getLeft()); }
			else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) > 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (node.isLeaf()) { node.setData(null); }
			
			else if (oneChild(node)) {
				if (node.getParent() != null) {
					if (!node.getParent().getLeft().equals(node)) {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setRight(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setRight(node.getRight());
							node.getRight().setParent(node.getParent());
						}

					} else {
						if (!node.getLeft().isEmpty()) {
							node.getParent().setLeft(node.getLeft());
							node.getLeft().setParent(node.getParent());
						} else {
							node.getParent().setLeft(node.getRight());
							node.getRight().setParent(node.getParent());
						}
					}
				} else {
					if (node.getLeft().isEmpty()) { this.root = (BSTNode<T>) node.getRight(); }
					else { this.root = (BSTNode<T>) node.getLeft(); }
					this.root.setParent(null);
				}
			} else {
				T sucessorNode = sucessor(node.getData()).getData();
				remove(sucessorNode);
				node.setData(sucessorNode);
			}
		}
	}
	
	private boolean oneChild(BSTNode<T> node) {
		return ((node.getRight().isEmpty() && !node.getLeft().isEmpty() || node.getLeft().isEmpty() && !node.getRight().isEmpty()));
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			preOrder(this.root, aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}
	
	private void preOrder(BSTNode<T> node, List<T> array) {

		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrder((BSTNode<T>) node.getLeft(), array);
			preOrder((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] order() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			Order(this.root, aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}
	
	private void Order(BSTNode<T> node, List<T> array) {

		if (!node.isEmpty()) {
			Order((BSTNode<T>) node.getLeft(), array);
			array.add(node.getData());
			Order((BSTNode<T>) node.getRight(), array);
		}
	}

	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			postOrder(this.root, aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}
	
	private void postOrder(BSTNode<T> node, List<T> array) {

		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), array);
			postOrder((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
		}
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
