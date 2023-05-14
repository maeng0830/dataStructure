package stack;

public interface StackInterface<T> {

	/**
	 * 스택의 최상단에 요소를 추가한다.
	 * @param element: 스택에 추가할 요소
	 * @return 스택에 추가된 요소
	 */
	T push(T element);

	/**
	 * 스택의 최상단 요소를 제거하고 반환한다.
	 *
	 * @return 제거된 요소
	 */
	T pop();

	/**
	 * 스택의 최상단 요소를 제거하지 않고 반환한다.
	 * @return 스택 맨 위에 있는 요소
	 */
	T peek();

	/**
	 * 스택 최상단과 목표 요소의 거리를 반환
	 * @param element: 목표 요소
	 * @return 목표 요소와 스택 최상단의 거리
	 */
	int search(T element);

	/**
	 * 스택의 요소 개수를 반환
	 * @return 스택의 요소 개수
	 */
	int size();

	/**
	 * 스택의 모든 요소를 삭제
	 */
	void clear();

	/**
	 * 빈 스택인지 여부를 반환
	 * @return 빈 스택이면 true, 아니면 false
	 */
	boolean empty();
}
