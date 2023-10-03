import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_BOJ_2644_S2_촌수계산 {
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		
		//인접 리스트 제작
		List<Integer>[] adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		//계산할 사람 input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		//그래프 입력(인접 리스트 방식)
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			//양방향 연결
			adjList[x].add(y);
			adjList[y].add(x);
		}
		//정답 변수
		int answer = -1;
		int cnt = 0;
		
		//bfs
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		//큐 추가
		queue.add(A);
		visited[A] = true;
		//탐색 시작
		while(!queue.isEmpty()) {
			int size = queue.size();
			//현재 단계 :  {cnt}촌
			for(int i = 0; i < size; i++) {
				//데이터 꺼내서 비교
				int data = queue.poll();
				if(data == B) {
					//일치 시 answer 갱신 후 탐색 종료
					answer = cnt;
					break;
				}
				//데이터와 인접하며 미방문한 사람 넣기
				for(int a : adjList[data]) {
					if(!visited[a]) {
						visited[a] = true;
						queue.add(a);
					}
				}
			}
			//카운트 추가
			cnt++;
		}
		
		//결과 출력
		System.out.println(answer);
		
		br.close();
	}//end of main method
	
	
}
