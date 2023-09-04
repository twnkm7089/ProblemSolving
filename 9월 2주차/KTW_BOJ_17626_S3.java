package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KTW_BOJ_17626_S3 {
	public static int[] sel;
	public static int idx, sidx;
	public static int N;
	public static int R;
	/*알고리즘 설명
	 * 1. 조합을 이용
	 * 2. 하나, 둘, 세개의 제곱수로 되는지 확인 후 안되면 무조건 4개의 제곱수로 표현 가능한 것.
	 * 3. 우선 하나의 제곱수로 표현 가능한지 검사
	 * 4. 아니면 (1,1), (1,2), (2,1)같은 크기 2의 중복조합쌍을 생성, 그 후 이들로 표현 가능한지 검증
	 * 5. 아니면 크기 3의 중복조합쌍 형성해서 비교
	 * 6. 셋 다 아니면 4가 정답.
	 * */
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		N = Integer.parseInt(br.readLine());
		//루트N 이하의 수 중 최대 정수
		int max_sqrt = (int)Math.sqrt(N);
		
		//case 1
		if(max_sqrt * max_sqrt == N) {//하나의 제곱으로 표현 가능한 완전제곱수면
			System.out.println(1);//1 출력
		} else {//아니면
			//case 2
			//크기 2의 중복조합 생성
			sel = new int[2];
			R = 2;
			int caseTwo = comb(1, 0, max_sqrt);
			
			if(caseTwo != -1) {//해당 쌍 존재시
				System.out.println(2);//2 출력
			} else {//case 3 and 4
				//없으면 크기 3 중복조합 생성
				sel = new int[3];
				R = 3;
				int caseThree = comb(1, 0, max_sqrt);
				if(caseThree != -1) {//해당 쌍 존재시
					System.out.println(3);//3 출력
				} else {//없으면
					System.out.println(4);//4 출력
				}
			}
		}
		
		
	}//end of main method
	
	public static int comb(int idx, int sidx, int max_sqrt) {
		if(sidx == R) {//조합쌍 생성 완료
			//검증
			int sum = 0;
			for(int i = 0; i < R; i++) {
				sum += sel[i] * sel[i];
				if(sum > N) return -1;//sum이 이미 N 넘으면 가망 없음. -1
			}
			if(N == sum) return R;//다 더한 수가 같으면 R 반환
			else return -1;//아니면 -1 반환
		}
		//조합 생성
		for(int i = idx; i <= max_sqrt; i++) {
			sel[sidx] = i;//추가
			int temp = comb(i, sidx+1, max_sqrt);//재귀, (1,1)등 생성 위해 idx = i
			if(temp == R) return R;//조합 존재시 R 반환
		}
		
		return -1;//결국 아무것도 없으면 -1 반환
	}
	
}//end of class
