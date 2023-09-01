package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 너비 우선 탐색 이용
 * 2. 첫번째 칸에서 출발해 미로에 갈 수 있는 칸은 거리를 1씩 더해줌.
 * 3. 그 정보를 그대로 map배열에 저장해 갱신하며 거리 계산.
 * 4. BFS 구현 시, 몇번째 칸인지의 정보는 row*M + column으로 정수로 만들어 함.
 * 5. 완료후 map[N-1][M-1] 출력
 * */

public class KTW_BOJ_2178_S1 {
	public static int[][] map;
	public static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new int[N*M];
		//a번 칸 = row*M + column
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		//index는 0부터, 번호는 1부터. 보정 필요.
		
		//bfs 시작
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		visited[0] = 1;
		
		//delta(상, 하, 좌, 우)
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		//큐가 빌 때까지
		while(!queue.isEmpty()) {
			//위치 정보 행, 열 변환
			int pos = queue.remove();
			int r = pos / M;
			int c = pos % M;
			//주변 탐색
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				//갈 수 있는 위치면
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 0 && visited[nr*M + nc] == 0) {
					map[nr][nc] = map[r][c] + 1;//거리 표시
					int val = nr*M + nc;//위치 정보로 변환
					visited[val] = 1;//visited 갱신
					queue.add(val);//큐에 저장
				}
			}
		}
		//출력
		System.out.println(map[N-1][M-1]);
	}//end of main method
	
}//end of class
