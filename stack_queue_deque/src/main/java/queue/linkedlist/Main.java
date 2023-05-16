package queue.linkedlist;

public class Main {

	public static void main(String[] args) {
		LinkedListQueue<Integer> queue = new LinkedListQueue<>();

		for (int i = 0; i < 25; i++) {
			queue.offer(i + 1);
		}

		System.out.println("queue = " + queue);
		// queue = [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 ]

		///////////////////////////////////////////////////////////////////////
		int peek = queue.peek();
		System.out.println("peek = " + peek); // peek = 1
		System.out.println("queue = " + queue); // queue = [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 ]

		///////////////////////////////////////////////////////////////////////
		int pop = queue.poll();
		System.out.println("poll = " + pop); // poll = 1
		System.out.println("queue = " + queue); // queue = [ 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 ]

		///////////////////////////////////////////////////////////////////////
		System.out.println("queue.empty() = " + queue.isEmpty()); // queue.empty() = false
		queue.clear();
		System.out.println("queue.empty() = " + queue.isEmpty()); // queue.empty() = true
	}

}
