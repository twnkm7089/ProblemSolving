import java.util.Scanner;

/*알고리즘 설명
 * 1. 대표적인 DFS 이용 문제.
 * 2. 새로운 색 나올 때마다 카운트를 올리고, 같은 색이 있는 장소의 check 배열을 모두 1로 전환해 중복 방지.
 * 3. 적록색약의 경우 탐색 시, map의 G를 모두 R로 바꾸어 탐색.
 * */

public class KTW_BOJ_10026_G5 {
	public static char[][] map = new char[1][1];//map 입력
	public static int[][] check = new int[1][1];//확인 배열(0: 미탐색, 1 : 탐색 완료)
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//입력
		int N = sc.nextInt();
		//배열 초기화
		map = new char[N][N];
		check = new int[N][N];
		//map 배열 입력(String을 char형 2차원 배열로...)
		for(int i = 0; i < N; i++) {
			String str = sc.next();
			char[] temp = str.toCharArray();
			for(int j = 0; j < N; j++) {
				map[i][j] = temp[j];
			}
		}
		
		//정상인 구역 탐색
		int cnt = 0;//카운트 횟수
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(check[i][j] == 0) {//미탐색 구역의 경우(새로운 색)
					cnt++;//카운트 증가
					dfs(i, j, N, map[i][j]);//dfs함수 실행
				}
			}
		}
		
		//check array clear & make R = G
		//check 배열을 다시 사용하도록 0으로 초기화 + map배열의 G를 R로 바꾸어 적록색약 구현
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 'G') {
					map[i][j] = 'R';
				}
				check[i][j] = 0;
			}
		}
		
		//적록색약 구역 탐색
		int cnt2 = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(check[i][j] == 0) {
					cnt2++;
					dfs(i, j, N, map[i][j]);
				}
			}
		}
		//결과 출력
		System.out.println(cnt + " " + cnt2);
		
	}
	
	//dfs함수, 인자는 행, 열, 전체 배열 크기, 현재 색깔
	public static void dfs(int r, int c, int N, char x) {
		check[r][c] = 1;//탐색 완료 선언
		//delta 이용, 상, 우, 하, 좌 순(시계방향)
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		for(int i = 0; i < 4; i++) {//각 방향
			//다음 행, 열
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && x == map[nr][nc] && check[nr][nc] == 0) {
				dfs(nr, nc, N, x);//index가 범위 내부에 있고, 색이 일치하며, 미탐색 지역일 경우, dfs 실행
			}
		}//이렇게 실행된 dfs 함수 내부에서 재귀적으로 dfs 함수를 실행해, 결과적으로 같은 색을 가지고 이어진 모든 구역을 탐색할 예정.
	}
	
	
}

