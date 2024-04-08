package Feb4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1182_S2_부분수열의_합 {
	static int[] nums;
	static int N, S;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		answer = 0;
		
		//완탐 + DFS
		nums = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		search(0, 0);
		
		//빈 배열의 경우 제외
		//S=0이었으면 빈 배열도 인식된다.
		if(S == 0) {
			answer--;
		}
		
		//결과 출력
		System.out.println(answer);
	}
	
	//cumul : 누적값, idx : 현재 탐색 위치
	public static void search(int cumul, int idx) {
		if(idx == N) {
			//일치시 정답
			if(S == cumul) answer++;
			//탐색 완료니 돌아가기
			return;
		}
		
		//이번 탐색값을 추가하느냐 아니냐
		search(cumul + nums[idx], idx+1);
		search(cumul, idx + 1);
	}
	
	
}
