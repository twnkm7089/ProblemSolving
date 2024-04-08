package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 그리디 알고리즘
 * 2. 큰 단위부터 각 화폐 단위별 낼 수 있는 화폐 개수 구해 그만큼 주고 빼기.
 * 3. 이 문제는 문제점이 몇가지 있음, 동전이 배수단위로 커져야 함.(예: 400, 500 공존은 불가. 500은 400의 배수 아니므로)
 * 4. 기본적 그리디 문제.
 * */
public class KTW_BOJ_11047_S4 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coins = new int[N];
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		int answer = 0;
		for(int i = N-1; i >= 0; i--) {//각 동전당 낼 수 있는 화폐 수 세어 더해주고 총액에서 빼주기.
			int temp = K / coins[i];
			answer += temp;
			K = K - coins[i]*temp;
		}
		
		System.out.println(answer);
		
		
		
	}//end of main method
}//end of class
