import java.util.Scanner;

public class KTW_BOJ_11727_S3 {
	/*알고리즘 설명
	 * 1. 다이나믹 프로그래밍 이용
	 * 2. 2*N-1까지 타일링 한 후 1*2 타일을 붙이는 경우.
	 * 3. 2*N-2까지 타일링 한 후 2*1 타일 2개나 2*2 하나 붙이는 경우.
	 * 4. 식으로 나타내면 dP[N] = dP[N-1] + 2*dP[N-2]
	 * 5. 범위 초과 방지 위해 나머지 정리 이용. a+b의 나머지는 a의 나머지 + b의 나머지
	 * 6. 결과 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//input
		int N = sc.nextInt();
		//dP
		int[] dP = new int[N+1];
		dP[1] = 1;	
		if(N >=2) dP[2] = 3;
		for(int i = 3; i <= N; i++) {//dP검사
			dP[i] = dP[i-1]%10007 + (2*dP[i-2])%10007;
		}
		//출력
		System.out.println(dP[N]%10007);
	}
}
