package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (buscaBinaria(array, 0, array.length-1, x) == -1) {
			return buscaBinaria(array, 0, array.length-1, x-1);
		} else {
			return buscaBinaria(array, 0, array.length-1, x);				
		}
	}

	private int buscaBinaria(Integer[] array, int ini, int fim, int x) {
		int meio = (ini+fim)/2;
		
		if (ini > fim) {
			return -1;
		} else if (array[meio] == x) {
			return x;
		}
		if (array[meio] < x) {
			return buscaBinaria(array, meio+1, fim, x);
		} else {
			return buscaBinaria(array, ini, meio-1, x);
		}
		
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		if (buscaBinaria(array, 0, array.length-1, x) == -1) {
			return buscaBinaria(array, 0, array.length-1, x+1);
		} else {
			return buscaBinaria(array, 0, array.length-1, x);				
		}
	}

}
