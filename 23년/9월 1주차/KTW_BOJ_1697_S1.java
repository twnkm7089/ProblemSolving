package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. BFS 이용(알고리즘 분류 보고 알았음)
 * 2. N이 K이상인 경우 -1 반복밖에 답이 없으니 이걸로 끝.
 * 3. N이 K보다 작은 경우가 중요. 우선 -1, +1, *2한 수 중 K 만족하는지 확인.
 * 4. 없으면 이 수들을 모두 임시큐에 집어넣음. 이 때, visited 배열을 만들어 방문을 한 경우나 0~K+1 범위 초과하는 수는 추가 안함.
 * 5. 큐가 빌 때까지 반복한 후 다 썼으면 이제 temp_queue에 있던 걸 queue로 옮김.
 * 6. 수 찾을 때까지 반복
 * */

public class KTW_BOJ_1697_S1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int min = Integer.MAX_VALUE;
		if(N >= K) {//N이 K 이상인 경우는 마이너스 반복만이 답이다.
			min = N-K;
		} else {//N이 K보다 작은 경우
			int cnt = 0;//횟수 세기
			Queue<Integer> queue = new LinkedList<>();
			Queue<Integer> temp_queue = new LinkedList<>();
			queue.add(N);
			int[] visited = new int[K+2];//방문 배열
			outer : while(true) {//반복
				cnt++;//카운트 세기
				//현재 큐에 든 것 확인, 그 후 temp_queue에 임시 저장
				while(!queue.isEmpty()) {
					//큐에 든거 꺼내 방문 배열 확인 + -1, +1, *2 유효성 평가
					int data = queue.remove();
					visited[data] = 1;
					int minus = data - 1;
					int plus = data + 1;
					int twice = data * 2;
					//K와 일치한다면 해당 값이 답
					if(minus == K || plus == K || twice == K) {
						min = cnt;
						break outer;//탐색 종료
					} else {//아닌 경우
						//temp_queue에 임시 추가, queue에 추가하면 queue 내부 다 확인한 뒤에 넘어가버려 문제 발생
						if(minus >= 0 && visited[minus] == 0)//minus가 0이상이며 미방문이면 큐에 추가
							temp_queue.add(minus);
						if(visited[plus] == 0)//plus 미방문이면 추가, plus가 범위 넘어가는 경우는 K+1 밖에 없는데, 이 경우는 minus값이 K라 위에서 걸러짐.
							temp_queue.add(plus);
						if(twice <= K+1 && visited[twice] == 0)//twice가 K+1 이하이며 미방문이면 큐 추가
							temp_queue.add(twice);
					}
				}
				//data 옮기기
				while(!temp_queue.isEmpty()) {
					queue.add(temp_queue.remove());
				}
			}
		}
		//출력
		System.out.println(min);
	}//end of main method
	
}//end of class
