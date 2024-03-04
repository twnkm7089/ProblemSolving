package Feb5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15988_S2_123더하기3 {
	public static void main(String[] args) throws IOException {
		//very simple DP
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] dP = new int[1000001];
		dP[1] = 1;
		dP[2] = 2;
		dP[3] = 4;
		for(int i = 4; i <= 1000000; i++) {
			dP[i] = ((dP[i-1] + dP[i-2])%1000000009 + dP[i-3])%1000000009;
		}
		
		int T = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			System.out.println(dP[N]);
		}


	}
	

}
