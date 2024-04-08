import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[][] map;
	static int N, M, P, C, D;
	
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //8���� delta
        int[] dr1 = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dc1 = {0, 1, 1, 1, 0, -1, -1, -1};
        
        //��Ÿ�� ���� 4���� delta
        int[] dr2 = {-1, 0, 1, 0};
        int[] dc2 = {0, 1, 0, -1};
        
        
        //input
        N = sc.nextInt();
        M = sc.nextInt();
        P = sc.nextInt();
        C = sc.nextInt();
        D = sc.nextInt();
        //�絹�� �ʱ� ��ġ
        int[][] map = new int[N][N];
        
        int[] rudolPos = new int[] {sc.nextInt()-1, sc.nextInt()-1};
        //��ġ�� -1�� ǥ��
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
        //��������
        int[] faint = new int[P+1];
        
        
        //�ʱ�ȭ �Ϸ�, ���� �ϼ���� �����ؾ�....
        
        
        int turn = 1;
        int fail = 0;
        while(turn <= M && fail < P) {
	        
	        
	        //�絹�� �̵�
	        //����ư �Ÿ� �Ǻ�
	        double dist = Double.MAX_VALUE;
	        int targetR = -1;
	        int targetC = -1;
	        int santaNum = -1;
	        for(int i = 1; i <= P; i++) {
	        	//�� ��Ÿ �� �絹������ �Ÿ��� Ž��, �Ÿ��� �����ų� �Ÿ��� ���Ƶ� r, c�� ū ��Ÿ�� Ÿ������ ����.
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
	        
	        //��� ���� �Ϸ�, ���� �絹���� �̵�
	        int dir = -1;
	        dist = Double.MAX_VALUE;
	        //����Ž��
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
	        
	        //���� ���� �Ϸ�, ���� dir�� ����Ǿ� ����.
	        //push queue
	        Queue<Integer> push = new LinkedList<>();

	        map[rudolPos[0]][rudolPos[1]] = 0;
	        //��ǥ ����
	        rudolPos[0] += dr1[dir];
	        rudolPos[1] += dc1[dir];
	        if(map[rudolPos[0]][rudolPos[1]] != 0) {
	        	int num = map[rudolPos[0]][rudolPos[1]];
	        	push.add(num);
	        	//���� �߰�, ���� ǥ��
	        	santaScore[num] += C;
	        	faint[num] = 2;
	        }
	        map[rudolPos[0]][rudolPos[1]] = -1;
	        
	        int nowR = rudolPos[0] + C*dr1[dir];
	        int nowC = rudolPos[1] + C*dc1[dir];
	        
	        while(!push.isEmpty()) {
	        	//���� ���̸� Ż��
	        	if(!isInside(nowR, nowC, N)) {
	        		//������ ���� ����, ��ġ�� INTEGER.MAXVALUE
	        		//���� �߰�
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
	        	//��Ÿ �߰�, ��ǥ ����
	        	int santa = push.poll();
	        	map[nowR][nowC] = santa;
	        	santaPos[santa] = new int[] {nowR, nowC};
	        	
	        	//��ǥ ����
	        	nowR += dr1[dir];
	        	nowC += dc1[dir];
	        	
	        	
	        }
	        
	        
	        //���� ��Ÿ�� �̵� �����ϸ� ��.
	        //���⿡ ����.
	        
	        //������ ��Ÿ ���� ������ ��Ÿ ť
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
	        	
	        	//deltaŽ��
	        	for(int d = 0; d < 4; d++) {
	        		int nr = santaPos[num][0] + dr2[d];
	        		int nc = santaPos[num][1] + dc2[d];
	        		//�̵� ����
	        		if(isInside(nr, nc, N) && temp > findDist(new int[] {nr, nc}, rudolPos)) {
	        			//�� ������ ���
	        			if(map[nr][nc] == 0) {
	        				map[santaPos[num][0]][santaPos[num][1]] = 0;
	        				santaPos[num][0] = nr;
	        				santaPos[num][1] = nc;
	        				map[nr][nc] = num;
	        				break;
	        			} else if(map[nr][nc] == -1) {//�絹���� �浹
	        				push.clear(); //push �ʱ�ȭ
	        				
	        				//��Ÿ�� ���� ����, ���� ��ȭ, ��ġ �̵�
	        				santaScore[num] += D;
	        				dir = (d+2)%4; //���� ��ȯ
	        				//���� Ž�� ��ġ
	        				nowR = nr + D*dr2[dir];
	        				nowC = nc + D*dc2[dir];
//
//	        				push.add(num);
//	        				while(!push.isEmpty()) {
//	        		        	//���� ���̸� Ż��
//	        		        	if(!isInside(nowR, nowC, N)) {
//	        		        		//������ ���� ����, ��ġ�� INTEGER.MAXVALUE
//	        		        		//���� �߰�
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
//	        		        	//��Ÿ �߰�, ��ǥ ����
//	        		        	int santa = push.poll();
//	        		        	map[nowR][nowC] = santa;
//	        		        	santaPos[santa] = new int[] {nowR, nowC};
//	        		        	
//	        		        	//��ǥ ����
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
    
    //�Ÿ� �Ǻ�
    public static double findDist(int[] pos, int[] target) {
    	int diffx = pos[0] - target[0];
    	int diffy = pos[1] - target[1];
    	return Math.sqrt((diffx)*(diffx) + (diffy)*(diffy));
    }
    
    //���� �Ǻ�
    public static boolean isInside(int r, int c, int N) {
    	if(r >= 0 && r < N && c >= 0 && c < N) {
    		return true;
    	}
    	return false;
    }
}