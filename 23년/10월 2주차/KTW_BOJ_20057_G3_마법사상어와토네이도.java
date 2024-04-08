import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 그냥 구현
 * 2. 아래 참조
 * */
public class KTW_BOJ_20057_G3_마법사상어와토네이도 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		//map input, 전체 모래 초기값 찾기
		int[][] map = new int[N][N];
		int total = 0;
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		

		
		//direction
		int[] dr = {0, 1, 0, -1};
		int[] dc = {1, 0, -1, 0};
		int dir = 0;
		
		//스택에 0,0부터 시계방향으로 돌며 다음 위치를 저장, 그 후 스택에서 뽑으며 방향 전환
		Stack<int[]> pos = new Stack<>();
		int r = 0;
		int c = 0;
		boolean[][] visited = new boolean[N][N];
		
		//달팽이
		//0,0부터 돌며 가운데 올 때까지 저장
		while(true) {
			//저장 후 이동
			pos.add(new int[] {r, c});
			visited[r][c] = true;
		
			if(r == N/2 && c == N/2) break;
			
			
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] == true) {
				dir = (dir+1)%4;
				nr = r + dr[dir];
				nc = c + dc[dir];
			}
			
			r = nr;
			c = nc;
		}
		
		
		//마지막 위치 뽑기
		int[] temp = pos.pop();
		r = temp[0];
		c = temp[1];
		//개시
		while(!pos.isEmpty()) {
			//이동 위치 결정
			temp = pos.pop();
			int nr = temp[0];
			int nc = temp[1];

			//모래를 흩뿌리고 이동하자
			//흩뿌리기, 현재 방향 기준 좌, 우 방향은?
			
			//현재 방향 구해서 좌, 우 방향도 파악
			int diffR = nr - r;
			int diffC = nc - c;
			int nDir = -1, lDir = -1, rDir = -1;
			for(int d = 0; d < 4; d++) {
				if(diffR == dr[d] && diffC == dc[d]) {
					nDir = d;
					lDir = (d+3)%4;
					rDir = (d+1)%4;
					break;
				}
			}
			
			//흩뿌리기 개시
			int sand = map[nr][nc];
			map[nr][nc] = 0;
			//x의 좌우로 흩뿌리기
			//추가되는 R, C의 칸 저장
			int nDR = dr[nDir];
			int nLR = dr[lDir];
			int nRR = dr[rDir];
			
			int nDC = dc[nDir];
			int nLC = dc[lDir];
			int nRC = dc[rDir];
			
			//1퍼 2개, 7퍼 2개, 2퍼 2개, 10퍼 2개, 나머지, 5퍼
			int[] addR = {nLR, nRR, nDR+nLR, nDR+nRR, nDR+2*nLR, nDR+2*nRR, 2*nDR+nLR, 2*nDR+nRR, 2*nDR, 3*nDR};
			int[] addC = {nLC, nRC, nDC+nLC, nDC+nRC, nDC+2*nLC, nDC+2*nRC, 2*nDC+nLC, 2*nDC+nRC, 2*nDC, 3*nDC};
			
			for(int i = 0; i < 10; i++) {
				//target
				int tr = r + addR[i];
				int tc = c + addC[i];
				if(tr >= 0 && tr < N && tc >= 0 && tc < N) {
					if(i < 2) {
						map[tr][tc] = (int)(map[tr][tc] + sand*0.01);
					} else if(i < 4) {
						map[tr][tc] = (int)(map[tr][tc] + sand*0.07);
					} else if(i < 6) {
						map[tr][tc] = (int)(map[tr][tc] + sand*0.02);
					} else if(i < 8) {
						map[tr][tc] = (int)(map[tr][tc] + sand*0.1);
					} else if(i == 8) {
						//나머지 구해 추가
						int nowadd = sand - (2*(int)(sand*0.01) + 2*(int)(sand*0.07) + 2*(int)(sand*0.02) + 2*(int)(sand*0.1) + (int)(sand*0.05));
						map[tr][tc] = (int)(map[tr][tc] + nowadd);
					} else {
						map[tr][tc] = (int)(map[tr][tc] + sand*0.05);
					}
				}
			
			}
			r = nr;
			c = nc;

		
		}
		
		//이제 모래 구하기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				total -= map[i][j];
			}
		}
		//결과 출력
		System.out.println(total);
	}
	
}
