import java.util.Arrays;
import java.util.Scanner;

public class KTW_BOJ_11399_S4 {
	/*알고리즘 설명
	 * 1. 특성상 앞에 나온 숫자는 뒤에서 계속 더해진다(누적됨).
	 * 2. 그렇다면 앞에 나오는 숫자가 적을수록 시간이 적어진다.
	 * 3. 오름차순 정렬->누적합 구하기->결과 더하기
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		//input
		for(int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr); //오름차순 정렬
		for(int i = 1; i < N; i++) {//누적합 구하기
			arr[i] += arr[i-1];
		}
		int sum = 0;//결과 모두 더하기
		for(int i = 0; i < N; i++) {
			sum += arr[i];
		}
		System.out.println(sum);//출력
	}
}
