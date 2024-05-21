import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_11657_G4_타임머신 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//bellman ford algorithm 활용
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//graph
		List<int[]>[] graph = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		//edge info [도착 노드, 시간]
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			
			graph[start].add(new int[] {end, time});
		}
		
		//음수 사이클 확인
		boolean isNegCycleExist = false;
		
		//dist배열
		long[] dist = new long[N+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[1] = 0; //start point
		
		outer : for(int i = 1; i <= N; i++) { //bellman ford
			for(int v = 1; v <= N; v++) { //about all vertex
				if(dist[v] != Long.MAX_VALUE) { //visited
					for(int[] edge : graph[v]) {
						int end = edge[0];
						long time = (long)edge[1];
						//갱신 가능
						if(dist[end] > dist[v] + time) {
							if(i == N) {
								//N번째 반복에서도 갱신? -> 음의 사이클
								isNegCycleExist = true;
								break outer;
							} else {
								//아니면 갱신
								dist[end] = dist[v] + time;
							}
						}
					}
					
				}
			} //end of now turn
			
		}//bellman ford
		
		
		if(isNegCycleExist) {
			System.out.println(-1); //음의 사이클? -1
		} else {
			for(int i = 2; i <= N; i++) {
				if(dist[i] == Long.MAX_VALUE) dist[i] = -1;
				System.out.println(dist[i]);
			}
		}
		
		br.close();
		
	}
}
