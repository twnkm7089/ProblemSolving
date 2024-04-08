import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_BOJ_20058_G3_마법사상어와파이어스톰 {
	static int[][] map;
	static boolean[][] visited;
	static int mapSize;
	//delta
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		//map input
		mapSize = 1<<N;
		map = new int[mapSize][mapSize];
		
		for(int i = 0; i < mapSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < mapSize; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		if(!st.hasMoreTokens())
			st = new StringTokenizer(br.readLine());
		for(int tc = 0; tc < Q; tc++) {
			int L = Integer.parseInt(st.nextToken());
			int boxSize = 1<<L; //2의 L승
			//돌리기 시작
			int[][] temp = new int[mapSize][mapSize];
			//시작점 잡기
			for(int i = 0; i < mapSize; i+=boxSize) {
				for(int j = 0; j < mapSize; j+=boxSize) {
					//박스 내부에서 
					//시작점으로부터 어느 방향으로 몇칸 가느냐를 기준으로 계산하면 용이
					for(int r = 0; r < boxSize; r++) {
						for(int c = 0; c < boxSize; c++) {
							temp[i+c][j+boxSize-1-r] = map[r+i][c+j];
						}
					}
				}
			}
			//갱신
			map = temp;
			
			//얼음 탐색 시작
			//줄어드는 칸 큐에 넣기
			Queue<int[]> queue = new LinkedList<>();
			
			for(int r = 0; r < mapSize; r++) {
				for(int c = 0; c < mapSize; c++) {
					//이미 다 녹았으면 생략
					if(map[r][c] == 0) continue;
					//근처 칸의 수 계산
					int cnt = 0;
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(nr >= 0 && nr < mapSize && nc >= 0 && nc < mapSize && map[nr][nc] > 0) {
							cnt++;
						}
					}
					//작으면 줄어들 것이라 표시
					if(cnt < 3) {
						queue.add(new int[] {r, c});
					}
				}
			}
			
			//녹이기
			while(!queue.isEmpty()) {
				int[] pos = queue.poll();
				map[pos[0]][pos[1]]--;
			}
		}
		
		//정답 분석
		int sum = 0;
		int ice = 0;
		visited = new boolean[mapSize][mapSize];
		for(int i = 0; i < mapSize; i++) {
			for(int j = 0; j < mapSize; j++) {
				sum += map[i][j];
				if(map[i][j] > 0 && !visited[i][j]) {
					//dfs이용한 얼음 크기 계산
					ice = Math.max(dfs(i, j), ice);
				}
			}
		}
		
		//결과 출력
		System.out.println(sum);
		System.out.println(ice);
	}//end of main method
	
	//dfs, 반환은 얼음 크기
	public static int dfs(int i, int j) {
		//방문 표시
		visited[i][j] = true;
		int size = 0;
		//4방향 누적 얼음 크기 구하기
		for(int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr >= 0 && nr < mapSize && nc >= 0 && nc < mapSize && map[nr][nc]>0 && !visited[nr][nc]) {
				size += dfs(nr, nc);
			}
		}
		//반환
		return size + 1;
	}

	
	
}//end of class
