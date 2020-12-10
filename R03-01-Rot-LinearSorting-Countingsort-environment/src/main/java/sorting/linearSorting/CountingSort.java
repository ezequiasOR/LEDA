package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {

		int k = maiorElemneto(array, leftIndex, rightIndex); // Maior elemento do array

		int[] freq = frequenciaECumulativo(array, leftIndex, rightIndex, k);

		int[] b = new int[rightIndex - leftIndex + 1];

		for (int i = rightIndex; i >= leftIndex; i--) {
			b[freq[array[i]] - 1] = array[i];
			freq[array[i]] -= 1;
		}

		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = b[i];
		}
	}

	public int maiorElemneto(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) {
			return 0;
		}
		int k = array[0];
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] > k) {
				k = array[i];
			}
		}
		return k;
	}

	public int[] frequenciaECumulativo(Integer[] array, int leftIndex, int rightIndex, int k) {
		int[] freq = new int[k+1];
		
		for (int i = leftIndex; i <= rightIndex; i++) {									// Frequencia do elemento no array
			freq[array[i]] = freq[array[i]] + 1; 
		}
		
		for (int i = leftIndex + 1; i <= k; i++) {										// Cumulativa
			freq[i] += freq[i-1];
		}
		return freq;
	}

}
