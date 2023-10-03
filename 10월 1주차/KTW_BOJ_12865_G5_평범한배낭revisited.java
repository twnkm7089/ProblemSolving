import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KTW_BOJ_12865_G5_평범한배낭revisited {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dP = new int[N+1][K+1];
		//물건 W, V 입력
		int[][] stuff = new int[N+1][2];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			stuff[i][0] = Integer.parseInt(st.nextToken());
			stuff[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//dP
		//i번째 물건까지 반영, j킬로그램 배낭에 넣을 수 있는 최대 가치
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= K; j++) {
				//i-1번째까지의 반영 vs 이번 턴의 j-1킬로그램까지의 반영
				dP[i][j] = Math.max(dP[i-1][j], dP[i][j-1]);
				//물건 weight 이상이면 dP[i-1][j-W] + V도 비교 대상 추가
				if(j >= stuff[i][0]) {
					dP[i][j] = Math.max(dP[i][j], dP[i-1][j-stuff[i][0]] + stuff[i][1]);
				}
			}
		}
		//결과 출력
		System.out.println(dP[N][K]);
	}//end of main method
}//end of class
