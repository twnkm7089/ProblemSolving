import java.io.IOException;
import java.util.Scanner;
/*알고리즘 설명
 * 1. 행렬 제곱과 비슷
 * 2. [1 1]^N = [f(N+1)   f(N)]
 * 3. [1 0]     [f(N)   f(N-1)]
 * 4. 행렬 제곱에 의해 f(N/2)*f(N/2)=f(N)을 이용한 재귀 함수 적용
 * */
public class KTW_BOJ_11444_G2_피보나치6 {
	static final long MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		long N = sc.nextLong();
		//입력에 따른 결과 출력
		if(N>=3) {			
			long[][] answer = fibo(N-1);
			System.out.println(answer[0][0]%MOD);
		} else {
			System.out.println(1);
		}
	}//end of main method
	//fibo method
	public static long[][] fibo(long N){
		if(N == 1) {//N=1이면 돌려주기
			long[][] fibo1 = {{1, 1}, {1, 0}};
			return fibo1;
		}
		
		if(N%2 == 0) {//N이 짝수면 f(N/2)*f(N/2) = f(N)
			long[][] temp = fibo(N/2);
			long[][] ans = new long[2][2];
			//행렬 곱 계산
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					for(int k = 0; k < 2; k++) {
						ans[i][j] += ((temp[i][k]%MOD)*(temp[k][j]%MOD))%MOD;
					}
					ans[i][j] %= MOD;
				}
			}
			return ans;
		} else { //홀수면 f((N-1)/2)*f((N-1)/2)*f(1) = f(N)
			long[][] temp = fibo((N-1)/2);
			long[][] ans = new long[2][2];
			//행렬 곱 계산
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					for(int k = 0; k < 2; k++) {
						ans[i][j] += ((temp[i][k]%MOD)*(temp[k][j]%MOD))%MOD;
					}
					ans[i][j] %= MOD;
				}
			}
			//[1 1]
			//[1 0]에 대한 곱 한번더
			long[][] ans2 = new long[2][2];
			long[][] fibo1 = {{1, 1}, {1, 0}};
			for(int i = 0; i < 2; i++) {
				for(int j = 0; j < 2; j++) {
					for(int k = 0; k < 2; k++) {
						ans2[i][j] += ((fibo1[i][k]%MOD)*(ans[k][j])%MOD)%MOD;
					}
					ans2[i][j] %= MOD;
				}
			}

			return ans2;
		}
	}
	
}//end of class
