import java.util.Scanner;

public class KTW_BOJ_2567_S4 {
	/*알고리즘 설명
	 * 1. 우선 색종이를 바탕으로 캔버스에 색칠한다(값을 1로 변경).
	 * 2. 둘레는 색종이가 붙여진 곳과 아닌 곳의 경계에만 나타난다. 
	 * 즉, 색종이가 붙여진 곳(1)이 나올 때 상하좌우에 (0)이 있는, 즉 안 붙어 있는곳이 있다면 경계가 1만큼 생긴다.
	 * 3. 이를 바탕으로 도화지의 경계에 색종이가 붙은 경우 + 색종이와 안붙은 곳의 경계를 토대로 계산을 했다.
	 * */
	public static void main(String[] args) {
		int[][] canvas = new int[100][100];
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) { //색종이 붙이기
			int x = sc.nextInt();
			int y = sc.nextInt();
			for(int j = x; j < x+10; j++) {
				for(int k = y; k < y+10; k++) {
					if(j < 100 && k < 100)
						canvas[j][k] = 1;
				}
			}
		}
		
		int count = 0; //정답
		for(int i = 0; i < 100; i++) { //색종이 둘레 세기
			for(int j = 0; j < 100; j++) {
				if(canvas[i][j] == 1) { //색종이가 붙은 단위 면적에서
					//up
					if(i == 0) { //도화지 경계
						count += 1;
					} else if(canvas[i-1][j] == 0) { //도화지 경계는 아니나 색종이 경계
							count += 1;
					}
					
					//down
					if(i == 99) {//도화지 경계
						count += 1;
					} else if(canvas[i+1][j] == 0) {//도화지 경계는 아니나 색종이 경계
						count += 1;
					}
					
					//left
					if(j == 0) {//도화지 경계
						count += 1;
					} else if (canvas[i][j-1] == 0) {//도화지 경계는 아니나 색종이 경계
						count += 1;
					}
					
					//right
					if(j == 99) {//도화지 경계
						count += 1;
					} else if (canvas[i][j+1] == 0) {//도화지 경계는 아니나 색종이 경계
						count += 1;
					}
				}
			}
		}
		
		//출력
		System.out.println(count);
		
	}
}
