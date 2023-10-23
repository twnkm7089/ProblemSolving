import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class KTW_CodeTree_예술성 {
	static int N;
	static int[][] map, groupMap;
	static List<int[]> group;
	static int harmony;
	static boolean[][] visited;
	
	//delta
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	//cnt
	static int cnt, meet;
	

	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //input
    	N = sc.nextInt();
    	map = new int[N][N];
    	
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			map[i][j] = sc.nextInt();
    		}
    	}
    	
    	harmony = 0;
    	//rotate를 돌려가며 답 구하기
    	for(int rot = 0; rot < 4; rot++) {
    		
    	
	    	//dfs를 통해 각 그룹의 시작점과 값을 찾아 저장
	    	visited = new boolean[N][N];
	    	groupMap = new int[N][N];//i번 그룹의 경우 이 배열에서 i의 위치에 존재
	    	group = new ArrayList<>();
	    	int group_cnt = 0;
	    	//배열에 들어가는 값 : [0]시작 행, [1]시작 열, [2]칸의 개수
	    	for(int i = 0; i < N; i++) {
	    		for(int j = 0; j < N; j++) {
	    			if(!visited[i][j]) {
	    				cnt = 0;
	    				
	    				//dfs
	    				dfs(i, j, group_cnt);
	    				
	    				//group에 정보 추가
	    				group.add(new int[] {i, j, cnt});
	    				
	    				//group_cnt 추가
	    				group_cnt++;
	    			}
	    		}
	    	}
	    	

	    	
	    	//조합을 만들어 조화도 계산
	    	comb();

	    	if(rot == 3) break;
	    	//회전
	    	rotate();
    	}
    	//결과 출력
    	System.out.println(harmony);
    }//end of main method
    
    //해당 좌표가 map 이내인지 확인하는 메서드
    public static boolean isValid(int r, int c) {
    	if(r >= 0 && r < N && c >= 0 && c < N) {
    		return true;
    	}
    	
    	return false;
    }
    
    
    //조합 생성, 2개의 원소만 추출
    public static void comb() {
    	
    	for(int i = 0; i < group.size()-1; i++) {
    		for(int j = i+1; j < group.size(); j++) {
    			//i, j가 groupNum이 된다.
    			int[] infoA = group.get(i);
        		int[] infoB = group.get(j);
        		
        		//맞닿은 변 계산, dfs 이용
        		meet = 0;
        		
        		visited = new boolean[N][N];
        		
        		//맞닿은 변 계산
        		meetDfs(infoA[0], infoA[1], j);

        		//조화 갱신
        		harmony += (infoA[2] + infoB[2])*map[infoA[0]][infoA[1]]*map[infoB[0]][infoB[1]]*meet;
    		}
    	}
    }
    
    public static void rotate() {
    	//groupMap을 새로운 map으로 재활용
    	
    	//가운데 십자가 반시계 rotate
    	int mid = N/2;
    	for(int i = 0; i < N; i++) {
    		groupMap[mid][i] = map[i][mid];
    		groupMap[N-1-i][mid] = map[mid][i];
    	}
    	
    	//각 box별 시계방향 rotate
    	//각 박스별 시작점 잡기
    	for(int i = 0; i < N; i += (mid+1)) {
    		for(int j = 0; j < N; j += (mid+1)) {
    			//각 박스별 탐색 개시
    			for(int r = 0; r < mid; r++) {
    				for(int c = 0; c < mid; c++) {
    					groupMap[i+c][j+(mid-1)-r] = map[i+r][j+c];
    				}
    			}
    		}
    	}
    	
    	//map 갱신
    	map = groupMap;
    }
    
    
    //dfs
    public static void dfs(int r, int c, int gNum) {
    	//방문표시, 개수 세기
    	visited[r][c] = true;
    	groupMap[r][c] = gNum;
    	cnt++;
    	//주변 탐색 후 재귀 방문
    	for(int d = 0; d < 4; d++) {
    		int nr = r + dr[d];
    		int nc = c + dc[d];
    		//범위 내, 미방문, 같은 수
    		if(isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[r][c]) {
    			dfs(nr, nc, gNum);
    		}
    	}
    }
    
    //dfs for 닿는 면 측정
    public static void meetDfs(int r, int c, int otherGnum) {
    	visited[r][c] = true;
    	
    	for(int d = 0; d < 4; d++) {
    		int nr = r + dr[d];
    		int nc = c + dc[d];
    		if(isValid(nr, nc)) {
    			if(map[nr][nc] == map[r][c] && !visited[nr][nc]) {
    				meetDfs(nr, nc, otherGnum);
    			} else if (groupMap[nr][nc] == otherGnum) {//찾고자 하는 상대 그룹과 맞닿아 있으면
    				//추가
					meet++;
				}
    		}
    	}
    }
    
}