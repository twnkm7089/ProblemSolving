import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 000..001 부터 1111...10까지의 비트 배열을 생성한다.
 * 2. 이를 바탕으로 부분집합을 나눈다.(A지역, B지역)
 * 3. BFS를 이용해 각 지역이 이어져 있는지 확인한다.
 * 4. 이어져 있다면 차이를 비교해 갱신한다.
 * */
public class KTW_BOJ_17471_G4_게리맨더링 {

	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		
		int[] popul = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		//인구 입력
		for(int i = 1; i <= N; i++) {
			popul[i] = Integer.parseInt(st.nextToken());
		}
		
		//인접 리스트, 인접 여부까지 입력 받기
		List<Integer>[] adjList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j = 0; j < size; j++) {
				int adjNode = Integer.parseInt(st.nextToken());
				adjList[i].add(adjNode);
			}
		}
		
		//정답값 초기화
		int minDiff = Integer.MAX_VALUE;
		//부분집합 생성, 배열 내 있는지 비교
		for(int i = 1; i < Math.pow(2, N)-1; i++) {
			//A구역, B구역
			List<Integer> areaA = new ArrayList<>();
			List<Integer> areaB = new ArrayList<>();
			
			//비트마스킹을 통해 구역 부분집합 만들기
			for(int j = 0; j < N; j++) {
				if((i & (1<<j)) == 0) {
					areaA.add(j+1);
				} else {
					areaB.add(j+1);
				}
			}
			
			//팀 나누었으니 연결 여부 확인
			int startA = areaA.get(0);
			int startB = areaB.get(0);
			boolean[] visited = new boolean[N+1];
			visited[startA] = true;
			visited[startB] = true;
			
			Queue<Integer> queue = new LinkedList<>();
			//A그룹 bfs
			queue.add(startA);
			while(!queue.isEmpty()) {
				int now = queue.poll();
				for(int a : adjList[now]) {
					if(areaA.contains(a) && !visited[a]) {
						visited[a] = true;
						queue.add(a);
					}
				}
			}
			
			//B그룹 bfs
			queue.add(startB);
			while(!queue.isEmpty()) {
				int now = queue.poll();
				for(int a : adjList[now]) {
					if(areaB.contains(a) && !visited[a]) {
						visited[a] = true;
						queue.add(a);
					}
				}
			}
			//bfs종료, 만약 이어져 있지 않은 부분 있다면 visited false인 지역 존재할 것
			
			//방문 배열 탐색, false 있으면 불가능
			boolean flag = true;
			for(int j = 1; j <= N; j++) {
				if(visited[j] == false) {
					flag = false;
					break;
				}
			}
			//모두 이어져 있다(가능한 배열)면 다음 연산
			if(flag == true) {
				//각 지역 인구수 구하기
				int popA = 0;
				int popB = 0;
				for(int a : areaA) {
					popA += popul[a];
				}
				for(int b : areaB) {
					popB += popul[b];
				}
				//차이 갱신
				minDiff = Math.min(minDiff, Math.abs(popA - popB));
			}
			
		}
		
		if(minDiff == Integer.MAX_VALUE) minDiff = -1; //불가능한 경우 minDiff = -1로
		//결과 출력
		System.out.println(minDiff);
		
		
		br.close();
	}//end of main method

	
}
