package codetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class KTW_BOJ_11659_S3 {
	/*누적합 이용 문제
	 * 1. a, b(문제에서는 i,j)가 1부터 시작하니 -1로 보정
	 * 2. 값을 입력 받은 후 처음부터 누적한 결과를 저장.
	 * 3. a==1이면 arr[b-1]가 곧 누적합이니 그거 출력.
	 * 4. a > 1이면 arr[b-1] - arr[a-2]가 우리가 원하던 답
	 * */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		//array 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//array 누적합 구하기.
		for(int i = 1; i < N; i++) {
			arr[i] += arr[i-1];
		}
		//시작 끝 입력 받으며 출력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int sum = 0;
			if(a == 1) {//처음부터 누적 합
				sum = arr[b-1];
			} else {//그 외
				sum = arr[b-1] - arr[a-2];
			}
			//결과 스트링빌더로 변환
			sb.append(String.valueOf(sum));
			sb.append("\n");
		}
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}//end of main method
}//end of class
