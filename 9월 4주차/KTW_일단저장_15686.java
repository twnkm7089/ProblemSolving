import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_일단저장_15686 {
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		int[][] chicken = new int[13][2];
		int chickenNum = 0;
		
		int[][] house = new int[2*N][2];
		int houseNum = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house[houseNum][0] = i;
					house[houseNum][1] = j;
					houseNum++;
				} else if(map[i][j] == 2) {
					chicken[chickenNum][0] = i;
					chicken[chickenNum][1] = j;
					chickenNum++;
				}
			}
		}
		
//		//현재 가까운 치킨집은?
//		int[] nearchicken = new int[houseNum];
//		for(int i = 0; i < houseNum; i++) {
//			int distance = Integer.MAX_VALUE;
//			for(int j = 0; j < chickenNum; j++) {
//				int temp = (Math.abs(house[i][0]-chicken[j][0]) + Math.abs(house[i][1]-chicken[j][1]));
//				
//				if(temp < distance) {
//					nearchicken[i] = j;
//					distance = temp;
//				}
//			}
//		}

		int exist = chickenNum;
		
		if(exist == M) {
			int distance = 0;
			for(int i = 0; i < houseNum; i++) {
				int temp = Integer.MAX_VALUE;
				for(int j = 0; j < chickenNum; j++) {
					temp = Math.min(temp, (Math.abs(house[i][0]-chicken[j][0]) + Math.abs(house[i][1]-chicken[j][1])));
				}
				distance += temp;
			}
			System.out.println(distance);
		} else {
			while(exist > M) {
				
				int distance = Integer.MAX_VALUE;
				int delete = -1;
				
				for(int i = 0; i < chickenNum; i++) {
					if(chicken[i][0] == -1) continue;
					int temp = 0;
					for(int j = 0; j < houseNum; j++) {
						int temp2 = Integer.MAX_VALUE;//가까운 치킨집과 최소 거리 구하기
						for(int k = 0; k < chickenNum; k++) {
							if(chicken[k][0] != -1 && k != i)//치킨집 삭제했다고 가정하고 최소거리 구하기
								temp2 = Math.min(temp2, Math.abs(house[j][0]-chicken[k][0]) + Math.abs(house[j][1]-chicken[k][1]));
						}
						temp += temp2;
					}
					
					if(temp < distance) {
						delete = i;
						distance = temp;
					}
				}

				//삭제
				map[chicken[delete][0]][chicken[delete][1]] = 0;
				
				chicken[delete][0] = -1;
				chicken[delete][1] = -1;
				
				
				exist--;
//				System.out.println(delete);
				
				if(exist == M) {
					distance = 0;
					for(int i = 0; i < houseNum; i++) {
						int temp = Integer.MAX_VALUE;
						for(int j = 0; j < chickenNum; j++) {
							if(chicken[j][0] == -1) continue;
							temp = Math.min(temp, (Math.abs(house[i][0]-chicken[j][0]) + Math.abs(house[i][1]-chicken[j][1])));
						}
						distance += temp;
					}
					System.out.println(distance);
				}
			}
		}
		
	}//end of main method
}//end of class
