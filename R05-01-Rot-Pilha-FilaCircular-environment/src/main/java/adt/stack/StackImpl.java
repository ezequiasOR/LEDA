package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (this.top == -1) {
			return null;
		}
		return array[top];
	}

	@Override
	public boolean isEmpty() {
		if (this.top() == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (this.top >= this.array.length - 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException();
		} else {
			this.top += 1;
			this.array[this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException();
		} else {
			this.top -= 1;
			return array[top + 1]; 
		}
	}

}
