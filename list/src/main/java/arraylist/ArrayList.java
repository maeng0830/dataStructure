package arraylist;

import java.util.Arrays;

public class ArrayList<T> {

	private final static int DEFAULT_CAPACITY = 10;

	private int size;
	private T[] array;

	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.size = 0;
		this.array = (T[]) new Object[0];
	}

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		this.size = 0;
		this.array = (T[]) new Object[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		int curCapacity = array.length;

		if (curCapacity == 0) {
			array = (T[]) new Object[DEFAULT_CAPACITY];
			return;
		}

		if (size == array.length) {
			int newCapacity = curCapacity * 2;

			array = Arrays.copyOf(array, newCapacity);
			return;
		}

		if (size < curCapacity / 2) {
			int newCapacity = curCapacity / 2;

			array = Arrays.copyOf(array, newCapacity);
		}
	}

	public boolean add(T data) {
		addLast(data);
		return true;
	}

	public void addLast(T data) {
		resize();

		array[size] = data;
		size++;
	}

	public void add(int index, T data) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (index == size) {
			addLast(data);
		} else {
			resize();

			for (int i = size; i > index; i--) {
				array[i] = array[i - 1];
			}

			array[index] = data;
			size++;
		}
	}

	public T get(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		return array[index];
	}

	public void set(int index, T data) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		array[index] = data;
	}

	public int indexOf(T data) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(data)) {
				return i;
			}
		}

		return -1;
	}

	public int lastIndexOf(T data) {
		for (int i = size - 1; i >= 0; i--) {
			if (array[i].equals(data)) {
				return i;
			}
		}

		return -1;
	}

	public boolean contains(T data) {
		return indexOf(data) >= 0;
	}

	public boolean remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		for (int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
			array[i + 1] = null;
		}

		size--;
		resize();

		return true;
	}

	public boolean remove(T data) {
		int index = indexOf(data);

		if (index == -1) {
			return false;
		} else {
			remove(index);
			return true;
		}
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		int oldSize = size;
		array = (T[]) new Object[size];
		size = 0;
		resize();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[");

		for (int i = 0; i < size; i++) {
			stringBuilder.append(" " + array[i]);
		}

		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}
}
