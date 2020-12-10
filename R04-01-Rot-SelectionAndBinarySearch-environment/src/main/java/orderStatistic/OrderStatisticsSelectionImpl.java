package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a
	 * estrategia de usar o selection sem modificar o array original. Note que seu
	 * algoritmo vai apenas aplicar sucessivas vezes o selection ate encontrar a
	 * estatistica de ordem desejada sem modificar o array original.
	 * 
	 * Restricoes: - Preservar o array original, ou seja, nenhuma modificacao pode
	 * ser feita no array original - Nenhum array auxiliar deve ser criado e
	 * utilizado. - Voce nao pode encontrar a k-esima estatistica de ordem por
	 * contagem de elementos maiores/menores, mas sim aplicando sucessivas selecoes
	 * (selecionar um elemento como o selectionsort mas sem modificar nenhuma
	 * posicao do array). - Caso a estatistica de ordem nao exista no array, o
	 * algoritmo deve retornar null. - Considerar que k varia de 1 a N - Sugestao: o
	 * uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		int index = 0;
		T menor = array[0];
		if (k == 1) {
			return array[index+1];
	    }
	    else {
	    	getOrderStatistics(array, k-1);
	    	for (int i = index + 1; i < array.length-1; i++) {
	    		if (menor.compareTo(array[i]) < 0) {
	    			menor = array[i];
	    		}
	    	}
	        if (array[k-1].compareTo(menor) > 0) {
	            index++;
	        }
	    }

		return array[index+1];
	}

}
