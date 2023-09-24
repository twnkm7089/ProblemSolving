import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 다익스트라 알고리즘
 * 2. 효율을 위해 PriorityQueue를 이용해 구현.
 * 3. 하는게 너무 어려워 시행착오 엄청 겪었다.
 * 4. 하단 참조
 * */
public class KTW_BOJ_1753_G4_최단경로 {
	//Node가 저장하는 것 vertex, val
	//dijkstra는 일종의 dP이기도 하다.
	//vertex는 여기까지 도달하는데, val은 이정도 비용
	static class Node implements Comparable<Node>{
		int vertex;//여기까지는(위치)
		int val;//이정도 거리(누적)
		
		public Node(int vertex, int val) {
			this.vertex = vertex;
			this.val = val;
		}

		//priorityqueue에 넣기 위한 비교 기준
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
			
		int K = Integer.parseInt(br.readLine());
		
		//방향 그래프(인접 그래프 방식)
		List<Node>[] edges = new ArrayList[V+1];
		for(int i = 1; i < V+1; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges[u].add(new Node(v, w));
		}
		
		//거리 배열 초기화
		int[] dist = new int[V+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		//초기값 설정
		dist[K] = 0;
		//시작점까지의 누적 거리는? 0
		pq.add(new Node(K, 0));
		
		//dijkstra
		while(!pq.isEmpty()) {
			//노드를 꺼낸다
			Node n = pq.poll();
			
			//해당 위치와 이어진 모든 노드를 검사한다.
			for(Node m : edges[n.vertex]) {
				//n에서 m으로 가는게 더 이득인 경우
				if((n.val + m.val) < dist[m.vertex]) {
					//값을 갱신하고 priorityqueue에 추가
					dist[m.vertex] = n.val + m.val;
					pq.add(new Node(m.vertex, n.val + m.val));
				}
			}
			
			
		}
		//거리 출력
		for(int i = 1; i < V+1; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
				continue;
			}
			System.out.println(dist[i]);
		}
		
		br.close();
	}//end of main method
	
	
}//end of class
