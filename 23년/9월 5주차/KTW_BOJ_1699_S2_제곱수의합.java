import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*dP사용
 * 1. 각 수에 대해 나올 수 있는 최대 제곱수 찾기
 * 2. dP[i-해당제곱수] + 1(제곱수 하나 추가) vs 기존 dP[i]
 * 3. N까지
 * */


public class KTW_BOJ_1699_S2_제곱수의합 {

	
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		//input
		int N = Integer.parseInt(br.readLine());
		int[] dP = new int[N+1];
		//모두 차 있었음
		Arrays.fill(dP, 100);
		//dP[0]초기화
		dP[0] = 0;
		for(int i = 1; i <= N; i++) {//dP배열 채우기
			int sqrt = (int)Math.sqrt(i);//최대 제곱수 찾기
			for(int j = 1; j <= sqrt; j++) {//dP배열 비교 존재할 수 있는 모든 제곱수에 대해
				//dP[i-해당제곱수] + 1(제곱수 하나 추가) vs 기존 dP[i]
				dP[i] = Math.min(dP[i], dP[i - (j*j)]+1);
			}
		}
		//결과 출력
		System.out.println(dP[N]);
		
	}//end of main method
	
}
