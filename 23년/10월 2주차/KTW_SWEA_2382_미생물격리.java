import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class KTW_SWEA_2382_미생물격리
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			
			//미생물군집정보 저장하는 큐
			Queue<int[]> queue = new LinkedList<>();
			//[0] [1] [2] [3]은 각각 문제에서 주어진 대로 행, 열, 개수, 방향
			for(int i = 0; i < K; i++) {
				int[] virus = new int[4];
				virus[0] = sc.nextInt();
				virus[1] = sc.nextInt();
				virus[2] = sc.nextInt();
				virus[3] = sc.nextInt();
				
				queue.add(virus);
			}
			
			//방향 델타, 0, 1, 2, 3
			int[] dr = {-1, 1, 0, 0};
			int[] dc = {0, 0, -1, 1};
			
			//이동 시작
			for(int t = 0; t < M; t++) {
				List<int[]>[][] map = new ArrayList[N][N];
				
				
				//큐에서 바이러스 군집 빼며 이동
				while(!queue.isEmpty()) {
					int[] info = queue.poll();
					int r = info[0];
					int c = info[1];
					int num = info[2];
					int dir = info[3] - 1;
					
					int nr = r + dr[dir];
					int nc = c + dc[dir];
					
					//가장자리면 방향 전환 및 미생물 감소
					if(nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
						num /= 2;
						if(dir == 0 || dir == 2) dir+=1;
						else dir-=1;
					}
					
					//바이러스가 있는 경우(군집 남아 있음)만 기록
					if(num > 0) {
						//리스트 추가
						if(map[nr][nc] == null) {
							map[nr][nc] = new ArrayList<int[]>();
						}
						
						//추가
						map[nr][nc].add(new int[] {nr, nc, num, dir+1});
					}
				}
				
				//map에 있는 군집 내용 바탕으로 바이러스 군집 정보 큐에 넣기
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] == null) {
							continue;
						} else if(map[i][j].size() == 1) {//1개의 군집이면 그냥 넣기
							queue.add(map[i][j].get(0));
						} else {//여러개는 통합
							int num = 0;
							int max_virnum = 0;
							int dir = 0;
							
							for(int k = 0; k < map[i][j].size(); k++) {
								int[] temp = map[i][j].get(k);
								//수 추가
								num += temp[2];
								//최대에 따른 방향 추가
								if(max_virnum < temp[2]) {
									dir = temp[3];
									max_virnum = temp[2];
								}
							}
							//큐에 추가
							queue.add(new int[] {i, j, num, dir});
						}
					}
				}
				
			}//end of move
			
			//수 세기
			int answer = 0;
			while(!queue.isEmpty()) {
				answer += queue.poll()[2];
			}
			//결과 출력
			System.out.println("#" + test_case + " " + answer);
		}//end of test case 
	}
}