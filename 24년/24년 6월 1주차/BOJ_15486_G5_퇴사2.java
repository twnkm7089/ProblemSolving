import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15486_G5_퇴사2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//Dynamic Programming
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		int N = Integer.parseInt(br.readLine());
		int[][] counsel = new int[N+1][2];
		int[] dP = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			counsel[i][0] = Integer.parseInt(st.nextToken());
			counsel[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//dP
		//dP[i]는 i일까지 벌 수 있는 최대 금액
		//i일에 하는 상담 포함
		//예를들어, 1일에 1일짜리 상담을 하고 10을 받으면 dP[1] = 10
		//상향식 dP
		for(int i = 1; i <= N; i++) {
			int T = counsel[i][0];
			int P = counsel[i][1];
			
			if(i+T-1 <= N) {
				dP[i+(T-1)] = Math.max(dP[i+(T-1)], dP[i-1] + P); //상향
			}
			//누적합 업데이트
			dP[i] = Math.max(dP[i-1], dP[i]); //오늘 업데이트 분 포함
		}
		
		System.out.println(dP[N]);
	}
}
