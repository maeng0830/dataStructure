package stack;

public class Main {

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < 10; i++) {
			stack.push(i + 1);
		}

		System.out.println("stack = " + stack);
		//stack = [ 1 2 3 4 5 6 7 8 9 10 ]

		///////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 10; i++) {
			System.out.print(stack.search(i + 1) + " ");
		}

		System.out.println();
		// 10 9 8 7 6 5 4 3 2 1

		///////////////////////////////////////////////////////////////////////
		int peek = stack.peek();
		System.out.println("peek = " + peek); // peek = 10
		System.out.println("stack = " + stack); // stack = [ 1 2 3 4 5 6 7 8 9 10 ]

		///////////////////////////////////////////////////////////////////////
		int pop = stack.pop();
		System.out.println("pop = " + pop); // pop = 10
		System.out.println("stack = " + stack); // stack = [ 1 2 3 4 5 6 7 8 9 ]

		///////////////////////////////////////////////////////////////////////
		System.out.println("stack.empty() = " + stack.empty()); // stack.empty() = false
		stack.clear();
		System.out.println("stack.empty() = " + stack.empty()); // stack.empty() = true
	}
}
