import java.util.Scanner;

public class KTW_BOJ_2798_B2 {
	public static void main(String[] args) {
		/*알고리즘 설명
		 * 1. 브루트포스로 3중 for문 돌려 모조리 탐색.
		 * 2. 합이 M을 넘기면 안되니 넘기면 continue로 생략
		 * 3. 아니면 기존 있던 최소 차이(diff)와 비교해 M과의 차이가 작으면 갱신.
		 * */
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		int[] cards = new int[N];
		for(int i = 0; i < N; i++) {
			cards[i] = sc.nextInt();
		}
		
		int diff = Integer.MAX_VALUE; //최소 차이(누적)
		int sum = 0; //합
		int temp = 0; //임시(현재 탐색 값의 M과의 차)
		int answer = 0; //정답
		for(int i = 0; i < N-2; i++) { //모든 경우 계산
			for(int j = i + 1; j < N-1; j++) {
				for(int k = j + 1; k < N; k++) {
					sum = cards[i] + cards[j] + cards[k]; //더한 값

					if(sum > M) { //크면 생략
						continue;
					} else { //작으면 계산 시작
						temp = M - sum;
					}
					
					if(temp < diff) { //더 작은 차이면 갱신
						diff = temp;
						answer = sum;
					}
				}
			}
		} // card searching
		
		System.out.println(answer);
	}
}
