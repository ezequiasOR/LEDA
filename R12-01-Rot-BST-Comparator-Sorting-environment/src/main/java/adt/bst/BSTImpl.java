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
		if (!node.isEmpty()) {														// Se node não estiver vazio
			int left = height((BSTNode<T>) node.getLeft(), currentHeight + 1);		// Chama o metodo height recursivamente para a esquerda e incrementa 1 no currentHeight
			int right = height((BSTNode<T>) node.getRight(), currentHeight + 1);	// Chama o metodo height recursicamente para a direita e incrementa 1 no currentHeight
		
			currentHeight = Math.max(left, right);									// Soma os valores de left e right
		}
		return currentHeight;														// Retorna o currentHeight (altura)
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
		BSTNode<T> nodeResult;
		
		if (node.isEmpty()) { nodeResult = new BSTNode.Builder().build(); }			// Se o node e vazio, nodeResult passa a ser um Node "nulo"
		else if(element.compareTo(node.getData()) == 0) { nodeResult = node; }		// se o element for igual, o nodeResult vai apontar para o node
		else if (element.compareTo(node.getData()) > 0) {							// se o element for maior
			nodeResult = search((BSTNode<T>) node.getRight(), element);				// o nodeResult vai chamar recursivamente o search passando o right do node e o element a ser encontrado
		} else {																	// se o element for menor
			nodeResult = search((BSTNode<T>) node.getLeft(), element);				// o nodeResult vai chamar recursivamente o search passando o left do node e o element
		}
		return nodeResult;															// retorna o nodeResult
	}

	@Override
	public void insert(T element) {
		insert(this.root, element);
	}

	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {														// Se o node for vazio
			node.setData(element);													// a data do node passa a ser o element
			node.setLeft(new BSTNode.Builder().parent(node).build());				// cria um novo node com o pai sendo o node e configura o left do node como esse novo node
			node.setRight(new BSTNode.Builder().parent(node).build());				// cria um node node com o pai sendo o node e configura o right do node como esse novo node
		} else {																	// Se o node nao for vazio
			if (element.compareTo(node.getData()) > 0) {							// Se o element for maior que o data do node
				insert((BSTNode<T>) node.getRight(), element);						// chamar recursivamente o insert passando o right do node e o element
			} else {																// Caso o element nao seja maior
				insert((BSTNode<T>) node.getLeft(), element);						// chama recursivamente o insert passando o left do node e o element
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty()) { return null; }												// Se for vazio retorna null
		else {																		// Caso contrario
			return maximum(this.root);												// Chama o metodo maximum passando um node como parametro
		}
	}

	protected BSTNode<T> maximum(BSTNode<T> node) {
		if (!node.getRight().isEmpty()) {											// Se o right do node nao for vazio
			return maximum((BSTNode<T>) node.getRight());							// (retorna) Chama o maximum recursivamente passando o right do node como parametro
		}
		else {																		// Caso contrario
			return node;															// retorna o node
		}
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty()) { return null; }												// Se for vazio retorna null
		else { return minimum(this.root); }											// Caso contrario, chama o metodo minimum passando o node e retorna o resultado do metodo
	}

	protected BSTNode<T> minimum(BSTNode<T> node) {
		if (!node.getLeft().isEmpty()) {											// Se o left do node nao for vazio
			return minimum((BSTNode<T>) node.getLeft());							// chama o metodo minimum recursivamente passando o left do node como parametro e retorna o resultado do metodo
		} else { return node; }														// Caso contrario, retorna o node
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);										// Procura pelo element e guarda ele na variavel node
		if (!node.isEmpty()) {														// Se o node nao for vazio
			if (!node.getRight().isEmpty()) {										// Se o right do node nao for vazio	
				return minimum((BSTNode<T>) node.getRight());						// chama o metodo minimum passando o right do node e retorna o resultado no metodo
			}
			else {																	// Se for vazio
				BSTNode<T> parentNode = (BSTNode<T>) node.getParent();				// Pega o pai do node e gurada na variavel

				while (parentNode != null && parentNode.getData().compareTo(node.getData()) < 0) {	// Enquanto o parentNode nao for nulo e o data do parentNode for menor que o data do note
					node = parentNode;																// node recebe parentNode
					parentNode = (BSTNode<T>) node.getParent();										// e o parentNode recebe o pai do node
				}
				return parentNode;													// retorna o parentNode
			}
		}
		return null;																// Se for vazio, retorna null
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
