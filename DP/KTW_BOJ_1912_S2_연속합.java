import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. nums배열에 누적합 저장
 * 2. 현재 까지 나온 누적합의 기준값이 될 0 이하의 최소값을 min으로 둠.
 * 3. 아래 코드 참조
 * */
public class KTW_BOJ_1912_S2_연속합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N+1];
		
		//누적합을 저장
		//i ~ j번째 합은 nums[j] - nums[i-1]로 표현 가능
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			sum += Integer.parseInt(st.nextToken());
			nums[i] = sum;
		}
		
		//dP
		int[] dP = new int[N+1];
		//min값(이 값이랑 빼줄 거임)
		int min = 0;
		//dP[1]저장
		dP[1] = nums[1];
		//음수일 경우 min을 음수로
		if(nums[1] < 0) min = nums[1];
		//dP
		for(int i = 2; i <= N; i++) {
			//dP값 갱신(이전 최대 vs nums[i]-min)
			dP[i] = Math.max(dP[i-1], nums[i] - min);
			//min값 갱신(필요하면)
			if(nums[i] < min) {
				min = nums[i];
			}
			
		}
		//결과 출력
		System.out.println(dP[N]);
	}//end of main method
}//end of class
