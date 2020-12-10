package bst;

public class Mainzao {
	public static void main(String[] args) {
		BST bst = new BST();
		
		System.out.println(bst.isEmpty());
		bst.insert(10);
		System.out.println(bst.isEmpty());
		
		System.out.println(bst.minimum().getData());
		System.out.println(bst.maximum().getData());
		
		bst.insert(4);
		System.out.println(bst.minimum().getData());
		System.out.println(bst.maximum().getData());
		
		bst.insert(17);
		System.out.println(bst.minimum().getData());
		System.out.println(bst.maximum().getData());
		
		bst.insert(1);
		System.out.println(bst.minimum().getData());
		System.out.println(bst.maximum().getData());
		
		bst.insert(21);
		System.out.println(bst.minimum().getData());
		System.out.println(bst.maximum().getData());
		
		bst.insert(5);
		System.out.println(bst.minimum().getData());
		System.out.println(bst.maximum().getData());
		
		bst.insert(16);

		System.out.println(bst.minimum().getData());
		System.out.println(bst.maximum().getData());
		
		System.out.println("successor: ");
		System.out.println(bst.successor(10).getData());
		System.out.println(bst.successor(17).getData());
		System.out.println(bst.successor(4).getData());
		System.out.println(bst.successor(5).getData());
		if (bst.successor(21) != null) { System.out.println(bst.successor(21).getData()); }
		else { System.out.println("Sucessor nulo"); }
		
		System.out.println("predecessor: ");
		System.out.println(bst.predecessor(10).getData());
		System.out.println(bst.predecessor(17).getData());
		System.out.println(bst.predecessor(4).getData());
		System.out.println(bst.predecessor(5).getData());
		if (bst.successor(1) != null) { System.out.println(bst.successor(1).getData()); }
		else { System.out.println("Sucessor nulo"); }
		
		System.out.println("preorder: ");
		bst.preOrder();
		
		System.out.println();
		System.out.println("inorder: ");
		bst.inOrder();
		
		System.out.println();
		System.out.println("posorder: ");
		bst.posOrder();
		
		System.out.println();
		System.out.println("inverse order: ");
		bst.inverseOrder();
	}
}
