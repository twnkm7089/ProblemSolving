import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KTW_BOJ_14500_G4_테트로미노 {
	/*알고리즘 설명
	 * 1. 그냥 쌩 구현함, 모든 경우의 수 4*1, 1*4, 2*2, 3*2, 2*3 나누어 구함
	 * 2. 설명할거 없음
	 * 3. 다른사람 코드 보니 dfs 많이 사용했는데 차이는 없는듯.
	 * */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//직선(세로로)
		for(int i = 0; i <= N-4; i++) {
			for(int j = 0; j < M; j++) {
				int temp = 0;
				for(int d = 0; d < 4; d++) {
					temp += map[i+d][j];
				}
				max = Math.max(max, temp);
			}
		}
		
		//직선(가로로)
		for(int i = 0; i < N; i++) {
			for(int j = 0; j <= M-4; j++) {
				int temp = 0;
				for(int d = 0; d < 4; d++) {
					temp += map[i][j+d];
				}
				max = Math.max(max, temp);
			}
		}
		
		//사각형
		for(int i = 0; i <= N-2; i++) {
			for(int j = 0; j <= M-2; j++) {
				int temp = map[i][j] + map[i+1][j] + map[i][j+1] + map[i+1][j+1];
				max = Math.max(max, temp);
			}
		}
		
		
		//가로3 세로2
		for(int i = 0; i <= N-2; i++) {
			for(int j = 0; j <= M-3; j++) {
				// ㄴ 자 모형, T자 모형(위가 찬 모습)
				int temp1 = 0;
				for(int d = 0; d < 3; d++) {
					temp1+=map[i][j+d];
				}
				int temp2 = temp1;
				temp1 += Math.max(map[i+1][j], map[i+1][j+2]);
				temp2 += map[i+1][j+1];
				max = Math.max(max, temp1);
				max = Math.max(max, temp2);
				
				//아래가 찬 모습
				temp1 = 0;
				for(int d = 0; d < 3; d++) {
					temp1+=map[i+1][j+d];
				}
				temp2 = temp1;
				temp1 += Math.max(map[i][j], map[i][j+2]);
				temp2 += map[i][j+1];
				max = Math.max(max, temp1);
				max = Math.max(max, temp2);
				
				//나머지 모양
				int temp3 = map[i][j+1] + map[i][j+2] + map[i+1][j] + map[i+1][j+1];
				int temp4 = map[i+1][j+1] + map[i+1][j+2] + map[i][j] + map[i][j+1];
				max = Math.max(max, temp3);
				max = Math.max(max, temp4);
			}
		}
		
		//세로3 가로2
		for(int i = 0; i <= N-3; i++) {
			for(int j = 0; j <= M-2; j++) {
				// ㄴ 자 모형, T자 모형(좌가 찬 모습)
				int temp1 = 0;
				for(int d = 0; d < 3; d++) {
					temp1+=map[i+d][j];
				}
				int temp2 = temp1;
				temp1 += Math.max(map[i][j+1], map[i+2][j+1]);
				temp2 += map[i+1][j+1];
				max = Math.max(max, temp1);
				max = Math.max(max, temp2);
				
				//우가 찬 모습
				temp1 = 0;
				for(int d = 0; d < 3; d++) {
					temp1+=map[i+d][j+1];
				}
				temp2 = temp1;
				temp1 += Math.max(map[i][j], map[i+2][j]);
				temp2 += map[i+1][j];
				max = Math.max(max, temp1);
				max = Math.max(max, temp2);
				
				//나머지 모양
				int temp3 = map[i+1][j] + map[i+2][j] + map[i][j+1] + map[i+1][j+1];
				int temp4 = map[i+1][j+1] + map[i+2][j+1] + map[i][j] + map[i+1][j];
				max = Math.max(max, temp3);
				max = Math.max(max, temp4);
			}
		}
		//결과 출력
		System.out.println(max);
	}
	
	
}
