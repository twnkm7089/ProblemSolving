import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 완탐
 * */
public class KTW_BOJ_14888_S1_연산자끼워넣기 {
	static int N;
	static int[] num;
	static int[] op;
	static int min;
	static int max;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//숫자 수 입력
		N = Integer.parseInt(br.readLine());
		//숫자 배열 입력
		num = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		//연산자 개수 입력
		op = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		//최소, 최대값 초기화
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		//조합 짜기
		operation(1, num[0]);
		System.out.println(max);
		System.out.println(min);
	}
	//조합, 연산
	//idx는 현재 연산을 적용할 인덱스, temp는 누적 결과
	public static void operation(int idx, int temp) {
		//다 뽑았으면 값 갱신
		if(idx == N) {
			max = Math.max(max, temp);
			min = Math.min(temp, min);
			return;
		}
		//+기호가 남아 있으면 뽑아서 더해주고 다음 index로 재귀
		//나머지 기호도 똑같이
		if(op[0] > 0) {
			op[0]--;
			int res = temp + num[idx];
			operation(idx+1, res);
			op[0]++;
		}
		
		if(op[1] > 0) {
			op[1]--;
			int res = temp - num[idx];
			operation(idx+1, res);
			op[1]++;
		}
		
		if(op[2] > 0) {
			op[2]--;
			int res = temp * num[idx];
			operation(idx+1, res);
			op[2]++;
		}
		
		if(op[3] > 0) {
			op[3]--;
			int res = temp / num[idx];
			operation(idx+1, res);
			op[3]++;
		}
	}
	
}
