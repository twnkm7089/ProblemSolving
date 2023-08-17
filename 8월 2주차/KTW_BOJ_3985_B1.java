import java.util.Scanner;

public class KTW_BOJ_3985_B1 {
	/*알고리즘 설명
	 * 1. 케이크 상태 저장 배열과 입력 저장 배열 선언
	 * 2. 압력값 바탕으로 받을 거라는 기대값 도출, 최대값 찾기.
	 * 3. 입력값과 케이크 배열 토대로 실제 받는 경우의 수 탐색. 최대값 찾기.
	 * 4. 해당 내용 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt(); //케이크 길이
		int N = sc.nextInt(); //사람 수
		//공간 할당, 입력
		int[] cake = new int[L];
		int[][] info = new int[N][2];
		for(int i = 0; i < N; i++) {
			info[i][0] = sc.nextInt();
			info[i][1] = sc.nextInt();
		}
		
		int max_expect = 0;
		int max_expect_person = 0;
		int max = 0;
		int max_person = 0;
		//탐색 시작
		for(int i = 0; i < N; i++) {
			//기대값 계산
			int temp = info[i][1] - info[i][0] + 1;
			if(temp > max_expect) {
				max_expect = temp;
				max_expect_person = i+1;
			}
			//실제값 계산
			int count = 0;
			for(int j = info[i][0]-1; j <= info[i][1]-1; j++) {
				if(cake[j] == 0) {
					cake[j] = 1;
					count++;
				}
			}
			if(count > max) {
				max = count;
				max_person = i+1;
			}
		}
		//출력
		System.out.println(max_expect_person);
		System.out.println(max_person);
	}
}
