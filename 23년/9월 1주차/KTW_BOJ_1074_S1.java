package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KTW_BOJ_1074_S1 {
	/*알고리즘 설명
	 * 1. 사이즈 구하기(2의 N승)
	 * 2. 블록을 4개의 subblock으로 나누기.
	 *    0 1
	 *    2 3
	 * 3. idx에 해당 블록 번호 * subblock 크기를 누적해서 더하기.
	 * 4. 블록을 쪼개서 반복.
	 * */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		//사이즈 구하기
		int size = (int)Math.pow(2, N);
		//번호 누적 합
		int idx = 0;
		while(size > 1) {//사이즈가 2 이상(변이 2 이상)
			int half = size / 2;//반으로 나누어 4개의 subblock
			int block_num = (2*((r % size)/half) + ((c % size)/half));//블록 번호 구하기. r % size를 half로 나누자. 
			idx += block_num * (half * half);//누적 합
			size/=2;//사이즈 절반으로 나누기.
		}
		System.out.println(idx);//정답 구하기.

	}

}//end of class
