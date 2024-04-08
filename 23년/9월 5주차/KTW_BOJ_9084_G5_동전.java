import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. dP이용
 * 2. 배낭문제와 흡사, 아래 코드 참조.
 * */
public class KTW_BOJ_9084_G5_동전 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int T = Integer.parseInt(br.readLine());
		//start of test case
		for(int tc = 1; tc <= T; tc++) {
			//동전 종류 입력
			int N = Integer.parseInt(br.readLine());
			int[] money = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				money[i] = Integer.parseInt(st.nextToken());
			}
			
			//목표 금액 입력
			int M = Integer.parseInt(br.readLine());
			
			//dP배열 생성
			int[][] dP = new int[N+1][M+1];
			//i : 현재 반영된 동전의 종류 수
			//j : 현재 계산하고 있는 금액
			for(int i = 1; i <= N; i++) {
				//이 i의 반복문을 통해 반영하는 동전의 종류 개수를 추가해 나갈 것이다.
				//예를 들어 2원, 3원짜리 동전이 있다고 가정하자.
				//그럼 차례차례 2원짜리 동전으로 만들 수 있는 금액의 종류 수, 2원과 3원짜리 동전으로 만들 수 있는 종류 수가 나온다.
				for(int j = 1; j <= M; j++) {
					//이제부터 dP[i][j]를 찾아보자
					
					
					//나누어 떨어지면 j원을 money[i]로만 만든 것.
					//예를들어 money[i]가 3원짜리 동전이라 치자.
					//j가 9원이라면, 9원은 3원짜리 동전 3개로 만들 수 있으니 그 만들 수 있는 경우의 수가 1개 추가된다.
					if(j % money[i] == 0) {
						dP[i][j] ++;
					}
					
					//j원을 낼 경우, money[i]로 낼 수 있는 최대 동전 개수
					//예를 들어 13원을 3원짜리 동전으로 내려면 최대 4개까지 가능하다.
					int moneyNum = j / money[i];
					
					for(int k = 0; k <= moneyNum; k++) {
						//기존 동전으로 j-k*money[i]원 만들 수 있던 가짓 수 + money[i]원 동전 k개 사용한 경우의 수 추가
						//money[i] = 3이라 하자.
						//예를 들어 13원을 만들 수 있는 경우의 수는 우선 기존 13원을 만드는데 사용되던 경우의 수가 있다.
						//그리고 dP[i-1][10]의 상황에서 3원짜리 동전 1개를 쓸 수 있다.
						//그리고 dP[i-1][7]의 상황에서 3원짜리 동전 2개를 쓸 수 있다.
						//즉, dP[i-1][13]+dP[i-1][10]+dP[i-1][7]+...하면 된다.
						//현재 dP[i-1][0]이 0이므로 위의 j%money[i]로 추가하는 문이 있다.
						dP[i][j] += dP[i-1][j-k*money[i]];
					}
				}
			}
			//결과 출력
			System.out.println(dP[N][M]);
		}//end of test case
	}//end of main method
}//end of class
