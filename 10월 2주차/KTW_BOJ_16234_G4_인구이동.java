import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. bfs 응용
 * */
public class KTW_BOJ_16234_G4_인구이동 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		//delta
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		//map 배열
		int[][] map = new int[N][N];
		//map input
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//계산 시작
		int day = 0;
		while(true) {
			int cnt = 0;
			boolean[][] visited = new boolean[N][N];
			int[][] temp = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					//미방문시 bfs 적용
					if(!visited[i][j]) {
						//덩어리 수 세기
						cnt++;
						visited[i][j] = true;
						//bfs용 큐
						Queue<int[]> bfs = new LinkedList<>();
						//좌표저장용 큐
						Queue<int[]> store = new LinkedList<>();
						
						bfs.add(new int[] {i, j});
						store.add(new int[] {i, j});
						
						//연합 인구 구하기
						int sum = map[i][j];
						while(!bfs.isEmpty()) {
							int[] pos = bfs.poll();
							
							for(int d = 0; d < 4; d++) {
								int nr = pos[0] + dr[d];
								int nc = pos[1] + dc[d];
								//다음 인덱스가 맵 내부이며 차이가 인구 이동 가능이면 큐에 추가 후 총합 추가, 방문 표시
								if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
									int diff = Math.abs(map[nr][nc] - map[pos[0]][pos[1]]);
									if(diff >= L && diff <= R) {
										visited[nr][nc] = true;
										store.add(new int[] {nr, nc});
										bfs.add(new int[] {nr, nc});
										sum += map[nr][nc];
									}
								}
							}
						}
						
						//store.size()는 현재 연합 덩어리수, sum을 국가 수로 나누어 인구 구하기
						int popul = sum / store.size();
						//store에 있던 좌표에 인구수 업데이트
						//저번 계산이 바로 다음 계산에 반영되는 문제 해결 위해 temp배열에 구현
						while(!store.isEmpty()) {
							int[] pos = store.poll();
							temp[pos[0]][pos[1]] = popul;
						}
						
					}
				}
			}//map 탐색
			
			if(cnt == N*N) break; //덩어리수가 N*N개 -> 인구 이동 없었다. -> break
			//갱신, 날짜 변경
			map = temp;
			day++;
		}//end of while
		
		System.out.println(day);
	}//end of main method
	
}//end of class
