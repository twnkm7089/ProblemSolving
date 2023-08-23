package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 하단의 알고리즘 설명이 힌트가 됨.
 * 2. N부터 1을 만드는 최소 횟수는 1부터 N을 만드는 최소 횟수와 같다.
 * 3. 예를 들어 N이 6의 배수라 하자, 그럼, N/3을 만드는데 필요한 최소 회수, N/2를 만드는데 필요한 최소 회수, N-1을 만드는데 필요한 최소 회수 중 제일 작은 것을 찾아야 한다.
 * 4. 거기에 1을 더하면 된다.
 * 5. 1부터 시작해 이 회수를 저장하며 다이나믹 프로그래밍을 구현해 문제를 풀었다.
 * */
public class KTW_BOJ_1463_S3 {
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//입력
		if(N < 4) {//N이 4 미만인 경우
			int[] dP = new int[4];
			dP[1] = 0;
			dP[2] = 1;
			dP[3] = 1;
			System.out.println(dP[N]);
		} else {//그 외
			long[] dP = new long[N+1];//배열 생성
			//초기값 저장
			dP[1] = 0;
			dP[2] = 1;
			dP[3] = 1;
			for(int i = 4; i <= N; i++) {//N까지 계산
				if(i % 6 == 0) {//i가 2, 3의 공배수
					dP[i] = small(dP[i/3], dP[i/2], dP[i-1]) + 1;// i/3, i/2, i-1 중 최소 횟수 찾아 거기 1 더함.
				} else if(i % 3 == 0) {//i가 3의 배수(2는 아님)
					dP[i] = Math.min(dP[i/3], dP[i-1]) + 1;// i/3, i-1중 최소 회수
				} else if(i % 2 == 0) {//i가 2의 배수(3은 아님)
					dP[i] = Math.min(dP[i/2], dP[i-1]) + 1;// i/2, i-1 중 최소 회수
				} else {//그 외
					dP[i] = dP[i-1] + 1;//1 빼는 경우만 있으니 i-1 만드는데 든 회수 + 1
				}
				
			}
			System.out.println(dP[N]);//결과 출력
		}
		
		
	}//end of main method
	
	public static long small(long a, long b, long c) {//3개 중 최소값 찾는 함수
		if(a <= b && a <= c) {
			return a;
		} else if(b <= a && b <= c) {
			return b;
		} else {
			return c;
		}
	}
}//end of class
