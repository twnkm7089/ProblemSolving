import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 그리디 알고리즘(모든 화폐단위가 서로로 나누어 떨어짐)
 * 2. 큰 단위부터 거스를 수 있는 만큼 주고 다음 단위로 계속 가기
 * */
public class KTW_BOJ_5585_B2_거스름돈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		//거스름돈
		int change = 1000 - N;
		//화폐단위
		int[] money = {500, 100, 50, 10, 5, 1};
		//답 구하기
		int answer = 0;
		for(int i = 0; i < 6; i++) {
			int temp = change / money[i];
			answer += temp;
			change -= money[i]*temp;	
		}
		//정답 출력
		System.out.println(answer);
		
	}
}
