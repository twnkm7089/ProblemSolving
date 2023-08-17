import java.util.Scanner;

public class KTW_BOJ_1003_S3 {
	/*알고리즘 설명
	 * 1. 0과 1을 호출하는 횟수를 담은 배열은 만든다.
	 * 2. 0과 1은 각각 본인을 한번씩만 출력한다.
	 * 3. fibo(n)=fibo(n-2)+fibo(n-1)이니, 이들이 불러오는 fibo(0)과 fibo(1)의 횟수는 fibo(n-2)가 불러왔던 횟수 + fibo(n-1)이 불러왔던 횟수다.
	 * 4. 그래서 미리 배열에 그 값을 구해놓고 불러만 온다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//zero, one array
		int[] zero = new int[41];
		int[] one = new int[41];
		//fibonacci(0), fibonacci(1)이 불러오는 횟수 초기화
		zero[0] = 1;
		zero[1] = 0;
		one[0] = 0;
		one[1] = 1;
		for(int i = 2; i <= 40; i++) {//배열 구하기, fibonacci(i)가 불러오는 횟수는 fibonacci(i-1)이 불러온 횟수 + fibonacci(i-2)가 불러온 횟수
			zero[i] = zero[i-2] + zero[i-1];
			one[i] = one[i-2] + one[i-1];
		}
		//이제 입력 받고 해당 내용 출력
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			int N = sc.nextInt();
			System.out.println(zero[N] + " " + one[N]);
		}
		
	}
}
