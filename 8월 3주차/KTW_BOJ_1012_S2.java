import java.util.Scanner;

/*알고리즘 설명
 * 1. DFS 이용
 * 2. 배추가 있는 지역인데 check가 안된 지역 나오면 count 추가하고 실행
 * 3. dfs를 이용해 재귀적으로 순회하며 아직 check 안되었고, 배추 있는 지역의 check 표시 바꿈.
 * 4. 결과 출력
 * */

public class KTW_BOJ_1012_S2 {
	public static int[][] map = new int[1][1];//map
	public static int[][] check = new int[1][1];//check(0: 미탐색, 1: 탐색완료)
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {//start of test case
			//전체 크기 입력
			int M = sc.nextInt();
			int N = sc.nextInt();
			int K = sc.nextInt();
			//배열 재할당
			map = new int[N][M];
			check = new int[N][M];
			//배추 위치 입력
			for(int i = 0; i < K; i++) {
				int c = sc.nextInt();
				int r = sc.nextInt();
				map[r][c] = 1;
			}
			//탐색 시작
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] == 1 && check[i][j] == 0) {//배추 존재 + 미탐색지
						cnt++;//증가
						dfs(i, j, N, M);//dfs
					}
				}
			}
			
			//결과 출력
			System.out.println(cnt);
		}// end of test case
	}//end of main
	
	//DFS 탐색
	public static void dfs(int i, int j, int N, int M) {
		check[i][j] = 1;//탐색 했다고 표시
		//델타, 상, 우, 하, 좌의 시계방향
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		//각 방향별
		for(int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 1 && check[nr][nc] == 0) {
				dfs(nr, nc, N, M);//인덱스 내에 들고, 배추 있고, 미탐색(즉, 인접한 곳에 배추가 있으면 호출)
			}
		}
	}
}

