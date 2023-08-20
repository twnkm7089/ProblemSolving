import java.util.Scanner;


public class KTW_BOJ_1654_S2 {
	/*알고리즘 설명
	 * 1. 이진 탐색 이용
	 * 2. max를 끝점, 1을 시작점으로 삼고 중간부터 이진탐색
	 * 3. 조각 개수가 목표보다 적으면 너무 크게 자른 것이니 작게 잘라 탐색.
	 * 4. 조각 개수가 목표 이상이면 성립이니 정답으로 갱신 + 더 큰거 있는지 확인.
	 * 5. 자료형 주의! (K=10,000 N=1,000,000이 최대라 최대 100억개의 조각 가능, long)
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//입력
		int K = sc.nextInt();
		int N = sc.nextInt();
		//막대 배열 입력, 최대값 찾기
		int max = -1;
		int[] arr = new int[K];
		for(int i = 0; i < K; i++) {
			arr[i] = sc.nextInt();
			if(max < arr[i]) {
				max = arr[i];
			}
		}
		//탐색 범위 설정
		long st = 1;
		long ed = max;
		long answer = 0;//정답
		while(st <= ed) {//이진 탐색(모든 계산 long으로)
			long mid = (st + ed)/(long)2;
			long cnt = 0;
			for(int i = 0; i < K; i++) {//조각 개수 세기
				cnt += (long)arr[i]/mid;
				if(cnt > (long)N)//조각 개수 많아지면 그 이상 탐색 불필요
					break;
			}
			
			if(cnt >= (long)N) {//이상이면 문제 없음
				if(answer < mid) {//갱신
					answer = mid;
				}
				st = mid + 1;//더 큰 경우 탐색
			} else {//조각 개수가 목표 미만이면 더 작게 잘라 탐색 필요
				ed = mid - 1;//탐색
			}
		}
		System.out.println(answer);//정답 출력
		
	}//end of main

}//end of class

