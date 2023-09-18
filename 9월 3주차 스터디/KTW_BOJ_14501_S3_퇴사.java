import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘
 * 1. 상향식 dP 썼으면 더 쉬웠을 듯.
 * 2. 하향식 사용함.
 * 3. i일이 종료일인 경우 탐색. 기본값은 어제 이후 돈 못벌었다! i-1일까지 번 최대금액
 * 4. 각 종료일 별로 탐색. 시작일이 j일인데 종료일이 i일인 경우를 발견했다!
 * 5. (j-1일까지 번 돈 + 이번에 번돈) vs 현재 dP[i] 중 최대값.
 * 6. 끝나고 dP[N-1] 출력
 * */
public class KTW_BOJ_14501_S3_퇴사 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		
		int[][] data = new int[N][3];
		//배열 입력
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int endDay = i + T - 1;
			data[i][0] = T;
			data[i][1] = P;
			data[i][2] = endDay;//종료일 계산 후 입력
		}
		
		
		int[] dP = new int[N];//dP[i]는 i일까지 벌 수 있는 최대 금액(0부터 시작)
		//index는 0부터
		for(int i = 0; i < N; i++) {//dP
			if(i != 0) dP[i] = dP[i-1];//default는 어제까지의 최대값
			for(int j = 0; j < N; j++) {//시작일
				if(j == 0) {//0일에 시작
					if(data[j][2] == i) {//종료일이 i일
						dP[i] = Math.max(dP[i], data[j][1]);//기존값 하고만 비교
					}
				} else {
					if(data[j][2] == i) {
						int moneytemp = dP[j-1] + data[j][1]; //j-1일까지 벌 수 있는 금액 + 이번에 번 금액
						dP[i] = Math.max(dP[i], moneytemp); //최대값 갱신
					}
				}
			}
		}
		//결과 출력
		System.out.println(dP[N-1]);
	}
}
