import java.util.Scanner;

public class KTW_BOJ_17271_S2_LOLSMALL {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		//스킬 개수
		int[] dP = new int[N+1];
		//A만 사용하는 경우
		dP[1] = 1;
		for(int i = 2; i <= M; i++) {
			if(i > N) break;
			dP[i] = (dP[i-1])%1000000007;
		}
		//dP[M]은 dP[M-1] 상황에서 A를 하는 경우와 dP[0]에서 B를 하는 경우로 나누어진다.
		//위에서 A는 했으니 B로 가자
		if(M <= N) dP[M]=(dP[M]+1)%1000000007;
		
		//이제 추가
		for(int i = M+1; i <= N; i++) {
			dP[i] = (dP[i-1] + dP[i-M])%1000000007;
		}
		
		
		System.out.println(dP[N]);
		sc.close();
	}
}
