import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*알고리즘 설명
 * 1. Dynamic Programming
 * 2. dP배열을 이차원으로 만든다.
 * 3. dP[i][0]은 rgb[i][0] + (i-1까지 계산시 1, 2번 칸에 들어갈 수 있는 최소값 중 작은 값)
 * 4. 이걸 1, 2번 칸에도 도입
 * 5. 마지막까지 한 후, dP[N-1][0,1,2]중 최소값 출력
 * */

public class KTW_BOJ_1149_S1_RGB거리 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//rgb거리 input
		int[][] rgb = new int[N][3];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			rgb[i][0] = Integer.parseInt(st.nextToken());
			rgb[i][1] = Integer.parseInt(st.nextToken());
			rgb[i][2] = Integer.parseInt(st.nextToken());
		}
		//dP 계산
		int[][] dP = new int[N][3];
		dP[0] = rgb[0];//첫 값은 초기값
		//dP계산
		for(int i = 1; i < N; i++) {
			dP[i][0] = rgb[i][0] + Math.min(dP[i-1][1], dP[i-1][2]);
			dP[i][1] = rgb[i][1] + Math.min(dP[i-1][0], dP[i-1][2]);
			dP[i][2] = rgb[i][2] + Math.min(dP[i-1][0], dP[i-1][1]);
		}
		//결과 출력
		System.out.println(Math.min(Math.min(dP[N-1][0], dP[N-1][1]), dP[N-1][2]));
		
		
		br.close();
	}	
}
