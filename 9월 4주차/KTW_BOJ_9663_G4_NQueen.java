import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 백트래킹 문제
 * 2. chess위에 놓을 수 있는 위치인지 유효성 검사하고 가능하면 놓고 다음 행으로 재귀
 * 3. 위만 고려하면 되니 방향은 위 3방향
 * */
public class KTW_BOJ_9663_G4_NQueen {
	public static int[][] chess;
	public static int N;
	public static int[] dc = {-1, 0, 1};
	public static int cnt;
	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		chess = new int[N][N];//chess판 할당
		nQueen(0);//0번행부터 실행
		System.out.println(cnt);//결과 출력
		br.close();
	}//end of main method
	
	//nQueen function
	public static void nQueen(int row) {		
		if(row == N) {//다 놓았으면 cnt 증가 후 return
			cnt++;
			return;
		}
		
		
		for(int i = 0; i < N; i++) {//각 행별 분석
			//유효성 검사(위에 다른 말이 있는가)
			boolean flag = true;
			outer : for(int d = 0; d < 3; d++) {
				for(int m = 1; m <= row; m++) {
					int nr = row - m;
					int nc = i + m*dc[d];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
						break;
					}
					
					if(chess[nr][nc] == 1) {
						flag = false;
						break outer;
					}
				}
			}

			if(flag == false) continue;//유효성 검사 실패시 다음 칸으로
			else {//놓을 수 있다(유효성 검사 성공)
				chess[row][i] = 1;//방문 표시
				nQueen(row+1);//재귀, 다음행으로
				chess[row][i] = 0;//방문 복원
			}
		}
	}
	
}//end of class
