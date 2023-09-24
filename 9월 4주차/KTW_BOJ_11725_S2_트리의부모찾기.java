import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 인접 리스트로 간선 저장
 * 2. 1부터 시작해 스택에 넣음.
 * 3. 스택에서 숫자 뽑음.(부모 노드)
 * 4. 숫자와 이어져 있으면서 아직 방문 안 한 노드들은 자식 노드.부모 배열에 정보 저장. 방문 표시.
 * 5. 스택 빌 때까지 반복.
 * */
public class KTW_BOJ_11725_S2_트리의부모찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//인접 리스트
		List<Integer>[] edges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		//관계 input
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			edges[A].add(B);
			edges[B].add(A);
		}
		//배열 초기화
		int[] parent = new int[N+1];
		boolean[] visited = new boolean[N+1];
		//큐에 초기값 저장
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		visited[1] = true;
		//큐 빌 때까지
		while(!queue.isEmpty()) {
			//부모 노드 픽업
			int p = queue.poll();
			//자식 노드 찾기
			for(int child : edges[p]) {
				if(!visited[child]) {
					parent[child] = p;
					visited[child] = true;
					queue.add(child);
				}
			}
		}
		//출력
		for(int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
		
		br.close();
	}
}
