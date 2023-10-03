package Sept2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*알고리즘 설명
 * 1. BFS 이용
 * 2. 지금까지의 누적 명령어와 현재 번호를 저장하는 class인 Data 생성
 * 3. 연산을 수행하는 함수 doD,S,L,R 생성
 * 4. while문을 돌리며 queue에서 꺼낸 Data객체의 number를 보며 D,S,L,R로 분기
 * 5. 수와 비교한 후 command를 추가하고 다시 넣어주기 반복.
 * 6. 메모리 초과 방지 위해 visited 마련해 중복 제거
 * */

public class KTW_BOJ_9019_G4_DSLR {
	//Data class 정의
	static class Data{
		int number;//숫자
		String command;//명령어
		
		public Data() {
			
		}
		
		public Data(int number,String command) {
			this.number = number;
			this.command = command;
		}
	}
	//D,S,L,R 실행 함수
	public static int doD(int num) {
		//2배로 곱하고 천의 자리까지만
		num = num *  2 % 10000;
		return num;
	}
	
	public static int doS(int num) {
		//하나 빼기
		num -= 1;
		if(num<0) num = 9999;
		return num;
	}
	
	public static int doL(int num) {
		//좌로 간 수
		int d1 = num/1000;
		return (num*10%10000) + d1;
	}
	
	public static int doR(int num) {
		//우로 간 수
		int d4 = num%10;
		return (num/10)+(1000*d4);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());//test case input
		
		for(int tc = 1; tc <= T; tc++) {
			//input
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			//queue추가
			Queue<Data> queue = new LinkedList<>();
			queue.add(new Data(A,""));
			//일단 비교
			if(A == B) {
				System.out.println("");
				continue;
			}
			//방문 배열(중복 방지)
			int[] visited = new int[10000];//0:not visited, 1:visited
			visited[A] = 1;
			//탐색 실시
			outer : while(true) {
				//사이즈 구하고 tree의 각 level별로 분기시킴(지금 생각하면 불필요)
				int size = queue.size();
				for(int i = 0; i < size; i++) {
					//데이터 추출
					Data d = queue.remove();
					int num = d.number;
					String com = d.command;
					//D,S,L,R 적용 결과 획득
					int numD = doD(num);
					int numS = doS(num);
					int numL = doL(num);
					int numR = doR(num);
					//비교(일치 시 출력 후 탐색 종료)
					if(numD == B) {
						System.out.println(com+"D");
						break outer;
					} else if(numS == B) {
						System.out.println(com+"S");
						break outer;
					} else if(numL == B) {
						System.out.println(com+"L");
						break outer;
					} else if(numR == B) {
						System.out.println(com+"R");
						break outer;
					}
					//큐에 추가(만약 미방문 숫자일 시 추가)
					if(visited[numD] == 0) {
						visited[numD] = 1;
						queue.add(new Data(numD,com+"D"));
					}
					
					if(visited[numS] == 0) {
						visited[numS] = 1;
						queue.add(new Data(numS,com+"S"));
					}
					
					if(visited[numL] == 0) {
						visited[numL] = 1;
						queue.add(new Data(numL,com+"L"));
					}
					
					if(visited[numR] == 0) {
						visited[numR] = 1;
						queue.add(new Data(numR,com+"R"));
					}
				}
			}
			
		}
		
	}
}
