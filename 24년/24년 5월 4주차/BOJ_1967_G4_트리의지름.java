import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1967_G4_트리의지름 {
	static int N;
	static List<int[]>[] tree;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());

		//tree init
		tree = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			tree[parent].add(new int[] {child, value});
		}
		
		max = 0;
		
		//dfs
		//자식이 여럿이면 이 node를 root로 해서 펼칠 수 있는 최대 길이 구하기
		//아니면 이 노드까지의 최대 길이 return
		dfs(1);
		
		System.out.println(max);
		br.close();
		
	}
	
	public static int dfs(int node){
		//leaf node
		if(tree[node].size() == 0) {
			return 0;
		} else if(tree[node].size() == 1) {
			int[] edge = tree[node].get(0);
			int line_length = dfs(edge[0]) + edge[1]; 
			
			//update and return
			max = Math.max(line_length, max);
			return line_length; //length + child node length
		} else {
			int size = tree[node].size();
			
			int[] length = new int[size];
			
			for(int i = 0; i < size; i++) {
				int[] edge = tree[node].get(i);
				length[i] = dfs(edge[0]) + edge[1];
			}
			//sort
			Arrays.sort(length);
			
			//max length update
			max = Math.max(max, length[size-1]+length[size-2]);
			
			//return
			return length[size-1];
		}
	}
	
}
