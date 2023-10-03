import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 각 지점으로부터 반경 1000m까지 갈 수 있다.
 * 2. BFS를 사용한다. 출발 지점을 큐에 넣는다.
 * 3. 큐에서 빼낸 지점의 좌표로부터 아직 방문하지 않았으며 반경 1000m 이내의 위치 좌표를 큐에 넣는다.
 * 4. 반복하다 펜타포트 락 페스티벌에 도착하면 happy. 큐를 다 비웠는데도 못가면 sad.
 * */
public class KTW_BOJ_9205_G5_맥주마시며걸어가기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int T = Integer.parseInt(br.readLine());
		//start of test case
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] position = new int[N+2][2];
			boolean[] visited = new boolean[N+2];
			
			//map intput
			for(int i = 0; i <= N+1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				position[i][0] = Integer.parseInt(st.nextToken());
				position[i][1] = Integer.parseInt(st.nextToken());
			}
			
			//queue생성
			Queue<int[]> queue = new LinkedList<>();
			queue.add(position[0]);
			visited[0] = true;//방문 표시
			
			//탐색 시작(BFS)
			boolean happy = false;
			outer : while(!queue.isEmpty()) {
				//좌표 꺼내기
				int[] temp = queue.poll();
				//탐색
				for(int i = 1; i <= N+1; i++) {
					if(!visited[i]) {//미방문 좌표의 경우
						//거리 구해서 1000이내이면 큐에 추가후 방문표시, 그 중 펜타포트면 happy true로.
						int distance = Math.abs(temp[0] - position[i][0]) + Math.abs(temp[1] - position[i][1]);
						if(distance <= 1000) {
							queue.add(position[i]);
							visited[i] = true;
							
							if(i == N+1) {
								happy = true;
								break outer;
							}
						}
					}
				}//end of for
			}//end of while
			
			//결과 출력
			if(happy) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
			
		}//end of test case
		
	}//end of main method
	
	
}//end of class
