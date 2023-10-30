import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KTW_BOJ_9663_G4_NQueen {
	static boolean[][] map;
	static int ans;
	static int[] dc = {-1, 0, 1};
	static int N;
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //퀸은 한 줄에 한개씩만 놓을 수 있다.
        //위에서부터 놓아가면서 유효성 검사
        //맨 아랫줄을 채우면 end
        
        //map, true면 놓여 있는 것
        map = new boolean[N][N];
        ans = 0;
        
        search(0);
        
        System.out.println(ans);
    }//end of main method
    
    public static void search(int r) {
    	//다 찾았으면 값 추가
    	if(r == N) {
    		ans++;
    		return;
    	}

    	//이번 행에 대해 조사
    	for(int c = 0; c < N; c++) {
    		//[r][c]에는 놓을 수 있을까? 위에서부터 놓으니 위쪽 분석
    		boolean flag = true;
    		outer : for(int d = 0; d < 3; d++) {
    			for(int m = 1; m < N; m++) {
    				int nr = r - m;
    				int nc = c + m*dc[d];
    				if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
    					if(map[nr][nc]) {
    						//놓여 있으면 여기는 놓을 수 없다.
    						flag = false;
    						break outer;
    					}
    				} else {
    					//범위 초과면 다음 방향 계산
    					break;
    				}
    			}
    		}
    		
    		//놓을 수 있으면
    		if(flag) {
    			//놓고 다음 행 탐색
    			map[r][c] = true;
    			search(r+1);
    			//돌아왔으면 복원
    			map[r][c] = false;
    		}
    	}
    }
    
    
}