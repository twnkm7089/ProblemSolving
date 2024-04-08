import java.util.Scanner;
/*알고리즘 설명
 * 1. 재귀를 이용해 구현
 * 2. W, B를 따로 구함.
 * 3. 탐색 범위를 넣고 돌려서 탐색 범위 내에 다른 원소가 있으면 왼쪽위, 오른쪽위, 왼쪽아래, 오른쪽아래에 탐색 재 실시.
 * 4. 결과 출력
 * */
public class KTW_BOJ_2630_S2 {
	public static int[][] arr = new int[100][100];
	public static int N = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//input
		N = sc.nextInt();
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		//search
		int cntW = countW(0, N, 0, N);
		int cntB = countB(0, N, 0, N);
		//output
		System.out.println(cntW);
		System.out.println(cntB);
		sc.close();
	}
	
	//search algorithm
	public static int countW(int st, int ed, int st2, int ed2) {
		int cnt = 0;
		boolean flag = true;
		
		//1 있는지 탐색
		outer : for(int i = st; i < ed; i++) {
			for(int j = st2; j < ed2; j++) {
				if(arr[i][j] == 1) {
					flag = false;
					break outer;
				}
			}
		}
		
		if(flag) {//모두 1인 구역이면 1 반환
			cnt++;
		} else {//아니었으면
			if(ed - st == 1 && ed2 - st2 == 1) {//최소 범위였는지 파악
				return 0;
			}
			
			//재귀적으로 네 부분으로 나누어 탐색
			//만약 함수 내부에서도 충족 못시키면 다시분할
			//나온 결과들을 모두 합치면 결과 출력
			cnt = countW(st, (st + ed)/2, st2, (st2 + ed2)/2) + countW(st, (st + ed)/2, (st2 + ed2)/2, ed2) + countW((st + ed)/2, ed, st2, (st2 + ed2)/2) + countW((st + ed)/2, ed, (st2 + ed2)/2, ed2);
		}
		
		return cnt;//반환
	}
	//위와 동일한 매커니즘으로 만든 Blue개수 세기. 여기의 경우 0(흰색)이 구역에 있는지로 판별
	public static int countB(int st, int ed, int st2, int ed2) {
		int cnt2 = 0;
		boolean flag = true;
		
		//0 있는지 탐색
		outer : for(int i = st; i < ed; i++) {
			for(int j = st2; j < ed2; j++) {
				if(arr[i][j] == 0) {
					flag = false;
					break outer;
				}
			}
		}
		
		if(flag) {
			cnt2++;
		} else {
			if(ed - st == 1 && ed2 - st2 == 1) {
				return 0;
			}
			
			
			cnt2 = countB(st, (st + ed)/2, st2, (st2 + ed2)/2) + countB(st, (st + ed)/2, (st2 + ed2)/2, ed2) + countB((st + ed)/2, ed, st2, (st2 + ed2)/2) + countB((st + ed)/2, ed, (st2 + ed2)/2, ed2);
		}
		
		return cnt2;
	}
}
