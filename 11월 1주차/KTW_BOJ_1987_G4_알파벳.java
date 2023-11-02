import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//위상정렬때 썼던 테크닉 응용
public class KTW_BOJ_1987_G4_알파벳 {
	static char[][] map;
	static boolean[][] visited;
	static boolean[] alphabet = new boolean[26];
	static int max;
	static int R, C;
	//delta
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        
        for(int i = 0; i < R; i++) {
        	map[i] = br.readLine().toCharArray();
        }
        
        
        //dfs 이용
        max = 0;
        dfs(0, 0, 0);
        
        System.out.println(max);
    }
    
    public static void dfs(int r, int c, int now) {
    	now++; //방문 칸수 증가
    	//최대 갱신
    	max = Math.max(now, max);
    	//방문 표시
    	visited[r][c] = true;
    	alphabet[map[r][c] - 'A'] = true;
    	
    	//다음 칸 방문
    	for(int d = 0; d < 4; d++) {
    		int nr = r + dr[d];
    		int nc = c + dc[d];
    		//map 범위 내, 미방문, 해당 알파벳 미방문시 방문
    		if(isValid(nr,nc) && !visited[nr][nc] && !alphabet[map[nr][nc] - 'A']) {
    			dfs(nr, nc, now);
    		}
    		
    	}
    	
    	//원상 복구
    	visited[r][c] = false;
    	alphabet[map[r][c] - 'A'] = false;
    }
    
    //map범위 내인지 판별하는 메서드
    public static boolean isValid(int nr, int nc) {
    	if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
    		return true;
    	}
    	return false;
    }
    
    
}