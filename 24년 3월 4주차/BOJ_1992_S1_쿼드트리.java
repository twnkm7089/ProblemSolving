import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1992_S1_쿼드트리 {
	static int N;
	static int[][] screen;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//재귀
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		screen = new int[N][N];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < N; j++) {
				screen[i][j] = str.charAt(j) - '0';
			}
		}
		
		//compress함수로 재귀
		System.out.println(compress(0,0,N));
	}
	
	public static String compress(int startR, int startC, int size) {
		//size1이면 그냥 반환
		if(size == 1) {
			return screen[startR][startC]+"";
		}
		String ans = "";
		//모두 같은 값인지 탐색
		int num = screen[startR][startC];
		boolean flag = true;
		outer: for(int i = startR; i < startR+size; i++) {
			for(int j = startC; j < startC+size; j++) {
				if(screen[i][j] != num) {
					flag = false;
					break outer;
				}
			}
		}
		
		if(flag) {
			//맞으면 숫자 추가
			ans += num+"";
		} else {
			//아니면 사이즈 반으로 나누어 네 구역 순서대로 재귀해 결과 누적
			int half = size/2;
			ans += "(";
			ans += compress(startR, startC, half);
			ans += compress(startR, startC+half, half);
			ans += compress(startR+half, startC, half);
			ans += compress(startR+half, startC+half, half);
			ans += ")";
		}
		//결과 반환
		return ans;
	}

}
