package August_4;

import java.util.Arrays;
import java.util.Scanner;
/*알고리즘 설명
 * 1. 수리 가능한 구역은 L을 항상 좌우 0.5만큼 여유를 주어야 하니 테이프를 붙이기 시작한 시작점 ~ +(L-1)이랑 같다.
 * 2. 구멍이 난 곳을 초기화한다.
 * 3. 구멍이 난 곳을 발견하면 시작점으로 삼고, 테이프 수 추가하고, 해당 위치로부터 L-1 떨어진 지점까지는 보수가 자동으로 된다고 친다.
 * 4. 정답 출력
 * */
public class KTW_BOJ_1449_S3 {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int L = sc.nextInt();
		int[] arr = new int[N];
		for(int i = 0; i < N; i++) {//구멍난 위치 입력
			arr[i] = sc.nextInt();
		}
		
		Arrays.sort(arr);//오름차순
		
		L-=1;//계산 편의 위해 L을 1 뺌.(이제 L은 L-1)
		int idx = 0;//현재 구멍
		int cnt = 0;//테이프 개수
		int start = -1;//시작점 초기화
		while(idx < N) {//탐색
			if(start == -1) {//첫 테이프면
				cnt++;//테이프 붙이고
				start = arr[idx];//시작점 설정
				idx++;//다음 구멍으로
			} else if(arr[idx] <= start+L) {//구멍이 테이프로 커버 가능
				idx++;//여기도 보수됨
			} else {//커버 안됨
				cnt++;//새 테이프
				start = arr[idx];//시작점 재설정
				idx++;//다음 구멍으로
			}
		}
		System.out.println(cnt);//결과 출력
		
		
	}//end of main method

}//end of class
