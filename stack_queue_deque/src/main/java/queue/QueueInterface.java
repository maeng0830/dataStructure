package queue;

public interface QueueInterface<T> {

	/**
	 * 큐의 맨 끝에 요소를 추가한다.
	 * @param element: 추가할 요소
	 * @return 추가 성공: true, 추가 실패: false
	 */
	boolean offer(T element);

	/**
	 * 큐의 맨 앞 요소를 제거하고 반환한다.
	 * @return 큐의 맨 앞 요소
	 */
	T poll();

	/**
	 * 큐의 맨 앞 요소를 반환한다.
	 * @return 큐의 맨 앞 요소
	 */
	T peek();

	/**
	 * 큐의 맨 앞 요소를 제거하고 반환한다.
	 * 요소가 없을 경우 예외 발생 <- poll()과 차이점
	 * @return 큐의 맨 앞 요소
	 */
	T remove();

	/**
	 * 큐의 맨 앞 요소를 반환한다.
	 * 요소가 없을 경우 예외 발생 <- peek()와 차이점
	 * @return 큐의 맨 앞 요소
	 */
	T element();
}
