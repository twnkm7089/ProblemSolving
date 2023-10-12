import java.util.Scanner;
import java.io.FileInputStream;

class KTW_SWEA_2112_보호필름
{
	static int min;
	static int[] sel;
	static int D, W, K;
	static int[][] film;
	
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			
			film = new int[D][W];
			for(int i = 0; i < D; i++) {
				for(int j = 0; j < W; j++) {
					film[i][j] = sc.nextInt();
				}
			}
			
			//약품 투입 최소횟수의 최대값은 K다. K번 연속으로 같은 걸로 넣으면 된다.
			min = K;
			sel = new int[D];
			
			comb(0, 0);
			//결과 출력
			System.out.println("#" + test_case + " " + min);

		}//end of test case
	}//end of main method

	
	public static void comb(int sidx, int times) {
		//약물 투여 횟수 min회 이상이면 그냥 생략
		//백트리캥
		if(times >= min) {
			return;
		}
		//다 뽑으면 검사 후 갱신
		if(sidx == D) {
			if(check()) {
				min = Math.min(times, min);
			}
			return;
		}
		
		//아무 주입 없음
		sel[sidx] = 0;
		comb(sidx+1, times);
		
		//특성 A 주입
		sel[sidx] = 1;
		comb(sidx+1, times+1);
		
		//특성 B 주입
		sel[sidx] = 2;
		comb(sidx+1, times+1);
	}
	
	public static boolean check() {
		int[][] temp = new int[D][W];
		//sel에 따른 특성 반영한 film의 상태 temp 만들기
		for(int i = 0; i < D; i++) {
			if(sel[i] == 0) {
				for(int j = 0; j < W; j++) {
					temp[i][j] = film[i][j];
				}
			} else if(sel[i] == 1) {
				for(int j = 0; j < W; j++) {
					temp[i][j] = 0;
				}
			} else {
				for(int j = 0; j < W; j++) {
					temp[i][j] = 1;
				}
			}
		}
		
		//검사 개시
		for(int j = 0; j < W; j++) {
			int maxcnt = 1;
			int cnt = 1;
			for(int i = 1; i < D; i++) {
				if(temp[i][j] == temp[i-1][j]) {
					//전과 같으면 카운트 추가
					cnt++;
					
					if(i == D-1) {
						maxcnt = Math.max(cnt, maxcnt);
					}
				} else {
					//아니면 다시 세기
					maxcnt = Math.max(cnt, maxcnt);
					cnt = 1;
				}
			}
			//한 열의 특성이 불충족 -> fail
			if(maxcnt < K) {
				return false;
			}
		}
		return true;
	}
}