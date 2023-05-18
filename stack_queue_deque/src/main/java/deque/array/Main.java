package deque.array;

public class Main {

	public static void main(String[] args) {
		ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();

		for (int i = 0; i < 10; i++) {
			arrayDeque.offer(i + 1);
		}

		for (int i = 10; i < 20; i++) {
			arrayDeque.offerLast(i + 1);
		}

		System.out.println("arrayDeque = " + arrayDeque);
		// arrayDeque = [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ]

		/////////////////////////////////////////////////////////////////////////
		System.out.println("arrayDeque.peekFirst() = " + arrayDeque.peekFirst());
		// arrayDeque.peekFirst() = 1
		System.out.println("arrayDeque.peekLast() = " + arrayDeque.peekLast());
		// arrayDeque.peekLast() = 20
		System.out.println("arrayDeque = " + arrayDeque);
		// arrayDeque = [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 ]
		System.out.println("arrayDeque.pollFirst() = " + arrayDeque.pollFirst());
		// arrayDeque.pollFirst() = 1
		System.out.println("arrayDeque.pollLast() = " + arrayDeque.pollLast());
		// arrayDeque.pollLast() = 20
		System.out.println("arrayDeque = " + arrayDeque);
		// arrayDeque = [ 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 ]

		/////////////////////////////////////////////////////////////////////////
		System.out.println("arrayDeque.isEmpty() = " + arrayDeque.isEmpty());
		// arrayDeque.isEmpty() = false
		arrayDeque.clear();
		System.out.println("arrayDeque.isEmpty() = " + arrayDeque.isEmpty());
		// arrayDeque.isEmpty() = true
	}

}
