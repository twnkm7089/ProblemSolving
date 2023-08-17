import java.util.Arrays;
import java.util.Scanner;

public class KTW_BOJ_2865_S4 {
	/*알고리즘 설명
	 * 한 줄 후기 : 문제 잘못 이해해서 개고생함.
	 * 1. 각 학생 별 낼 수 있는 최대 능력을 배열에 저장한 후, 배열을 내림차순한다.
	 * 2. 내림차순 한 후, 뒤에서 K번째 수까지 더해주어서 반올림 후 출력한다.
	 * */
	public static void main(String[] args) {
		//input
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		//배열 생성 각 index+1이 학생 번호에 대응함
		double[] studentMax = new double[N];
		for(int i = 0; i < M; i++) {//M개 장르
			for(int j = 0; j < N; j++) {//N명의 학생
				int idx = sc.nextInt();//번호 입력
				double power = sc.nextDouble();//능력 입력
				if(studentMax[idx-1] < power) {//현재 장르에서 낼 수 있는 능력이 그 학생이 낼 수 있는 능력의 최대치면 
					studentMax[idx-1] = power;//갱신
				}
			}
		}
		//배열 정렬(오름차순)
		Arrays.sort(studentMax);
		double sum = 0;//정답 배열
		for(int i = N-1; i > N-1-K; i--) {//뒤에서부터 K개(상위 K개)
			sum += studentMax[i];//더해주기
		}
		//출력(소수점 첫째자리까지 반올림)
		System.out.println(Math.round(sum*10)/10.0);
	}
}
