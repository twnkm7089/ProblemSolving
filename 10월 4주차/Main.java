import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int N, M, P, C, D;
	
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //8방향 delta
        int[] dr1 = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc1 = {0, 1, 1, 1, 0, -1, -1, -1};
        
        //산타를 위하 4방향 delta
        int[] dr2 = {-1, 0, 1, 0};
        int[] dc2 = {0, 1, 0, -1};
        
        
        //input
        N = sc.nextInt();
        M = sc.nextInt();
        P = sc.nextInt();
        C = sc.nextInt();
        D = sc.nextInt();
        //루돌프 초기 위치
        int[][] map = new int[N][N];
        
        int[] rudolPos = new int[] {sc.nextInt()-1, sc.nextInt()-1};
        //위치는 -1로 표시
        map[rudolPos[0]][rudolPos[1]] = -1;
        
        
        //santa
        
        int[][] santaPos = new int[P+1][2];
        int[] santaScore = new int[P+1];
        for(int i = 0; i < P; i++) {
        	int num = sc.nextInt();
        	int r = sc.nextInt();
        	int c = sc.nextInt();
        	santaPos[num] = new int[] {r-1, c-1};
        	map[r-1][c-1] = num;
        }
        //기절여부
        int[] faint = new int[P+1];
        
        
        //초기화 완료, 이제 턴수대로 구현해야....
        
        
        int turn = 1;
        int fail = 0;
        while(turn <= M && fail < P) {
	        
	        
	        //루돌프 이동
	        //맨허튼 거리 판별
	        double dist = Double.MAX_VALUE;
	        int targetR = -1;
	        int targetC = -1;
	        int santaNum = -1;
	        for(int i = 1; i <= P; i++) {
	        	//각 산타 별 루돌프와의 거리를 탐색, 거리가 가깝거나 거리가 같아도 r, c가 큰 산타를 타깃으로 설정.
	        	double temp_dist = findDist(rudolPos, santaPos[i]);
	        	if(temp_dist < dist) {
	        		dist = temp_dist;
	        		targetR = santaPos[i][0];
	        		targetC = santaPos[i][1];
	        		santaNum = i;
	        	} else if(temp_dist == dist) {
	        		if((santaPos[i][0] > targetR) || (santaPos[i][0] == targetR) && (santaPos[i][1] > targetC)) {
	        			dist = temp_dist;
	            		targetR = santaPos[i][0];
	            		targetC = santaPos[i][1];
	            		santaNum = i;
	        		}
	        	}
	        }
	        
	        //대상 설정 완료, 이제 루돌프는 이동
	        int dir = -1;
	        dist = Double.MAX_VALUE;
	        //방향탐색
	        for(int d = 0; d < 8; d++) {
	        	int nr = rudolPos[0] + dr1[d];
	        	int nc = rudolPos[1] + dc1[d];
	        	if(isInside(nr, nc, N)) {
	        		double temp_dist = findDist(new int[] {nr, nc}, santaPos[santaNum]);
	        		if(dist > temp_dist){
	        			dist = temp_dist;
	        			dir = d;
	        		}
	        	}
	        }
	        
	        //방향 설정 완료, 현재 dir에 저장되어 있음.
	        //push queue
	        Queue<Integer> push = new LinkedList<>();

	        map[rudolPos[0]][rudolPos[1]] = 0;
	        //좌표 갱신
	        rudolPos[0] += dr1[dir];
	        rudolPos[1] += dc1[dir];
	        if(map[rudolPos[0]][rudolPos[1]] != 0) {
	        	int num = map[rudolPos[0]][rudolPos[1]];
	        	push.add(num);
	        	//점수 추가, 기절 표시
	        	santaScore[num] += C;
	        	faint[num] = 2;
	        }
	        map[rudolPos[0]][rudolPos[1]] = -1;
	        
	        int nowR = rudolPos[0] + C*dr1[dir];
	        int nowC = rudolPos[1] + C*dc1[dir];
	        
	        while(!push.isEmpty()) {
	        	//범위 밖이면 탈락
	        	if(!isInside(nowR, nowC, N)) {
	        		//영원히 기절 상태, 위치는 INTEGER.MAXVALUE
	        		//수로 추가
	        		while(!push.isEmpty()) {
	        			int temp = push.poll();
	        			fail++;
	        			santaPos[temp] = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
	        			faint[temp] = -1;
	        		}
	        		break;
	        	}
	        	
	        	int num = map[nowR][nowC];
	        	if(num != 0) push.add(num);
	        	//산타 추가, 좌표 갱신
	        	int santa = push.poll();
	        	map[nowR][nowC] = santa;
	        	santaPos[santa] = new int[] {nowR, nowC};
	        	
	        	//좌표 갱신
	        	nowR += dr1[dir];
	        	nowC += dc1[dir];
	        	
	        	
	        }
	        
	        
	        //이제 산타의 이동 구현하면 됨.
	        //여기에 구현.
	        
	        //기절한 산타 제외 움직일 산타 큐
	        Queue<Integer> santa = new LinkedList<>();
	        for(int i = 1; i >= P; i++) {
	        	if(faint[i] == 0) {
	        		santa.add(i);
	        	} else if(faint[i] > 0) {
	        		faint[i]--;
	        	}
	        }
	        
	        
	        while(!santa.isEmpty()) {
	        	int num = santa.poll();
	        	double temp = findDist(santaPos[num], rudolPos);
	        	
	        	//delta탐색
	        	for(int d = 0; d < 4; d++) {
	        		int nr = santaPos[num][0] + dr2[d];
	        		int nc = santaPos[num][1] + dc2[d];
	        		//이동 가능
	        		if(isInside(nr, nc, N) && temp > findDist(new int[] {nr, nc}, rudolPos)) {
	        			//빈 공간의 경우
	        			if(map[nr][nc] == 0) {
	        				map[santaPos[num][0]][santaPos[num][1]] = 0;
	        				santaPos[num][0] = nr;
	        				santaPos[num][1] = nc;
	        				map[nr][nc] = num;
	        				break;
	        			} else if(map[nr][nc] == -1) {//루돌프와 충돌
	        				push.clear(); //push 초기화
	        				
	        				//산타의 점수 증가, 방향 변화, 위치 이동
	        				santaScore[num] += D;
	        				dir = (d+2)%4; //방향 전환
	        				//현재 탐색 위치
	        				nowR = nr + D*dr2[dir];
	        				nowC = nc + D*dc2[dir];
//
//	        				push.add(num);
//	        				while(!push.isEmpty()) {
//	        		        	//범위 밖이면 탈락
//	        		        	if(!isInside(nowR, nowC, N)) {
//	        		        		//영원히 기절 상태, 위치는 INTEGER.MAXVALUE
//	        		        		//수로 추가
//	        		        		while(!push.isEmpty()) {
//	        		        			temp = push.poll();
//	        		        			fail++;
//	        		        			santaPos[temp] = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
//	        		        			faint[temp] = -1;
//	        		        		}
//	        		        		break;
//	        		        	}
//	        		        	
//	        		        	int num = map[nowR][nowC];
//	        		        	if(num != 0) push.add(num);
//	        		        	//산타 추가, 좌표 갱신
//	        		        	int santa = push.poll();
//	        		        	map[nowR][nowC] = santa;
//	        		        	santaPos[santa] = new int[] {nowR, nowC};
//	        		        	
//	        		        	//좌표 갱신
//	        		        	nowR += dr1[dir];
//	        		        	nowC += dc1[dir];
//	        		        	
//	        		        	
//	        		        }
	        				
	        				
	        			}
	        		}
	        	}
	        	
	        	
	        }
	        
	        
	        
	        
	        
	        
	        
	        turn++;
        }//end of turn
    }
    
    //거리 판별
    public static double findDist(int[] pos, int[] target) {
    	int diffx = pos[0] - target[0];
    	int diffy = pos[1] - target[1];
    	return Math.sqrt((diffx)*(diffx) + (diffy)*(diffy));
    }
    
    //범위 판별
    public static boolean isInside(int r, int c, int N) {
    	if(r >= 0 && r < N && c >= 0 && c < N) {
    		return true;
    	}
    	return false;
    }
}