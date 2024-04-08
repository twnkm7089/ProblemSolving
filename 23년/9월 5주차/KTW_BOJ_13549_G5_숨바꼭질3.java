import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. PriorityQueue로 구현한 Dijkstra 이용
 * 2. cnt(누적 횟수)를 기준으로 pq구현, min head인 점 이용
 * 3. dist 배열에 각 숫자로 가는 누적 횟수 저장
 * 4. N이 K 이상이면 -1로만 갈 수 있으니 차이를 출력
 * 5. 반대의 경우 pq 이용 다익스트라, 백 트래킹 기법 등 구현
 * */
public class KTW_BOJ_13549_G5_숨바꼭질3 {
	//Node class 정의
	static class Node implements Comparable<Node>{
		int value;
		int cnt;
		
		public Node(int value, int cnt) {
			this.value = value;
			this.cnt = cnt;
		}


		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.cnt - o.cnt;
		}
		
		
	}
	
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if(N >= K) {//크면 차이 출력
			System.out.println(N-K);
		} else {
			//dijkstra
			PriorityQueue<Node> pq = new PriorityQueue<>();
			//dist배열 초기화
			int[] dist = new int[100001];
			Arrays.fill(dist, Integer.MAX_VALUE);
			//pq시작
			pq.add(new Node(N, 0));
			
			while(!pq.isEmpty()) {//pq빌 때까지
				//poll하기.
				Node data = pq.poll();
				int num = data.value;
				int nowdist = data.cnt;

				if(nowdist > dist[K]) {//dist[K]에 저장된 값보다 크면 더 해봤자 소용 없음 continue
					continue;
				}
				//이걸로 백트래킹
				
				//아니면
				if(nowdist < dist[num]) {//누적 거리가 dist[num]에 저장된 기존 거리보다 작은 경우
					//갱신
					dist[num] = nowdist;
					
					//추가
					if(num > 1) {//minus
						pq.add(new Node(num-1, nowdist+1));//새로 넣기
					} 
					if(num < 100000) {//plus
						pq.add(new Node(num+1, nowdist+1));//새로 넣기
					}
					//double
					if(num*2 <= K) {
						//2배한 값이 범위 내
						pq.add(new Node(num*2, nowdist));//pq에 추가, 여기서 nowdist는 증가 안시킴(순간이동)
					} else {//넘어갔으면?
						//해당 숫자에서 -1 연산을 해서 가는 것만 가능
						//연산 후 최소값이면 갱신(pq에 추가는 안함)
						dist[K] = Math.min(nowdist + (num*2 - K), dist[K]);
					}
				}
			}
			//결과 출력
			System.out.println(dist[K]);
		}
		
		
		
	}//end of main method
	
}
