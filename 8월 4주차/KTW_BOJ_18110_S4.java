package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class KTW_BOJ_18110_S4 {
	/*알고리즘 설명
	 * 1. 로직은 쉬웠음, a = N*0.15를 구한 후, 모든 수를 더한 값에서 정렬 결과의 맨 앞 a개와 맨 뒤 a개를 뺐음. 
	 * 2. 그 후, 해당 값을 N-2a로 나눔.
	 * 3. 버퍼드, selection algorithm을 썼다가 발생한 문제, 부동소수점 문제로 고생해서 간신히 해결.
	 * */
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		if(N == 0) {//의견 없으면 0 출력
			System.out.println(0);
		} else {
			int[] arr = new int[N];//의견 개수 등록
			int sum = 0;//합
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(br.readLine());//입력 받고
				sum += arr[i];//합계 구하기
			}
	
			int a = (int)((N * 0.15) + 0.5);//반올림

			Arrays.sort(arr);//sorting

			for(int i = 0; i < a; i++) {//맨앞, 맨뒤 a개 빼기
				sum -= arr[i];
				sum -= arr[N-1-i];
			}
			System.out.println((int)(((double)sum / (N-2*a))+0.5));//평균 출력	
		}
		
	}//end of main method
}//end of class
