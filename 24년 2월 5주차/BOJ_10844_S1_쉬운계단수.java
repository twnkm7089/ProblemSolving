import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844_S1_쉬운계단수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		//dP초기화
		int[][] dP = new int[N+1][10];
		for(int j = 1; j <= 9; j++) {
			dP[1][j] = 1;
		}
		
		//dP[a][b]에 있던 수들은 dP[a+1][b-1], dP[a+1][b+1]로 갈 수 있다.
		for(int i = 1; i < N; i++) {
			for(int j = 0; j <= 9; j++) {
				if(j > 0) {
					dP[i+1][j-1] += dP[i][j] % 1000000000;
					dP[i+1][j-1] %= 1000000000;
				}
				
				if(j < 9) {
					dP[i+1][j+1] += dP[i][j] % 1000000000;
					dP[i+1][j+1] %= 1000000000;
				}
			}
		}
		
		//최종 구하기
		int sum = 0;
		for(int j = 0; j <= 9; j++) {
			sum += dP[N][j];
			sum %= 1000000000;
		}
		
		System.out.println(sum);
	}
}
