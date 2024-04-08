import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_BOJ_15657_S3_N과M8 {
	static int N, M;
	static int[] nums, sel;
	/*알고리즘 설명
	 * 1. 중복 순열 제작
	 * 2. 근데 비내림차순
	 * 3. 코드 참고
	 * */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		//end of input
		
		//비내림차순이니 정렬부터
		Arrays.sort(nums);
		sel = new int[M];
		//함수 가동
		perm(0, 0);
		
		
		br.close();
	}
	
	//perm method
	public static void perm(int idx, int sidx) {
		//다 뽑으면 출력 후 리턴
		if(sidx == M) {
			for(int i = 0; i < M; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}
		
		//뽑기, idx부터(본인은 중복, 동시에 비내림차순_
		for(int i = idx; i < N; i++) {
			sel[sidx] = nums[i];
			//재귀로 다음거 뽑기
			perm(i, sidx+1);
		}
	}
}
