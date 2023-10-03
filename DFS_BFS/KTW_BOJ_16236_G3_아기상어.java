import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. BFS이용
 * 2. 아래 코드 참고
 * */
public class KTW_BOJ_16236_G3_아기상어 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		int[] now = new int[2];
		//map input
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					now[0] = i;
					now[1] = j;
				}
			}
		}
		
		//delta
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};
		//인자 초기화
		Queue<int[]> queue = new LinkedList<>();
		int time = 0;
		int fishSize = 2;
		int eat = 0;
		//탐색 시작
		while(true) {
			//현 위치 추가(현재 위치부터 시작)
			queue.add(now);
			
			boolean flag = false;
			int temp = 0;
			boolean[][] visited = new boolean[N][N];
			visited[now[0]][now[1]] = true;
			int[] next = new int[2];
			Arrays.fill(next, Integer.MAX_VALUE);
			//bfs
			while(!queue.isEmpty()) {
				int size = queue.size();
				for(int i = 0; i < size; i++) {
					int[] pos = queue.poll();
					
					//먹이 발견
					if(map[pos[0]][pos[1]] != 0 && map[pos[0]][pos[1]] != 9 && map[pos[0]][pos[1]] < fishSize) {
						//주의 fishSize가 9이상일 경우 제대로 처리 안하면 문제 발생
						//발견 표시 & 다음 위치로 좋으면 갱신
						flag = true;
						if(pos[0] < next[0]) {
							next[0] = pos[0];
							next[1] = pos[1];
						} else if(pos[0] == next[0] && pos[1] < next[1]) {
							next[0] = pos[0];
							next[1] = pos[1];
						}
						continue;
					}
					
					//먹이 못 발견했으면 다음 장소 추가
					if(!flag) {
						for(int d = 0; d < 4; d++) {
							int nr = pos[0] + dr[d];
							int nc = pos[1] + dc[d];
							
							if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc] && map[nr][nc] <= fishSize) {
								queue.add(new int[]{nr, nc});
								visited[nr][nc] = true;
							}
						}
					}
				}
				
				//이번 층의 탐색이 끝난 후, 찾은 물고기 있으면 이동
				if(flag) {
					//시간 갱신
					time += temp;
					
					//먹은 수 갱신, fish크기 갱신
					eat++;
					if(eat == fishSize) {
						eat = 0;
						fishSize++;
					}
					
					//이동 표시
					map[now[0]][now[1]] = 0;
					map[next[0]][next[1]] = 9;
					
					now = next;
					
					queue.clear();
					break;
				}
				
				//없으면 다음 턴으로
				temp++;
			}//end of bfs
			
			//먹이 못찾았다 -> 끝
			if(!flag) {
				break;
			}
		}//end of search
	
	//결과 출력	
	System.out.println(time);
	}//end of main method

}//end of class
