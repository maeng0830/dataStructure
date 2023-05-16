package linkedlist.doublelinkedlist;

import java.util.NoSuchElementException;

public class DoubleLinkedList<T> {

	private NodeBi<T> head; // 첫 번째 노드 참조
	private NodeBi<T> tail; // 마지막 노드 참조
	private int size; // 저장된 데이터 개수(= head ~ tail 노드 개수)

	// DoubleLinkedList의 기본 생성자
	// 저장된 데이터가 없는 상태이므로, 기본 값으로 초기화한다.
	public DoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	// 목표 인덱스의 노드를 반환한다.
	private NodeBi<T> search(int index) {
		// 목표 인덱스가 불가능한 값이면 예외 발생
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (index + 1 > size / 2) { // 목표 인덱스가 tail에 더 가까운 경우
			NodeBi<T> cur = tail;

			for (int i = size - 1; i > index; i--) {
				cur = cur.prev;
			}

			return cur;
		} else { // 목표 인덱스가 head에 더 가까운 경우
			NodeBi<T> cur = head;

			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}

			return cur;
		}
	}

	// 맨 앞에 데이터를 추가한다.
	public boolean addFirst(T data) {
		// 목표 데이터를 가진 새로운 노드를 생성한다.
		NodeBi<T> newNode = new NodeBi<>(data);
		// 새로운 노드가 다음 노드로 기존의 head를 참조한다.
		newNode.next = this.head;

		if (this.head != null) { // 기존의 head가 null이 아닌 경우 = 새로운 노드 이전에 노드가 존재
			// 기존 head가 이전 노드로 새로운 노드를 참조
			this.head.prev = newNode;
			// head가 새로운 노드를 참조
			this.head = newNode;
		} else { // 기존의 head가 null인 경우 = 새로운 노드가 첫 번째 노드
			// head가 새로운 노드를 참조
			this.head = newNode;
			// tail이 새로운 노드를 참조
			this.tail = newNode;
		}

		size++;
		return true;
	}

	// 맨 끝에 데이터를 추가한다.
	public boolean addLast(T data) {
		// 목표 데이터를 가진 새로운 노드 생성
		NodeBi<T> newNode = new NodeBi<>(data);

		// 새로운 노드 이전에 노드가 없었던 경우
		if (size == 0) {
			return addFirst(data);
		}

		// 새로운 노드 이전에 노드가 있었던 경우
		tail.next = newNode; // 기존 tail이 다음 노드로 새로운 노드를 참조
		newNode.prev = tail; // 새로운 노드가 이전 노드로 기존 tail을 참조
		tail = newNode; // tail이 새로운 노드를 참조

		size++;
		return true;
	}

	// add는 기본적으로 addLast로 동작한다.
	public boolean add(T data) {
		return addLast(data);
	}

	// 목표 인덱스에 데이터를 추가한다.
	public boolean add(int index, T data) {
		// 목표 인덱스가 불가능한 값이면 예외 발생
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}

		// 맨 앞에 데이터를 추가하는 경우
		if (index == 0) {
			return addFirst(data);
		}

		// 맨 끝에 데이터를 추가하는 경우
		if (index == size) {
			return addLast(data);
		}

		// 중간에 데이터를 추가하는 경우
		NodeBi<T> prevNode = search(index - 1); // 목표 인덱스 이전 노드
		NodeBi<T> nextNode = search(index); // 기존의 목표 인덱스 노드

		NodeBi<T> newNode = new NodeBi<>(data); // 목표 데이터를 가진 새로운 노드 생성

		prevNode.next = newNode; // 목표 인덱스 이전 노드가 다음 노드로 새로운 노드를 참조
		nextNode.prev = newNode; // 기존의 목표 인덱스 노드가 이전 노드로 새로운 노드를 참조

		newNode.prev = prevNode; // 새로운 노드가 이전 노드로 목표 인덱스 이전 노드를 참조
		newNode.next = nextNode; // 새로운 노드가 다음 노드로 기존 목표 인덱스 노드를 참조

		size++;
		return true;
	}

	// 첫 번째 데이터를 삭제한다
	public boolean remove() {
		// 노드가 없을 경우 예외 발생
		if (head == null) {
			throw new NoSuchElementException();
		}

		NodeBi<T> nextNode = head.next; // 기존 head의 다음 노드

		if (nextNode != null) { // 기존 head의 다음 노드가 존재하는 경우
			nextNode.prev = null;
			head = nextNode;
		} else { // 기존 head의 다음 노드가 존재하지 않는 경우
			head = null;
			tail = null;
		}

		size--;

		return true;
	}

	// 목표 인덱스의 데이터를 삭제한다.
	public boolean remove(int index) {
		// 목표 인덱스가 불가능한 값인 경우 예외 발생
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// 첫번째 노드를 삭제하는 경우
		if (index == 0) {
			remove();
			return true;
		}

		// 삭제하려는 노드가 첫 번째 노드가 아닌 경우
		NodeBi<T> removeNode = search(index); // 삭제하려는 노드
		NodeBi<T> prevNode = removeNode.prev; // 이전 노드
		NodeBi<T> nextNode = removeNode.next; // 다음 노드

		removeNode.prev = null;
		removeNode.next = null;
		removeNode.data = null;

		prevNode.next = nextNode;
		if (nextNode != null) { // 다음 노드가 null이 아닌 경우 != 이전 노드가 마지막 노드
			nextNode.prev = prevNode;
		} else { // 다음 노드가 null인 경우 = 이전 노드가 마지막 노드
			tail = prevNode;
		}

		size--;

		return true;
	}

	// 목표 데이터를 삭제한다.
	public boolean remove(T data) {

		NodeBi<T> x = head;

		// head부터 순차적으로 참조하면서 목표 데이터를 가진 노드를 찾는다.
		for (; x != null; x = x.next) {
			if (data.equals(x.data)) {
				break;
			}
		}

		if (x == null) { // 목표 데이터를 가진 노드가 없는 경우
			return false;
		} else if (x.equals(head)) { // 목표 데이터를 가진 노드가 head인 경우
			return remove();
		} else { // 목표 데이터를 가진 노드가 head가 아닌 경우
			NodeBi<T> prevNode = x.prev; // 이전 노드
			NodeBi<T> nextNode = x.next; // 다음 노드

			x.prev = null;
			x.next = null;
			x.data = null;

			prevNode.next = nextNode;
			if (nextNode != null) { // 다음 노드가 null이 아닌 경우 != 이전 노드가 마지막 노드
				nextNode.prev = prevNode;
			} else { // 다음 노드가 null인 경우 = 이전 노드가 마지막 노드
				tail = prevNode;
			}

			size--;

			return true;
		}
	}

	// 목표 인덱스의 데이터를 반환한다.
	public T get(int index) {
		return search(index).data;
	}

	// 목표 인덱스의 데이터를 변경한다.
	public void set(T data, int index) {
		NodeBi<T> targetNode = search(index);
		targetNode.data = data;
	}

	// 목표 데이터의 첫 번째 인덱스를 반환한다.
	public int indexOf(T data) {
		int index = 0;

		// head부터 순차적으로 참조하여 목표 데이터를 가진 노드를 찾는다.
		for (NodeBi<T> x = head; x != null; x = x.next) {
			if (data.equals(x.data)) {
				return index; // 찾은 경우 인덱스 반환
			}
			index++;
		}

		return -1; // 찾지 못한 경우 -1 반환
	}

	// 목표 데이터의 마지막 인덱스를 반환한다.
	public int lastIndexOf(T data) {
		int index = size - 1;

		// tail부터 순차적으로 참조하여 목표 데이터를 가진 노드를 찾는다.
		for (NodeBi<T> x = tail; x != null; x = x.prev) {
			if (data.equals(x.data)) {
				return index; // 찾은 경우 인덱스 반환
			}
			index--;
		}

		return -1; // 찾지 못한 경우 -1 반환
	}

	// 목표 데이터의 저장 여부를 반환한다.
	public boolean contains(T data) {
		return indexOf(data) == -1;
	}

	// 저장된 데이터 존재 여부를 반환한다.
	public boolean isEmpty() {
		return size == 0;
	}

	// 저장된 데이터 개수를 반환한다.
	public int size() {
		return size;
	}

	// 저장된 데이터를 모두 비운다.
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

	// toString() 오버라이딩
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
