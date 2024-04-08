import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*치킨 시키신 분... 알고리즘 설명
 * 1. 집이랑 치킨집 위치 배열 저장
 * 2. 조합으로 폐업할 치킨집 구함
 * 3. 남은 치킨집 치킨거리 구함
 * 4. 완전탐색으로 최소값 찾기
 * */
public class KTW_BOJ_15686_G5_치킨배달 {
	public static int[] sel;
	public static int close;
	public static int N, M;
	public static int[][] map;
	public static List<int[]> chicken;
	public static List<int[]> house;
	public static int distance;
	
	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		//치킨집, 집 위치 저장 배열 초기화
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		//정보 입력 받으면서 집, 치킨집 나오면 위치 저장
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house.add(new int[] {i, j});
				} else if(map[i][j] == 2) {
					chicken.add(new int[] {i, j});
				}
			}
		}

		//폐업할 개수 결정
		close = chicken.size() - M;
		sel = new int[close];//sel 배열 생성
		//거리 초기화 및 조합 함수 돌림
		distance = Integer.MAX_VALUE;
		comb(0, 0);
		//결과 출력
		System.out.println(distance);
		
		br.close();
	}//end of main method
	
	
	//조합
	public static void comb(int idx, int sidx) {
		if(sidx == close) {//다 뽑았으면
			//치킨거리 구하기
			int distOfNow = 0;
			for(int i = 0; i < house.size(); i++) {//각 집에 관해
				//거리가 최소인 치킨집과의 거리 찾기
				int temp = Integer.MAX_VALUE;
				outer : for(int j = 0; j < chicken.size(); j++) {
					//폐업한 가게 아니어야 함
					for(int k = 0; k < sel.length; k++) {
						if(sel[k] == j) continue outer;
					}
					//거리가 최소인 치킨집과의 거리
					temp = Math.min(temp, Math.abs(house.get(i)[0]-chicken.get(j)[0]) + Math.abs(house.get(i)[1]-chicken.get(j)[1]));
				}
				//더해주기
				distOfNow += temp;
			}
			//모든 집에 대해 다 수행했으면 거리 정보 갱신
			distance = Math.min(distOfNow, distance);
			return;
		}
		//재귀로 조합 생성
		for(int i = idx; i <= chicken.size() - close + sidx; i++) {
			sel[sidx] = i;
			comb(i + 1, sidx + 1);
		}
	}
	
	
}//end of class
