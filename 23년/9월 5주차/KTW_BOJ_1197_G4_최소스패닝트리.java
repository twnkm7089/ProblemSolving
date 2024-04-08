import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. KRUSKAL algorithm 이용
 * 2. make-set해서 p배열(부모 배열) 만들고, rank배열 만들고
 * 3. 간선 edges weight기준으로 오름차순 정렬(Priority Queue에 넣기)
 * 4. 그리고 꺼내면서 각 경우마다 findset으로 집합 대표자 찾아 비교하고 같으면 사이클이니 추가 안하고, 다르면 추가 후 union
 * 5. 그리고 결과 출력
 * */
public class KTW_BOJ_1197_G4_최소스패닝트리 {
	static int[] p, rank;
	
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		//p배열 생성
		p = new int[V+1];
		int[][] edges = new int[E][3];
		//make-set
		for(int i = 1; i <= V; i++) {
			p[i] = i;
		}
		//rank 배열 생성(rank 이용 union 위해)(초기값은 0)
		rank = new int[V+1];
		
		//간선 입력
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			edges[i] = new int[] {A, B, C};
		}
		
		//weight 기준 오름차순 정렬하는 min-heap
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
			
		});
		//priority queue에 넣기
		for(int i = 0; i < E; i++) {
			pq.add(edges[i]);
		}
		//탐색 시작
		int count = 0;
		int answer = 0;
		while(count != (V-1)) {//V-1개 간선 뽑을 때까지
			//pq에서 뽑기
			int[] temp = pq.poll();
			
			int A = temp[0];
			int B = temp[1];
			int weight = temp[2];
			//각 그룹 대표자 찾기
			int fA = findSet(A);
			int fB = findSet(B);
			
			if(fA != fB) {//일치 안하면
				//정답 추가, 배열 선택, union
				answer += weight;
				count++;
				union(fA, fB);
			}
		}
		//결과 출력
		System.out.println(answer);
		
	}//end of main method
	
	//find set
	public static int findSet(int x) {
		//path-compression
		if(p[x] != x) {
			p[x] = findSet(p[x]);
		}
		//대표 반환
		return p[x];
	}
	
	//union
	//findset 결과가 들어온다고 가정
	public static void union(int fx, int fy) {
		//rank 기반
		if(rank[fx] > rank[fy]) {//rank fx가 fy보다 높으면 fy를 fx에 통합
			p[fy] = fx;
		} else {//아니면 반대
			p[fx] = fy;
			
			if(rank[fx] == rank[fy]) {//같았다면 fy 랭크 증가
				rank[fy]++;
			}
		}
	}
	
}
