package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*알고리즘 설명
 * 1. bfs 이용
 * 2. 현재 위치를 우선 start_r, c에 저장한다.
 * 3. 위치를 r * m + c로 변환해 큐에 넣는다.
 * 4. bfs를 시작한다. queue에서 값을 꺼내 행, 열로 변환하고 델타로 탐색해 갈 수 있으면서 미방문이면 해당 위치도 r*m+c로 변환해 큐에 넣고 result[r][c] + 1을 넣는다.
 * 5. 가까운 곳부터 순서대로 하므로 이 값이 곳 최단거리다.
 * 6. 탐색 종료 후 미방문 노드의 거리값을 -1로 바꾼 후, 모든 노드의 거리값을 출력한다.
 * */
public class KTW_BOJ_14940_S1 {
	public static int[][] map;
	public static int[][] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//N, M 구하기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//map
		map = new int[N][M];
		result = new int[N][M];
		
		int start_r = -1, start_c = -1;
		//input
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());//값 저장
				if(map[i][j] == 2) {//현재 위치면 저장
					start_r = i;
					start_c = j;
				}
			}
		}
		

		result[start_r][start_c] = 0;//현재 위치 초기화
		//bfs 위한 큐 설정
		Queue<Integer> queue = new LinkedList<>();
		queue.add(M*start_r + start_c);//위치 정보를 int로 변환, r*m+c
		
		//delta(상,하,좌,우)
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		while(!queue.isEmpty()) {//큐가 빌 때까지
			//위치값 꺼내 변환
			int pos = queue.remove();
			int r = pos / M;
			int c = pos % M;

			for(int d = 0; d < 4; d++) {//주위 델타 탐색
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && result[nr][nc] == 0) {
					//갈수 있는 미방문 노드면
					queue.add(nr * M + nc);//큐에 추가
					result[nr][nc] = result[r][c] + 1;//거리 저장
				}
			}
		}
		
		//도달 불가 위치 -1로 수정
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && result[i][j] == 0) {
					result[i][j] = -1;
				}
			}
		}
		//결과 출력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}//end of main method
	
}//end of class
