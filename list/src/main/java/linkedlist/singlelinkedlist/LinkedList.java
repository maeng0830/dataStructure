package linkedlist.singlelinkedlist;

import java.util.NoSuchElementException;

public class LinkedList<T> {

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	private Node<T> search(int index) {

		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> x = head;

		for (int i = 0; i < index; i++) {
			x = x.next;
		}

		return x;
	}

	public boolean addFirst(T data) {
		Node<T> newNode = new Node<>(data);
		newNode.next = head;
		head = newNode;
		size++;

		if (head.next == null) {
			tail = head;
		}

		return true;
	}

	public boolean addLast(T data) {
		if (size == 0) {
			return addFirst(data);
		}

		Node<T> newNode = new Node<>(data);

		tail.next = newNode;
		tail = newNode;
		size++;

		return true;
	}

	public boolean add(T data) {
		return addLast(data);
	}

	public boolean add(int index, T data) {

		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return addFirst(data);
		}

		if (index == size) {
			return addLast(data);
		}

		Node<T> prevNode = search(index - 1);
		Node<T> nextNode = prevNode.next;
		Node<T> newNode = new Node<>(data);

		prevNode.next = newNode;
		newNode.next = nextNode;
		size++;

		return true;
	}

	public boolean remove() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		head = head.next;

		if (size == 0) {
			tail = null;
		}

		size--;

		return true;
	}

	public boolean remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return remove();
		}

		Node<T> prevNode = search(index - 1);
		Node<T> removeNode = prevNode.next;
		Node<T> nextNode = removeNode.next;

		prevNode.next = nextNode;

		if (prevNode.next == null) {
			tail = prevNode;
		}

		size--;

		return true;
	}

	public boolean remove(T data) {
		Node<T> prevNode = head;
		Node<T> x = head;

		for (; x != null; x = x.next) {
			if (data.equals(x.data)) {
				break;
			}
			prevNode = x;
		}

		if (x == null) {
			return false;
		} else if (x.equals(head)) {
			return remove();
		} else {
			prevNode.next = x.next;

			if (prevNode.next == null) {
				tail = prevNode;
			}

			size--;
			return true;
		}
	}

	public T get(int index) {
		return search(index).data;
	}

	public void set(int index, T data) {
		Node<T> targetNode = search(index);
		targetNode.data = data;
	}

	public int indexOf(T data) {
		int index = 0;

		for (Node<T> x = head; x != null; x = x.next) {
			if (data.equals(x.data)) {
				return index;
			}
			index++;
		}

		return -1;
	}

	public boolean contains(T data) {
		return indexOf(data) >= 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void clear() {
		for(Node<T> x = head; x != null;) {
			Node<T> nextNode = x.next;

			x.data = null;
			x.next = null;

			x = nextNode;
		}

		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[");

		for (Node<T> x = head; x != null; x = x.next) {
			stringBuilder.append(" ").append(x.data);
		}

		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}
}
