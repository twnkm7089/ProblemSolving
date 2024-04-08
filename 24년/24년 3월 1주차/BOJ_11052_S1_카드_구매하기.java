import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_11052_S1_카드_구매하기 {	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//2D dP
		int[][] dP = new int[N+1][N+1];
		
		//간단 설명
		//dP[i][j] = 1개~i개 든 카드팩으로 j개의 카드 세트 만들기
		//dP[i][j] = dP[i-1][j] vs dP[i][j-i]
		//이번 카드팩 말고 i-1번 카드팩까지 포함 시 최대값 vs
		//이번 카드팩 포함(중복 허용) 최대값
		//(중복 미허용이면 dP[i][j-i]는 dP[i-1][j-i]로 바뀜)
		for(int i = 1; i <= N; i++) {
			//i번째 카드 선택
			for(int j = 1; j <= N; j++) {
				if(j >= i)
					dP[i][j] = Math.max(dP[i-1][j], dP[i][j-i] + arr[i]);
				else
					dP[i][j] = dP[i-1][j];
			}
		}
		
		//output
		System.out.println(dP[N][N]);
	}
}
