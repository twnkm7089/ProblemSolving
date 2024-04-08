import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*알고리즘 설명
 * 1. P(n) = P(n-1) +  P(n-5)라는 점화식을 세워서 풀었음. 다이나믹 프로그래밍
 * 2. 중간에 int범위 넘어가 long으로 배열 선언
 * */

public class KTW_BOJ_9461_S3 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //test case input
		for(int tc = 1; tc <= T; tc++) {//매 테케마다
			int N = Integer.parseInt(br.readLine());//N input
			long[] arr = new long[N+1];//배열 선언
			if(N <= 5) {//N이 5이하인 경우 대비한 배열 선언
				arr = new long[6];
			}
			//초기식 입력
			arr[1] = 1L;
			arr[2] = 1L;
			arr[3] = 1L;
			arr[4] = 2L;
			arr[5] = 2L;
			for(int i = 6; i <= N; i++) {//점화식 풀기
				arr[i] = arr[i-1] + arr[i-5];
			}
			
			System.out.println(arr[N]);//결과 출력
			
		}
	}
}
