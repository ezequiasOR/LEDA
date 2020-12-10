package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   /**
    * A hash table with closed address works with a hash function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size (or the given size, if it is already prime). 
    * For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number.
    * If the given number is prime, it is returned. 
    * You can use the method Util.isPrime to check if a number is
    * prime.
    */
   int getPrimeAbove(int number) {
      while (!Util.isPrime(number)) {
    	  number++;
      }
      return number;
   }

   @Override
   public void insert(T element) {
      if (element != null) {
    	  int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));
    	  
    	  @SuppressWarnings("unchecked")
    	  LinkedList<T> elemTable = (LinkedList<T>) this.table[index];
    	  
    	  if (elemTable == null) {				// Se for nulo cria a LinkedList e add o element a ela na posição index e aumenta o numero de elementos na hash table
    		  elemTable = new LinkedList<T>();
    		  elemTable.add(element);
    		  this.table[index] = elemTable;
    		  this.elements++;
    	  } else {								// Se o elemTable não for nulo
    		  if (!elemTable.contains(element)) {			// Se não tiver o elemento na LinkedList e aumenta o numero de colisoes e de elementos na hash table
    			  	elemTable.add(element);
					this.COLLISIONS++;
					this.elements++;
    		  }
    	  }
      }
   }

   @Override
   public void remove(T element) {
      if (element != null) {
    	  int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));
    	  @SuppressWarnings("unchecked")
    	  LinkedList<T> elemTable = (LinkedList<T>) this.table[index];
    	  
    	  if (elemTable != null) {
    		  if (elemTable.isEmpty()) { this.table[index] = null; }

    		  if (elemTable.size() > 1) { this.COLLISIONS--; }		// se tiver mais de um elemento na posição diminui a quantidade de colisoes e de elementos na hash table e remove o elemento
    		  
    		  elemTable.remove(element);							// se não tiver mais de um elemento o elemento sera removido e a quantidade de elementos tbm será diminuida
    		  this.elements--;
    		  
    	  }
      }
   }

   @Override
   public T search(T element) {
		T resultElem = null;

		if (element != null) {
			int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));
			@SuppressWarnings("unchecked")
			LinkedList<T> elemTable = ((LinkedList<T>) this.table[index]);

			if (elemTable != null) {
				if (elemTable.contains(element)) { resultElem = elemTable.get(elemTable.indexOf(element)); }
			}
		}
		return resultElem;
   }

   @Override
   public int indexOf(T element) {
	   int result = -1;
	   int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));
	   @SuppressWarnings("unchecked")
	   LinkedList<T> elemTable = ((LinkedList<T>) this.table[index]);
	
	   if (elemTable != null) {
		   if (elemTable.contains(element)) { result = index; }
	   }
	   return result;
   }

}
