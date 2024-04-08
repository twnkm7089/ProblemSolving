package August_4;

import java.util.Scanner;
/*알고리즘 설명
 * 1. 뒷자리에 나오는 0의 개수는 10이 얼마나 많이 곱해졌냐로 결정. 10은 2*5
 * 2. 즉, 팩토리얼에서 2와 5가 짝을 이루어 곱해지는 횟수가 곧 0의 개수, 2의 배수가 5의 배수에 비해 많으니 5가 몇 번 곱해지냐가 중요.
 * 3. 여기서 25의 배수는 5가 2번, 125는 3번 곱해지고, 이 둘은 5의 배수이기도 함.
 * 4. 고로, 5로 나눈 몫으로 1번 카운팅 + 25로 나눈 몫으로 25의 배수 추가 카운팅 + 125로 나눈 몫으로 125의 경우 추가 카운팅
 * 5. 이렇게 답 구함
 * */
public class KTW_BOJ_1676_S5 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println((N/5) + (N/25) + (N/125));//해당 로직
		
	}//end of main method
}//end of class
