import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. DFS 이용
 * 2. 물의 수위가 1일 때부터 최대수위까지 연산
 * 3. 현재 물의 높이보다 높은 곳 만나면 카운트 한 후, 주위의 모든 물의 높이보다 높은 곳 탐색
 * */
public class KTW_BOJ_2468_S1_안전영역 {
	static int[][] map;
	static boolean[][] visited;
	static int max, N;
	
	//delta
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		N = Integer.parseInt(br.readLine());
		//map input
		map = new int[N][N];
		max = 0;//물 최대 수위 구하기
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > max) {//물 최대 수위 구하기
					max = map[i][j];
				}
			}
		}
		//답 구하기
		int answer = 1;//초기값은 아무것도 안 잠겼을 경우의 1
		//각 수위별 분석
		for(int h = 1; h <= max; h++) {
			int cnt = 0;
			visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					//현재 물의 높이보다 높으면서 아직 방문 안한 곳 만나면
					if(map[i][j] > h && !visited[i][j]) {
						//카운트, dfs
						cnt++;
						dfs(i, j, h);
					}
				}
			}
			//지역 수(cnt)결과와 현재 answer 비교 후 갱신
			if(cnt > answer) {
				answer = cnt;
			}
		}
		//결과 출력
		System.out.println(answer);
		
	}//end of main method
	
	
	//dfs
	public static void dfs(int r, int c, int h) {
		//방문표시
		visited[r][c] = true;
		//delta 탐색
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			//index 범위 내, 수위보다 높은, 미방문 지역은 dfs
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] > h && !visited[nr][nc]) {
				dfs(nr, nc, h);
			}
		}
	}
}//end of class
