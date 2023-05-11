package linkedlist.doublelinkedlist;

public class NodeBi<T> {
	T data;
	NodeBi<T> prev;
	NodeBi<T> next;

	public NodeBi(T data) {
		this.data = data;
	}
}
