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
 * 1. 간단요약 : bfs를 이용해보자
 * 2. 바깥쪽 공기로 찬 공간을 하나의 덩어리로 보면, bfs로 공기층과 치즈 내부의 빈 공간을 분리 가능
 * 3. (0,0)부터 bfs를 하며 바깥 공간을 탐색한다, 이 과정에서 치즈와 부딪힐 경우, 해당 공간의 visited배열의 숫자를 늘린다.
 * 4. 치즈의 visited가 2 이상이 되면 2면이 접촉한 경우, 다음 번에 삭제한다.
 * 5. 자세한 것은 아래 코드 참고
 * */
public class KTW_BOJ_2638_G3_치즈 {
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		int cheese = 0;
		//map입력, cheese를 통해 cheese가 있는 칸을 센다.
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheese++;
				}
			}
		}
		
		//bfs, 지울 노드 초기화
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> delete = new LinkedList<>();
		//지운 치즈 칸의 수 저장 변수, 그리고 시간 측정 변수
		int delcheese = 0;
		int cnt = 0;
		//delta
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		//시작
		while(cheese != delcheese) {
			cnt++;//시간 증가
			//초기 위치 (0,0) 입력
			queue.add(new int[]{0, 0});
			//방문 배열 초기화
			int[][] visited = new int[N][M];
			visited[0][0] = 1;
			//큐 비우며 바깥쪽 완전 탐색
			//bfs
			while(!queue.isEmpty()) {
				//큐에서 위치 뽑기
				int[] pos = queue.poll();
				int r = pos[0];
				int c = pos[1];
				//delta로 주변 탐색
				for(int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr >= 0 && nr < N && nc >= 0 && nc < M) {//map 내부면
						//아직 방문 하지 않은 빈 공간이면,
						if(visited[nr][nc] == 0 && map[nr][nc] == 0) {
							//다음 방문 예정지로
							visited[nr][nc] = 1;
							queue.add(new int[] {nr, nc});
						}
						//치즈를 만나면(치즈와 공기가 접촉)
						if(map[nr][nc] == 1) {
							//방문 배열 증가
							visited[nr][nc]++;
							//2면과 접촉했음이 판명
							if(visited[nr][nc] == 2) {
								delcheese++;//지운 치즈 칸수 증가
								delete.add(new int[] {nr, nc});//삭제 큐에 넣기
							}
						}
					}
				}
			}//end of bfs
			
			//다 지웠으면 break
			if(cheese == delcheese) {
				break;
			}
			//삭제 큐에서 꺼내며 지우기
			while(!delete.isEmpty()) {
				int[] pos = delete.poll();
				map[pos[0]][pos[1]] = 0;
			}
		}
		//결과 출력
		System.out.println(cnt);
		
		br.close();
	}
	
	
}
