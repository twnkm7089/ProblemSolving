package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 대표적인 Dynamic Programming 문제
 * 2. N-3칸에서 N-1, N칸 밟기 vs N-2칸에서 바로 N칸 밟기
 * 3. 초기값을 구한 후, dP[N] = Math.max(dP[N-2] + stair[N], dP[N-3] + stair[N-1] + stair[N]) 사용
 * */
public class KTW_BOJ_2579_S3 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		
		int[] stair = new int[N];
		for(int i = 0; i < N; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		//dP배열 생성
		int[] dP = new int[N];
		if(N > 0) {
			dP[0] = stair[0];//0번째 칸은 그냥 그 점수가 최대
		}
		if(N > 1) {
			dP[1] = dP[0] + stair[1];//1번 칸은 0번 + 1번의 값이 최대
		}
		if(N > 2) {
			dP[2] = Math.max(dP[0] + stair[2], stair[1] + stair[2]);//2번칸은 밟는게 0,2냐 1,2냐
		}
		for(int i = 3; i < N; i++) {
			dP[i] = Math.max(dP[i-2] + stair[i], dP[i-3] + stair[i-1] + stair[i]);//그 이상은 위의 점화식대로
		}
		//결과 출력
		System.out.println(dP[N-1]);
		
	}//end of main method
	
	
}//end of class
