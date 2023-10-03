import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 완전탐색
 * 2. 빈칸 중 3개 뽑아 벽 세우고 바이러스 전파(BFS)
 * 3. 전파 시킨 후 안전구역 탐색 후 갱신
 * */
public class KTW_BOJ_14502_G4_연구소 {
	static int N, M;
	static int[][] map;
	static int safety;
	static List<int[]> blank = new ArrayList<>();
	static List<int[]> virus = new ArrayList<>();
	static int[] sel = new int[3];
	//delta
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//map input
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) blank.add(new int[] {i, j});
				if(map[i][j] == 2) virus.add(new int[] {i, j});
			}
		}

		//안전구역 초기화 후 조합
		safety = 0;
		comb(0, 0);
		
		//결과 출력
		System.out.println(safety);
	}//end of main method

	//조합 제조
	public static void comb(int idx, int sidx) {
		//기저부
		if(sidx == 3) {
			//벽 위치 지정
			int[] wall1 = blank.get(sel[0]);
			int[] wall2 = blank.get(sel[1]);
			int[] wall3 = blank.get(sel[2]);
			
			boolean[][] visited = new boolean[N][M];
			//벽세우기
			map[wall1[0]][wall1[1]] = 1;
			map[wall2[0]][wall2[1]] = 1;
			map[wall3[0]][wall3[1]] = 1;
			
			//bfs이용한 virus 전파
			for(int i = 0; i < virus.size(); i++) {
				int[] start = virus.get(i);
				Queue<int[]> queue = new LinkedList<>();
				queue.add(start);
				visited[start[0]][start[1]] = true;
				
				while(!queue.isEmpty()) {
					int[] pos = queue.poll();
					int r = pos[0];
					int c = pos[1];
					
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 0) {
							queue.add(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
				}
			}//전파 완료
			
			
			//안전 구역 검사
			int cnt = 0;
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < M; k++) {
					if(map[j][k] == 0 && !visited[j][k]) {
						cnt++;
					}
				}
			}

			//갱신
			if(cnt > safety) {				
				safety = cnt;
			}
			
			
			//벽 없애기
			map[wall1[0]][wall1[1]] = 0;
			map[wall2[0]][wall2[1]] = 0;
			map[wall3[0]][wall3[1]] = 0;
			return;
			
		}

		
		//조합 만들기(재귀부)
		for(int i = idx; i <= blank.size()-3+sidx; i++) {
			sel[sidx] = i;
			comb(i+1, sidx+1);
		}
	}
}//end of class
