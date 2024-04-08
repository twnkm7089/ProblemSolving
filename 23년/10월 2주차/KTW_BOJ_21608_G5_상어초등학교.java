import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class KTW_BOJ_21608_G5_상어초등학교 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};
		
		//좋아하는 학생 리스트, index:학생번호, 안에 든게 좋아하는 사람
		List<Integer>[] likes = new ArrayList[(N*N)+1];
		
		//학생 배치
		for(int num = 0; num < N*N; num++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//학생 넘버
			int sNum = Integer.parseInt(st.nextToken());
			//좋아하는 학생
			likes[sNum] = new ArrayList<>();
			for(int i = 0; i < 4; i++) {				
				likes[sNum].add(Integer.parseInt(st.nextToken()));
			}
			
			int updateR = -1;
			int updateC = -1;
			int max = -1;
			//작은 행, 열부터 조사
			//주변에 빈칸이 있으면 10점 추가, 좋아하는 친구 있으면 100점 추가
			//점수가 높은 곳에 갱신
			//i, j를 작은 것부터 조사해 우선순위 조건 3 해결
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int score = 0;
					for(int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						//map내부일 경우
						if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
							//빈칸은 10점 추가
							if(map[nr][nc] == 0) {
								score += 10;
							} else if(likes[sNum].contains(map[nr][nc])) {//좋아하는 친구는 100점 추가
								score += 100;
							}
						}
					}
					//점수가 제일 높고, 해당 위치에 학생이 들어갈 수 있으면 갱신
					if(score>max && map[i][j] == 0) {
						updateR = i;
						updateC = j;
						max = score;
					}
				}
			}
			
			//갱신
			map[updateR][updateC] = sNum;
		}
		
		//최종 만족도 조사
		int answer = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int cnt = 0;
				//주변에 좋아하는 학생 수 조사
				int sNum = map[i][j];
				for(int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					//map내부일 경우
					if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
						if(likes[sNum].contains(map[nr][nc])) {
							cnt++;
						}
					}
				}
				
				//1이상이면 10^(cnt-1) 추가
				if(cnt != 0) {
					answer += (int)Math.pow(10, cnt-1);
				}
			}
		}
		//결과 출력
		System.out.println(answer);
	}//end of main method
	
	
}//end of class
