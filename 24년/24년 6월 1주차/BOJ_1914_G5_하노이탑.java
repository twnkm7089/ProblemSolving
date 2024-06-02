import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1914_G5_하노이탑 {
	static int[] K;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//횟수 구하기
		//dP dP[N] = 2*dP[N-1]+1
		long[] dP = new long[N+1];
		K = new int[35];
		K[34] = 1;
		for(int i = 2; i <= N; i++) {
			nextK();
		}
		
		boolean start = false;
		for(int i = 0; i < 35; i++) {
			if(!start) {
				if(K[i] != 0) start = true;
			}
			
			if(start) {
				System.out.print(K[i]);
			}
		}
		System.out.println();
		
		
		//과정 구하기
		if(N <= 20) {
			findProcess(1, 3, 2, N);
		}
		
	}
	
	//big Integer op
	public static void nextK() {
		//add
		int carry = 1; // +1
		int[] newK = new int[35];
		for(int i = 34; i >= 0; i--) {
			int sum = 2*K[i] + carry;
			newK[i] = sum%10;
			carry = sum/10;
		}
		
		K = newK;
	}
	
	
	//find process
	//S : start, T : target, R : reserved, N : level
	public static void findProcess(int S, int T, int R, int N) {
		if(N == 1) {
			//1층이면 그냥 시작에서 끝으로
			System.out.println(S + " " + T);
			return;
		}
		
		//재귀
		findProcess(S, R, T, N-1); //S지점에 있던 N-1층 탑을 R지점으로 옮기고
		System.out.println(S + " " + T); //맨 아래 있던 것을 T지점으로 옮기고
		findProcess(R, T, S, N-1); //R지점에 있던 N-1층 탑을 T지점으로 옮긴다.
	}
	
}
