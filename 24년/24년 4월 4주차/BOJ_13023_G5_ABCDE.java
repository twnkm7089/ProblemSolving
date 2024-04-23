import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13023_G5_ABCDE {
	static int ans;
	static HashSet<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//graph
		//친구 관계 있는지 빨리 검색 위해 hashset배열 사용 탐색 O(1)
		//graph[a] -> a 의 친구 관계
		graph = new HashSet[N];
		for(int i = 0; i < N; i++) {
			graph[i] = new HashSet<>();
		}
		visited = new boolean[N];
		
		//관계 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		//각 노드별 dfs
		//level 5 도달하면 합격
		
		ans = 0;
		for(int i = 0; i < N; i++) {
			dfs(i, 0);
			
			if(ans == 1) break;
		}
		
		System.out.println(ans);
		
		br.close();
	}
	
	static void dfs(int node, int depth) {
		if(depth >= 4) {
			ans = 1;
			return;
		}
		
		visited[node] = true;
		for(int a : graph[node]) {
			if(!visited[a]) {
				dfs(a, depth+1);
			}
		}
		visited[node] = false;
	}
}
