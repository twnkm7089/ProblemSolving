import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class KTW_BOJ_7569_G5_토마토3차원 {
	/*알고리즘 설명
	 * 1. 3차원 토마토
	 * 2. BFS 이용
	 * 3. 3차원 위치 정보를 정수로 변환
	 * 4. 층*(N*M) + 행*M + 열
	 * 5. 복구는 아래 로직 참조
	 * 6. 실수로 큐가 아니라 스택에 추가. 어차피 푸는데 영향은 없었음
	 * */
	
	public static void main(String[] args) throws IOException {
		//input, 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] box = new int[N][M][H];
		int total = N*M*H; //전체 토마토 수
		int now = 0;//익은 토마토 수
		//위치 정보 (현재높이*(N*M) + 현재행*(M) + 현재 열)
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < M; k++) {
					box[j][k][i] = Integer.parseInt(st.nextToken());
					if(box[j][k][i] == -1) {//토마토 총 개수 구하기
						total--;//없는 곳은 빼기
					}
					if(box[j][k][i] == 1) {
						stack.add(i*(N*M) + j*M + k);//스택에 위치 정보 저장
						now++;//익은 토마토 수 증가
					}
				}
			}
		}
		//delta
		int[] dr = {-1, 1, 0, 0, 0, 0};
		int[] dc = {0, 0, -1, 1, 0, 0};
		int[] dh = {0, 0, 0, 0, -1, 1};
		int day = 0;//일수
		//임시 스택
		Stack<Integer> temp = new Stack<>();
		while(now != total) {//익은 토마토 수가 전체 토마토 수와 다르면
			day++;//일수 추가
			while(!stack.isEmpty()) {//스택에서 뽑기
				int pos = stack.pop();
				//위치정보 변환
				int height = pos / (N*M);
				pos -= (N*M)*height;
				int row = pos / M;
				int col = pos % M;
				//탐색
				for(int d = 0; d < 6; d++) {
					int nr = row + dr[d];
					int nc = col + dc[d];
					int nh = height + dh[d];
					//범위내, 익지않은 토마토 존재시
					if(nr >= 0 && nr < N && nc >= 0 && nc < M && nh >= 0 && nh < H && box[nr][nc][nh] == 0) {
						box[nr][nc][nh] = 1;//익히고
						now++;//수 증가
						temp.add(nh*(N*M) + nr*M + nc);//저장
					}
				}
			}	
			if(temp.isEmpty()) {//더이상 탐색 불가
				break;//종료
			}
			//임시 큐에서 이동
			while(!temp.isEmpty()) {
				stack.add(temp.pop());
			}
		}
		//결과 출력
		if(now != total) {//안익은거 존재시 -1
			System.out.println(-1);
		} else {//다익으면 일수 출력
			System.out.println(day);
		}
	}

}
