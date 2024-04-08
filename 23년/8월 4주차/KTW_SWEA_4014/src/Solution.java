import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//input
			int N = sc.nextInt();
			int X = sc.nextInt();
			
			//지도 입력
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			int answer = 0;
			//row search
			int cnt_up = 0; //올라갈 수 있는지 거리 판별용
			int cnt_down = 0; // 내려간 후 판별용 충분한 경사로 면적 확보 + 경사로끼리 겹침 방지
			for(int i = 0; i < N; i++) {
				//좌로 검사
				int height = map[i][0];//현재 높이
				//cnt 초기화
				cnt_up = 1;
				cnt_down = 0;
				boolean flag = true;//활주로 건설 가능 여부
				for(int j = 1; j < N; j++) {//좌로 검사 시작
					if(height == map[i][j]) {//같은 높이
						if(cnt_down == 0) {//하강 경사로 없으면 증가
							cnt_up++;
						} else {//하강 경사로 구간이면
							cnt_down++;//증가
							if(cnt_down == X) {//경사로 확보 완료시 인자 초기화
								cnt_down = 0;
							}
						}
					} else if(height + 1 == map[i][j]) {//1칸 높은데 이미 충분한 공간 확보 확인
						if(cnt_up >= X && cnt_down == 0) {//경사로를 위한 충분한 공간 확보 시
							cnt_up = 1;//초기화
							height++;//현재 높이 증가
						} else {//확보 불가
							flag = false;
							break;
						}
					} else if(height + 1 < map[i][j]) {//높이 너무 높아짐
						flag = false;
						break;
					} else {//높이 낮아지면 cnt_down활성화. 경사로 끼리 겹침 방지
						height = map[i][j];
						cnt_up = 0;
						cnt_down = 1;
					}
				}
				//우로 검사(위와 같은 매커니즘)
				height = map[i][N-1];//현재 높이
				cnt_up = 1;
				cnt_down = 0;
				for(int j = N - 2; j >= 0; j--) {
					if(height == map[i][j]) {//같은 높이
						if(cnt_down == 0) {
							cnt_up++;
						} else {
							cnt_down++;
							if(cnt_down == X) {
								cnt_down = 0;
							}
						}
					} else if(height + 1 == map[i][j]) {//1칸 높은데 이미 충분한 공간 확보 확인
						if(cnt_up >= X && cnt_down == 0) {
							cnt_up = 1;//초기화
							height++;//현재 높이 증가
						} else {
							flag = false;
							break;
						}
					} else if(height + 1 < map[i][j]) {//높이 너무 높아짐
						flag = false;
						break;
					} else {//높이 낮아지면 cnt_down활성화. 경사로 끼리 겹침 방지
						height = map[i][j];
						cnt_up = 0;
						cnt_down = 1;
					}
				}
				
				//검사 종료 후 건설 가능하다 판별되면?
				if(flag) {
//					System.out.println("i : " + i);
					answer++;
				}
			}// end of row search
			
			//column search
			//메커니즘은 위와 동일
			for(int j = 0; j < N; j++) {
				//아래로 검사
				int height = map[0][j];//현재 높이
				cnt_up = 1;
				cnt_down = 0;
				boolean flag = true;//활주로 건설 가능 여부
				for(int i = 1; i < N; i++) {
					if(height == map[i][j]) {//같은 높이
						if(cnt_down == 0) {
							cnt_up++;
						} else {
							cnt_down++;
							if(cnt_down == X) {
								cnt_down = 0;
							}
						}
					} else if(height + 1 == map[i][j]) {//1칸 높은데 이미 충분한 공간 확보 확인
						if(cnt_up >= X && cnt_down == 0) {
							cnt_up = 1;//초기화
							height++;//현재 높이 증가
						} else {
							flag = false;
							break;
						}
					} else if(height + 1 < map[i][j]) {//높이 너무 높아짐
						flag = false;
						break;
					} else {//높이 낮아지면 cnt_down활성화. 경사로 끼리 겹침 방지
						height = map[i][j];
						cnt_up = 0;
						cnt_down = 1;
					}
				}
				//위로 검사
				height = map[N-1][j];//현재 높이
				cnt_up = 1;
				cnt_down = 0;
				
				for(int i = N - 2; i >= 0; i--) {
					if(height == map[i][j]) {//같은 높이
						if(cnt_down == 0) {
							cnt_up++;
						} else {
							cnt_down++;
							if(cnt_down == X) {
								cnt_down = 0;
							}
						}
					} else if(height + 1 == map[i][j]) {//1칸 높은데 이미 충분한 공간 확보 확인
						if(cnt_up >= X && cnt_down == 0) {
							cnt_up = 1;//초기화
							height++;//현재 높이 증가
						} else {
							flag = false;
							break;
						}
					} else if(height + 1 < map[i][j]) {//높이 너무 높아짐
						flag = false;
						break;
					} else {//높이 낮아지면 cnt_down활성화. 경사로 끼리 겹침 방지
						height = map[i][j];
						cnt_up = 0;
						cnt_down = 1;
					}
				}
				
				//검사 종료 후 건설 가능하다 판별되면?
				if(flag) {
//					System.out.println("j : " + j);
					answer++;
				}
			}// end of column search
			
			
			
			System.out.println("#" + test_case + " " + answer);

		}//end of test case
	}//end of main method
}//end of class