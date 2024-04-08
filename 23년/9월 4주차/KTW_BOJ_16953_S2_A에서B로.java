import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 큐에 있는 수를 빼면서 BFS
 * 2. 2곱한 수, 1 추가한 수 구해서 비교 후 맞으면 탐색 종료.
 * 3. 아닌 경우 두 선택지 모두 수가 증가하기만 하므로 B보다 작으면 큐에 다시 넣기
 * 4. 큐 빌 때까지 반복, 없으면 -1 그대로.
 * 5. long으로 해야 오류 안남, 10^9 근처에서 1 추가하면 범위 초과.
 * */
public class KTW_BOJ_16953_S2_A에서B로 {
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		long A = (long)a;
		long B = (long)b;
		
		if(A == B) {//비교
			System.out.println(1);
		} else {//탐색
			//answer 초기화
			int answer = -1;
			Queue<Long> queue = new LinkedList<>();
			queue.add(A);// 큐 생성 및 추가
			int cnt = 1;//카운트 초기화
			outer : while(!queue.isEmpty()) {//큐 빌때까지
				cnt++;//각 계층별 1 증가
				
				int N = queue.size();//큐 사이즈만큼 반복
				
				for(int i = 0; i < N; i++) {
					//데이터 꺼내 연산
					long data = queue.remove();

					long doub = data * 2;
					long addone = data*10 + 1;
					
					if(doub == B || addone == B) {//일치시 탐색 종료
						answer = cnt;
						break outer;
					}
					//아니면서 B보다 작으면 다시 큐로
					if(doub < B) {
						queue.add(doub);
					}
					if(addone < B) {
						queue.add(addone);
					}
				}
			}
			
			//결과 출력
			System.out.println(answer);
		}
		
	}//end of main method
}//end of class
