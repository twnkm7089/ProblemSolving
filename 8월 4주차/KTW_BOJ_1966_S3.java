package August_4;

import java.util.Scanner;

public class KTW_BOJ_1966_S3 {
	/*알고리즘 설명
	 * 1. 배열을 만들어 중요도와 빈도수 저장.
	 * 2. 최대 중요도 저장 변수 생성.
	 * 3. 탐색 시작, 인덱스 돌면서 최대 중요도와 일치하는 곳 나오면 해당 부분 출력(중요도 0 재조정) 및 빈도수 배열 감소
	 * 4. 해당 중요도 다 뽑았으면 다음으로 적은 빈도수로
	 * 5. 인덱스와 M 일치시 목표 인덱스 출력, 탐색 종료 및 count값 출력
	 * */
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for(int tc = 0; tc < t; tc++) {//테스트 케이스
			//입력
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] priority = new int[10];
			int max_p = 0;
			int[] queue = new int[N];
			for(int i = 0; i < N; i++) {
				queue[i] = sc.nextInt();//queue배열에 중요도 입력
				if(queue[i] > max_p) {//최대 중요도 찾기
					max_p = queue[i];
				}
				priority[queue[i]]++;//중요도 빈도수
			}
			
			int cnt = 0;
			int idx = 0;
			while(max_p > 0) {//탐색 시작
				if(priority[max_p] == 0) {//최대 중요도 조절
					max_p--;
					continue;
				}
				
				if(queue[idx] == max_p) {//최대 중요도 일치하는 곳 나오면
					cnt++;//카운트 증가(출력)
					queue[idx] = 0;//중요도 조절
					priority[max_p]--;//빈도수 감소
					if(idx == M) {//만약 목표 찾으면 탐색 중단
						break;
					}
				}
				//현재 탐색 위치 조절
				idx++;
				if(idx >= N) {
					idx-=N;
				}
			}
			
			System.out.println(cnt);//결과 출력
		}
		
		
	}//end of main method

}//end of class
