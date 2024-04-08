import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 상!!!어!!!야!!!
 * 2. 그냥 구현 문제
 * 3. 구름 위치가 들어가는 큐와 비가 내린 장소를 저장하는 rained 배열이 핵심
 * 4. 큐에서 위치 정보를 꺼내 모듈러 연산 후, 음수의 경우 +N 보정을 해주었음.
 * 5. 해당 위치로 구름 이동 후 비내리기. rained에 표시
 * 6. 후에 rained에 표시된 부분은 대각선 조사해서 물 양 증가
 * 7. 후에 rained 표시 안된 부분 중 구름 생성 필요한 부분에 구름 생성
 * 8. 명령어 끝날때까지 반복
 * */
public class KTW_BOJ_21610_G5_마법사상어와비바라기 {	
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//map입력(지도 정보)
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//delta
		int[] dr = {Integer.MAX_VALUE, 0, -1, -1, -1, 0, 1, 1, 1};
		int[] dc = {Integer.MAX_VALUE, -1, -1, 0, 1, 1, 1, 0, -1};
		//이동 정보 입력
		int[][] move = new int[M][2];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//모든 구름이 들어있는 큐
		Queue<int[]> queue = new LinkedList<>();
		//초기 위치
		queue.add(new int[] {N-2, 0});
		queue.add(new int[] {N-2, 1});
		queue.add(new int[] {N-1, 0});
		queue.add(new int[] {N-1, 1});
		//명령어 실행
		for(int i = 0; i < M; i++) {
			//비 옴 표시 배열 초기화
			boolean[][] rained = new boolean[N][N];
			//방향, 길이 추출
			int dir = move[i][0];
			int length = move[i][1];
			//비내리기
			while(!queue.isEmpty()) {
				//구름이 이동한 위치 구하기
				int[] cloud = queue.poll();
				int nr = (cloud[0] + dr[dir]*length)%N;
				int nc = (cloud[1] + dc[dir]*length)%N;
				
				if(nr < 0) nr += N;
				if(nc < 0) nc += N;
				//비옴 표시
				map[nr][nc]++;
				rained[nr][nc] = true;
			}
			
			//물복사
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(rained[j][k] == true) {//비가 온 장소는
						int cnt = 0;
						//대각선 방향 델타 탐색
						for(int d = 2; d < 9; d+=2) {
							int nr = j + dr[d];
							int nc = k + dc[d];
							if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != 0) {
								cnt++;
							}
						}//end of delta search
						//물 있던 만큼 물 양 증가
						map[j][k] += cnt;
					}
				}
			}
			
			//구름 생성
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					//비 안온 지역 중 물이 2 이상인 곳
					if(map[j][k] >= 2 && !rained[j][k]) {
						//큐에 추가 후 수위 조절
						queue.add(new int[] {j, k});
						map[j][k] -= 2;
					}
				}
			}
			
		}
		
		//모든 칸의 물의 양 증가시키기
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				answer += map[i][j];
			}
		}
		//출력
		System.out.println(answer);
	}//end of main method
	
}
