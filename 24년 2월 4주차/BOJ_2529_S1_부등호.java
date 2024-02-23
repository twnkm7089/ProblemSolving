package Feb4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2529_S1_부등호 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//부등호 저장
		char[] compare = new char[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			compare[i] = st.nextToken().charAt(0);
		}
		
		
		//최대 구하기
		int[] max = new int[N + 1];
		Arrays.fill(max, -1);
		int nowNum = 9;
		int lastIdx = -1;
		//최대값은 > 를 만날 때마다 업데이트
		for(int i = 0; i < N; i++) {
			if(compare[i] == '>') {
				for(int j = i; j > lastIdx; j--) {
					max[j] = nowNum--;
				}
				lastIdx = i;
			}
		}
		
		//남은거 채우기
		for(int i = N; i >= 0; i--) {
			if(max[i] != -1) break;
			max[i] = nowNum--;
		}
		
		
		//최소 구하기
		int[] min = new int[N + 1];
		Arrays.fill(min, -1);
		nowNum = 0;
		lastIdx = -1;
		//최소값은 <를 만날 때마다 업데이트
		for(int i = 0; i < N; i++) {
			if(compare[i] == '<') {
				for(int j = i; j > lastIdx; j--) {
					min[j] = nowNum++;
				}
				lastIdx = i;
			}
		}
		//마지막 빈 부분 청산
		for(int i = N; i >= 0; i--) {
			if(min[i] != -1) break;
			min[i] = nowNum++;
		}
		
		
		String maxString = "";
		String minString = "";
		
		for(int i = 0; i <= N; i++) {
			maxString += max[i];
			minString += min[i];
		}
		
		//결과 출력
		System.out.println(maxString);
		System.out.println(minString);

	}

}
