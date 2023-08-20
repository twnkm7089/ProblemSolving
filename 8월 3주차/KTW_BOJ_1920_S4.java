import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KTW_BOJ_1920_S4 {
	/*알고리즘 설명
	 * 1. 최적화가 중요
	 * 2. 이진탐색을 이용해 문제 해결. A 배열을 sort한 후 2진탐색으로 O(log(N))의 탐색 알고리즘 구현
	 * */
	public static void main(String[] args) throws Exception {
		//버퍼드, 스트링토크나이저
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//A 크기 입력
		int[] A = new int[N];
		//A 배열 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);//sort
		//정답 저장 배열 입력
		int M = Integer.parseInt(br.readLine());
		int[] answer = new int[M];
		//이진 탐색
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int temp = Integer.parseInt(st.nextToken());
			int flag = 0;
			int start = 0;
			int end = N-1;
			while(start <= end) {//이진 탐색 구현
				int idx = (start + end) / 2;
				if(A[idx] == temp) {
					flag = 1;
					break;
				} else if(A[idx] < temp) {
					start = idx + 1;
				} else {
					end = idx - 1;
				}
			}
			answer[i] = flag;
		}
		//정답 출력
		for(int i = 0; i < M; i++) {
			System.out.println(answer[i]);
		}
	
	}
}

