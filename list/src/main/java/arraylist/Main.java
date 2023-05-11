package arraylist;

public class Main {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			list.add(i + 1);
		}

		System.out.println("list = " + list);
		// list = [ 1 2 3 4 5 6 7 8 9 10 ]

		////////////////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 10; i++) {
			list.add(i, (i + 1) * 100);
		}

		System.out.println("list = " + list);
		// list = [ 100 200 300 400 500 600 700 800 900 1000 1 2 3 4 5 6 7 8 9 10 ]

		////////////////////////////////////////////////////////////////////////////////////
		list.remove(0);
		list.remove(Integer.valueOf(1));

		System.out.println("list = " + list);
		// list = [ 200 300 400 500 600 700 800 900 1000 2 3 4 5 6 7 8 9 10 ]

		////////////////////////////////////////////////////////////////////////////////////
		System.out.println("list.contains(1000) = " + list.contains(1000));
		// true
		System.out.println("list.contains(100) = " + list.contains(100));
		// false

		////////////////////////////////////////////////////////////////////////////////////
		System.out.println("list.indexOf(200) = " + list.indexOf(200));
		// 0
		System.out.println("list.lastIndexOf(10) = " + list.lastIndexOf(10));
		// 17

		////////////////////////////////////////////////////////////////////////////////////
		System.out.println("list.isEmpty() = " + list.isEmpty());
		// false
		list.clear();
		System.out.println("list.isEmpty() = " + list.isEmpty());
		// true
	}
}
