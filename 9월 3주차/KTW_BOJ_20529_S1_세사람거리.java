import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. mbti는 16종, 고로, 비둘기집 원리에 의거, 33이상의 사람이 있으면 같은 mbti가진 3명의 조합이 최소 1개 나온다.
 * 2. 고로, N이 33 이상이면 답은 0.
 * 3. 나머지 경우는 브루트 포스로 해결, N개 중 3개를 뽑은 조합을 생성해 거리 구하고 최소값 갱신.
 * 4. 출력
 * */
public class KTW_BOJ_20529_S1_세사람거리 {
	public static String[] mbti;
	public static int min = 0;
	public static String[] sel = new String[3];
	public static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {//start test case
			//인원수 입력
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			mbti = new String[N];
			//mbti 행렬 입력
			for(int i = 0; i < N; i++) {
				mbti[i] = st.nextToken();
			}
			//최소값 초기화
			min = Integer.MAX_VALUE;
			if(N >= 33) {//비둘기집 원리에 의해 33이상은 0
				min = 0;
			} else {//그 외는 조합 만들어 판단
				comb(0, 0);
			}
			//output
			System.out.println(min);
		}//end of test case
	}
	//조합 함수
	public static void comb(int idx, int sidx) {
		if(sidx == 3) {//다 뽑으면
			int temp = 0;
			for(int i = 0; i < 4; i++) {//거리 구하기
				if(sel[0].charAt(i)  != sel[1].charAt(i)) {
					temp++;
				}
				if(sel[1].charAt(i)  != sel[2].charAt(i)) {
					temp++;
				}
				if(sel[0].charAt(i)  != sel[2].charAt(i)) {
					temp++;
				}
			}
			if(min > temp) {//최소값 갱신
				min = temp;
			}
			return;//반환
		}
		//조합 생성
		for(int i = idx; i < N-2+sidx; i++) {
			sel[sidx] = mbti[i];
			comb(i+1, sidx+1);
		}
	}
}
