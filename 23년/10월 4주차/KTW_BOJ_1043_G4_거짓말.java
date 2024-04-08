import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_BOJ_1043_G4_거짓말 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        //파티 방문 여부
        boolean[] visited = new boolean[M+1];
        //탐색한 사람 배열
        boolean[] searched = new boolean[N+1];
        
        //각 참석자가 참석한 파티 배열
        List<Integer>[] goParty = new ArrayList[N+1];
        for(int i = 1; i <= N; i++) {
        	goParty[i] = new ArrayList<>();
        }
        
        //각 파티의 참석자 배열
        List<Integer>[] p = new ArrayList[M+1];
        for(int i = 1; i <= M; i++) {
        	p[i] = new ArrayList<>();
        }
        
        //진실을 아는 사람
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        List<Integer> truth = new ArrayList<>();
        for(int i = 0; i < T; i++) {
        	truth.add(Integer.parseInt(st.nextToken()));
        }
        
        //파티 참석자와 파티 정보 입력
        for(int i = 1; i <= M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int num = Integer.parseInt(st.nextToken());
        	for(int j = 0; j < num; j++) {
        		int person = Integer.parseInt(st.nextToken());
        		goParty[person].add(i);
        		p[i].add(person);
        	}
        }
        
        
        //진실을 아는 사람이 참석한 파티와 그 파티 참석자 제외
        
        //가서는 안되는 파티 배열
        Queue<Integer> queue = new LinkedList<>();
        //초기화, 진실을 아는 사람이 방문한 파티 정보 저장
        for(int i = 0; i < truth.size(); i++) {
        	//탐색한 사람임 표시
        	int know = truth.get(i);
        	searched[know] = true;
        	for(int j = 0; j < goParty[know].size(); j++) {
        		//진실을 아는 사람이 방문했으나 아직 표시 안된 파티 추가
        		int pNo = goParty[know].get(j);
        		if(!visited[pNo]) {
        			visited[pNo] = true;
        			queue.add(pNo);
        		}
        	}
        }
        
        //해당 파티에 참여했던 또다른 사람이 참석한 파티 방문
        while(!queue.isEmpty()) {
        	int pNo = queue.poll();
        	
        	//해당 파티에 방문한 사람 찾기
        	for(int i = 0; i < p[pNo].size(); i++) {
        		int person = p[pNo].get(i);
        		//아직 탐색 안한 사람이면
        		if(!searched[person]) {
        			searched[person] = true;
        			
        			//이 사람이 방문한 파티 중 탐색 안한 파티 추가
        			for(int j = 0; j < goParty[person].size(); j++) {
        				int nowParty = goParty[person].get(j);
        				if(!visited[nowParty]) {
        					visited[nowParty] = true;
        					queue.add(nowParty);
        				}
        			}
        		}
        	}
        }
        
        
        //참석 안한 파티 개수 구하기
        int cnt = 0;
        for(int i = 1; i <= M; i++) {
        	if(!visited[i]) cnt++;
        }
        
        System.out.println(cnt);
        
    }//end of main method
}