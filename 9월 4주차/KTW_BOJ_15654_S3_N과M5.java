import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 순열 문제
 * 2. 순열 만들어 풀었음
 * 3. 오름차순 표시니 오름차순 정렬함.
 * */
public class KTW_BOJ_15654_S3_N과M5 {
	public static int[] nums;
	public static int[] sel;
	public static int N, M;
	public static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//배열 초기화
		nums = new int[N];
		visited = new boolean[N];
		//nums input
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		//sort for 오름차순
		Arrays.sort(nums);
		//sel배열 초기화
		sel = new int[M];
		//함수 실행
		perm(0);
		br.close();
	}
	//perm method, 인자는 sel에 들어갈 인덱스 위치
	public static void perm(int sidx) {
		//다 뽑았으면
		if(sidx == M) {
			//출력 후 리턴
			for(int i = 0; i < M; i++) {
				System.out.print(sel[i] + " ");
			}
			System.out.println();
			return;
		}
		
		//반복문
		for(int i = 0; i < N; i++) {
			//미방문 노드면
			if(!visited[i]) {
				//선택
				sel[sidx] = nums[i];
				
				visited[i] = true;//방문표시
				perm(sidx + 1);//재귀(다음 원소 뽑기)
				visited[i] = false;//미방문 표시
			}
		}
	}
}
