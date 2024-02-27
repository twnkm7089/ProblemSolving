package Feb5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7562_S1_나이트의이동 {
	//size
	static int I;
	
	//dir
	static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws IOException {
		//BFS + BackTracking
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			I = Integer.parseInt(br.readLine());
			
			boolean[][] visited = new boolean[I][I];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startRow = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int targetR = Integer.parseInt(st.nextToken());
			int targetC = Integer.parseInt(st.nextToken());
			
			//시작점 같으면 다음 loop로
			if(startRow == targetR && startCol == targetC) {
				System.out.println(0);
				continue;
			}
			
			visited[startRow][startCol] = true;
			Queue<int[]> queue = new LinkedList<>();
			int time = 1; //이동횟수
			
			queue.add(new int[] {startRow, startCol});
			outer : while(!queue.isEmpty()) {
				//이번회차
				int N = queue.size();
				for(int i = 0; i < N; i++) {
					int[] pos = queue.poll();

					for(int d = 0; d < 8; d++) {
						//다음 좌표를 분석하면서...
						int nr = pos[0] + dr[d];
						int nc = pos[1] + dc[d];
						
						//발견하면 바로 종료
						if(nr == targetR && nc == targetC) {
							break outer;
						} else {
							//이제 nr, nc를 추가
							if(inRange(nr, nc) && !visited[nr][nc]) {
								queue.add(new int[] {nr, nc});
								visited[nr][nc] = true;
							}
						}
					}
					
				}
				//다음 회차로
				time++;
			}
			
			//output
			System.out.println(time);
		}
		

	}
	
	//현재 index가 범위 내인지 확인하는 함수
	public static boolean inRange(int row, int col) {
		return row >= 0 && row < I && col >= 0 && col < I;
	}
}
