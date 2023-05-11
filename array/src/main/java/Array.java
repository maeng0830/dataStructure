import java.util.Arrays;

/**
 * 1차원 배열은 int, double, String 등 다양한 타입의 데이터를 원소로 가질 수 있다.
 * 2차원 배열은 이러한 1차원 배열을 원소로 가지는 1차원 배열을 의미한다.
 */
public class Array {

	public static void main(String[] args) {
//		=====================2차원 배열의 선언==========================
		// 앞으로 1차원 배열을 원소로 가지는 배열을 외부 배열, 원소로 담기는 1차원 배열을 내부 배열이라고 지칭하겠다.

		// 앞의 [3]은 외부 배열의 길이, 뒤의 [3]은 내부 배열의 길이를 의미한다.
		int[][] ints = new int[3][3];

		// 외부 배열의 길이만 먼저 선언하고, 내부 배열의 길이는 나중에 선언해도 된다.
		// 일반적인 1차원 배열의 선언을 생각해보자. 길이만 지정하고, 안에 담길 원소는 나중에 저장해도 된다. 2차원 배열도 똑같다.
		// 내부 배열은 각각 개별적인 원소이기 때문에 길이를 다르게 선언할 수 있다.
		long[][] longs = new long[3][];
		longs[0] = new long[3];
		longs[1] = new long[2];
		longs[2] = new long[1];

//		=====================2차원 배열의 원소 접근==========================
		// 2차원 배열의 원소 접근은 외부 배열 인덱스 -> 내부 배열 인덱스로 진행된다.
		// 이러한 접근 방법을 통해 ints와 longs의 데이터를 채워보겠다.
		int con = 1;
		for (int i = 0; i < ints.length; i++) {
			for (int j = 0; j < ints[i].length; j++) {
				ints[i][j] = con + j;
			}
			con += ints[i].length;
			System.out.println(Arrays.toString(ints[i]));
		}
//		[1, 2, 3]
//		[4, 5, 6]
//		[7, 8, 9]

		con = 1;
		for (int i = 0; i < longs.length; i++) {
			for (int j = 0; j < longs[i].length; j++) {
				longs[i][j] = con + j;
			}
			con += longs[i].length;
			System.out.println(Arrays.toString(longs[i]));
		}
//		[1, 2, 3]
//		[4, 5]
//		[6]

//		=====================행렬로 2차원 배열 이해하기==========================
		// 모든 내부 배열의 길이가 같은 2차원 배열은 사각형의 행렬로 이해할 수 있다.
		// 행은 외부 배열의 인덱스, 열을 내부 배열의 인덱스이다. 보편적으로 행을 y, 열을 x라고 지칭하여 사용한다.

//		 ints를 행렬로 나타내면 아래와 같다.
//			 0, 1, 2  x축
//		  0 [1, 2, 3]
//		  1	[4, 5, 6]
//		  2	[7, 8, 9]
//		  y축

		// x = 0, y = 2에 해당하는 원소에 접근
		System.out.println(ints[2][0]); // print: 7
	}
}
