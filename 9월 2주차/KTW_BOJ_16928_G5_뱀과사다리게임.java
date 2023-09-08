import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*알고리즘 설명
 * 1. 존재하는 모든 분기점마다 분기하며 수 세기
 * 2. BFS, Queue 이용
 * 3. 시작 위치 기준으로 다음 위치 찾기. 사다리, 뱀이 있으면 그 끝점을 다음 위치로 큐에 추가
 * 4. 사다리, 뱀 있는 점 제외한 최대 위치도 추가.
 * 5. 다음 턴이 되면 큐에 있는 모든 점을 꺼내, 그곳을 시작으로 같은 탐색 실시.
 * 6. 100에 도착하는 점에서 탐색 종료
 * */

public class KTW_BOJ_16928_G5_뱀과사다리게임 {
	
	public static void main(String[] args) throws IOException {
		//input, 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int pos = 1;//시작 위치
		//사다리, 뱀 기록
		int[][] change = new int[N+M][2];
		for(int i = 0; i < (N+M); i++) {
			st = new StringTokenizer(br.readLine());
			change[i][0] = Integer.parseInt(st.nextToken());
			change[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//사다리, 뱀마다 분기
		Queue<Integer> queue = new LinkedList<>();
		Queue<Integer> temp = new LinkedList<>();
		queue.add(1);//큐에 시작 위치 입력
		int cnt = 0;//횟수 세기
		outer : while(true) {//탐색
			cnt++;//횟수 증가
			while(!queue.isEmpty()) {//큐에있는 점마다 시행
				int data = queue.remove();//큐에서 꺼내기
				int[] cango = new int[7]; //사다리, 뱀 없는 최대 칸 분석
				for(int i = 1; i <= 6; i++) {//1~6까지 던지며
					int newpos = data + i;//새로운 칸
					if(newpos == 100) {//종료
						break outer;//탐색 종료
					} else if(newpos < 100){
						//뱀, 사다리 탐색, 다음칸 저장.
						for(int j = 0; j < (N+M); j++) {
							if(change[j][0] == newpos) {
								temp.add(change[j][1]);
								cango[i] = 1;//사다리가 있어서 해당 칸에 갈 수 없음 표시.
							}
						}
					}
				}
				
				//사다리, 뱀이 없어서 도달 가능한 최대 칸 저장.
				for(int i = 6; i >= 1; i--) {
					if(cango[i] == 0) {
						temp.add(data + i);
						break;
					}
				}
			}
			
			//분기 완료
			while(!temp.isEmpty()) {
				queue.add(temp.remove());
			}
		}
		//출력
		System.out.println(cnt);
		
		
	}//end of main method

}//end of class
