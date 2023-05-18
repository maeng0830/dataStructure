package deque.linkedlist;

public class NodeBi<T> {

	T element; // 요소
	NodeBi<T> next; // 다음 노드 참조
	NodeBi<T> prev; // 이전 노드 참조

	public NodeBi(T element) {
		this.element = element;
		this.next = null;
		this.prev = null;
	}
}
