package queue.linkedlist;

public class Node<T> {

	T element; // 요소
	Node<T> next; // 다음 노드에 대한 참조

	public Node(T element) {
		this.element = element;
		this.next = null;
	}
}
