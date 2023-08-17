import java.util.Scanner;

public class KTW_BOJ_8320_B2 {
	public static void main(String[] args) {
		/*알고리즘 설명
		 * 1. 검사 범위는 root N까지만 해도 충분
		 * 2. N/i - (i-1)만 해도 충분
		 * 3. 예) 6의 경우 1*1, 1*2, 1*3...으로 6개
		 * 4. 2*1, 2*2, 2*3의 경우 앞은 1*2와 중복이니 제외.
		 * 5. 3부터는 겹침.
		 * */
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int limit = (int)Math.sqrt(N);
		int answer = 0;
		for(int i = 1; i <= limit; i++) {
			answer += ((N / i) - (i-1)); //공식
		}
		
		System.out.println(answer);
	}
}
