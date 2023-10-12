import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class KTW_SWEA_5648_원자소멸
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();

			
			int[] dr = {0, 0, -1, 1};
			int[] dc = {1, -1, 0, 0};
			
			//0.5초, 0.5초 간격이라 가정해서 구한다.
			//지도 위치는 +1000한 후, *2
			Queue<int[]> queue = new LinkedList<>();
			for(int i = 0; i < N; i++) {
				int r = (sc.nextInt()+1000)*2;
				int c = (sc.nextInt()+1000)*2;
				int dir = sc.nextInt();
				int energy = sc.nextInt();
				queue.add(new int[] {r, c, dir, energy});
			}
			
			//좌표에 따른 pq 생성
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0] == o2[0]) {
						return o1[1] - o2[1];
					}
					return o1[0] - o2[0];
				}
				
			});
			
			int answer = 0;
			while(true) {
				while(!pq.isEmpty()) {
					int[] atom = pq.poll();
					//중복 제거
					if(!pq.isEmpty() && atom[0] == pq.peek()[0] && atom[1] == pq.peek()[1]) {
						answer += atom[3];
						while(!pq.isEmpty() && atom[0] == pq.peek()[0] && atom[1] == pq.peek()[1]) {
							answer += pq.poll()[3];
						}
						//다 뽑았으면 교체
						if(pq.isEmpty()) break;
						atom = pq.poll();
					}
					
					//범위 밖이면 생략
					if(atom[0] < 0 || atom[0] > 4000 || atom[1] < 0 || atom[1] > 4000) {
						continue;
					}
					
					//모두 아니었으면 큐에 임시 저장
					int dir = atom[2];
					int nr = atom[0] + dr[dir];
					int nc = atom[1] + dc[dir];
					int energy = atom[3];
					queue.add(new int[] {nr, nc, dir, energy});
				}
				
				if(queue.isEmpty()) break;
				//큐에서 뽑기
				while(!queue.isEmpty()) {
					pq.add(queue.poll());
				}
				
			}
			
			//결과 출력
			System.out.println("#" + test_case + " " + answer);

		}//end of test case
	}//end of main method

	
}