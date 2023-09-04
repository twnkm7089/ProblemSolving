import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*알고리즘 설명
 * 1. DFS 이용
 * 2. 집을 찾으면 totalcnt(단지 개수 세기) 추가
 * 3. DFS를 통해 연결된 집의 개수 찾기.
 * 4. 결과 출력
 * */
public class KTW_BOJ_2667_S1 {
	public static int[][] map;
	public static int N;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());//input
		//지도 입력
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			char[] temp = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				map[i][j] = temp[j] - '0';//숫자로 변환
			}
		}
		
		int totalcnt = 0;//단지 개수 세기
		List<Integer> house = new ArrayList<>();//각 단지별 집 개수 세기
		for(int i = 0; i < N; i++) {//탐색 시작
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 1) {//집을 찾으면
					totalcnt++;//추가
					house.add(dfs(i,j,0));//dfs 실행, 반환값은 포함된 집 개수
				}
			}
		}
		Collections.sort(house);//오름차순 정렬
		System.out.println(totalcnt);//단지수 출력
		for(int a : house) {//각 단지별 집 개수 출력
			System.out.println(a);
		}
		
	}
	//dfs
	public static int dfs(int r, int c, int cnt) {
		map[r][c] = 0;//방문 후 0으로 전환
		cnt++;//방문 마다 cnt추가(집 개수 세기)
		//delta 상,하,좌,우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		for(int d = 0; d < 4; d++) {//순회
			//행, 열
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr >= 0  && nr < N && nc >= 0 && nc < N && map[nr][nc] == 1) {
				cnt = dfs(nr, nc, cnt);//각 방향별 dfs 실행, cnt 갱신
			}
		}
		return cnt;//반환
	}
}
