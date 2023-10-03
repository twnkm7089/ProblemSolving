import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 2차원 배열로 만든 DP
 * 2. dP[i][j]는 첫 문자열의 i번째 문자와 두번째 문자열의 j번째 문자까지 비교했을 때의 최대 문자열의 길이다.
 * 3. dP배열은 두번째 문자열에 대해 첫 문자열의 문자를 하나씩 추가하며 최대 문자열의 길이를 구하는 방식으로 구한다.
 * 4. i번째 문자와 j번째 문자가 일치하지 않는 경우, dP[i-1][j](지난번 반복문 때 구한 이곳까지의 최대 길이) vs dP[i][j-1](이번 반복문 때 누적해서 구한 아까까지의 공통 문자열 최대 길이)
 * 5. 일치하는 경우, 여기에 더해 (dP[i-1][j-1] + 1)을 비교한다. (지난번 반복문 때 구했던 j-1번째 문자까지의 최대 길이 + 1(이번 문자로 추가))
 * 6. 계속 갱신하며 dP[N][M]을 구한다.
 * */
public class KTW_BOJ_9251_G5_LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		String str1 = br.readLine();
		String str2 = br.readLine();
		//length
		int N = str1.length();
		int M = str2.length();
		//dP
		int[][] dP = new int[N+1][M+1];
		for(int i = 1; i <= N; i++) {
			//str1의 문자 길이 조금씩 늘리며
			char now = str1.charAt(i-1);
			for(int j = 1; j <= M; j++) {
				//str2 전체에 대해 비교
				char comp = str2.charAt(j-1);
				if(now == comp) {//문자 일치 시
					dP[i][j] = Math.max(dP[i-1][j-1] + 1, Math.max(dP[i-1][j], dP[i][j-1]));
				} else {//문자 불일치 시
					dP[i][j] = Math.max(dP[i-1][j], dP[i][j-1]);
				}
			}
		}
		//결과 출력
		System.out.println(dP[N][M]);
	}//end of main method
}//end of class
