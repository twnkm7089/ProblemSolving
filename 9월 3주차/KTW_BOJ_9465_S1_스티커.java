import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. Dynamic Programming
 * 2. dP[0][i]을 뗄 때 고려해야 될 케이스는 dP[1][i-1]을 떼고 이걸 떼는 경우,  dP[1][i-2]를 떼고 이걸 떼는 경우
 * 3. 이에 따라 처리했다.
 * */
public class KTW_BOJ_9465_S1_스티커 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			//input
			int N = Integer.parseInt(br.readLine());
			int[][] sticker = new int[2][N];
			int[][] dP = new int[2][N];
			
			for(int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//초기값 설정
			if(N>=2) {
				dP[0][0] = sticker[0][0];
				dP[1][0] = sticker[1][0];
				dP[0][1] = sticker[1][0] + sticker[0][1];
				dP[1][1] = sticker[0][0] + sticker[1][1];
			} else {
				dP[0][0] = sticker[0][0];
				dP[1][0] = sticker[1][0];
			}
			
			//dP 수행
			for(int i = 2; i < N; i++) {
				dP[0][i] = sticker[0][i] + Math.max(dP[1][i-1], dP[1][i-2]);
				dP[1][i] = sticker[1][i] + Math.max(dP[0][i-1], dP[0][i-2]);
			}
			//결과 출력
			System.out.println(Math.max(dP[0][N-1], dP[1][N-1]));
		}
		
		
		
		
		br.close();

	}
	
	
}
