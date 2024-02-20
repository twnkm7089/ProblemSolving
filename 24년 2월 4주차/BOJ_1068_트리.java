package Feb4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1068_트리 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int root = -1;
		//자식 노드 저장 리스트
		List<Integer>[] child = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			child[i] = new ArrayList<Integer>();
		}
		
		//그래프 정보 저장, parent의 child로 i 추가
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if(parent == -1) {
				root = i;
			} else {
				child[parent].add(i);
			}
		}
		//삭제 노드 입력
		int deleted = Integer.parseInt(br.readLine());
		
		int answer = 0;
		

		//root 삭제 아니면
		if(root != deleted) {	
			//BFS
			LinkedList<Integer> queue = new LinkedList<Integer>();
			queue.add(root);
			while(!queue.isEmpty()) {
				int node = queue.poll();
				
				
				int cnt = 0;
				//leaf size안 쓰는 이유는 삭제 노드 인식 문제
	
				//삭제 노드 아닌 것 탐색
				for(int i = 0; i < child[node].size(); i++) {
					if(child[node].get(i) != deleted) {
						cnt++;
						queue.add(child[node].get(i));
					}
				}
				
				if(cnt == 0) {
					answer++;
				}
			}
		}
		
		//output
		System.out.println(answer);

	}
}
