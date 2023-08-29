package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 축! 김태운 드디어 DFS, BFS를 향한 발걸음 떼다.
 * 2. 기본적인 DFS, BFS 구현 문제. 간선 연결 여부를 담은 graph와 방문 여부 담은 visited 할당.
 * 3. DFS는 재귀 함수를 이용해 구현.
 * 4. BFS는 큐를 이용해 구현.
 * 5. 풀었음.
 * */
public class KTW_BOJ_1260_S2 {
	public static int[][] graph = new int[1][1];
	public static int[] visited = new int[1];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		//정점 개수 N개니 row를 시작점, column을 끝점으로 하는 2차원 배열 할당.
		graph = new int[N+1][N+1];
		//간선 연결
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//간선은 양방향, 상호 연결했다는 1 표시.
			graph[a][b] = 1;
			graph[b][a] = 1;
		}
		
		visited = new int[N+1];//방문 배열 초기화
		dfs(V, N);//dfs
		System.out.println();//줄바꿈
		
		visited = new int[N+1];//방문 배열 초기화
		bfs(V, N);//bfs
		System.out.println();//줄바꿈

	}
	//dfs 재귀 이용
	public static void dfs(int V, int N) {
		System.out.print(V + " ");//현재 방문 노드 출력
		visited[V] = 1;//방문 여부 표시
		for(int dest = 1; dest <= N; dest++) {//탐색
			if(graph[V][dest] == 1 && visited[dest] == 0) {//간선이 연결된 미방문 노드 존재시
				dfs(dest, N);//재귀로 탐색해 dfs 구현
			}
		}
	}
	//bfs 큐 이용
	public static void bfs(int V, int N) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(V);//큐에 현재 노드 할당
		visited[V] = 1;//방문 표시
		while(!(queue.size() == 0)) {//큐가 빌 때까지
			int data = queue.remove();//큐에서 데이터 뽑아
			System.out.print(data + " ");//출력
			for(int j = 1; j <= N; j++) {//탐색
				if(graph[data][j] == 1 && visited[j] == 0) {//연결된 간선에 미방문 노드 존재시
					queue.add(j);//큐에 추가
					visited[j] = 1;//방문 표시
				}
			}
		}
	}
}
