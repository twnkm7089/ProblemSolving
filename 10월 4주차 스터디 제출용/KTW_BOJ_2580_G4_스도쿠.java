import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KTW_BOJ_2580_G4_스도쿠 {
	//스도쿠판
	static int[][] game;
	static boolean found;
	static List<int[]> blank;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //빈칸 위치 저장 배열
        blank = new ArrayList<>();
        game = new int[9][9];
        
        //input
        for(int i = 0; i < 9; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < 9; j++) {
        		game[i][j] = Integer.parseInt(st.nextToken());
        		if(game[i][j] == 0) {
        			blank.add(new int[] {i, j});
        		}
        	}
        }
        
        //재귀로 탐색 시작
        found = false;
        search(0);
        //결과 출력
        for(int i = 0; i < 9; i++) {
        	for(int j = 0; j < 9; j++) {
        		System.out.print(game[i][j] + " ");
        	}
        	System.out.println();
        }
        
    }//end of main method
    
    //탐색
    public static void search(int idx) {
    	if(found) return; //이미 답 찾았으면 return
    	
    	//모든 칸을 다 채웠으면 찾았다고 표시하고 return
    	if(idx == blank.size()) {
    		found = true;
    		return;
    	}
    	
    	//칸에 채우기
    	int[] now = blank.get(idx);
    	//이 칸에 1 ~ 9까지 채우고 유효성 검사
    	for(int i = 1; i <= 9; i++) {
    		game[now[0]][now[1]] = i;
    		if(isValid(now[0], now[1])) {
    			//통과시 다음칸 검사
    			search(idx+1);
    		}
    		if(found) return; //찾았으면 돌아가
    	}
    	game[now[0]][now[1]] = 0; //못찾았으면 0으로 초기화(중요)
    }
    
    public static boolean isValid(int r, int c) {
    	int[] check = new int[10];
    	//중복만 없으면 된다. 0이 나와도 문제 없음(아직 안채운거니)
    	//가로줄 체크
    	for(int i = 0; i < 9; i++) {
    		check[game[r][i]]++;
    		if(game[r][i] != 0 && check[game[r][i]] >= 2) return false;
    	}
    	//세로줄 체크
    	check = new int[10];
    	for(int i = 0; i < 9; i++) {
    		check[game[i][c]]++;
    		if(game[i][c] != 0 && check[game[i][c]] >= 2) return false;
    	}
    	//박스 체크
    	check = new int[10];
    	int boxR = r/3*3;
    	int boxC = c/3*3;
    	for(int a = boxR; a < boxR+3; a++) {
    		for(int b = boxC; b < boxC+3; b++) {
    			check[game[a][b]]++;
    			if(game[a][b] != 0 && check[game[a][b]] >= 2) return false;
    		}
    	}
    	return true;
    }
    
    
}