import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1865_G3_웜홀 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		//test case on
		for(int t = 0; t < TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			//graph info
			List<int[]>[] graph = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}

			//road
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				graph[S].add(new int[] {E, T});
				graph[E].add(new int[] {S, T});
			}
			
			//wormhole
			for(int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				
				graph[S].add(new int[] {E, -T});
			}
			
			//bellman-ford 응용
			//시간이 줄어들며 출발 위치로 돌아온다 -> 음의 사이클 존재 -> 임의의 점에서 V번 돌려 음의 사이클 있으면 YES
			int[] dist = new int[N+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			
			
			//visited 배열 -> 이어지지 않은 그래프 처리
			boolean[] visited = new boolean[N+1];

			String ans = "NO";
			outer : for(int a = 1; a <= N; a++) {
				//미방문 시
				if(!visited[a]) {
					//방문 표시 후 벨만포드 시작
					dist[a] = 0;
					visited[a] = true;

					//bellman ford
					for(int i = 1; i <= N; i++) { //V번 수행
						//각 vertex 순회
						for(int v = 1; v <= N; v++) {
							//대상 조회
							if(dist[v] != Integer.MAX_VALUE) {
								//간선별 탐색
								for(int[] edge : graph[v]) {
									int end = edge[0];
									int time = edge[1];
									visited[end] = true; //방문 시 표시
									
									if(dist[end] > dist[v]+time) { //갱신 필요
										//마지막인지 확인
										if(i == N) {
											//마지막 업데이트? -> 음의 사이클 존재
											ans = "YES";
											break outer;
										}
										
										//갱신
										dist[end] = dist[v]+time;
									}
								}
							}
						}//end of turn
					} //end of bellman ford
				}
			}
			
			
			System.out.println(ans);
		}
		
		//+문제 자체는 맞았으나, 메모리 사용량이 너무 커 확인해보니
		//Edge같은 추가 자료형 사용하는 편이 좋다고 함.
		
		br.close();
		
	}
		
}
