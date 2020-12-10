package bst;

public class NodeBST {
	int data;
	NodeBST left;
	NodeBST right;
	NodeBST parent;
	
	public NodeBST() {
		
	}
	
	public NodeBST(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = new NodeBST();
	}
	
	public NodeBST(int data, NodeBST parent) {
		this.data = data;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}

	public NodeBST(int data, NodeBST left, NodeBST right, NodeBST parent) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}

	public int getData() {
		return this.data;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public NodeBST getLeft() {
		return this.left;
	}
	
	public void setLeft(NodeBST left) {
		this.left = left;
	}
	
	public NodeBST getRight() {
		return this.right;
	}
	
	public void setRight(NodeBST right) {
		this.right = right;
	}
	
	public NodeBST getParent() {
		return this.parent;
	}
	
	public void setParent(NodeBST parent) {
		this.parent = parent;
	}

}
