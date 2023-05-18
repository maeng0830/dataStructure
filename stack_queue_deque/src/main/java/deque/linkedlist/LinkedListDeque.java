package deque.linkedlist;

import java.util.NoSuchElementException;
import queue.QueueInterface;

public class LinkedListDeque<T> implements QueueInterface<T> {

	private NodeBi<T> head; // 첫 번째 노드에 대한 참조
	private NodeBi<T> tail; // 마지막 노드에 대한 참조
	private int size; // 요소 개수(= head~tail의 노드 개수)

	public LinkedListDeque() {
		head = null;
		tail = null;
		size = 0;
	}

	// 덱 맨 끝에 요소를 추가한다.
	// offer는 기본적으로 offerLast와 동일하게 작동한다.
	@Override
	public boolean offer(T element) {
		return offerLast(element);
	}

	// 덱 맨 끝에 요소를 추가한다.
	public boolean offerLast(T element) {
		// 기존에 노드가 없었을 경우
		if (size == 0) {
			return offerFirst(element);
		}

		// 추가할 새로운 노드 생성
		NodeBi<T> newNode = new NodeBi<>(element);

		tail.next = newNode; // 기존 tail이 다음 노드로 새로운 노드를 참조
		newNode.prev = tail; // 새로운 노드가 이전 노드로 기존 tail을 참조
		tail = newNode; // tail이 새로운 노드를 참조

		size++;

		return true;
	}

	// 덱 맨 앞에 요소를 추가한다.
	public boolean offerFirst(T element) {
		// 추가할 새로운 노드 생성
		NodeBi<T> newNode = new NodeBi<>(element);

		// 새로운 노드의 next가 기존 head를 참조
		newNode.next = head;

		if (head != null) { // 기존에 노드가 있었을 경우
			head.prev = newNode;
			head = newNode;
		} else { // 기존에 노드가 없었을 경우
			head = newNode;
			tail = newNode;
		}

		size++;

		return true;
	}

	// 덱 맨 앞의 요소를 제거하고 반환
	// poll은 기본적으로 pollFirst와 동일하게 작동한다.
	@Override
	public T poll() {
		return pollFirst();
	}

	// 덱 맨 앞의 요소를 제거하고 반환
	public T pollFirst() {
		// 삭제할 요소가 없는 경우, null
		if (size == 0) {
			return null;
		}

		T element = head.element; // 삭제하고 반환하려는 요소
		NodeBi<T> nextNode = head.next; // 삭제할 노드의 다음 노드

		if (nextNode != null) { // 기존 노드가 두 개 이상
			nextNode.prev = null;
			head = null;
			head = nextNode;
		} else { // 기존 노드가 한 개
			head = null;
			tail = null;
		}

		size--;

		return element;
	}

	// 덱 맨 끝의 요소를 제거하고 반환
	public T pollLast() {
		// 삭제할 요소가 없는 경우, null
		if (size == 0) {
			return null;
		}

		T element = tail.element; // 삭제하고 반환하려는 요소
		NodeBi<T> prevNode = tail.prev; // 삭제할 노드의 이전 노드

		if (prevNode != null) { // 기존 노드가 두 개 이상
			prevNode.next = null;
			tail = null;
			tail = prevNode;
		} else { // 기존 노드가 한 개
			head = null;
			tail = null;
		}

		size--;

		return element;
	}

	// 맨 앞 요소를 반환
	@Override
	public T peek() {
		return peekFirst();
	}

	// 맨 앞 요소를 반환
	public T peekFirst() {
		// 요소가 없을 경우 null 반환
		if (size == 0) {
			return null;
		}

		return head.element;
	}

	// 맨 끝 요소를 반환
	public T peekLast() {
		// 요소가 없을 경우 null 반환
		if (size == 0) {
			return null;
		}

		return tail.element;
	}

	// 맨 앞 요소를 제거하고 반환
	// 요소가 없을 경우, 예외 발생 <- pollFirst()와 차이
	@Override
	public T remove() {
		return removeFirst();
	}

	// 맨 앞 요소를 제거하고 반환
	// 요소가 없을 경우, 예외 발생 <- pollFirst()와 차이
	public T removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return pollFirst();
	}

	// 맨 끝 요소를 제거하고 반환
	// 요소가 없을 경우, 예외 발생 <- pollLast()와 차이
	public T removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return pollLast();
	}

	// 맨 앞 요소를 반환
	// 요소가 없을 경우, 예외 발생 <- peek()와 차이
	@Override
	public T element() {
		return peekFirst();
	}

	// 맨 앞 요소를 반환
	// 요소가 없을 경우, 예외 발생 <- peekFirst()와 차이
	public T getFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return peekFirst();
	}

	// 맨 끝 요소를 반환
	// 요소가 없을 경우, 예외 발생 <- peekLast()와 차이
	public T getLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return peekLast();
	}

	// 요소 개수 반환
	public int size() {
		return size;
	}

	// 덱이 비어있는 지 여부 반환
	public boolean isEmpty() {
		return size == 0;
	}

	// 목표 요소의 저장 여부 반환
	public boolean contains(T element) {
		for (NodeBi<T> x = head; x != null; x = x.next) {
			if (element.equals(x.element)) {
				return true;
			}
		}

		return false;
	}

	// 덱의 모든 요소 제거
	public void clear() {
		for (NodeBi<T> x = head; x != null; ) {
			NodeBi<T> next = x.next;
			x.element = null;
			x.next = null;
			x = next;
		}

		size = 0;
		head = null;
		tail = null;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[");

		for (NodeBi<T> x = head; x != null; x = x.next) {
			stringBuilder.append(" ").append(x.element);
		}

		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}
}
