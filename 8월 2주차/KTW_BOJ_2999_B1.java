import java.util.Scanner;

public class KTW_BOJ_2999_B1 {
	/*알고리즘 설명
	 * 1. 우선은 행렬의 r, c가 무엇인지를 먼저 파악
	 * 2. 문자열 분석해보니 해독을 위해서는 문자열의 0, 0+r, 0+2r번, ... 인덱스 접근 후 1, 1+r, 1+2r, ...을 반복 
	 * 3. 그러다가 마자막으로 c-1, c-1+r, c-1+2r, ...으로 끝
	 * 4. 이를 구현.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int r = 0; //row
		int c = 0; //column
		int n = s.length(); //lenght
		int a = (int)Math.sqrt(n); //root n까지만 탐색하면 충분
		for(int i = 1; i <= a; i++) { //r, c 계산
			if(n % i == 0) {
				r = i;
				c = n / i;
			}
		}
		
		String answer = ""; //정답 문자열
		for (int i = 0; i < r; i++) { //위의 알고리즘대로
			int idx = i;
			for(int j = 0; j < c; j++) {
				answer += s.charAt(idx);
				idx += r;
			}
		}
		//출력
		System.out.println(answer);
	}
}
