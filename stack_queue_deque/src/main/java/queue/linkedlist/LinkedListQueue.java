package queue.linkedlist;

import java.util.NoSuchElementException;
import queue.QueueInterface;

public class LinkedListQueue<T> implements QueueInterface<T> {

	private Node<T> head; // 첫 번째 노드에 대한 참조
	private Node<T> tail; // 마지막 노드에 대한 참조
	private int size; // 요소 개수(= head~tail의 노드 개수)

	public LinkedListQueue() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	// 큐 맨 끝에 요소 추가
	@Override
	public boolean offer(T element) {
		// 추가할 새로운 노드 생성
		Node<T> newNode = new Node<>(element);

		// 새로운 노드가 첫 번째 노드인 경우, head가 새로운 노드를 참조
		// 아닌 경우, tail.next가 새로운 노드를 참조
		if (size == 0) {
			head = newNode;
		} else {
			tail.next = newNode;
		}

		// tail이 새로운 노드를 참조
		tail = newNode;

		size++;

		return true;
	}

	// 맨 앞 요소를 제거하고 반환
	@Override
	public T poll() {
		// 삭제할 요소가 없는 경우, null
		if (size == 0) {
			return null;
		}

		T element = head.element; // 삭제하고 반환하려는 요소
		Node<T> nextNode = head.next; // 삭제할 노드의 다음 노드

		// head가 삭제할 노드의 다음 노드를 참조
		head = null;
		head = nextNode;

		size--;

		return element;
	}

	// 맨 앞 요소를 반환
	@Override
	public T peek() {
		// 삭제할 요소가 없는 경우, null
		if (size == 0) {
			return null;
		}

		return head.element;
	}

	// 맨 앞 요소를 제거하고 반환
	// 요소가 없을 경우, 예외 발생 <- poll()과 차이
	@Override
	public T remove() {
		// 요소가 없을 경우 예외 발생
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return poll();
	}

	// 맨 앞 요소를 반환
	// 요소가 없을 경우, 예외 발생 <- peak()와 차이
	@Override
	public T element() {
		// 요소가 없을 경우 예외 발생
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return peek();
	}

	// 요소 개수를 반환
	public int size() {
		return size;
	}

	// 큐가 비어있는 지 여부를 반환
	public boolean isEmpty() {
		return size == 0;
	}

	// 목표 요소가 저장되어있는 지 여부 반환
	public boolean contains(T element) {
		// head부터 순회하면서 요소를 가진 노드가 있는지 확인
		for (Node<T> x = head; x != null; x = x.next) {
			if (element.equals(x.element)) {
				return true;
			}
		}

		return false;
	}

	// 큐의 모든 요소를 제거한다.
	public void clear() {
		for (Node<T> x = head; x != null;) {
			Node<T> next = x.next;
			x.element = null;
			x.next = null;
			x = next;
		}

		size = 0;
		head = null;
		tail = null;
	}

	// toString() 오버라이딩
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[");

		for (Node<T> x = head; x != null; x = x.next) {
			stringBuilder.append(" ").append(x.element);
		}

		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}
}
