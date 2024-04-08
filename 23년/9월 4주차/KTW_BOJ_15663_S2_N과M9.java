import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 순열
 * 2. 근데 중복 체크 필요
 * */
public class KTW_BOJ_15663_S2_N과M9 {
	static int N, M;
	static int[] sel, nums;
	static List<int[]> answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//배열 초기화
		nums = new int[N];
		sel = new int[M];
		answer = new ArrayList<>();
		visited = new boolean[N];
		
		//input number
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		//정렬
		Arrays.sort(nums);
		//가동
		perm(0);
		//출력
		for(int[] arr : answer) {
			for(int i = 0; i < M; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
		}
		
		br.close();
	}
	//perm method
	public static void perm(int sidx) {
		if(sidx == M) {//다 뽑았으면
			boolean flag = true;
			//중복 탐색
			outer : for(int i = 0; i < answer.size(); i++) {
				int[] temp = answer.get(i);
				for(int j = 0; j < M; j++) {
					if(sel[j] != temp[j]) {
						continue outer;
					}
				}
				//중복 발생
				flag = false;
				break outer;
			}
			//중복 없었으면
			if(flag == true) {
				//배열 복제
				int[] temp = new int[M];
				for(int i = 0; i < M; i++) {
					temp[i] = sel[i];
				}
				//추가
				answer.add(temp);
			}
			return;
		}
		//뽑기
		for(int i = 0; i < N; i++) {
			if(visited[i] == false) {
				//숫자 뽑고
				sel[sidx] = nums[i];
				//방문 체크 후 재귀, 그 후 방문 체크 해제
				visited[i] = true;
				perm(sidx+1);
				visited[i] = false;
			}
		}
	}
	
	
}
