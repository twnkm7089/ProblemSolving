import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KTW_BOJ_11057_S1_오르막수 {	
	public static void main(String[] args) throws IOException {
		//dP
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		
		//dP[a][b] = 길이가 a이고 시작 수가 b인 오름수의 개수
		//dP[a+1][b] = b, (b or b+1 or ...) -> sum of dP[a][b], dP[a][b+1], ...
		int[][] dP = new int[N+1][10];
		for(int i = 0; i <= 9; i++) {
			dP[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j <= 9; j++) {
				int sum = 0;
				for(int k = j; k <= 9; k++) {
					sum += dP[i-1][k];
					sum %= 10007;
				}
				dP[i][j] = sum;
			}
		}
		
		int ans = 0;
		for(int i = 0; i <= 9; i++) {
			ans += dP[N][i];
			ans %= 10007;
		}
		
		System.out.println(ans);
		
	}

}
