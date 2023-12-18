import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KTW_BOJ_1916_최소비용구하기_G5 {
	//dijkstra용 node
	static class Node {
		int weight;
		int dest;
		
		public Node() {
			
		}
		
		public Node(int weight, int dest) {
			this.weight = weight;
			this.dest = dest;
		}
	}
	
	
	//dijkstra algorithm 사용
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		//거리 배열 초기화
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		//인접 리스트로 그래프 저장
		//edge는 버스의 노선과 비용
		List<int[]>[] adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			//start번 배열에 end로 가는 weight 비용의 노선 있음을 기록
			adjList[start].add(new int[] {end, weight});
		}
		
		//a, b
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		//dijkstra용 pq
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});
		
		//dijkstra 초기화 및 실행
		pq.add(new Node(0, A));
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			int dest = temp.dest;
			int weight = temp.weight;
			
			if(dist[dest] > weight) {
				dist[dest] = weight;
				for(int[] a : adjList[dest]) {
					pq.add(new Node(a[1]+weight, a[0]));
				}
			}
		}
		
		//결과 출력
		System.out.println(dist[B]);
		
	}
}
