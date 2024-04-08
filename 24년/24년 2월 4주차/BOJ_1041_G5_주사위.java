package Feb4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1041_G5_주사위 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Integer.parseInt(br.readLine());
		
		//dice input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dice = new int[6];
		for(int i = 0; i < 6; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		//선택 가능한 모든 경우의 수에 대한 조합 생성
		//A-F, B-E, C-D는 불가능하니, A,F중 하나, B,E중 하나, C,D중 하나 조합
		int[] arr1 = {0, 5};
		int[] arr2 = {1, 4};
		int[] arr3 = {2, 3};
		List<int[]> selected = new ArrayList<>();
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < 2; k++) {
					selected.add(new int[] {arr1[i], arr2[j], arr3[k]});
					selected.add(new int[] {arr1[i], arr3[j], arr2[k]});
					selected.add(new int[] {arr2[i], arr1[j], arr3[k]});
					selected.add(new int[] {arr2[i], arr3[j], arr1[k]});
					selected.add(new int[] {arr3[i], arr1[j], arr2[k]});
					selected.add(new int[] {arr3[i], arr2[j], arr1[k]});
				}
			}
		}
		
		//계산 시작
		long answer = Long.MAX_VALUE;
		//N == 1
		if(N == 1) {
			//제일 큰 면만 제외하면 됨.
			int sum = 0;
			int max = 0;
			for(int i = 0; i < 6; i++) {
				sum += dice[i];
				if(max < dice[i]) {
					max = dice[i];
				}
			}
			sum -= max;
			answer = sum;
		} else {
			//나머지 경우 가능한 조합에 대해 다음 함수 실행
			for(int[] sel : selected) {
				long first = (long)dice[sel[0]];
				long second = (long)dice[sel[1]];
				long third = (long)dice[sel[2]];
				long sum = 0;
				
				if (N == 2) {
					//N == 2
					sum = (first * 2 + second * 2 + third)*4;
				} else {
					//corner
					//위의 모서리
					sum += (first + second + third) * 4;
					//옆면 경계
					sum += (first + second) * (8 * (N-2) + 4);
					//아래 경계
					sum += (first) * 4 * (N-2);
					//inside
					sum += first * 5 * (N-2) * (N-2);
				}
				
				//최소값 갱신
				if(sum < answer) {
					answer = sum;
				}
			}

		}
		
		//결과 출력
		System.out.println(answer);

	}

}
