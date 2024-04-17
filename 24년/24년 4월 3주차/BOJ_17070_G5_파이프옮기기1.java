import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17070_G5_파이프옮기기1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dP
		//(i,j)에서 도달 가능한 파이프 개수는 파이프 종류에 따라 다르다
		//(i,j)에 도달한 파이프 종류 가로:0, 대각선:1, 세로:2
		//dP[i][j][0] = dP[i][j-1][0] + dP[i][j-1][1] //왼쪽칸 + 가로, 대각선 파이프에서 이어짐
		//dP[i][j][1] = dP[i-1][j-1][0] + dP[i-1][j-1][1] + dP[i-1][j-1][2] //대각선 왼쪽칸 + 가로, 세로, 대각선 파이프에서 이어짐
		//dP[i][j][2] = dP[i-1][j][1] + dP[i-1][j][2] // 위쪽칸 + 세로, 대각선 파이프에서 이어짐
		int[][][] dP = new int[N][N][3];
		dP[0][1][0] = 1;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				//벽이면 생략
				if(map[i][j] == 1) continue;
				
				//아니면 계산 시작
				//가로 파이프
				if(isInside(i, j-1, N) && map[i][j-1] == 0) {
					dP[i][j][0] += (dP[i][j-1][0] + dP[i][j-1][1]);
				}
				//대각선 파이프
				if(isInside(i-1, j-1, N) && map[i-1][j-1] == 0 && map[i][j-1] == 0 && map[i-1][j] == 0) {
					dP[i][j][1] += (dP[i-1][j-1][0] + dP[i-1][j-1][1] + dP[i-1][j-1][2]);
				}
				//세로 파이프
				if(isInside(i-1, j, N) && map[i-1][j] == 0) {
					dP[i][j][2] += (dP[i-1][j][1] + dP[i-1][j][2]);
				}
			}
		}

		
		System.out.println(dP[N-1][N-1][0]+dP[N-1][N-1][1]+dP[N-1][N-1][2]);
	}
	
	public static boolean isInside(int r, int c, int N) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
