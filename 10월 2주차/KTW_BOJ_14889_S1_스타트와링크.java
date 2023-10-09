import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 조합 이용
 * 2. 팀 조합 뽑아서 실행
 * */
public class KTW_BOJ_14889_S1_스타트와링크 {
	static int N;
	static int[][] map;
	static int min;
	static boolean[] sel;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		//input of first line
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		//map input
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//인자 초기화, 조합
		min = Integer.MAX_VALUE;
		sel = new boolean[N];
		comb(0, 0);
		//결과 출력
		System.out.println(min);
	}//end of main method
	
	//조합
	public static void comb(int idx, int sidx) {
		if(sidx == N/2) {//다 뽑았으면(N개중 N/2 뽑기)
			int[] teamA = new int[N/2];
			int[] teamB = new int[N/2];
			int idxA = 0, idxB = 0;
			//sel[i]가 true면 A팀, false면 B팀
			for(int i = 0; i < N; i++) {
				if(sel[i]) {
					teamA[idxA++] = i;
				} else {
					teamB[idxB++] = i;
				}
			}
			
			//팀나누기 완료
			int powerA = 0;
			int powerB = 0;
			//능력치 측정
			for(int i = 0; i < N/2; i++) {
				for(int j = 0; j < N/2; j++) {
					powerA += map[teamA[i]][teamA[j]];
					powerB += map[teamB[i]][teamB[j]];
				}
			}
			//갱신
			min = Math.min(min, Math.abs(powerA-powerB));
			return;
		}
		
		//sel이라는 boolean배열 만든 후, true, false에 따라 팀을 나누는 방식
		for(int i = idx; i <= N/2+sidx; i++) {
			sel[i] = true;
			comb(i+1, sidx+1);
			sel[i] = false;
		}
	}
	
}//end of class
