import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 전형적 BFS 문제
 * 2. 층수 탐색, 일치 시 탐색 종료
 * 3. 아니면 +U, -D 큐에 넣기.
 * 4. 단계별 반복.
 * */
public class KTW_BOJ_5014_S1_스타트링크 {

	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		//bfs
		Queue<Integer> queue = new LinkedList<>();
		//queue에 추가, 방문 표시
		queue.add(S);
		boolean[] visited = new boolean[F+1];
		visited[S] = true;
		//탐색 시작
		int answer = -1;
		int cnt = 0;
		outer : while(!queue.isEmpty()) {
			//현재 큐에 있던 것 빼면서
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				int floor = queue.poll();
				
				//큐에서 빼서 층수 비교
				if(floor == G) {
					answer = cnt;
					break outer;
				}
				
				//위층 갈 수 있고, 아직 미방문이면 방문(큐에 넣기)
				if((floor + U) <= F && !visited[floor+U]) {
					visited[floor+U] = true;
					queue.add(floor+U);
				}
				
				//아래층 갈 수 있고, 아직 미방문이면 방문(큐에 넣기)
				if((floor - D) > 0 && !visited[floor-D]) {
					visited[floor-D] = true;
					queue.add(floor-D);
				}
			}
			//카운트 추가
			cnt++;
		}
		
		//결과 출력
		if(answer == -1) {//갈 수 없다
			System.out.println("use the stairs");
		} else {//갈 수 있다.
			System.out.println(answer);
		}
		
		br.close();
	}//end of main method

	
}
