package linkedlist.doublelinkedlist;

import java.util.NoSuchElementException;

public class DoubleLinkedList<T> {

	private NodeBi<T> head;
	private NodeBi<T> tail;
	private int size;

	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	private NodeBi<T> search(int index) {
		// 범위 밖이면 예외 발생
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		// index의 위치에 따라 탐색 시작점(head, tail) 선택
		if (index + 1 > size / 2) {
			NodeBi<T> cur = tail;

			for (int i = size - 1; i > index; i--) {
				cur = cur.prev;
			}

			return cur;
		} else {
			NodeBi<T> cur = head;

			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}

			return cur;
		}
	}

	public boolean addFirst(T data) {
		NodeBi<T> newNode = new NodeBi<>(data);
		newNode.next = this.head;

		if (this.head != null) {
			this.head.prev = newNode;
			this.head = newNode;
		} else {
			this.head = newNode;
			this.tail = newNode;
		}

		size++;
		return true;
	}

	public boolean addLast(T data) {
		NodeBi<T> newNode = new NodeBi<>(data);

		// 첫번째 노드인 경우
		if (size == 0) {
			return addFirst(data); // 메소드 내부에서 size++ 실행
		}

		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;

		size++;
		return true;
	}

	// add는 기본적으로 addLast로 동작한다.
	public boolean add(T data) {
		return addLast(data);
	}

	public boolean add(int index, T data) {
		// 잘못된 인덱스를 참조할 경우 예외 발생
		// 마지막에 추가하는 것을 고려하여 index > size
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			return addFirst(data);
		}

		if (index == size) {
			return addLast(data);
		}

		NodeBi<T> prevNode = search(index - 1);
		NodeBi<T> nextNode = search(index);

		NodeBi<T> newNode = new NodeBi<>(data);

		prevNode.next = newNode;
		nextNode.prev = newNode;

		newNode.prev = prevNode;
		newNode.next = nextNode;

		size++;
		return true;
	}

	// remove()는 첫번째 노드륵 삭제한다
	public boolean remove() {
		// 노드가 없을 경우 예외 발생
		if (head == null) {
			throw new NoSuchElementException();
		}

		NodeBi<T> nextNode = head.next;

		if (nextNode != null) {
			nextNode.prev = null;
			head = nextNode;
		} else {
			head = null;
			tail = null;
		}

		size--;

		return true;
	}

	public boolean remove(int index) {
		// 잘못된 인덱스를 참조할 경우 예외 발생
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// 첫번째 노드를 삭제하는 경우
		if (index == 0) {
			remove();
			return true;
		}

		NodeBi<T> removeNode = search(index);
		NodeBi<T> prevNode = removeNode.prev;
		NodeBi<T> nextNode = removeNode.next;

		removeNode.prev = null;
		removeNode.next = null;
		removeNode.data = null;

		prevNode.next = nextNode;
		if (nextNode != null) {
			nextNode.prev = prevNode;
		} else {
			tail = prevNode;
		}

		size--;

		return true;
	}

	public boolean remove(T data) {

		NodeBi<T> x = head;

		for (; x != null; x = x.next) {
			if (data.equals(x.data)) {
				break;
			}
		}

		if (x == null) {
			return false;
		} else if (x.equals(head)) {
			return remove();
		} else {
			NodeBi<T> prevNode = x.prev;
			NodeBi<T> nextNode = x.next;

			x.prev = null;
			x.next = null;
			x.data = null;

			prevNode.next = nextNode;
			if (nextNode != null) {
				nextNode.prev = prevNode;
			} else {
				tail = prevNode;
			}

			size--;

			return true;
		}
	}

	public T get(int index) {
		return search(index).data;
	}

	public void set(T data, int index) {
		NodeBi<T> targetNode = search(index);
		targetNode.data = data;
	}

	public int indexOf(T data) {
		int index = 0;

		for (NodeBi<T> x = head; x != null; x = x.next) {
			if (data.equals(x.data)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public int lastIndexOf(T data) {
		int index = size - 1;

		for (NodeBi<T> x = tail; x != null; x = x.prev) {
			if (data.equals(x.data)) {
				return index;
			}
			index--;
		}

		return -1;
	}

	public boolean contains(T data) {
		return indexOf(data) == -1;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void clear() {
		for(NodeBi<T> x = head; x != null;) {
			NodeBi<T> nextNode = x.next;

			x.data = null;
			x.prev = null;
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

		for (NodeBi<T> x = head; x != null; x = x.next) {
			stringBuilder.append(" ").append(x.data);
		}

		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}
}
