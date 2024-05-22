import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1495_S1_기타리스트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] changeAble = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			changeAble[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] vol = new boolean[N+1][M+1];
		boolean isAble = true;
		
		vol[0][S] = true;
		
		//N <= 50, M <= 1000이라 탐색해봐야 최대 5000셀 -> 가능
		for(int i = 1; i <= N; i++) {
			boolean able = false;
			int change = changeAble[i];
			
			for(int j = 0; j <= M; j++) {
				if(vol[i-1][j]) {
					if(j - change >= 0) {
						vol[i][j-change] = true;
						able = true;
					}
					if(j + change <= M) {
						vol[i][j+change] = true;
						able = true;
					}
				}
			}
			
			if(!able) {
				//중간에 볼륨 조절 불가
				isAble = false;
				break;
			}
		}
		
		if(isAble) {
			//출력
			for(int i = M; i >= 0; i--) {
				if(vol[N][i]) {
					//최대값
					System.out.println(i);
					break;
				}
			}
		} else {
			//출력 불가
			System.out.println(-1);
		}
		
		
		br.close();
		
	}
}
