import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

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
		for(int i = 0; i < N; i++) {
			//불가능 숫자, true면 이번 digit으로 될 수 없다.
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
			
			for(int j = 0; j < 10; j++) {
				if(imposNum[j] == false) {
					possible[i].add(j);
				}
			}
		}
		
		sum = 0;
		
		cnt = 1;
		for(int i = 0; i < N; i++) {
			cnt *= possible[i].size();
		}
		
		for(int i = 0; i < N; i++) {
			List<Integer> arrList = possible[i];
			
			//곱하는 수
			long temp = 1;
			for(int j = 0; j < N; j++) {
				if(i != j) {
					temp *= possible[j].size();
				}
			}
			
			for(int a : arrList) {
				sum += a*(int)Math.pow(10, N-1-i)*temp;
			}
			
		}
		

		
		if(cnt > 0) {			
			System.out.println(sum / (double)cnt);
		} else {
			System.out.println(-1);
		}
		
		br.close();
	}//end of main method

	
}
