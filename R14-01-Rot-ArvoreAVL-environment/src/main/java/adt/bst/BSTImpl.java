package adt.bst;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

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
	public int height() {
		return height(this.root, -1);
	}

	protected int height(BSTNode<T> node, int currentHeight) {
		if (!node.isEmpty()) {
			int left = height((BSTNode<T>) node.getLeft(), currentHeight + 1);
			int right = height((BSTNode<T>) node.getRight(), currentHeight + 1);
			
			currentHeight = Math.max(left, right);
		}
		return currentHeight;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (!isEmpty()) {
			return search(this.root, element);
		} else {
			return new BSTNode.Builder().build();
		}
	}

	protected BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> nodeReturn;
		
		if (node.isEmpty()) {
			nodeReturn = new BSTNode.Builder().build();
		} else if (element.compareTo(node.getData()) == 0) {
			nodeReturn = node;
		} else if (element.compareTo(node.getData()) > 0){
			nodeReturn = search((BSTNode<T>) node.getRight(), element);
		} else {
			nodeReturn = search((BSTNode<T>) node.getLeft(), element);
		}
		return nodeReturn;
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setRight(new BSTNode.Builder().parent(node).build());
			node.setLeft(new BSTNode.Builder().parent(node).build());
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert((BSTNode<T>) node.getLeft(), element);
			} else if (element.compareTo(node.getData()) > 0){
				insert((BSTNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) { return null; }
		else { return maximum(this.root); }
	}

	protected BSTNode<T> maximum(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {
			return maximum((BSTNode<T>) node.getRight());
		} else {
			return node;
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) { return null; }
		else { return minimum(this.root); }
	}

	protected BSTNode<T> minimum(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {
			return minimum((BSTNode<T>) node.getLeft());
		} else {
			return node;
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (!node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				return minimum((BSTNode<T>) node.getRight());
			} else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
				
				while (!parentNode.isEmpty() && parentNode.getData().compareTo(node.getData()) < 0) {
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
		BSTNode<T> node = search(element);

		if (!node.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				return maximum((BSTNode<T>) node.getLeft());
			} else {
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();
				
				while (!parentNode.isEmpty() && parentNode.getData().compareTo(node.getData()) > 0) {
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
			if (node.isLeaf()) {
				node.setData(null);
			} else if (oneChild(node)) {
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

	protected boolean oneChild(BSTNode<T> node) {
		if (node.getLeft().isEmpty() && !node.getRight().isEmpty()) {
			return true;
		} else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public T[] preOrder() {
		T[] arrayPreOrder = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if (!this.isEmpty()) {
			preOrder(this.root, aux);
			aux.toArray(arrayPreOrder);
		}
		return arrayPreOrder;
	}

	private void preOrder(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());						// Root
			preOrder((BSTNode<T>) node.getLeft(), array);	// Left
			preOrder((BSTNode<T>) node.getRight(), array);	// Right
		}
	}

	@Override
	public T[] order() {
		T[] arrayOrder = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if (!this.isEmpty()) {
			order(this.root, aux);
			aux.toArray(arrayOrder);
		}
		return arrayOrder;
	}

	private void order(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			preOrder((BSTNode<T>) node.getLeft(), array);	// Left
			array.add(node.getData());						// Root
			preOrder((BSTNode<T>) node.getRight(), array);	// Right
		}
	}

	@Override
	public T[] postOrder() {
		T[] arrayPostOrder = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();
		
		if (!this.isEmpty()) {
			postOrder(this.root, aux);
			aux.toArray(arrayPostOrder);
		}
		return arrayPostOrder;
	}

	private void postOrder(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			postOrder((BSTNode<T>) node.getLeft(), array);	// Left
			postOrder((BSTNode<T>) node.getRight(), array);	// Right
			array.add(node.getData());						// Root
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
