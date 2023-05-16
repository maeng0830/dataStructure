package linkedlist.singlelinkedlist;

public class Node<T> {
	T data; // 데이터
	Node<T> next; // 다음 노드에 대한 참조

	public Node(T data) {
		this.data = data;
	}
}
