import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//위상정렬때 썼던 테크닉 응용
public class KTW_BOJ_1005_G3_ACM_Craft {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //input test case
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	//input
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int K = Integer.parseInt(st.nextToken());
        	
        	//걸리는 시간 저장
        	int[] time = new int[N+1];
        	st = new StringTokenizer(br.readLine());
        	for(int i = 1; i <= N; i++) {
        		time[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	
        	//연결리스트 만들기
        	List<Integer>[] order = new ArrayList[N+1];
        	for(int i = 1; i <= N; i++) {
        		order[i] = new ArrayList<>();
        	}
        	//order[i]에는 순서도상 i -> 한 건물들 존재.
        	
        	//들어오는 화살표 개수 : 0개여야 건설 시작 가능.
        	int[] indegree = new int[N+1];
        	
        	//건물 짓는 최소시간 저장 배열
        	int[] minimum = new int[N+1];
        	Arrays.fill(minimum, Integer.MAX_VALUE);
        	
        	//관계 저장
        	for(int i = 0; i < K; i++) {
        		st = new StringTokenizer(br.readLine());
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		//관계, indegree 저장
        		order[x].add(y);
        		indegree[y]++;
        	}
        	
        	
        	//목표 건물 찾기
        	int W = Integer.parseInt(br.readLine());
        	
        	
        	//탐색하면서 현재 건설 가능한 건물 저장
        	//끝나는 시간이 빠른 순서로 보기 위해 PriorityQueue 이용
        	//int[]배열, [0] : 탐색 노드, [1] : 건설 완료 시간
        	PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}    		
        	});
        	
        	for(int i = 1; i <= N; i++) {
        		if(indegree[i] == 0) {
        			minimum[i] = time[i];
        			pq.add(new int[] {i, time[i]});
        		}
        	}
        	
        	
        	
        	
        	//이제 건물 짓기 시작
        	//아직 최소를 못찾은 경우만 탐색
        	if(minimum[W] == Integer.MAX_VALUE) { 		
	        	outer : while(!pq.isEmpty()) {
	        		int[] now = pq.poll();
	        		int value = now[0];
	        		int endTime = now[1];
	        		//fin과 연결된 모든 화살표 없애기
	        		for(int a : order[value]) {
	        			indegree[a]--;
	        			if(indegree[a] == 0) {
	        				//fin이 끝나고 난 뒤 바로 건설 시작, 즉, minimum[fin] + time[a]후 건설 완료
	        				minimum[a] = time[a] + endTime;
	        				//찾던 건물이면 탐색 종료
	        				if(a == W) {
	        					break outer;
	        				}
	        				//아니면 큐에 추가
	        				pq.add(new int[] {a, minimum[a]});
	        			}
	        		}
	        	}//end of search
        	}

        	//output
        	System.out.println(minimum[W]);
        	
        	
        }
    }
}