package linkedlist.doublelinkedlist;

public class NodeBi<T> {
	T data; // 데이터
	NodeBi<T> prev; // 이전 노드 참조
	NodeBi<T> next; // 다음 노드 참조

	public NodeBi(T data) {
		this.data = data;
	}
}
