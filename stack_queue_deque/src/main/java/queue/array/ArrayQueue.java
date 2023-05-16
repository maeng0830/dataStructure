package queue.array;

import java.util.NoSuchElementException;
import queue.QueueInterface;

public class ArrayQueue<T> implements QueueInterface<T> {

	private static final int DEFAULT_CAPACITY = 64; // 기본 용적 크기

	private T[] array; // 저장 공간
	private int size; // 요소 개수

	private int front; // 시작 인덱스(항상 빈 공간 <- 큐가 비어있는지를 확인하기 위함)
	private int rear; // 마지막 요소의 인덱스

	// 용적 크기 0
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		this.array = (T[]) new Object[0];
		this.size = 0;
		this.front = 0;
		this.rear = 0;
	}

	// 용적 크기: capacity
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
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

	// 큐 맨 끝에 데이터 추가
	@Override
	public boolean offer(T element) {
		resize();

		rear = (rear + 1) % array.length;

		array[rear] = element;
		size++;

		return true;
	}

	// 맨 앞 요소를 제거하고 반환
	@Override
	public T poll() {

		if (size == 0) {
			return null;
		}

		front = (front + 1) % array.length;
		T element = array[front];

		array[front] = null;
		size--;

		resize();

		return element;
	}

	// 맨 앞 요소를 반환
	@Override
	public T peek() {
		if (size == 0) {
			return null;
		}

		return array[(front + 1) % array.length];
	}

	// 맨 앞 요소를 제거하고 반환
	// 요소가 없을 경우, 예외 발생 <- poll()과 차이
	public T remove() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return poll();
	}

	// 맨 앞 요소를 반환
	// 요소가 없을 경우, 예외 발생 <- peak()와 차이
	public T element() {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		return peek();
	}

	private void copyArray(int arrayCapacity, T[] newArray) {
		for (int i = 1, j = front + 1; i <= size; i++, j++) {
			newArray[i] = array[j % arrayCapacity];
		}

		this.array = newArray;
		front = 0;
		rear = size;
	}

	// 요소 개수 반환
	public int size() {
		return size;
	}

	// 큐가 비어있는 지 여부 반환
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

	// 큐의 모든 요소 제거
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
