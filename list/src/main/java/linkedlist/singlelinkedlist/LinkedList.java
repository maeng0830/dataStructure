package linkedlist.singlelinkedlist;

import java.util.NoSuchElementException;

public class LinkedList<T> {

	private Node<T> head; // 첫 번째 노드에 대한 참조
	private Node<T> tail; // 마지막 노드에 대한 참조
	private int size; // 저장된 데이터 개수(= head ~ tail 노드 개수)

	// LinkedList 생성자
	// 아직 노드가 없으므로 head, tail은 null, size는 0이다.
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	// 목표 인덱스의 노드를 반환하는 메소드
	private Node<T> search(int index) {

		// 목표 인덱스의 값이 가능한 범위의 값인지 확인한다.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node<T> x = head;

		// 목표 인덱스만큼 순차적으로 접근한다.
		for (int i = 0; i < index; i++) {
			x = x.next;
		}

		return x;
	}

	// 맨 앞에 데이터를 저장하는 메소드
	public boolean addFirst(T data) {
		// 새로운 데이터를 갖는 노드 새로운 생성
		Node<T> newNode = new Node<>(data);
		// 새로운 노드는 첫 번째에 위치할 것이므로, 새로운 노드는 기존의 head를 참조한다.
		newNode.next = head;
		// 새로운 노드를 head로 지정
		head = newNode;
		size++;

		// head의 다음 노드가 null이라는 것은 노드가 1개라는 의미
		if (head.next == null) {
			// tail을 head와 동일한 노드로 지정
			tail = head;
		}

		return true;
	}

	// 맨 끝에 데이터를 저장하는 메소드
	public boolean addLast(T data) {
		// 기존에 데이터가 없을 경우, 맨 앞에 저장하는 것과 동일한 의미이다.
		if (size == 0) {
			return addFirst(data);
		}

		// 기존에 데이터가 있는 경우, 따로 구현부를 작성한다.
		// 새로운 데이터를 갖는 새로운 노드를 생성한다.
		Node<T> newNode = new Node<>(data);

		// 기존의 마지막 노드가 새로운 노드를 참조하도록 지정한다.
		tail.next = newNode;
		// 새로운 노드를 tail로 지정한다.
		tail = newNode;
		size++;

		return true;
	}

	// 기본적인 데이터 저장 메소드
	public boolean add(T data) {
		// 기본적으로 맨 끝에 데이터를 저장하므로, addLast(...)를 호출한다.
		return addLast(data);
	}

	// 목표 인덱스에 데이터를 저장하는 메소드
	public boolean add(int index, T data) {
		// 목표 인덱스의 값이 가능한 범위의 값인지 확인
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// 맨 앞에 데이를 추가하는 경우
		if (index == 0) {
			return addFirst(data);
		}

		// 맨 끝에 데이터를 추가하는 경우
		if (index == size) {
			return addLast(data);
		}

		// 중간에 데이터를 추가하는 경우
		Node<T> prevNode = search(index - 1); // 목표 인덱스 이전의 노드(prevNode)
		Node<T> nextNode = prevNode.next; // prevNode의 기존 다음 노드(nextNode)
		Node<T> newNode = new Node<>(data); // 새로 추가할 노드(newNode)

		prevNode.next = newNode; // prevNode가 newNode를 참조하도록 변경
		newNode.next = nextNode; // newNode가 nextNode를 참조하도록 변경
		size++;

		return true;
	}

	// 기본적인 데이터 삭제 메소드
	// 맨 앞의 데이터 삭제
	public boolean remove() {
		// 저장된 데이터가 존재하는 지 확인
		if (head == null) {
			throw new NoSuchElementException();
		}

		// head를 head의 다음 노드로 변경
		// 기존의 head가 유일한 노드였을 경우, null로 변경되는 것
		head = head.next;

		size--;

		// 저장된 데이터가 없는 경우, tail도 null로 변경
		if (size == 0) {
			tail = null;
		}

		return true;
	}

	// 목표 인덱스 데이터 삭제 메소드
	public boolean remove(int index) {
		// 목표 인덱스가 가능한 범위의 인덱스인지 확인
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// 맨 앞의 데이터를 삭제하는 경우
		if (index == 0) {
			return remove();
		}

		// 그렇지 않는 경우
		Node<T> prevNode = search(index - 1); // 목표 인덱스의 이전 노드(prevNode)
		Node<T> removeNode = prevNode.next; // 목표 인덱스의 노드(removeNode)
		Node<T> nextNode = removeNode.next; // removeNode의 다음 노드(nextNode)

		prevNode.next = nextNode; // prevNode가 nextNode를 참조하도록 변경

		// prevNode가 마지막 노드일 경우, tail로 지정
		if (prevNode.next == null) {
			tail = prevNode;
		}

		size--;

		return true;
	}

	// 목표 데이터 삭제 메소드
	public boolean remove(T data) {
		Node<T> prevNode = head;
		Node<T> x = head;

		// head부터 순차적으로 참조하면서 목표 데이터를 갖는 노드를 찾는다.
		for (; x != null; x = x.next) {
			if (data.equals(x.data)) {
				// 찾을 경우 정지
				break;
			}
			prevNode = x;
		}

		if (x == null) { // 목표 데이터를 가진 노드가 없는 경우
			return false;
		} else if (x.equals(head)) { // 목표 데이터를 가진 노드가 head인 경우
			return remove();
		} else { // 목표 데이터를 가진 노드가 중간 ~ 마지막 노드인 경우
			prevNode.next = x.next;

			if (prevNode.next == null) {
				tail = prevNode;
			}

			size--;
			return true;
		}
	}

	// 목표 인덱스의 데이터 반환 메소드
	public T get(int index) {
		return search(index).data;
	}

	// 목표 인덱스의 데이터 변경 메소드
	public void set(int index, T data) {
		Node<T> targetNode = search(index);
		targetNode.data = data;
	}

	// 목표 데이터의 인데스 반환 메소드
	public int indexOf(T data) {
		int index = 0;

		// head ~ tail까지 순차적으로 참조하면서, 노드의 데이터가 목표 데이터와 일치하는지 확인
		for (Node<T> x = head; x != null; x = x.next) {
			if (data.equals(x.data)) {
				// 노드의 데이터와 목표 데이터가 일치할 경우 해당 인덱스 반환
				return index;
			}
			index++;
		}

		// 목표 데이터를 가진 노드가 없을 경우 -1 반환
		return -1;
	}

	// 목표 데이터가 저장되어있는지 확인하는 메소드
	public boolean contains(T data) {
		return indexOf(data) >= 0;
	}

	// 저장된 데이터 존재 여부를 확인하는 메소드
	public boolean isEmpty() {
		return size == 0;
	}

	// 저장된 데이터의 개수를 반환하는 메소드
	public int size() {
		return size;
	}

	// LinkedList에 저장된 모든 데이터를 삭제하는 메소드
	public void clear() {
		// head ~ tail까지 순차적으로 참조하며 데이터를 제거한다.
		for(Node<T> x = head; x != null;) {
			Node<T> nextNode = x.next;

			x.data = null;
			x.next = null;

			x = nextNode;
		}

		// head, tail, size를 초기 값으로 변경한다.
		head = null;
		tail = null;
		size = 0;
	}

	// toString() 오버라이딩
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
