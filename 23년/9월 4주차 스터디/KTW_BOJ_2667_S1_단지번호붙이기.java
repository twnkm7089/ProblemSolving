import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*알고리즘 설명
 * 1. dfs 이용
 * 2. 집을 만나면 cnt(단지개수) 올리고, dfs실행해서 단지에 있는 집 개수 구하기
 * 3. dfs로 끝까지 탐색 후 개수를 1씩 더해주는 방식으로 리턴을 받아 구하기
 * 4. 자세한건 코드로
 * */
public class KTW_BOJ_2667_S1_단지번호붙이기 {
	static int[][] map;
	static boolean[][] visited;
	static int N;
	//delta
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		//map input
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str.charAt(j)+"");
			}
		}
		
		int cnt = 0;//단지 개수 초기화
		List<Integer> answer = new ArrayList<>();//정답 증가
		//탐색 시작
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {//미방문했는데 집 존재(새 단지다!!)
					cnt++;//단지 개수 증가
					answer.add(dfs(i, j));//해당 단지의 집 개수 추가
				}
			}
		}
		
		Collections.sort(answer);//정렬
		//출력
		System.out.println(cnt);
		for(int a : answer) {
			System.out.println(a);
		}
		
		
	}//end of main method
	
	//dfs
	public static int dfs(int i, int j) {
		visited[i][j] = true;//방문 표시
		int sum = 0;//단지 개수 초기화
		for(int d = 0; d < 4; d++) {//delta 탐색
			//다음 위치 파악
			int nr = i + dr[d];
			int nc = j + dc[d];
			if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 1 && !visited[nr][nc]) {
				//map내부, 집 있음, 미방문한 경우 해당 방향으로 dfs 실행
				sum += dfs(nr, nc);//반환 받은 단지 개수 누적 합
			}
		}
		return (sum + 1);//내거 포함해 집 개수 추가시켜 반환
	}//end of dfs
}
