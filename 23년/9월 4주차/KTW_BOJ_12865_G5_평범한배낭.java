import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. dP문제
 * 2. dP는 해당 무게에서 얻을 수 있는 최대 가치 기준
 * 3. 각 무게별로 뒤에서부터 계산, K - weight(무게 초과 안나는 장소)부터 찾아보며 value값이 있는 경우 비교
 * 4. 이건 코드로 봐야 이해가 됨.
 * */
public class KTW_BOJ_12865_G5_평범한배낭 {
	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] list = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list[i][0] = Integer.parseInt(st.nextToken());
			list[i][1] = Integer.parseInt(st.nextToken());
		}


		int[] dP = new int[K+1];//dP
		for(int i = 0; i < N; i++) {
			//무게, 가치값 추출
			int weight = list[i][0];
			int value = list[i][1];
			
			//뒤에서부터 탐색
			//지금까지 나온 모든 경우의 수 중 이번 회차를 더했을 때 무게 범위 내에 들어가면
			//비교 갱신
			for(int j = K - weight; j >= 0; j--) {
				if(j == 0 || dP[j] != 0) {//이것만 추가 또는 이미 있는 조합에 추가
					dP[j + weight] = Math.max(dP[j] + value, dP[j + weight]);
				}
			}
			/*예시
			 * 4 15
			 * 6 13
			 * 4 8
			 * 3 6
			 * 5 12
			 * 의 경우를 보자
			 * 1. 6 13 의 경우 dP[6] = 13만이 가능하다.
			 * 2. 4 8 이 들어왔다. 뒤에서부터 탐색한다. dP[6] = 8을 발견했다. dP[6+4] = 13 + 8로 갱신하자.
			 * 3. 그리고 dP[4] = 8로도 갱신하자.
			 * 4. 3 6 이 들어왔다. 뒤에서부터 탐색하자. dP[6+4+3] = 13 + 8 + 6의 조합이 가능하다.
			 * 5. dP[6 + 3], dP[4 + 3], dP[3]도 가능하다.
			 * 6. 이런식으로 탐색하며 경우의 수 추가 가능하다. 다만, 기존 값과의 충돌 막기 위해 최대값 확인해 갱신한다.
			 * 7. 범위를 넘어가는 조합은 반복문 초기 조건으로 인해 애시당초 불가능하다.
			 * */
		}
		
		Arrays.sort(dP);//계산 완료, 최대 가치 구하기
		System.out.println(dP[K]);
		
		br.close();
	}//end of main method

}//end of class
