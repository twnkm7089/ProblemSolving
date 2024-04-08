import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 육지 만나면 DFS 실행
 * 2. 이게 다임
 * */
public class KTW_BOJ_4963_S2_섬의개수 {
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int w, h;
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) break;//0, 0은 나감
			
			map = new int[h][w];
			visited = new boolean[h][w];
			//map 입력
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//dfs
			int cnt = 0;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {//육지이고, 미방문
						//카운트 증가
						cnt++;
						//dfs로 연결된 육지 모두 방문
						dfs(i,j);
					}
				}
			}
			//결과 출력
			System.out.println(cnt);
		}
		
		
		
		
		
		
		br.close();
	}//end of main method
	
	//dfs
	static void dfs(int r, int c) {
		visited[r][c] = true;//방문표시
		for(int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			//index범위 내, 육지, 미방문이면 dfs
			if(nr >= 0 && nr < h && nc >= 0 && nc < w && map[nr][nc] == 1 && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
	
}
