import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 빙산의 위치를 큐에 저장
 * 2. 탐색을 시작하면 빙산의 위치를 꺼내 근처에 0이 있다면 높이를 줄임
 * 3. 그것을 after 배열에 저장(map에 바로 저장하면 다른 영역 탐색에 문제 발생)
 * 4. 빙산이 아직 남아 있으면 다시 큐에 넣기
 * 5. 1턴을 마치고 DFS 이용 덩어리 찾기 수행
 * 6. 2개 이상이면 탐색 종료, 결과 출력
 * */
public class KTW_BOJ_2573_G4_빙산 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//map input
		map = new int[N][M];
		Queue<int[]> queue = new LinkedList<>(); //빙산 위치 저장 배열
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0) {
					queue.add(new int[] {i, j});
				}
			}
		}
		
		int answer = 0;
		int cnt = 1;
		//시작
		while(!queue.isEmpty()) {
			//빙산이 녹는다.
			int size = queue.size();
			int[][] after = new int[N][M];
			for(int i = 0; i < size; i++) {
				int[] pos = queue.poll();
				int height = map[pos[0]][pos[1]];
				
				//delta search
				for(int d = 0; d < 4; d++) {
					int nr = pos[0] + dr[d];
					int nc = pos[1] + dc[d];
					//높이 조절
					if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
						height--;
					}
				}
				
				//다음 height 결정되었으니 after에 반영
				//기본 0으로 초기화 됨 반영
				if(height > 0) {
					queue.add(new int[] {pos[0],pos[1]});
					after[pos[0]][pos[1]] = height;
				}
			}
			
			//after를 map으로
			map = after;
						
			//덩어리 수 분석
			//dfs 이용
			int temp = 0;
			visited = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != 0 && !visited[i][j]) {
						temp++;
						dfs(i, j);
					}
				}
			}
			//덩어리가 2개 이상이면 answer에 저장하고 탐색 종료
			if(temp > 1) {
				answer = cnt;
				break;
			}
			
			cnt++;
		}
		//결과 출력
		System.out.println(answer);
	}//end of main method
	
	//dfs
	public static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && !visited[nr][nc]) {
				dfs(nr, nc);
			}
		}
	}
	
}//end of class
