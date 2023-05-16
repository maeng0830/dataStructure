package arraylist;

import java.util.Arrays;

// ArrayList 객체를 생성할 때, Object[]의 타입을 지정하기 위해 제네릭을 사용한다.
public class ArrayList<T> {

	private final static int DEFAULT_CAPACITY = 10;

	private int size; // 실제 저장된 데이터의 개수
	private T[] array; // 저장 공간

	// 기본 생성자
	// array의 크기는 0으로 초기화한다.
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.size = 0;
		this.array = (T[]) new Object[0];
	}

	// capacity 인자를 갖는 생성자
	// array의 크기를 capacity로 초기화한다.
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		this.size = 0;
		this.array = (T[]) new Object[capacity];
	}

	// 저장 공간(array)의 가변성을 위한 메소드
	@SuppressWarnings("unchecked")
	private void resize() {
		int curCapacity = array.length; // 현재 저장 공간 크기

		// 현재 저장 공간 크기 == 0
		if (curCapacity == 0) {
			// array를 DEFAULT_CAPACITY 크기로 초기화한다.
			array = (T[]) new Object[DEFAULT_CAPACITY];
			return;
		}

		// 현재 저장 공간 크기 == 저장된 데이터 개수
		if (size == array.length) {
			int newCapacity = curCapacity * 2;

			// 현재 저장 공간 크기 * 2의 새로운 배열 생성
			// 새로운 배열에 기존 데이터 복사 및 새로운 배열을 array로 지정
			array = Arrays.copyOf(array, newCapacity);
			return;
		}

		// 현재 저장 공간 크기 / 2 > 저장된 데이터 개수
		if (size < curCapacity / 2) {
			int newCapacity = curCapacity / 2;

			// 현재 저장 공간 크기 / 2의 새로운 배열 생성
			// 새로운 배열에 기존 데이터 복사 및 새로운 배열을 array로 지정
			array = Arrays.copyOf(array, newCapacity);
		}
	}

	// 데이터 추가 메소드
	public boolean add(T data) {
		// 기본적으로 저장 공간 맨 끝에 데이터를 추가하므로 addLast(...) 호출
		addLast(data);
		return true;
	}

	// 저장 공간 맨 끝에 데이터를 추가하는 메소드
	public void addLast(T data) {
		// 데이터 저장 전에 저장 공간 크기 조정
		resize();

		// 저장 공간 맨 끝에 데이터 저장
		array[size] = data;
		size++;
	}

	// 목표 인덱스에 데이터를 저장하는 메소드
	public void add(int index, T data) {
		// 목표 인덱스가 가능한 범위의 값인지 확인
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// 목표 인덱스 == 저장된 데이터 개수 : 저장 공간 맨 끝에 데이터 추가
		if (index == size) {
			addLast(data);
		} else {
			// 데이터 저장 전에 저장 공간 크기 조정
			resize();

			// 목표 인덱스 이후의 데이터들을
			// 기존 인덱스 + 1에 복사
			for (int i = size; i > index; i--) {
				array[i] = array[i - 1];
			}

			// 목표 인덱스에 새로운 데이터 추가
			array[index] = data;
			size++;
		}
	}

	// 목표 인덱스의 데이터를 반환하는 메소드
	public T get(int index) {
		// 목표 인덱스의 값이 가능한 범위의 값인지 확인
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		return array[index];
	}

	// 목표 인덱스의 데이터를 변경하는 메소드
	public void set(int index, T data) {
		// 목표 인덱스의 값이 가능한 범위의 값인지 확인
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		array[index] = data;
	}

	// 목표 데이터의 첫 번째 인덱스를 반환하는 메소드
	public int indexOf(T data) {
		// 첫 번째 데이터부터 목표 데이터와 일치하는 지 확인
		for (int i = 0; i < size; i++) {
			if (array[i].equals(data)) {
				// 일치할 경우 인덱스 반환
				return i;
			}
		}

		// 일치하는 데이터가 없는 경우 -1 반환
		return -1;
	}

	// 목표 데이터의 마지막 인덱스를 반환하는 메소드
	public int lastIndexOf(T data) {
		// 마지막 데이터부터 목표 데이터와 일치하는 지 확인
		for (int i = size - 1; i >= 0; i--) {
			if (array[i].equals(data)) {
				// 일치할 경우 인덱스 반환
				return i;
			}
		}

		// 일치하는 데이터가 없는 경우 -1 반환
		return -1;
	}

	// 목표 데이터가 저장되어 있는 지 확인하는 메소드
	public boolean contains(T data) {
		// indexOf(...)는 일치하는 데이터가 없을 때만 -1을 반환한다.
		return indexOf(data) >= 0;
	}

	// 목표 인덱스의 데이터를 삭제하는 메소드
	public boolean remove(int index) {
		// 목표 인덱스의 값이 가능한 범위의 값인지 확인
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		// 목표 인덱스 다음의 데이터들을
		// 기존 인덱스 - 1에 복사한다.
		for (int i = index; i < size - 1; i++) {
			array[i] = array[i + 1];
			array[i + 1] = null;
		}

		size--;

		// 저장 공간 조정
		resize();

		return true;
	}

	// 목표 데이터를 삭제하는 메소드
	public boolean remove(T data) {
		// 목표 데이터의 인덱스 확인
		int index = indexOf(data);

		// 목표 데이터의 인덱스가 -1이면 데이터가 존재하지 않는 것
		if (index == -1) {
			return false;
		} else {
			// 목표 데이터 삭제
			remove(index);
			return true;
		}
	}

	// 저장된 데이터 개수를 반환하는 메소드
	public int size() {
		return size;
	}

	// 저장된 데이터 존재 여부를 반환하는 메소드
	public boolean isEmpty() {
		return size == 0;
	}

	// 저장 공간을 비우는 메소드
	@SuppressWarnings("unchecked")
	public void clear() {
		// 저장 공간을 새로운 배열로 변경
		array = (T[]) new Object[size];
		size = 0;
		resize();
	}

	// toString() 오버라이딩
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
