package deque.linkedlist;

import deque.array.ArrayDeque;

public class Main {

	public static void main(String[] args) {
		LinkedListDeque<Integer> linkedListDeque = new LinkedListDeque<>();

		for (int i = 0; i < 10; i++) {
			linkedListDeque.offer(i + 1);
		}

		for (int i = 10; i < 20; i++) {
			linkedListDeque.offerLast(i + 1);
		}

		System.out.println("linkedListDeque = " + linkedListDeque);
		// linkedListDeque = [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ]

		/////////////////////////////////////////////////////////////////////////
		System.out.println("linkedListDeque.peekFirst() = " + linkedListDeque.peekFirst());
		// linkedListDeque.peekFirst() = 1
		System.out.println("linkedListDeque.peekLast() = " + linkedListDeque.peekLast());
		// linkedListDeque.peekLast() = 20
		System.out.println("linkedListDeque = " + linkedListDeque);
		// linkedListDeque = [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ]
		System.out.println("linkedListDeque.pollFirst() = " + linkedListDeque.pollFirst());
		// linkedListDeque.pollFirst() = 1
		System.out.println("linkedListDeque.pollLast() = " + linkedListDeque.pollLast());
		// linkedListDeque.pollLast() = 20
		System.out.println("linkedListDeque = " + linkedListDeque);
		// linkedListDeque = [ 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 ]

		/////////////////////////////////////////////////////////////////////////
		System.out.println("linkedListDeque.isEmpty() = " + linkedListDeque.isEmpty());
		// linkedListDeque.isEmpty() = false
		linkedListDeque.clear();
		System.out.println("linkedListDeque.isEmpty() = " + linkedListDeque.isEmpty());
		// linkedListDeque.isEmpty() = true
	}
}
