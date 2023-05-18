package deque.array;

import java.util.NoSuchElementException;
import queue.QueueInterface;

public class ArrayDeque<T> implements QueueInterface<T> {

	private static final int DEFAULT_CAPACITY = 10; // 기본 용적 크기

	private T[] array; // 저장 공간
	private int size; // 요소 개수

	private int front; // 시작 인덱스(항상 빈 공간 <- 큐가 비어있는지를 확인하기 위함)
	private int rear; // 마지막 요소의 인덱스

	// 용적 크기 0
	@SuppressWarnings("unchecked")
	public ArrayDeque() {
		this.array = (T[]) new Object[0];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}

	// 용적 크기: capacity
	@SuppressWarnings("unchecked")
	public ArrayDeque(int capacity) {
		this.array = (T[]) new Object[capacity];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}

	@SuppressWarnings("unchecked")
	private void resize() {
		int arrayCapacity = array.length;

		// 저장 공간 0
		if (arrayCapacity == 0) {
			array = (T[]) new Object[DEFAULT_CAPACITY];
			return;
		}

		// 저장 공간이 가득 찬 경우
		if ((rear + 1) % arrayCapacity == front) {
			T[] newArray = (T[]) new Object[arrayCapacity * 2];

			copyArray(arrayCapacity, newArray);
		}

		// 저장 공간/2 > 요소 개수
		if (size < (arrayCapacity/2)) {
			T[] newArray = (T[]) new Object[Math.max(arrayCapacity / 2, DEFAULT_CAPACITY)];

			copyArray(arrayCapacity, newArray);
		}
	}

	private void copyArray(int arrayCapacity, T[] newArray) {
		for (int i = 1, j = front + 1; i <= size; i++, j++) {
			newArray[i] = array[j % arrayCapacity];
		}

		this.array = newArray;
		front = 0;
		rear = size;
	}

	// 덱 맨 끝에 요소를 추가한다.
	// offer는 기본적으로 offerLast와 동일하게 작동한다.
	@Override
	public boolean offer(T element) {
		return offerLast(element);
	}

	// 덱 맨 끝에 요소를 추가한다.
	public boolean offerLast(T element) {
		// 요소 삽입 전에 용적 크기 재조정
		resize();

		// 요소가 저장될 인덱스
		rear = (rear + 1) % array.length;

		array[rear] = element;
		size++;

		return true;
	}

	// 덱 맨 앞에 요소를 추가한다.
	public boolean offerFirst(T element) {
		// 요소 삽입 전에 용적 크기를 재조정
		resize();

		// 현재 front 인덱스(빈 공간)에 요소 삽입
		array[front] = element;

		// 새로운 front 지정
		front = (front - 1 + array.length) % array.length;
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
		// 삭제할 요소가 없을 경우 null 반환
		if (size == 0) {
			return null;
		}

		front = (front + 1) % array.length;
		// 삭제하고 반환할 요소
		T element = array[front];

		array[front] = null;
		size--;

		resize();

		return element;
	}

	// 덱 맨 끝의 요소를 제거하고 반환
	public T pollLast() {
		// 삭제할 요소가 없을 경우 null 반환
		if (size == 0) {
			return null;
		}

		// 삭제하고 반환할 요소
		T element = array[rear];
		array[rear] = null;

		// 새로운 rear
		rear = (rear - 1 + array.length) % array.length;
		size--;

		resize();

		return element;
	}

	// 맨 앞 요소를 반환
	@Override
	public T peek() {
		return peekFirst();
	}

	// 맨 앞 요소를 반환
	public T peekFirst() {
		// 삭제할 요소가 없을 경우 null 반환
		if (size == 0) {
			return null;
		}

		return array[(front + 1) % array.length];
	}

	// 맨 끝 요소를 반환
	public T peekLast() {
		// 삭제할 요소가 없을 경우 null 반환
		if (size == 0) {
			return null;
		}

		return array[rear];
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
		int start = (front + 1) % array.length;

		for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
			if(array[idx].equals(element)) {
				return true;
			}
		}
		return false;
	}

	// 덱의 모든 요소 제거
	@SuppressWarnings("unchecked")
	public void clear() {
		this.array = (T[]) new Object[array.length];

		size = 0;
		front = 0;
		rear = 0;

		resize();
	}

	@Override
	public String toString() {
		int start = (front + 1) % array.length;

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("[");

		for(int i = 0, idx = start; i < size; i++, idx = (idx + 1) % array.length) {
			stringBuilder.append(" ").append(array[idx]);
		}

		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}
}
