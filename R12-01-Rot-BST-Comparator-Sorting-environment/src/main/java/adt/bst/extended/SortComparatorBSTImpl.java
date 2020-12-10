package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {														// Se o node for vazio
			node.setData(element);													// a data do node passa a ser o element
			node.setLeft(new BSTNode.Builder().parent(node).build());				// cria um novo node com o pai sendo o node e configura o left do node como esse novo node
			node.setRight(new BSTNode.Builder().parent(node).build());				// cria um node node com o pai sendo o node e configura o right do node como esse novo node
		} else {																	// Se o node nao for vazio
			if (this.comparator.compare(element, node.getData()) > 0) {				// Se o element for maior que o data do node
				insert((BSTNode<T>) node.getRight(), element);						// chamar recursivamente o insert passando o right do node e o element
			} else if (this.comparator.compare(element, node.getData()) < 0) {		// Caso o element nao seja maior
				insert((BSTNode<T>) node.getLeft(), element);						// chama recursivamente o insert passando o left do node e o element
			}
		}
	}
	
	@Override
	protected BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> nodeResult;
		
		if (node.isEmpty()) { nodeResult = new BSTNode.Builder().build(); }						// Se o node e vazio, nodeResult passa a ser um Node "nulo"
		else if(this.comparator.compare(element, node.getData()) == 0) { nodeResult = node; }	// se o element for igual, o nodeResult vai apontar para o node
		else if (this.comparator.compare(element, node.getData()) > 0) {						// se o element for maior
			nodeResult = search((BSTNode<T>) node.getRight(), element);							// o nodeResult vai chamar recursivamente o search passando o right do node e o element a ser encontrado
		} else {																				// se o element for menor
			nodeResult = search((BSTNode<T>) node.getLeft(), element);							// o nodeResult vai chamar recursivamente o search passando o left do node e o element
		}
		return nodeResult;																		// retorna o nodeResult	
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

				while (parentNode != null && this.comparator.compare(parentNode.getData(), node.getData())<0) {	// Enquanto o parentNode nao for nulo e o data do parentNode for menor que o data do note
					node = parentNode;																			// node recebe parentNode
					parentNode = (BSTNode<T>) node.getParent();													// e o parentNode recebe o pai do node
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

				while (parentNode != null && this.comparator.compare(parentNode.getData(), node.getData()) > 0) {
					node = parentNode;
					parentNode = (BSTNode<T>) node.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public T[] sort(T[] array) {
		while (!this.isEmpty())
			this.remove(this.root.getData());

		for (T element : array) {
			this.insert(element);
		}

		return this.order();
	}

	@Override
	public T[] reverseOrder() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			reverseOrder(this.root, aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}
	
	private void reverseOrder(BSTNode<T> node, List<T> array) {
		if (!node.isEmpty()) {
			reverseOrder((BSTNode<T>) node.getRight(), array);
			array.add(node.getData());
			reverseOrder((BSTNode<T>) node.getLeft(), array);
		}
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
