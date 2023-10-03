import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KTW_BOJ_2293_G5_동전1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dP = new int[N+1][K+1];
		
		//코인 종류 입력
		int[] coins = new int[N+1];
		for(int i = 1; i <= N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		//dP배열
		for(int i = 1; i <= N; i++) {
			//이 코인이 나온 경우의 수 추가할 것이다.
			int coin = coins[i];
			
			//배수 추가(이것만 쓴 경우)
			for(int j = coin; j <= K; j+=coin) {
				dP[i][j]++;
			}
			
			//탐색
			for(int j = 1; j <= K; j++) {
				//전 줄에 무언가 만들어진 경우의 수 있으면
				if(dP[i-1][j] != 0) {
					//j원에서 이번 코인 1개 더하기, 2개 더하기, ...
					int temp = j;
					while(temp <= K) {
						//지난번에 j원 만드는데 걸린 모든 경우의 수 더하기
						dP[i][temp]+=dP[i-1][j];
						temp += coin;
					}
				}
			}
		}
		//결과 출력
		System.out.println(dP[N][K]);
	}//end of main method
}//end of class
