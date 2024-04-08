package August_4;

import java.util.Scanner;

public class KTW_BOJ_11866_S5 {
	/*알고리즘 설명
	 * 1. while문을 이용해 제거한 사람 수가 N이 될 때까지 돌림
	 * 2. 배열을 원형으로 돌면서 제거되지 않은 인원이 나오면 temp_cnt 증가
	 * 3. 이 temp_cnt가 K가 되면 K번째 사람. 해당 배열에 있던 사람 번호를 기록하고 해당 인덱스 내용을 0으로 초기화 + temp_cnt 원래대로 + cnt증가해 제거한 수 세기
	 * 4. 그리고 인덱스 옮기기 반복
	 * 5. 끝나면 결과 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = i+1;
		}
		
		//정답 기록
		int[] answer = new int[N];
		int ans_idx = 0;//정답 배열 인덱스
		
		int idx = 0;//조사하는 인덱스
		int cnt = 0;//제거된 수
		int temp_cnt = 0;//몇번째 수
		while(cnt < N) {//탐색 시작
			if(arr[idx] != 0) {//제거 안된 사람이면 수 세기
				temp_cnt++;
			}
			
			if(temp_cnt == K) {//K번째 사람이면
				answer[ans_idx++] = arr[idx];//정답 배열 기록
				arr[idx] = 0;//해당 장소 제거
				temp_cnt = 0;//K번째 사람 세기 초기화
				cnt++;//제거한 사람 수 증가
			}
			
			idx = (idx+1)%N;//다음 인덱스로(원형)
		}
		//출력
		System.out.print("<");
		for(int i = 0; i < N; i++) {
			if(i == N-1) {
				System.out.print(answer[i]);
			} else {
				System.out.print(answer[i]+", ");
			}
		}
		System.out.println(">");
	}
}
