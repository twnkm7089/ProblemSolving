import java.util.Scanner;

public class KTW_BOJ_15829_B2 {
/*알고리즘 설명
 * 1. 문제의 핵심은 엄청난 크기의 숫자를 다루는 방법.
 * 2. 31^50은 약 3.69e+74, 정수 자료형으로 표현 불가능한 수준.
 * 3. ((a+b)%M = a%M + b%M) // 두 수의 합의 M에 대한 나머지는 각 수의 M에 대한 나머지의 합과 같다.
 * 4. ((ab)%M = a%M * b%M) // 두 수의 곱의 M에 대한 나머지는 각 수의 M에 대한 나머지의 곱과 같다.
 * 5. 이를 이용해 문제를 풀었다.
 * 6. 거듭제곱도 결국엔 곱, 거듭제곱을 수행하며 1234567891보다 커지면 그 나머지로 치환하는 것을 반복했다.(4번 식)
 * 7. 그 후, 결과값을 누적해 더한 후, 1234567891의 나머지로 치환했다.(3번 식)
 * 8. 이를 통해 구했다.
 * */
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//입력
		int N = sc.nextInt();
		String str = sc.next();
		long sum = 0;//결과 누적
		for(int i = 0; i < N; i++) {//문자별 연산
			long temp = (long)(str.charAt(i)-'a'+1);//번호 치환
			for(int j = 0; j < i; j++) {//거듭제곱
				temp *= 31;
				if(temp >= 1234567891)//수보다 커지면 나머지 연산 수행
					temp %= 1234567891;
			}
			//더하고 나머지 연산 수행
			sum += temp;
			sum %= 1234567891;
		}
        System.out.println(sum);//결과 출력 
	}
	
	
}

