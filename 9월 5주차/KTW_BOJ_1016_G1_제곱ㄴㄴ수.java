import java.io.IOException;
import java.util.Scanner;
/*알고리즘 설명
 * 1. 에라토스테네스 체 이용
 * 2. 탐색 범위 줄이기 위해 탐색 시작 값을 min이하 N의 배수 중 최대값으로 선언
 * 3. 아래 코드 참고
 * */
public class KTW_BOJ_1016_G1_제곱ㄴㄴ수 {

	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		
		long min = sc.nextLong();
		long max = sc.nextLong();
		
		int arrSize = (int)(max - min + 1);
		boolean[] jegobnono = new boolean[arrSize];
		//false: 제곱ㄴㄴ수, true : 제곱ㅇㅇ수
		
		//탐색 범위, max의 루트값 이하의 최대 정수값
		long maxSqrt = (long)Math.sqrt(max);

		for(long i = 2; i <= maxSqrt; i++) {//탐색
			long N = i*i;//제곱수
			long initNum = (min/N) * N; //min 이하의 N의 배수 중 최대값
			long temp = initNum;//시작 범위 지정
			//에라토스 테네스 체
			while(temp <= max) {
				//해당 제곱수의 배수면 표시
				if(min <= temp && max >= temp) {
					int idx = (int)(temp - min);
					jegobnono[idx] = true;
				}
				
				temp += N;
			}
		}
		//결과 찾기
		int cnt = 0;
		for(int i = 0; i < jegobnono.length; i++) {
			//제곱ㄴㄴ수 찾으면 count
			if(jegobnono[i] == false) {
				cnt++;
			}
		}
		//출력
		System.out.println(cnt);
		
	}//end of main method

	
}
