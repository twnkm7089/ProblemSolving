import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_G5_두용액_다시풀것 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//two pointer 사용
		//다시 풀어볼 것(너무 복잡하게 생각함)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(nums);
		
		//초기화, 끝과 끝
		int idx1 = 0;
		int idx2 = N-1;
		int minAbs = Math.abs(nums[idx1] + nums[idx2]);
		int minIdx1 = 0;
		int minIdx2 = N-1;

		while(idx1 < idx2) {
			//합 구하기
			int sum = nums[idx1] + nums[idx2];
			//절대값 갱신
			if(minAbs > Math.abs(sum)) {
				minAbs = Math.abs(sum);
				minIdx1 = idx1;
				minIdx2 = idx2;
			}
			
			if(sum < 0) {
				//음수면 왼쪽을 한칸 오른쪽으로
				idx1++;
			} else {
				//양수면 반대로 오른쪽을 한칸 왼쪽으로
				idx2--;
			}
		}
		
		
		//출력
		System.out.println(nums[minIdx1] + " " + nums[minIdx2]);
		
	}
}
