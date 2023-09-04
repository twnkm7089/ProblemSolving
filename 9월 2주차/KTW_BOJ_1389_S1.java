import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*알고리즘 설명
 * 1. BFS 이용
 * 2. 2차원 배열을 이용해 관계도 구현(1:연결)
 * 3. 1~N까지 각 점을 시작으로 함.
 * 4. cnt를 이용해 횟수를 저장.
 * 5. 반복문 안에 while 반복문을 넣고, 각 반복마다 cnt 증가.
 * 6. 큐에 있던 값을 뺀 후 해당 인원과 연결되어 있으면서 미방문한 사람 검색.
 * 7. 있으면 temp에 넣고, sum에 cnt 더함.
 * 8. 끝나면 temp에 있던 값 queue로. 이걸 반복
 * 9. 기존 최소와 비교, 최소면 갱신.
 * 10. 결과 출력
 * */


public class KTW_BOJ_1389_S1 {
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//관계도 생성
		int[][] fr = new int[N+1][N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			fr[a][b] = 1;
			fr[b][a] = 1;
		}
		//초기화
		int min = Integer.MAX_VALUE;
		int minidx = -1;
		for(int i = 1; i <= N; i++) {//시작점 변경
			int sum = 0;
			int cnt = 0;
			//방문 배열
			int[] visited = new int[N+1];
			visited[i] = 1;
			//큐 추가
			Queue<Integer> queue = new LinkedList<>();
			queue.add(i);//시작 노드 큐에 넣기
			Queue<Integer> temp = new LinkedList<>();
			while(true) {//반복
				cnt++;//cnt증가
				//현재 노드에서 이어진 것 조사
				while(!queue.isEmpty()) {//기존 큐가 빌 때까지 뽑아서 관계 추적, 방문
					int data = queue.remove();
					for(int j = 1; j <= N; j++) {
						if(fr[data][j] == 1 && visited[j] == 0) {
							sum+=cnt;
							visited[j] = 1;
							temp.add(j);
						}
					}
				}
				//없으면 break
				if(temp.isEmpty()) break;
				
				//현재 노드 갱신
				while(!temp.isEmpty()) {
					queue.add(temp.remove());
				}
			}
			//탐색 후 최소값 갱신 여부 파악
			if(sum < min) {
				min = sum;
				minidx = i;
			}
		}
		//결과 출력
		System.out.println(minidx);
	}
}
