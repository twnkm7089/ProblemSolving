package Feb4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//Dynamic Programming
public class BOJ_2156_포도주시식 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//wine[i] = i번째 와인잔에 든 것
		int[] wine = new int[N+1];
		for(int i = 1; i <= N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		//dP[0] = 0
		int[] dP = new int[N+1];
		//dP[1] = wine[1]
		dP[1] = wine[1];
		//dP[2] = wine[1] + wine[2]
		if(N >= 2) {
			dP[2] = wine[1] + wine[2];
		}
		//dP[i] = dP[i-3]+wine[i-1]+wine[i] vs dP[i-2]+wine[i] vs dP[i-1]
		//와인 이전거랑 이번거 먹기 vs 이번거만 먹고 전전거까지 최대값 vs 안먹기
		if(N >= 3) {
			for(int i = 3; i <= N; i++) {
				dP[i] = Math.max(dP[i-3] + wine[i-1] + wine[i], Math.max(dP[i-2] + wine[i], dP[i-1]));
			}
		}
//		System.out.println(Arrays.toString(dP));
		
		//output
		System.out.println(dP[N]);
	}
}
