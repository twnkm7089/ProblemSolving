import java.util.Scanner;

public class KTW_BOJ_2563_S5 {
	/*알고리즘 설명
	 * 1. 일단 100*100의 캔버스를 만든 후
	 * 2. 입력 값에 따라 격자에 색칠(1) 함.
	 * 3. 그 후 area를 구할 때는 나머지 0에 색칠한 면적 1짜리 부분만 값이 1이니 더하면 됨.
	 * */
	
	public static void main(String[] args) {
		int[][] canvas = new int[100][100];
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			outer : for(int j = x; j < x+10; j++) {
				for(int k = y; k < y+10; k++) {
					if(j >= 100 || k >= 100) {
						break outer;
					} else {
						canvas[j][k] = 1;
					}
				}
			}
		}
		int area = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				area += canvas[i][j];
			}
		}
		
		System.out.println(area);
	}

	
}
