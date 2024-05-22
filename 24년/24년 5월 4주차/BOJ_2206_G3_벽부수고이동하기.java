import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_G3_벽부수고이동하기 {
	static int N, M;
	static int min;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//벽 목록
		List<int[]> wall = new ArrayList<>();
		
		//map input
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
				
				if(str.charAt(j) == '1') {
					wall.add(new int[] {i, j});
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		
		bfs();
		
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(min);
		}

		
		br.close();
		
	}
	
	public static boolean isValid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
	
	public static void bfs() {
		boolean[][] visited = new boolean[N][M];
		boolean[][] brokenVisited = new boolean[N][M]; //부순 상태로 방문?
		Queue<int[]> queue = new LinkedList<>(); //벽 아직 안부숨

		//init
		visited[0][0] = true;
		
		//if startpoint == endpoint
		if(N == 1 && M == 1) {
			min = 1;
			return;
		}
		
		//[r, c, broken? 0 : 1]
		queue.add(new int[] {0, 0, 0});
		
		int dist = 0;
		//start of bfs
		outer : while(!queue.isEmpty()) {
			dist++;
			//아직 안 부순 벽에 대한 bfs(이게 우선)
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				int[] pos = queue.poll();
				
				//아직 안부순 경우
				if(pos[2] == 0) {		
					//탐색
					for(int d = 0; d < 4; d++) {
						int nr = pos[0] + dr[d];
						int nc = pos[1] + dc[d];
						
						if(isValid(nr, nc) && !visited[nr][nc]) {

							//도착 시 종료
							if(nr == N-1 && nc == M-1) {
								dist++; //다음 방문 때이니
								min = Math.min(min, dist);
								break outer;
							}
							
							//빈 경우
							if(map[nr][nc] == 0) {
								visited[nr][nc] = true;
								queue.add(new int[] {nr, nc, 0});
							} else {
								//안빈 경우
								brokenVisited[nr][nc] = true;
								queue.add(new int[] {nr, nc, 1});
							}
						}
					}
				} else {
					//이미 한번 부셨음
					//탐색
					for(int d = 0; d < 4; d++) {
						int nr = pos[0] + dr[d];
						int nc = pos[1] + dc[d];
						
						if(isValid(nr, nc) && !brokenVisited[nr][nc] && map[nr][nc] == 0) {
							//도착 시 종료
							if(nr == N-1 && nc == M-1) {
								dist++; //다음 방문 때이니
								min = Math.min(min, dist);
								break outer;
							}
							
							brokenVisited[nr][nc] = true;
							queue.add(new int[] {nr, nc, 1});
						}
					}
				}
			}
		} //end of bfs
		
		return;
	}
		
}
