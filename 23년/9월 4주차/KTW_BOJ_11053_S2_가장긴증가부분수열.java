import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. dP 이용
 * 2. dP[a]에는 arr[a]까지 나오는 증가하는 부분 수열의 최대 길이 저장
 * 3. dP[i]를 탐색해보자. 지금까지의 부분 중 arr의 크기가 현재보다 작으면서 dP값이 제일 큰 장소를 찾자.
 * 4. 그 값에 1을 더하면 dP[i]
 * 5. 최대값 출력
 * */
public class KTW_BOJ_11053_S2_가장긴증가부분수열 {
	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//탐색 시작
		int[] dP = new int[N];
		
		for(int i = 0; i < N; i++) {
			int maxdP = 0;//dP최대값 초기화
			for(int j = 0; j < i; j++) {//지금까지의 수열 중
				if(arr[j] < arr[i] && dP[j] > maxdP) {//크기가 작으면서 dP값이 제일 큰 것 발견
					maxdP = dP[j];//갱신
				}
			}
			dP[i] = maxdP + 1;//해당 값에 1 더해주면 최대 길이
		}
		//최대값 구해 출력
		Arrays.sort(dP);
		System.out.println(dP[N-1]);
		
		br.close();
	}//end of main method

}//end of class
