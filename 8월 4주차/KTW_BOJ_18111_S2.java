package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 높이별로 브루트포스 사용
 * 2. 최소 높이부터 시작해 해당 높이를 만들기 위한 작업 실시
 * 3. 인벤토리에 있는 블록을 모두 써도 안되는 경우(너무 높다) break
 * 4. 그 때까지 최소 시간이 들었던 곳의 높이 출력
 * */
public class KTW_BOJ_18111_S2 {
	public static void main(String[] args) throws IOException{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		//map 입력
		int[][] map = new int[N][M];
		int min = Integer.MAX_VALUE;//최소 높이 찾기
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] < min) {//최소 높이 갱신
					min = map[i][j];
				}
			}
		}
		
		int time = Integer.MAX_VALUE;//최소 시간 저장 변수
		int temp_height = min;//반복문용 높이
		int height = min;//높이값 초기화
		outer : while(true) {//각 높이별 구현을 위해서...
			int inventory = B;//인벤토리에 블록 저장
			int temp_time = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] > temp_height) {//높이가 높으면
						//파서 해당 블록 인벤토리에 저장(1번 작업), 시간, inventory 갱신
						temp_time += (2*(map[i][j] - temp_height));
						inventory += (map[i][j] - temp_height);
					}
					if(map[i][j] < temp_height) {//높이가 낮으면
						//2번 작업, 시간, inventory 갱신
						temp_time += (temp_height - map[i][j]);
						inventory -= (temp_height - map[i][j]);
					}
				}
			}//end of search
			if(inventory < 0) {//인벤토리에 든 블록이 음수면 불가능! 종료
				break outer;
			}
			if(temp_time <= time) {//최소 시간이면 시간, 높이 갱신
				time = temp_time;
				height = temp_height;
				temp_height++;
			} else {//시간이 최소가 아니다 -> 너무 높아서 이제 시간이 커지는 구간. 의미 없으니 탐색 종료
				break;
			}
			
		}
		
		System.out.println(time + " " + height);//결과 출력
		
		
		
		
	}//end of main method

}//end of class
