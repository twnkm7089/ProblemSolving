import java.util.Arrays;
import java.util.Scanner;

public class CodeTree_2302_Morning01 {
	static int[][] map;
	static int[][] knightMap;
	static int L;
	static int[][] knightPos;
	static int[] knightHP;
	static int[] original;
	static int[] knightAlready; //밟고 있는 함정 수
	static boolean[] knightDead;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		int N = sc.nextInt();
		int Q = sc.nextInt();
		
		//map input
		map = new int[L][L];
		
		for(int i = 0; i < L; i++) {
			for(int j = 0; j < L; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		//knight
		knightMap = new int[L][L];
		knightPos = new int[N+1][4]; //a (r,c,h,w) a번 기사의 위치와 w,h
		knightHP = new int[N+1]; //남은 체력
		original = new int[N+1]; //원래 체력
		knightAlready = new int[N+1]; //밟고 있는 함정 수
		knightDead = new boolean[N+1];
		
		
		for(int i = 1; i <= N; i++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			int h = sc.nextInt();
			int w = sc.nextInt();
			int k = sc.nextInt();
			
			knightPos[i] = new int[] {r, c, h, w};
			knightHP[i] = k;
			original[i] = k;
			for(int a = r; a < r+h; a++) {
				for(int b = c; b < c+w; b++) {
					knightMap[a][b] = i;
					if(map[a][b] == 1) {
						knightAlready[i]++;
					}
				}
			}
		}
		
		//show map
//		for(int[] arr: map) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println("-----------");
//		for(int[] arr : knightMap) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println(Arrays.toString(knightAlready));
		
		
		//now order
		for(int i = 0; i < Q; i++) {
			int num = sc.nextInt();
			int dir = sc.nextInt();
			if(dir == 0) {
				moveUp(num, true);
			} else if(dir == 1) {
				moveRight(num, true);
			} else if(dir == 2) {
				moveDown(num, true);
			} else {
				moveLeft(num, true);
			}
		}
		
		//answer output
		int score = 0;
		for(int i = 1; i <= N; i++) {
			if(!knightDead[i]) {
				score += (original[i] - knightHP[i]);
			}
		}
		System.out.println(score);
		
	}
	
	public static void moveUp(int num, boolean isRoot) {
		if(knightDead[num]) return; //이미 죽었으면 아웃
		//이동 가능하면 움직인다
		if(searchUp(num)) {
			//움직이기 시작
			int[] pos = knightPos[num];
			int r = pos[0];
			int c = pos[1];
			int h = pos[2];
			int w = pos[3];
			
			for(int i = c; i < c+w; i++) {
				//아래줄 제거
				knightMap[r+h-1][i] = 0;
				if(map[r+h-1][i] == 1) {
					knightAlready[num]--; //이미 밟은 함정이 제거되면 수 줄이기
				}
				
				//위로 올리는데...
				//존재 시 그거 먼저 밀기
				if(knightMap[r-1][i] != 0) {
					moveUp(knightMap[r-1][i], false);
				}
				knightMap[r-1][i] = num;
				if(map[r-1][i] == 1) {
					knightAlready[num]++; //새로 밟다
				}
			}
			
			//위치 업데이트
			knightPos[num][0]--;
			
			//데미지(밀린 경우만)
			if(!isRoot) {
				knightHP[num] -= knightAlready[num];
				if(knightHP[num] <= 0) {
					deadKnight(num); //사망처리
				}
			}
		}
		
		return;
	}
	
	public static void moveRight(int num, boolean isRoot) {
		if(knightDead[num]) return; //이미 죽었으면 아웃
		
		//이동 가능하면 움직인다
		if(searchRight(num)) {
			//움직이기 시작
			int[] pos = knightPos[num];
			int r = pos[0];
			int c = pos[1];
			int h = pos[2];
			int w = pos[3];
			
			for(int i = r; i < r+h; i++) {
				//왼쪽줄 제거
				knightMap[i][c] = 0;
				if(map[i][c] == 1) {
					knightAlready[num]--; //이미 밟은 함정이 제거되면 수 줄이기
				}
				
				//오른쪽으로 미는데...
				//존재 시 그거 먼저 밀기
				if(knightMap[i][c+w] != 0) {
					moveRight(knightMap[i][c+w], false);
				}
				knightMap[i][c+w] = num;
				if(map[i][c+w] == 1) {
					knightAlready[num]++; //새로 밟다
				}
			}
			
			//위치 업데이트
			knightPos[num][1]++;
			
			//데미지(밀린 경우만)
			if(!isRoot) {
				knightHP[num] -= knightAlready[num];
				if(knightHP[num] <= 0) {
					deadKnight(num); //사망처리
				}
			}
		}
		
		return;
	}
	
	public static void moveDown(int num, boolean isRoot) {
		if(knightDead[num]) return; //이미 죽었으면 아웃
		
		//이동 가능하면 움직인다
		if(searchDown(num)) {
			//움직이기 시작
			int[] pos = knightPos[num];
			int r = pos[0];
			int c = pos[1];
			int h = pos[2];
			int w = pos[3];
			
			for(int i = c; i < c+w; i++) {
				//위쪽줄 제거
				knightMap[r][i] = 0;
				if(map[r][i] == 1) {
					knightAlready[num]--; //이미 밟은 함정이 제거되면 수 줄이기
				}
				
				//아래쪽으로 미는데...
				//존재 시 그거 먼저 밀기
				if(knightMap[r+h][i] != 0) {
					moveDown(knightMap[r+h][i], false);
				}
				knightMap[r+h][i] = num;
				if(map[r+h][i] == 1) {
					knightAlready[num]++; //새로 밟다
				}
			}
			
			//위치 업데이트
			knightPos[num][0]++;
			
			//데미지(밀린 경우만)
			if(!isRoot) {
				knightHP[num] -= knightAlready[num];
				if(knightHP[num] <= 0) {
					deadKnight(num); //사망처리
				}
			}
		}
		
		return;
	}
	
	public static void moveLeft(int num, boolean isRoot) {
		if(knightDead[num]) return; //이미 죽었으면 아웃
		
		
		//이동 가능하면 움직인다
		if(searchLeft(num)) {
			//움직이기 시작
			int[] pos = knightPos[num];
			int r = pos[0];
			int c = pos[1];
			int h = pos[2];
			int w = pos[3];
			
			for(int i = r; i < r+h; i++) {
				//오른쪽줄 제거
				knightMap[i][c+w-1] = 0;
				if(map[i][c+w-1] == 1) {
					knightAlready[num]--; //이미 밟은 함정이 제거되면 수 줄이기
				}
				
				//아래쪽으로 미는데...
				//존재 시 그거 먼저 밀기
				if(knightMap[i][c-1] != 0) {
					moveLeft(knightMap[i][c-1], false);
				}
				knightMap[i][c-1] = num;
				if(map[i][c-1] == 1) {
					knightAlready[num]++; //새로 밟다
				}
			}
			
			//위치 업데이트
			knightPos[num][1]--;
			
			//데미지(밀린 경우만)
			if(!isRoot) {
				knightHP[num] -= knightAlready[num];
				if(knightHP[num] <= 0) {
					deadKnight(num); //사망처리
				}
			}
		}
		return;
	}
	
	public static boolean searchUp(int num) {
		boolean ans = true;
		//position불러오기
		int[] pos = knightPos[num];
		int r = pos[0];
		int c = pos[1];
		int h = pos[2];
		int w = pos[3];
		if(!isInside(r-1, c)) { //좌표값을 벗어나면
			return false;
		} else {
			for(int i = c; i < c+w; i++) {
				if(map[r-1][i] == 2) {
					//벽이 있으면 아웃
					ans = false;
					break;
				}
				if(knightMap[r-1][i] != 0) { //밀게 있으면
					//그것도 분석(DFS)
					if(!searchUp(knightMap[r-1][i])) { //그것도 못 밀면 생략
						ans = false;
						break;
					}
				}
			}
		}
		
		
		return ans;
	}
	
	public static boolean searchRight(int num) {
		boolean ans = true;
		//position불러오기
		int[] pos = knightPos[num];
		int r = pos[0];
		int c = pos[1];
		int h = pos[2];
		int w = pos[3];
		if(!isInside(r, c+w)) { //좌표값을 벗어나면
			return false;
		} else {
			for(int i = r; i < r+h; i++) {
				if(map[i][c+w] == 2) {
					//벽이 있으면 아웃
					ans = false;
					break;
				}
				if(knightMap[i][c+w] != 0) { //밀게 있으면
					//그것도 분석(DFS)
					if(!searchRight(knightMap[i][c+w])) { //그것도 못 밀면 생략
						ans = false;
						break;
					}
				}
			}
		}
		
		
		return ans;
	}
	
	public static boolean searchDown(int num) {
		boolean ans = true;
		//position불러오기
		int[] pos = knightPos[num];
		int r = pos[0];
		int c = pos[1];
		int h = pos[2];
		int w = pos[3];
		if(!isInside(r+h, c)) { //좌표값을 벗어나면
			return false;
		} else {
			for(int i = c; i < c+w; i++) {
				if(map[r+h][i] == 2) {
					//벽이 있으면 아웃
					ans = false;
					break;
				}
				if(knightMap[r+h][i] != 0) { //밀게 있으면
					//그것도 분석(DFS)
					if(!searchDown(knightMap[r+h][i])) { //그것도 못 밀면 생략
						ans = false;
						break;
					}
				}
			}
		}
		
		
		return ans;
	}
	
	public static boolean searchLeft(int num) {
		boolean ans = true;
		//position불러오기
		int[] pos = knightPos[num];
		int r = pos[0];
		int c = pos[1];
		int h = pos[2];
		int w = pos[3];
		if(!isInside(r, c-1)) { //좌표값을 벗어나면
			return false;
		} else {
			for(int i = r; i < r+h; i++) {
				if(map[i][c-1] == 2) {
					//벽이 있으면 아웃
					ans = false;
					break;
				}
				if(knightMap[i][c-1] != 0) { //밀게 있으면
					//그것도 분석(DFS)
					if(!searchLeft(knightMap[i][c-1])) { //그것도 못 밀면 생략
						ans = false;
						break;
					}
				}
			}
		}
		
		
		return ans;
	}
	
	public static boolean isInside(int r, int c) {
		return r >= 0 && r < L && c >= 0 && c < L;
	}
	
	public static void deadKnight(int num) {
		knightDead[num] = true;
		//position불러오기
		int[] pos = knightPos[num];
		int r = pos[0];
		int c = pos[1];
		int h = pos[2];
		int w = pos[3];
		//제거
		for(int i = r; i < r+h; i++) {
			for(int j = c; j < c+w; j++) {
				knightMap[i][j] = 0;
			}
		}
		
		return;
		
	}

}
