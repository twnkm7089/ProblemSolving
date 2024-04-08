package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. BFS 이용
 * 2. 토마토의 총 개수 total, 익은 토마토의 개수 cnt를 둠.
 * 3. 우선 익은 토마토의 위치를 모두 큐에 넣음. 넣는건 행*M+열
 * 4. 이제 큐가 빌 때까지 큐에서 위치를 꺼내 근처에 있는 토마토 중 안익은 토마토의 값을 1로 바꾸고 cnt더해줌.
 * 5. 그 후 그 위치를 임시큐에 넣음
 * 6. 임시큐에서 큐로 값을 옮김.
 * 7. 이걸 탐색 가능 범위에 모두 하고 total != cnt면 -1, 나머지면 걸린 일수 표시.
 * */
public class KTW_BOJ_7576_G5 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] box = new int[N][M];//box
		int total = M*N; //tomato 총 개수
		int cnt = 0;//익은거 개수
		//queue
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == -1) {//없는칸 total에서 제거
					total--;
				}
				
				if(box[i][j] == 1) {//있으면 count변경, 큐에 추가
					cnt++;
					queue.add(i*M + j);
				}
			}
		}
		
		int day = 0;//걸린 일수
		//delta
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		Queue<Integer> temp = new LinkedList<>();//임시큐
		while(cnt != total) {
			day++;//일수 1일 증가
			//들어있던 것 모두 제거
			while(!queue.isEmpty()){
				int pos = queue.remove();
				//위치 행, 열 변환
				int r = pos / M;
				int c = pos % M;
				for(int d = 0; d < 4; d++) {//주변 탐색
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr >= 0 && nr < N && nc >= 0 && nc < M && box[nr][nc] == 0) {
						//안익은 토마토 존재시 cnt추가 및 box값 1 변경, 임시큐에 추가
						box[nr][nc] = 1;
						temp.add(nr*M + nc);
						cnt++;
					}
				}
			}
			
			//temp가 비어있으면 탐색 완료. 더이상 불가능. break
			if(temp.isEmpty()) {
				break;
			}
			
			
			//temp에서 queue로 이전
			while(!temp.isEmpty()) {
				queue.add(temp.remove());
			}
			//임시큐를 두는 이유는 전날 익었었던 것을 모두 다 연산한 후 다음 날로 넘어가 이번에 익었던 것을 사용해야 하기 때문
		}
		//탐색 불가 장소가 있었다면
		if(cnt != total) day=-1;
		//출력
		System.out.println(day);

	}//end of main method
}//end of class
