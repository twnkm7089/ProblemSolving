package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. dfs 사용
 * 2. 간선 연결 정보를 map에 저장(1:연결 /row: 시작/ col: 도착)
 * 3. 방문 여부 visited로 저장
 * 4. dfs 재귀로 구현
 * 5. visited 배열 분석 중 미방문 노드 있으면 cnt 추가하고 dfs 실행해 연결된 노드 모두 방문
 * 6. 이렇게 연결 요소의 총 개수 구함.
 * */
public class KTW_BOJ_11724_S2 {
	public static int[][] map = new int[1][1];
	public static int[] visited = new int[1];
	public static int cnt = 0;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//N, M 구하기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		//map, visited 재할당
		map = new int[N+1][N+1];//1 : 연결
		visited = new int[N+1];//1:방문완료, 0:미방문
		
		//간선 정보 저장
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//연결 표시
			map[a][b] = 1;
			map[b][a] = 1;
		}
		//요소 탐새 시작
		for(int i = 1; i < N+1; i++) {
			if(visited[i] == 0) {//미방문 노드 발견시
				cnt++;//카운트 추가
				dfs(i, N);//dfs로 연결된 노드 모두 방문
			}
		}
		System.out.println(cnt);//연결 요소 출력
		br.close();
		
	}//end of main method
	
	//dfs method
	public static void dfs(int idx, int N) {
		visited[idx] = 1;//방문 표시
		
		for(int i = 1; i < N+1; i++) {//탐색
			if(map[idx][i] == 1 && visited[i] == 0) {//idx와 연결된 미방문 노드 발견시
				dfs(i, N);//방문
			}
		}
	}
}//end of class
