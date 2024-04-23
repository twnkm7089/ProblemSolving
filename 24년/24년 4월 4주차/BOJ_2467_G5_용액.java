import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2467_G5_용액 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		//two pointer
		int left = 0;
		int right = N-1;
		
		int minLeft = 0;
		int minRight = N-1;
		int min = Math.abs(nums[left]+nums[right]);
		while(left < right) {
			int sum = nums[left] + nums[right];
			
			if(min > Math.abs(sum)) {
				minLeft = left;
				minRight = right;
				min = Math.abs(nums[left]+nums[right]);
			}
			
			if(sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		
		System.out.println(nums[minLeft] + " " + nums[minRight]);
		//두 용액 문제와 차이가???(정렬 해준 상태인 것 정도?)
		br.close();
	}
}
