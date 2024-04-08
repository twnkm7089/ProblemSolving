import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. BFS 이용
 * 2. 상어가 있는 칸부터 시작해 모든 칸을 채울 때까지 BFS 돌리고 그 횟수를 계산
 * */
public class KTW_BOJ_17086_S2_아기상어2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//map, visited 공간 할당
		int[][] map = new int[N][M];
		boolean[][] visited = new boolean[N][M];
		//bfs for queue
		Queue<int[]> queue = new LinkedList<>();
		
		int total = N*M;//전체 칸수
		int shark = 0;//안전거리 측정용 방문한 칸 개수
		//map input
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//상어 존재시
				if(map[i][j] == 1) {
					//큐에 추가, 방문 표시, shark에 카운트
					queue.add(new int[] {i, j});
					visited[i][j] = true;
					shark++;
				}
			}
		}
		//delta
		int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
		//bfs 횟수
		int cnt = 1;
		//bfs 시작
		while(!queue.isEmpty() && (total != shark)) {
			//하나의 턴
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				//현재 큐에 있던 좌표만큼 모두 빼기
				int[] pos = queue.poll();
				for(int d = 0; d < 8; d++) {
					int nr = pos[0] + dr[d];
					int nc = pos[1] + dc[d];
					//주변 방문 가능하면 방문, 큐에 넣기, 카운트
					if(nr >= 0 && nr < N && nc >= 0 && nc < M && visited[nr][nc] == false) {
						queue.add(new int[] {nr, nc});
						visited[nr][nc] = true;
						shark++;
					}
				}
			}
			//다 방문 시 break
			if(shark == total) {
				break;
			}
			//카운트 추가
			cnt++;
		}
		//결과 출력
		System.out.println(cnt);
		
	}
}
