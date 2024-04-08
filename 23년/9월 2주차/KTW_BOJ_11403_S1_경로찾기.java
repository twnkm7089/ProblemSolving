import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*알고리즘 설명
 * 1. 그래프 탐색
 * 2. DFS 이용
 * 3. 인자로 탐색 시작한 정점 번호와 현재 DFS 탐색 중인 정점 번호 입력
 * 4. 연결된 노드 있으면 재귀식으로 호출, 탐색 시작점 기준 미방문 노드일 경우만 재귀호출
 * 5. 결과 출력
 * */

public class KTW_BOJ_11403_S1_경로찾기 {
	public static int[][] input;
	public static int[][] result;
	public static int N;
	
	public static void main(String[] args) throws IOException {
		//input, 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N][N];
		result = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dfs 실행
		for(int i = 0; i < N; i++) {
			dfs(i, i);//모든 정점에 대해 탐색 시작
		}
		
		//결과 출력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	public static void dfs(int search, int now) {//search:시작 정점, now:현재 정점
		for(int i = 0; i < N; i++) {
			if(input[now][i] == 1 && result[search][i] == 0) {//현재 정점과 연결되고 시작 정점과 비교 때 미방문시
				result[search][i] = 1;//방문 체크
				dfs(search, i);//다음 노드로
			}
		}
	}
	
}
