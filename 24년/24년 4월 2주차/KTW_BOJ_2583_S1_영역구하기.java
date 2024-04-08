import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_BOJ_2583_S1_영역구하기 {
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		//DFS, BFS
		//BFS로 풀겠음
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//map
		boolean[][] map = new boolean[N][M];
		
		//answer
		List<Integer> result = new ArrayList<>();
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			for(int r = r1; r < r2; r++) {
				for(int c = c1; c < c2; c++) {
					map[r][c] = true;
				}
			}
		}
		
		Queue<int[]> bfs = new LinkedList<>();
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				//BFS
				if(!map[i][j]) {
					int size = 1;
					map[i][j] = true;
					bfs.add(new int[] {i, j});
					
					while(!bfs.isEmpty()) {
						int bfsSize = bfs.size();
						for(int k = 0; k < bfsSize; k++) {
							int[] pos = bfs.poll();
							for(int d = 0; d < 4; d++) {
								int nr = pos[0] + dr[d];
								int nc = pos[1] + dc[d];
								if(isInside(nr, nc) && !map[nr][nc]) {
									size++;
									map[nr][nc] = true;
									bfs.add(new int[] {nr, nc});
								}
							}
						}
					}
					result.add(size);
				}
			}
		}
		
		System.out.println(result.size());
		Collections.sort(result);
		for(int a : result) {
			System.out.print(a + " ");
		}
		
		
	}
	
	//isInside
	public static boolean isInside(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
