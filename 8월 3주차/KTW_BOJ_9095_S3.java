import java.util.Scanner;

public class KTW_BOJ_9095_S3 {
	/*알고리즘 설명 : 계단 문제에서 영감 얻음.
	 * 1. n = (n-3)+3 = (n-2)+2 = (n-1)+1
	 * 2. 고로, n>=4에서 f(n) = f(n-3)+f(n-2)+f(n-1)
	 * 3. 배열을 만들어 해당 값 저장 후 출력(다이나믹 프로그래밍)
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//개수 계산
		int[] sum = new int[11];
		sum[1] = 1;
		sum[2] = 2;
		sum[3] = 4;
		for(int i = 4; i <= 10; i++) {//n>=4에서 f(n) = f(n-3)+f(n-2)+f(n-1)
			sum[i] = sum[i-3] + sum[i-2] + sum[i-1];
		}
		//입력, 출력
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			System.out.println(sum[sc.nextInt()]);
		}
		
	}
}
