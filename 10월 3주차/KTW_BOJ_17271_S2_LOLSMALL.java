import java.util.Scanner;

public class KTW_BOJ_17271_S2_LOLSMALL {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		//��ų ����
		int[] dP = new int[N+1];
		//A�� ����ϴ� ���
		dP[1] = 1;
		for(int i = 2; i <= M; i++) {
			if(i > N) break;
			dP[i] = (dP[i-1])%1000000007;
		}
		//dP[M]�� dP[M-1] ��Ȳ���� A�� �ϴ� ���� dP[0]���� B�� �ϴ� ���� ����������.
		//������ A�� ������ B�� ����
		if(M <= N) dP[M]=(dP[M]+1)%1000000007;
		
		//���� �߰�
		for(int i = M+1; i <= N; i++) {
			dP[i] = (dP[i-1] + dP[i-M])%1000000007;
		}
		
		
		System.out.println(dP[N]);
		sc.close();
	}
}
