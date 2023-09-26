import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/* 알고리즘 설명
 * 1. 다익스트라 이용
 * 2. X로부터 다른 마을 까지 가는 최단거리 계산
 * 3. 다른 마을에서 X로 오는 최단 거리는 방향을 뒤집으면 다익스트라로 풀 수 있다.
 * 4. 두 경우를 구한 후 각 마을별 왕복 시간을 비교하며 최대값 구하기.
 * */

public class KTW_BOJ_1238_G3_파티 {	
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		//인접행렬, 역방향 연산 시 용이성을 위해
		int[][] adjArr = new int[N+1][N+1];
		//그래프 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			adjArr[A][B] = W;
		}
		
		//from 2
		int[] dist = new int[N+1];
		//to 2
		int[] dist2 = new int[N+1];
		//배열 초기화
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(dist2, Integer.MAX_VALUE);
		//pq 선언
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];//누적시간을 기준으로
			}
			
		});
		//초기값 추가
		pq.add(new int[] {X, 0});
		
		//from 2
		while(!pq.isEmpty()) {
			int[] data = pq.poll();
			int pos = data[0];
			int distance = data[1];
			
			if(distance < dist[pos]) {//기존 값보다 현재 들어온 데이터의 누적 시간이 적으면 갱신
				dist[pos] = distance;
				//근처 비교하며 pos로부터 나가는 행렬 찾아 추가
				for(int i = 1; i <= N; i++) {
					if(adjArr[pos][i] != 0) {
						pq.add(new int[] {i, distance + adjArr[pos][i]});
					}
				}
				
			}
		}
		//초기화
		pq.add(new int[] {X, 0});
		//to 2
		//역방향으로 계산
		while(!pq.isEmpty()) {
			int[] data = pq.poll();
			int pos = data[0];
			int distance = data[1];
			
			if(distance < dist2[pos]) {
				dist2[pos] = distance;
				//이번에는 pos로 들어오는 화살표를 반대로 했다고 생각, 같은 과정 진행
				for(int i = 1; i <= N; i++) {
					if(adjArr[i][pos] != 0) {
						pq.add(new int[] {i, distance + adjArr[i][pos]});
					}
				}
				
			}
		}
		//최대값 구하기
		int max = Integer.MIN_VALUE;
		for(int i = 1; i <= N; i++) {
			if((dist[i] + dist2[i]) > max) {
				max = dist[i] + dist2[i];
			}
		}
		//결과 출력
		System.out.println(max);
	}//end of main method
	
}
