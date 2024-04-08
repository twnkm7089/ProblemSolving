import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class KTW_BOJ_17140_G4_이차원배열과연산 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] map = new int[100][100];
		
		//map input
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int times = 0;
		
		//정렬 위한 PriorityQueue
		//o1[0] == 숫자, o1[1] == 개수
		//개수순 우선, 숫자 나중, 오름차순
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}	
		});
		
		//시작
		while(map[r-1][c-1] != k) {
			//결과 저장
			int[][] temp = new int[100][100];
			
			
			//시간 경과
			times++;
			if(times > 100) {
				times = -1;
				break;
			}
			
			
			//행렬 크기 구하기
			int max_r = 0;
			int max_c = 0;
			for(int i = 0; i < 100; i++) {
				for(int j = 0; j < 100; j++) {
					if(map[i][j] != 0) {
						max_r = Math.max(max_r, i);
						max_c = Math.max(max_c, j);
					}
				}
			}
			
			//크기에 따른 연산 시작
			if(max_r >= max_c) {//R연산
				for(int i = 0; i <= max_r; i++) {
					//카운팅
					int[] count = new int[101];
					for(int j = 0; j <= max_c; j++) {
						count[map[i][j]]++;
					}
					
					
					//1부터 pq에 추가
					for(int j = 1; j <= 100; j++) {
						if(count[j] > 0) {
							pq.add(new int[] {j, count[j]});
						}
					}
					
					//꺼내면서 열에 다시 넣기
					int idx = 0;//넣을 인덱스
					while(!pq.isEmpty()) {
						if(idx == 100) {//범위 초과시 종료
							pq.clear();
							break;
						}
						
						int[] info = pq.poll();
						temp[i][idx] = info[0];
						temp[i][idx+1] = info[1];
						idx+=2;
					}
				}//end of 연산
			} else {//C연산
				for(int j = 0; j <= max_c; j++) {
					//카운팅
					int[] count = new int[101];
					for(int i = 0; i <= max_r; i++) {
						count[map[i][j]]++;
					}
					
					
					//1부터 pq에 추가
					for(int i = 1; i <= 100; i++) {
						if(count[i] > 0) {
							pq.add(new int[] {i, count[i]});
						}
					}
					
					//꺼내면서 열에 다시 넣기
					int idx = 0;//넣을 인덱스
					while(!pq.isEmpty()) {
						if(idx == 100) {//범위 초과시 종료
							pq.clear();
							break;
						}
						
						int[] info = pq.poll();
						temp[idx][j] = info[0];
						temp[idx+1][j] = info[1];
						idx+=2;
					}
				}//end of 연산
			}
			//배열 갱신
			map = temp;
			
		}
		//결과 출력
		System.out.println(times);
		
	}//end of main method
	
}//end of class
