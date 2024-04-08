import java.util.Scanner;

public class KTW_BOJ_10610_S4 {
	/*알고리즘 설명
	 * 1. 30의 배수는 3의 배수인 동시에 10의 배수
	 * 2. 고로, 모든 자릿수의 합은 3의 배수인 동시에 0이 최소 하나 존재해야 함.
	 * 3. 각 자릿수를 int로 전환해 개수를 counting + 모든 자릿수의 합을 확인
	 * 4. 만약 0이 없거나 모든 자리수의 합이 3의 배수가 아니면 -1 출력
	 * 5. 아닐 경우 30의 배수 가능, 내림차순으로 counting한 결과 출력
	 * */
	public static void main(String[] args) {
		//input
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int sum = 0;//모든 자리수의 합
		int[] num = new int[10];//개수 세기
		for(int i = 0; i < s.length(); i++) {//탐색
			int temp = s.charAt(i) - '0';//각 자리수 정수 변환
			sum += temp;//합하기
			num[temp]++;//카운팅
		}
		
		if(sum % 3 != 0 || num[0] == 0) {//모든 자리수의 합이 3의 배수가 아니거나 0이 없음
			System.out.println(-1);//30의 배수 불가
		} else {//가능이면
			for(int i = 9; i >= 0; i--) {//최대를 만들어야 하니 각 숫자를 큰 것부터
				for(int j = 0; j <num[i]; j++) {//카운팅한 개수만큼 출력
					System.out.print(i+"");
				}
			}
		}
	}
}
