import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 재귀로 구현
 * 2. 코드 참조
 * */
public class KTW_BOJ_12100_G2_2048Easy {
	static int N;
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		N = Integer.parseInt(br.readLine());
		//map input
		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//최대 초기화
		max = 0;
		//함수 가동
		game(map, 0);
		//결과 출력
		System.out.println(max);
	}//end of main method
	
	//board를 매개변수로 받아 복원 쉽게 만들기
	//times는 누적 이동 횟수, 5회가 최대
	public static void game(int[][] board, int times) {
		//5번 이동 완료
		if(times == 5) {
			//배열에서 최대값 찾아 갱신
			int tempMax = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					tempMax = Math.max(board[i][j], tempMax);
				}
			}
			
			
			max = Math.max(tempMax, max);
			return;
		}
		
		//최대 검사 후 백트래킹(현재 최대에서 더 해도 갱신 불가)
		int tempMax = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				tempMax = Math.max(board[i][j], tempMax);
			}
		}
		//최대에 비트마스킹 이용해서 앞으로 5-times번 더 해도 최대값보다 작으면 갱신 안함.
		if((tempMax << (5-times)) < max) return; 
		
		
		//아래는 모두 스와이프
		//0이 아닌 지역은 stack에 넣는다.
		//만약 직전에 합친게 없고 같은 숫자가 나왔다면 합쳐준다.
		//한 줄이 다 끝나면 스택에 들어있는 것을 순서대로 추가 후 재귀함수 가동
		
		
		//위로 스와이프
		Stack<Integer> stack = new Stack<>();
		int[][] temp = new int[N][N];
		for(int j = 0; j < N; j++) { //각 열별 분석
			//아까 합쳐졌는지 여부 검사하는 구문
			boolean flag = false;
			//각 행 순회
			for(int i = 0; i < N; i++) {
				if(board[i][j] != 0) {
					if(!stack.isEmpty() && stack.peek()==board[i][j] && flag == false) {
						//합쳐짐 표시
						stack.pop();
						stack.push(board[i][j]*2);
						flag = true;
					} else {
						stack.push(board[i][j]);
						flag = false;
					}
				}
			}
			
			//넣었으면 이제 빼면서 반영
			int size = stack.size();
			for(int i = size - 1; i >= 0; i--) {
				temp[i][j] = stack.pop();
			}
		}
		game(temp, times+1);
		
		
		//아래로 스와이프
		stack.clear();
		temp = new int[N][N];
		for(int j = 0; j < N; j++) { //각 열별 분석
			//아까 합쳐졌는지 여부 검사하는 구문
			boolean flag = false;
			//각 행 순회
			for(int i = N-1; i >= 0; i--) {
				if(board[i][j] != 0) {
					if(!stack.isEmpty() && stack.peek()==board[i][j] && flag == false) {
						//합쳐짐 표시
						stack.pop();
						stack.push(board[i][j]*2);
						flag = true;
					} else {
						stack.push(board[i][j]);
						flag = false;
					}
				}
			}
			
			//넣었으면 이제 빼면서 반영
			int size = stack.size();
			for(int i = N-size; i < N; i++) {
				temp[i][j] = stack.pop();
			}
		}
		game(temp, times+1);
		
		
		//왼쪽으로 스와이프
		stack.clear();
		temp = new int[N][N];
		for(int i = 0; i < N; i++) { //각 열별 분석
			//아까 합쳐졌는지 여부 검사하는 구문
			boolean flag = false;
			//각 행 순회
			for(int j = 0; j < N; j++) {
				if(board[i][j] != 0) {
					if(!stack.isEmpty() && stack.peek()==board[i][j] && flag == false) {
						//합쳐짐 표시
						stack.pop();
						stack.push(board[i][j]*2);
						flag = true;
					} else {
						stack.push(board[i][j]);
						flag = false;
					}
				}
			}
			
			//넣었으면 이제 빼면서 반영
			int size = stack.size();
			for(int j = size-1; j >= 0; j--) {
				temp[i][j] = stack.pop();
			}
		}
		game(temp, times+1);
		
		//오른쪽으로 스와이프
		stack.clear();
		temp = new int[N][N];
		for(int i = 0; i < N; i++) { //각 열별 분석
			//아까 합쳐졌는지 여부 검사하는 구문
			boolean flag = false;
			//각 행 순회
			for(int j = N-1; j >= 0; j--) {
				if(board[i][j] != 0) {
					if(!stack.isEmpty() && stack.peek()==board[i][j] && flag == false) {
						//합쳐짐 표시
						stack.pop();
						stack.push(board[i][j]*2);
						flag = true;
					} else {
						stack.push(board[i][j]);
						flag = false;
					}
				}
			}
			
			//넣었으면 이제 빼면서 반영
			int size = stack.size();
			for(int j = N-size; j < N; j++) {
				temp[i][j] = stack.pop();
			}
		}
		game(temp, times+1);
	}
	
	
}//end of class
