package Feb4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1052_S1_물병 {
	//방법
	//2진수로 만든다. glass[]는 앞에서부터 2^0, 2^1자리...
	//처음 나오는 두 1의 차만큼 더한 새 수로 갱신한다.
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//유리잔 개수 구하기, arr[k]는 2^k의 물 들어 있는 물병
		int logNum = (int)Math.ceil(Math.log(N)/Math.log(2));
		int[] glass = new int[logNum+1];
		glass[0] = N;
		//init value setting
		for(int i = 0; i < logNum; i++) {
			int temp = glass[i];
			glass[i+1] = temp / 2;
			glass[i] = temp % 2;
		}
		//잔의 개수는?
		int glassNum = 0;
		for(int i = 0; i <= logNum; i++) {
			glassNum += glass[i];
		}
		
//		System.out.println(Arrays.toString(glass));
//		System.out.println(glassNum);
		
		//이제 추가 시작
		int answer = 0;
		while(glassNum > K) {
			int pos1 = -1;

			for(int i = 0; i < logNum; i++) {
				if(glass[i] != 0) {
					//첫 병 위치
					if(pos1 == -1) {
						pos1 = i;
					} else {
						//다음 병 위치
						//기존 병 2개에 새로산 물 추가로 다음 병 하나로 바뀌었다.
						answer += (int)Math.pow(2, i) - (int)Math.pow(2, pos1);
						glass[pos1] = 0;
						glass[i] = 0;
						glass[i+1]++;
						
						//연쇄작용
						for(int j = i+1; j < logNum; j++) {
							if(glass[j] == 2) {
								glass[j+1]++;
								glass[j] = 0;
							} else {
								break;
							}
						}
						
						//glassNum 구하기
						glassNum = 0;
						for(int j = 0; j <= logNum; j++) {
							glassNum += glass[j];
						}
						
//						System.out.println("--------------------");
//						System.out.println(Arrays.toString(glass));
//						System.out.println(glassNum);
//						System.out.println("Answer:" + answer);
						
						//추가 했으니 다음 loop로
						break;
					}
				}
			}
			

		}
		//output
		System.out.println(answer);

	}

}
