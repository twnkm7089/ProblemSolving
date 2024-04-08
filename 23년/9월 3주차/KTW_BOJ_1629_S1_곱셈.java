import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 재귀써도 뭐해도 안풀려 질문게시판 참조
 * 2. 기본적으로 재귀, f(1) = A%C, f(N) = ...
 * 3. 홀수면 f((N-1)/2)*f((N-1)/2)*f(1)
 * 4. 짝수면 f(N/2) * f(N/2)
 * 5. 범위 초과 안되게 만드는게 중요
 * */
public class KTW_BOJ_1629_S1_곱셈 {
	public static int A;
	public static int C;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		
		//output
		System.out.println(multi(B));
	}
	//함수
	public static long multi(int n) {
		if(n == 1) {//f(1)
			return A%C;
		}
		//f(n) 재귀 계산
		if(n%2 == 1) {
			long temp = multi((n-1)/2)%C;
			return (temp * temp)%C * (A%C) % C;//중간마다 %C해서 범위 초과 안되게 만들기.
		} else {
			long temp = multi(n/2)%C;
			return (temp * temp) % C;//중간마다 %C해서 범위 초과 안되게 만들기.
		}
	}
	
	
}
