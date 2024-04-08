import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 디스플레이 각 digit을 분리해내고, 켜져 있는 전구들을 통해 불가능한 수를 구한다.
 * 2. 그리고 확률론을 이용해 가능한 수를 구해 더한다.
 * 3. 계산한다. 자세한건 아래에
 * */
public class KTW_BOJ_1089_G4_스타트링크타워 {
	static long sum, cnt;
	static int N;
	static List<Integer>[] possible;
	static int[] sel;
	
	//main method
	public static void main(String[] args) throws NumberFormatException, IOException {
		//이 칸이 켜져 있으면 이 수는 불가능하다는 것 저장하는 배열
		int[][][] impossible = new int[5][3][];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 3; j++) {
				impossible[i][j] = new int[] {};
			}
		}
		impossible[0][0] = new int[] {1};
		impossible[0][1] = new int[] {1, 4};
		impossible[1][0] = new int[] {1, 2, 3, 7};
		impossible[1][1] = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		impossible[1][2] = new int[] {5, 6};
		impossible[2][0] = new int[] {1, 7};
		impossible[2][1] = new int[] {0, 1, 7};
		impossible[3][0] = new int[] {1, 3, 4, 5, 7, 9};
		impossible[3][1] = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		impossible[3][2] = new int[] {2};
		impossible[4][0] = new int[] {1, 4, 7};
		impossible[4][1] = new int[] {1, 4, 7};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//디스플레이 정보 입력
		char[][][] display = new char[N][5][3];
		
		for(int i = 0; i < 5; i++) {//입력 행
			String str = br.readLine();
			for(int j = 0; j < N; j++) {//N번째 digit
				for(int k = 4*j; k < 4*j+3; k++) {//입력열
					display[j][i][k%4] = str.charAt(k);
				}
			}
		}
		
		//가능한 숫자 배열로 입력
		possible = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			possible[i] = new ArrayList<>();
		}
		
		//각 숫자별 탐색
		//각 digit별 켜진 칸을 분석해 어떤 칸이 켜져 있으면 될 수 없는 수를 제외한다.
		//켜져야 할 전구가 꺼지는 에러만 있지 반대는 없으니 가능하다.
		//예를 들어, (0,0)칸이 켜져 있는 경우 1은 가능한 숫자에서 제외된다. 그럼 1을 false로 바꾼다.
		for(int i = 0; i < N; i++) {
			//불가능한 숫자, true면 이번 digit으로 될 수 없다.
			boolean[] imposNum = new boolean[10];
			
			//켜진 부분 탐색, 불가능한 숫자 저장
			char[][] temp = display[i];
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < 3; k++) {
					if(temp[j][k] == '#' && impossible[j][k].length > 0) {
						for(int im : impossible[j][k]) {
							imposNum[im] = true;
						}
					}
				}
			}
			//탐색 종료 후 가능한 숫자들을 possible[i]에 추가
			for(int j = 0; j < 10; j++) {
				if(imposNum[j] == false) {
					possible[i].add(j);
				}
			}
		}
		//총합
		sum = 0;
		//가능한 숫자 개수
		cnt = 1;
		//각 digit별 가능한 숫자의 개수를 곱해준다. 불가능한 칸이 섞이면 0이 곱해지며 자동으로 0이 된다.
		for(int i = 0; i < N; i++) {
			cnt *= possible[i].size();
		}
		
		//총합 구하기
		//하는법
		//예를 들어, 세자리수 이고, 첫 자리에 1, 2, 3이 가능하다고 하자.
		// 1**로 나오는 숫자의 가짓수는 나머지 두 자리에 각각 들어갈 수 있는 숫자의 개수의 곱이다.
		// 예를 들어, 십의 자리에는 5가지 숫자, 일의 자리에는 4가지 숫자가 들어갈 수 있으면, 1**로 표현 가능한 수는 5*4=20가지다.
		// 이는 역으로 말해 100 * 20을 더할 수 있다는 의미다.
		// 이를 모든 자릿수의 모든 가능한 숫자에 해보자.
		for(int i = 0; i < N; i++) {
			//각 digit별 가능한 숫자 리스트 불러오기
			List<Integer> arrList = possible[i];
			
			//곱하는 수
			//해당 칸의 숫자가 확정될 경우 가능한 나머지 경우의 수 곱하기
			long temp = 1;
			for(int j = 0; j < N; j++) {
				if(i != j) {
					temp *= possible[j].size();
				}
			}
			//총합
			for(int a : arrList) {
				sum += a*(int)Math.pow(10, N-1-i)*temp;
			}
			
		}
		

		//결과
		if(cnt > 0) {//가능하면 나누기	
			System.out.println(sum / (double)cnt);
		} else {//불가능 시 -1 출력
			System.out.println(-1);
		}
		
		br.close();
	}//end of main method

	
}
