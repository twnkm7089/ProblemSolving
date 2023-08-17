import java.util.Scanner;

public class KTW_BOJ_10163_B1 {
	/*알고리즘 설명
	 * 1.미리 도화지 공간을 마련.
	 * 2.색종이가 붙는 해당 칸의 번호를 색종이 숫자로 대입.
	 * 3. 새로 붙는게 있으면 새 숫자로 갱신.
	 * 4. 그러면 최종적으로 맨 위에 있는 색종이로 번호 나옴.
	 * 5. 이를 토대로 세어서 넓이 파악.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] canvas = new int[1001][1001]; //도화지 공간
		
		//인원수, x,y,width,height 입력
		int N = sc.nextInt();
		int x, y, w, h;
		for(int i = 1; i <= N; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			w = sc.nextInt();
			h = sc.nextInt();
			
			for(int j = x; j < x+w; j++) { //해당 구간 색칠하기
				for(int k = y; k < y+h; k++) {
					canvas[j][k] = i;
				}
			}
		}
		//탐색
		int count = 0;
		for(int i = 1; i <= N; i++) {
			count = 0;
			for(int j = 0; j < 1001; j++) {
				for(int k = 0; k < 1001; k++) {
					if(canvas[j][k] == i) { //어느 단위 면적 위에 맨 위에 해당 색종이 존재시
						count++; //넓이로 더하기
					}
				}
			}
			//출력
			System.out.println(count);
		}
	}
}
