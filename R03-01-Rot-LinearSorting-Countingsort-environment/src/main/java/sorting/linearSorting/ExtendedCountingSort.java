package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		int min = menorElemneto(array, leftIndex, rightIndex);
		int aux = -min;
		int max = maiorElemneto(array, leftIndex, rightIndex);
		int k = max - min;
		
		int[] counting = frequenciaECumulativo(array, leftIndex, rightIndex, k, aux);
		
		int[] b = new int[rightIndex - leftIndex + 1];
		
		for (int i = rightIndex; i >= leftIndex; i--) {
			b[counting[array[i]+aux] - 1] = array[i];
			counting[array[i]+aux] -= 1;
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
	
	public int menorElemneto(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length == 0) {
			return 0;
		}
		int k = array[0];
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i] < k) {
				k = array[i];
			}
		}
		return k;
	}
	
	public int[] frequenciaECumulativo(Integer[] array, int leftIndex, int rightIndex, int k, int aux) {
		int[] counting = new int[k+1];
		
		for (int i = leftIndex; i <= rightIndex; i++) {				// Frequencia
			counting[array[i]+aux] += 1;
		}
		
		for (int i = leftIndex + 1; i <= k; i++) {					// Cumulativa
			counting[i] += counting[i-1];
		}
		
		return counting;
	}

}
