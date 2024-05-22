import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12101_S1_123더하기2 {
	static int N;
	static long K;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		//brute force + recursion
		//가능한 경우의 수 찾아서 불가능한 경우는 -1로 출력
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		cnt = 0;
		
		//경우의 수 구하기
		int[] dP = new int[N+1];
		//initial
		if(N >= 1) {
			dP[1] = 1;
		}
		if(N >= 2) {
			dP[2] = 2;
		}
		if(N >= 3) {
			dP[3] = 4;
		}
		for(int i = 4; i <= N; i++) {
			dP[i] = dP[i-1] + dP[i-2] + dP[i-3];
		}
		
		if(dP[N] < K) {
			//불가능한 수
			System.out.println(-1);
		} else {
			//그 외(재귀)
			makeStr(1, "1");
			makeStr(2, "2");
			makeStr(3, "3");
		}
		
		br.close();
		
	}
	
	public static void makeStr(int cumul, String str) {
		//이미 문자열 찾았으면 return
		if(cnt >= K) return;
		
		if(cumul == N) {
			//더한 식 찾으면 cnt 증가
			cnt++;
			//K번째 수식이면 출력
			if(cnt == K) {
				System.out.println(str);
				return;
			}
		} else if(cumul > N) {
			return;
		} else {
			makeStr(cumul+1, str+"+1");
			makeStr(cumul+2, str+"+2");
			makeStr(cumul+3, str+"+3");
		}
		
	}
}
