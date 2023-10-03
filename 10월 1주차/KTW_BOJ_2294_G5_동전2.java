import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 주의) 살짝 보고 힌트를 갖고 푼 것이니 나중에 다시 풀어보길
 * 2. 각 동전 관련 해 j=1 부터 j=K까지 연산
 * 3. Math.min(dP[j], dP[j-coin]+1) //동전 추가 vs 안 추가
 * 4. 기존 dP 배열에 하는 것이니 결과가 반영되어 여러 coin에 대해 대응 가능.
 * */
public class KTW_BOJ_2294_G5_동전2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		//coin input
		int[] coins = new int[N+1];
		for(int i = 1; i <= N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		//dP초기화
		int[] dP = new int[K+1];
		Arrays.fill(dP, Integer.MAX_VALUE);
		dP[0] = 0;
		
		//각 코인 추가하며
		for(int i = 1; i <= N; i++) {
			int coin = coins[i];
			for(int j = 1; j <= K; j++) {
				if((j - coin) >= 0 && dP[j-coin] != Integer.MAX_VALUE) {//dP[j-coin]이 존재하는 상황에서...
					dP[j] = Math.min(dP[j], dP[j-coin]+1);//비교 갱신
				}
			}
		}
		
		
		//결과 출력
		if(dP[K] == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(dP[K]);
		}
		
	}//end of main method
}//end of class
