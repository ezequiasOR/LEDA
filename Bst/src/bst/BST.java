package bst;

import java.util.ArrayList;
import java.util.List;

public class BST {
	NodeBST root;
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	public NodeBST search(int element) {
		if (!isEmpty())
			return search(this.root, element);
		else return null;
	}
	
	private NodeBST search(NodeBST node, int element) {
		NodeBST nodeResult;
		if (node == null) nodeResult = new NodeBST();
		
		else if (element == node.getData()) nodeResult = node;
		
		else if (element < node.getData()) nodeResult = search(node.getLeft(), element);
		
		else nodeResult = search(node.getRight(), element);
		
		return nodeResult;
	}

	public void insert(int element) {
		insert(this.root, element);
	}

	private void insert(NodeBST node, int element) {
		if(isEmpty()) { this.root = new NodeBST(element); }
		else {
			if (element < node.getData()) {
				if (node.getLeft() == null)
					node.setLeft(new NodeBST(element, node));
				else insert(node.getLeft(), element);
			}
			else if (element > node.getData()) {
				if (node.getRight() == null)
					node.setRight(new NodeBST(element, node));
				else insert(node.getRight(), element);
			}
		}
	}
	
	public void remove(int element) {
		NodeBST node = search(element);
		if (!isEmpty()) {
			if (node != null) {
				if (isLeaf(node)) {
					node = null;
				}
			}
		}
		//TODO terminar aqui
	}
	
	private boolean isLeaf(NodeBST node) {
		return (node.getLeft() == null && node.getRight() == null);
		//TODO conferir se ta certo aqui
	}

	public NodeBST minimum() {
		return minimum(this.root);
	}
	
	private NodeBST minimum(NodeBST node) {
		if (!isEmpty()) {
			if (node.getLeft() != null)
				return minimum(node.getLeft());
			else return node;
		}
		else return null;
	}
	
	public NodeBST maximum() {
		return maximum(this.root);
	}

	private NodeBST maximum(NodeBST node) {
		if (!isEmpty()) {
			if (node.getRight() != null)
				return maximum(node.getRight());
			else return node;
		}
		else return null;
	}
	
	public NodeBST successor(int element) {
		NodeBST node = search(element);

		if (node.getRight() != null) {
			return minimum(node.getRight());
		}
		
		NodeBST parentNode = node.getParent();
		
		while (parentNode != null) {
			if (parentNode.getData() > node.getData()) {
				return parentNode;
			}
			parentNode = parentNode.getParent();			
		}
		
		return null;
	}
	
	public NodeBST predecessor(int element) {
		NodeBST node = search(element);
		if (node.getLeft() != null)
			return maximum(node.getLeft());
		
		NodeBST parentNode = node.getParent();
		
		while (parentNode != null) {
			if (parentNode.getData() < node.getData()) {
				return parentNode;
			}
			parentNode = parentNode.getParent();
		}
		return null;
	}
	
	public void preOrder() {
		if (!isEmpty())
			preOrder(this.root);
	}

	private void preOrder(NodeBST node) {
		if (node!= null) {
			System.out.print(node.getData() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
	
	public void inOrder() {
		if(!isEmpty())
			inOrder(this.root);
	}

	private void inOrder(NodeBST node) {
		if (node != null) {
			inOrder(node.getLeft());
			System.out.print(node.getData() + " ");
			inOrder(node.getRight());
		}
	}

	public void posOrder() {
		if (!isEmpty())
			posOrder(this.root);
	}
	
	private void posOrder(NodeBST node) {
		if (node != null) {
			posOrder(node.getLeft());
			posOrder(node.getRight());
			System.out.print(node.getData() + " ");
		}
	}
	
	public void inverseOrder() {
		if (!isEmpty())
			inverseOrder(this.root);
	}

	private void inverseOrder(NodeBST node) {
		if (node != null) {
			inverseOrder(node.getRight());
			System.out.print(node.getData() + " ");
			inverseOrder(node.getLeft());
		}
	}

	public int size() {
		return size(this.root);
	}

	private int size(NodeBST node) {
		int result = 0;
		if (node != null) {
			result = 1 + size(node.getLeft()) + size(node.getRight());
		}
		return result;
	}
}
