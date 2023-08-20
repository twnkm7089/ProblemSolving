import java.util.Scanner;

public class KTW_BOJ_11050_B1 {
	/*알고리즘 설명
	 * 1. 이항계수 공식 따라 풀었음.
	 * 2. 분자는 n * (n-1) * ... *(n-k+1)
	 * 3. 분모는 k * (k-1) * ... * 1
	 * 4. 그리고 나누면 답 나옴.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//입력
		int N = sc.nextInt();
		int K = sc.nextInt();
		int numerator = 1, denominator = 1;
		for(int i = N; i >= N-K+1; i--) {//분자
			numerator *= i;
		}
		for(int i = K; i >= 2; i--) {//분모
			denominator *= i;
		}
		System.out.println(numerator / denominator);//나누기
	}
	
	
}

