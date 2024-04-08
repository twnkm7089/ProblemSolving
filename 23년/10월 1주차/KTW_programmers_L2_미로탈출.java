import java.util.*;
/*알고리즘 설명
 * 1. BFS 이용
 * 2. 레버 위치까지 간 후 exit 위치를 향해 감.
 * */
public class KTW_programmers_L2_미로탈출 {
	 public int solution(String[] maps) {
	        int answer = 0;
	        //일단 레버를 당기자.
	        
	        int N = maps.length;
	        int M = maps[0].length();
	        
	        //시작 위치 찾기
	        int startR = -1, startC = -1;
	        outer : for(int i = 0; i < N; i++){
	            for(int j = 0; j < M; j++){
	                if(maps[i].charAt(j) == 'S'){
	                    startR = i;
	                    startC = j;
	                    break outer;
	                }
	            }
	        }
	        
	        //bfs 이용 레버까지 가기
	        Queue<int[]> queue = new LinkedList<>();
	        queue.add(new int[]{startR, startC});
	        boolean[][] visited = new boolean[N][M];
	        visited[startR][startC] = true;
	        
	        //delta
	        int[] dr = {-1, 1, 0, 0};
	        int[] dc = {0, 0, -1, 1};
	        
	        
	        int cnt = 0;
	        outer2 : while(!queue.isEmpty()){
	            int size = queue.size();
	            for(int i = 0; i < size; i++){
	                int[] temp = queue.poll();
	                int r = temp[0];
	                int c = temp[1];
	                
	                if(maps[r].charAt(c) == 'L'){
	                    answer += cnt;
	                    queue.clear();
	                    //레버부터 시작이니 start위치 변경
	                    startR = r;
	                    startC = c;
	                    break outer2;
	                }
	                
	                for(int d = 0; d < 4; d++){
	                    int nr = r + dr[d];
	                    int nc = c + dc[d];
	                    if(nr >= 0 && nr < N && nc >= 0 && nc < M && maps[nr].charAt(nc) != 'X' && !visited[nr][nc]){
	                        queue.add(new int[]{nr, nc});
	                        visited[nr][nc] = true;
	                    }
	                }
	            }//end of one turn
	            
	            cnt++;
	        }
	        //레버 도달 못했으면 -1 반환
	        if(answer == 0){
	            return -1;
	        }
	        
	        //이제 레버로부터 exit까지
	        int check = answer; //현재 answer저장, 탐색 종료 후에도 갱신 안되었으면 불가능
	        
	        queue.add(new int[]{startR, startC});
	        visited = new boolean[N][M];
	        visited[startR][startC] = true;
	        
	        cnt = 0;
	        outer3 : while(!queue.isEmpty()){
	            int size = queue.size();
	            for(int i = 0; i < size; i++){
	                int[] temp = queue.poll();
	                int r = temp[0];
	                int c = temp[1];
	                
	                if(maps[r].charAt(c) == 'E'){
	                    answer += cnt;
	                    queue.clear();
	                    break outer3;
	                }
	                
	                for(int d = 0; d < 4; d++){
	                    int nr = r + dr[d];
	                    int nc = c + dc[d];
	                    if(nr >= 0 && nr < N && nc >= 0 && nc < M && maps[nr].charAt(nc) != 'X' && !visited[nr][nc]){
	                        queue.add(new int[]{nr, nc});
	                        visited[nr][nc] = true;
	                    }
	                }
	            }//end of one turn
	            
	            cnt++;
	        }
	        
	        
	        if(answer == check){
	            return -1;
	        }
	        
	        return answer;
	    }
}
