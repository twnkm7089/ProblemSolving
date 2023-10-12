import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class KTW_SWEA_5656_벽돌깨기
{
	static int N, W, H;
	static int min;
	static int[] sel;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			
			map = new int[H][W];
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			sel = new int[N];
			min = Integer.MAX_VALUE;
			//조합 가동
			comb(0);
			
			System.out.println("#" + test_case + " " + min);
		}
	}
	
	
	public static void comb(int sidx) {
		//다 조합했으면 사격 개시
		if(sidx == N) {
			shoot(0, map);
			return;
		}
		
		//조합 형성
		for(int i = 0; i < W; i++) {
			sel[sidx] = i;
			comb(sidx+1);
		}
	}
	
	
	public static void shoot(int idx, int[][] now) {
		//사격 완료시 남아있는 블럭 정보 바탕으로 갱신
		if(idx == N) {
			int cnt = 0;
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(now[i][j] > 0) {
						cnt++;
					}
				}
			}

			min = Math.min(cnt, min);
			return;
		}
		
		
		//쏘는 위치
		int shoot = sel[idx];
		
		Queue<int[]> bfs = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		//쏜 직후 bfs 시작 위치 찾기
		for(int i = 0; i < H; i++) {
			if(now[i][shoot] > 0) {
				bfs.add(new int[] {i, shoot});
				visited[i][shoot] = true;
				break;
			}
		}
		
		//아무것도 없었다면 그냥 다음 턴으로
		if(bfs.isEmpty()) {
			shoot(idx+1, now);
		}
		
		
		//bfs
		while(!bfs.isEmpty()) {
			int[] pos = bfs.poll();
			int num = now[pos[0]][pos[1]];
			
			for(int m = 1; m < num; m++) {
				for(int d = 0; d < 4; d++) {
					int nr = pos[0] + m*dr[d];
					int nc = pos[1] + m*dc[d];
					
					if(nr >= 0 && nr < H && nc >= 0 && nc < W && now[nr][nc] > 0 && !visited[nr][nc]) {
						bfs.add(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		//지도 갱신
		//0이 아니고 방문하지 않은 부분만 남기자.
		//스택을 이용해 각 열의 아래서부터 읽어들인 후, 유효한 값만 새로운 지도에 차례대로 갱신
		int[][] temp = new int[H][W];
		Stack<Integer> stack = new Stack<>();
		for(int j = 0; j < W; j++) {
			//남은 블록 찾기
			for(int i = H-1; i >= 0; i--) {
				if(now[i][j] > 0 && !visited[i][j]) {
					stack.add(now[i][j]);
				}
			}
			
			//새 지도에 갱신
			int size = stack.size();
			for(int i = H-size; i < H; i++) {
				temp[i][j] = stack.pop();
			}
		}
		//다음 사격
		shoot(idx+1, temp);
		
	}
}