package Sept2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 전형적인 그래프 탐색
 * 2. BFS 이용
 * */
public class KTW_BOJ_21736_S2_헌내기 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];//map
		boolean[][] visited = new boolean[N][M];//방문(true, false)
		
		int posr = -1, posc = -1;
		//input
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j);
				if(map[i][j] == 'I') {//start position 찾기
					posr = i;
					posc = j;
					visited[i][j] = true;
				}
			}
		}
		
		
		//bfs
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(posr*M + posc);//위치정보
		
		//delta
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		int cnt = 0;
		while(!queue.isEmpty()) {//탐색 시작
			//현 위치 파악
			int pos = queue.remove();
			int r = pos / M;
			int c = pos % M;
			//delta순회
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] != 'X' && visited[nr][nc] == false) {
					//벽이 아닌 미방문 노드는 큐에 추가, 방문 표시
					queue.add(nr * M + nc);
					visited[nr][nc] = true;
					if(map[nr][nc] == 'P') {//사람 만나면 count
						cnt++;
					}
				}
			}
		}
		
		//output
		if(cnt > 0) {
			System.out.println(cnt);
		} else {
			System.out.println("TT");
		}
		
	}//end of main method
}//end of main class
