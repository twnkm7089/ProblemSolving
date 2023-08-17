import java.util.Scanner;

public class KTW_BOJ_13300_B2 {
	public static void main(String[] args) {
		/*알고리즘 설명
		 * 1. 성별, 학년에 따라 분류할 12개의 공간 할당
		 * 2. 해당 공간 각각에 해당하는 인원 수 추가
		 * 3. 인원 0 인 경우 제외 나머지 경우에 필요한 방 개수 계산 
		 * */
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		int[][] room = new int[2][6]; //성별, 학년별 인원수 측정
		for(int i = 0; i < n; i++) {
			int s = sc.nextInt();
			int y = sc.nextInt();
			room[s][y-1]++;
		}
		
		int count = 0;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 6; j++) {
				if(room[i][j] != 0)
					count += (((room[i][j] - 1) / k) + 1); //필요한 방 개수
				else //인원 없으면 방 없어도 됨
					continue;
			}
		}
		
		System.out.println(count);
	}
}
