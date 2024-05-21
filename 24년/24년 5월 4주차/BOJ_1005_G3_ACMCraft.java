import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1005_G3_ACMCraft {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		//test case 반복
		for(int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//input
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			//inDeg 등 저장
			int[] inDeg = new int[N+1];
			int[] startTime = new int[N+1];
			
			//소요시간
			int[] time = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			//graph
			List<Integer>[] graph = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				graph[start].add(end);
				inDeg[end]++; //indegree 추가
			}
			
			//목표
			int target = Integer.parseInt(br.readLine());
			
			//시작
			outer : while(true) {
				//타겟 찾기
				List<Integer> able = new ArrayList<>();
				for(int i = 1; i <= N; i++) {
					if(inDeg[i] == 0) {
						able.add(i);
					}
				}
				
				//건설 시작
				for(int a : able) {
					inDeg[a]--; //다음에 검색 안되도록 건설 처리
					
					//처음 시작할 때 건물이 타깃이면?
					if(a == target) {
						System.out.println(startTime[a] + time[a]);
						break outer;
					}
					
					
					for(int b : graph[a]) {
						//a 다음에 지어야 되는 건물에 대한 indegree 제거
						inDeg[b]--;
						
						//시작시간 기록
						//최대값으로 갱신(더 늦은 것 기준 시작이니)
						startTime[b] = Math.max(startTime[a] + time[a], startTime[b]);

						//b가 지어질 수 있게 되었다면?
						if(inDeg[b] == 0) {	
							//목표면?
							if(b == target) {
								System.out.println(startTime[b]+time[b]);
								break outer;
							}
						}
					}
				} //건설 종료
			} //다음 타깃 찾기
		}
		
		br.close();
		
	}
}
