import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 행, 열별 누적합을 구함
 * 2. 우선 첫 행의 경우 누적합을 각 칸에 저장
 * 3. 그 다음 열은 이전 행의 값 + 현재 행의 누적합을 저장.
 * 4. (x1, y1) (x2, y2)가 들어오면 ((x2,y2)-(x2,y1-1)) - ((x1-1,y2) - (x1-1, y1-1)))의 값을 출력
 * */
public class KTW_BOJ_11660_S1_구간합구하기5 {
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N+1][N+1];
		//구간합으로 입력 행렬
		for(int i = 1; i <= N; i++) {
			int num = 0;
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = map[i-1][j];
				num += Integer.parseInt(st.nextToken());
				map[i][j] += num;
			}
		}
		
		//결과 출력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int sum = (map[x2][y2] - map[x1-1][y2]) - (map[x2][y1-1] - map[x1-1][y1-1]);
			System.out.println(sum);
		}
		
	}//end of main method
	
	
}
