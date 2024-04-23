import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589_G5_보물섬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int max = 0;
		
		//상하좌우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		//bfs + bruteforce
		//bruteforce면 시간 초과라는 선입견 가지지 말고, 계산한 후 코드 쓰자
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'L') { //육지면 bfs로 가장 멀리 떨어진 곳 찾기
					int temp = 0;
					boolean[][] visited = new boolean[N][M];
					Queue<int[]> bfs = new LinkedList<>();
					
					bfs.add(new int[] {i, j});
					visited[i][j] = true;
					while(true) {
						int size = bfs.size();
						for(int k = 0; k < size; k++) {
							int[] pos = bfs.poll();
							for(int d = 0; d < 4; d++) {
								int nr = pos[0] + dr[d];
								int nc = pos[1] + dc[d];
								
								if(isInside(nr, nc, N, M) && map[nr][nc] == 'L' && !visited[nr][nc]) {
									visited[nr][nc] = true;
									bfs.add(new int[] {nr, nc});
								}
							}
						}
						
						//bfs end? break
						if(bfs.isEmpty()) {
							break;
						}
						
						temp++;
					}
					
					max = Math.max(max, temp);
				}
			}
		}
		
		System.out.println(max);
	}
	
	public static boolean isInside(int r, int c, int N, int M) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
