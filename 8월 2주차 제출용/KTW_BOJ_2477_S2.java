import java.util.Scanner;

public class KTW_BOJ_2477_S2 {
	/*알고리즘 설명
	 * 1. 오른쪽 아래에서 시작한다고 가정할때, 좌상단으로 들어간 모양부터 시계방향으로 414231, 423131, 424231, 423231
	 * 2. 그래서 4, 1이 나온 횟수 세서 모양 판별 
	 * 3. 시작지점이 랜덤이니 start_idx를 지정하고 해당 수열이 나오는 지점을 start_idx로 설정
	 * 4. area를 이 start_idx 바탕으로 구함.
	 * 5. 계산 후 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//input
		int N = sc.nextInt();
		int[][] map = new int[6][2];
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 2; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int area = 0;
		int cnt_1 = 0;
		int cnt_4 = 0;
		for(int i = 0; i < 6; i++) { //모양 판별
			if(map[i][0] == 1)
				cnt_1++;
			else if(map[i][0] == 4)
				cnt_4++;
		}
		int start_idx = 0;
		if(cnt_1 == 2 && cnt_4 == 2) {
			for(int i = 0; i < 6; i++) {
				if(map[i][0] == 4 && map[(i+1)%6][0] == 1) {//시작점 초기화
					start_idx = i;
				}
			}
			area = (map[(start_idx + 4)%6][1] * map[(start_idx + 3)%6][1]) - (map[(start_idx)%6][1] * map[(start_idx + 1)%6][1]);//넓이 구하기
		} else if (cnt_1 == 2) {
			for(int i = 0; i < 6; i++) {
				if(map[i][0] == 4 && map[(i+1)%6][0] == 2) {//시작점 초기화
					start_idx = i;
				}
			}
			area = (map[(start_idx)%6][1] * map[(start_idx + 1)%6][1]) - (map[(start_idx + 3)%6][1] * map[(start_idx + 4)%6][1]);//넓이 구하기
		} else if (cnt_4 == 2) {
			for(int i = 0; i < 6; i++) {
				if(map[(i+4)%6][0] == 3 && map[(i+5)%6][0] == 1) {//시작점 초기화
					start_idx = i;
				}
			}
			area = (map[(start_idx + 4)%6][1] * map[(start_idx + 5)%6][1]) - (map[(start_idx + 1)%6][1] * map[(start_idx + 2)%6][1]);//넓이 구하기
		} else {
			for(int i = 0; i < 6; i++) {
				if(map[i][0] == 4 && map[(i+1)%6][0] == 2) {//시작점 초기화
					start_idx = i;
				}
			}
			area = (map[(start_idx)%6][1] * map[(start_idx + 5)%6][1]) - (map[(start_idx + 2)%6][1] * map[(start_idx + 3)%6][1]);//넓이 구하기
		}
		//결과 출력
		System.out.println(area * N);
	}
}
