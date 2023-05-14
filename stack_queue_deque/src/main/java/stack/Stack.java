package stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> implements StackInterface<T> {

	private static final int DEFAULT_CAPACITY = 10; // 기본 용적 크기

	private T[] array; // 저장 공간
	private int size; // 요소 개수

	// 기본 용적 크기: 0
	@SuppressWarnings("unchecked")
	public Stack() {
		this.array = (T[]) new Object[0];
		this.size = 0;
	}

	// 기본 용적 크기: capacity
	@SuppressWarnings("unchecked")
	public Stack(int capacity) {
		this.array = (T[]) new Object[capacity];
		this.size = 0;
	}

	// 용적 크기 재조정
	@SuppressWarnings("unchecked")
	private void resize() {

		// 기본 용적 크기: 0
		if (array.length == 0) {
			array = (T[]) new Object[DEFAULT_CAPACITY];
			return;
		}

		// 용적 크기 == 요소 개수
		if (array.length == size) {
			int newCapacity = array.length * 2;

			array = Arrays.copyOf(array, newCapacity);
			return;
		}

		// 요소 개수 < 용적 크기/2
		if ((array.length / 2) > size) {
			int newCapacity = array.length / 2;

			array = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
		}
	}

	// 스택 최상단에 요소 추가
	@Override
	public T push(T element) {
		// 요소 추가 전에 용적 크기 재조정
		resize();

		array[size] = element;
		size++;

		return element;
	}

	// 스택 최상단 요소를 제거하고 반환
	@Override
	public T pop() {
		// 요소가 없을 경우 예외 발생
		if (size == 0) {
			throw new EmptyStackException();
		}

		// 삭제될 요소를 반환하기 위한 임시 저장
		T element = array[size - 1];
		// 삭제될 요소 삭제
		array[size - 1] = null;

		size--;
		resize();

		return element;
	}

	// 스택 최상단 요소 반환
	@Override
	public T peek() {
		// 요소가 없으면 예외 발생
		if (size == 0) {
			throw new EmptyStackException();
		}

		return array[size - 1];
	}

	// 스택 최상단과 목표 요소의 거리 반환(시작 값: 1)
	@Override
	public int search(T element) {
		// 저장된 요소가 없으면 예외 발생
		if (size == 0) {
			throw new EmptyStackException();
		}

		// 목표 요소가 null이면 예외 발생
		if (element == null) {
			throw new IllegalArgumentException();
		}

		// 스택 최상단부터 순회하면서 요소 탐색
		for (int i = size - 1; i >= 0; i--) {
			// 요소를 찾으면 거리 반환
			if (array[i].equals(element)) {
				return size - i;
			}
		}

		// 목표 요소가 없을 경우 -1 반환
		return -1;
	}

	// 저장된 요소 개수 반환
	@Override
	public int size() {
		return size;
	}

	// 저장 공간 비우기
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.array = (T[]) new Object[size];
		size = 0;
		// 용적 크기 -> 기존 크기/2
		resize();
	}

	// 스택이 비어있다면 true, 비어있지 않다면 false
	@Override
	public boolean empty() {
		return size == 0;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[");

		for (int i = 0; i < size; i++) {
			stringBuilder.append(" ").append(array[i]);
		}

		stringBuilder.append(" ]");

		return stringBuilder.toString();
	}
}
