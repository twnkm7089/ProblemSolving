package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*알고리즘 설명
 * 1. BFS 이용
 * 2. map에 간선 정보 저장, 연결이 있는 경우 1이라고 표시
 * 3. visited 배열로 방문 여부 저장.
 * 4. 큐에 1을 넣어 초기화, 방문 여부도 변경.
 * 5. 큐가 빌 때까지 큐에서 값 빼낸 후 map 탐색하며 연결된 노드 모두 queue에 담고 방문했다고 함. 그리고 cnt 추가
 * 6. 큐가 다 비면 cnt값 출력
 * */

public class KTW_BOJ_2606_S3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int V = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		
		int[][] map = new int[V+1][V+1];//1 : 연결
		int[] visited = new int[V+1];//1:방문완료, 0:미방문
		
		//간선 정보 저장
		for(int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//연결 표시
			map[a][b] = 1;
			map[b][a] = 1;
		}
		//큐 설정 및 초기화
		Queue<Integer> queue = new LinkedList<>();
		visited[1] = 1;
		queue.add(1);
		
		int cnt = 0;
		while(!queue.isEmpty()) {//큐가 빌 때까지
			int data = queue.remove();//큐에서 데이터 빼내
			for(int i = 1; i < V+1; i++) {//탐색
				if(map[data][i] == 1 && visited[i] == 0) {//연결 + 미방문이면 방문하자
					visited[i] = 1;//방문여부 변경
					queue.add(i);//큐에 추가
					cnt++;//개수 세기
				}
			}
		}
		
		System.out.println(cnt);//출력
		
		
		br.close();
		
	}//end of main method
}//end of class
