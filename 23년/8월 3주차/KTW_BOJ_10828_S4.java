import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/*알고리즘 설명
 * 1. 스택 구현은 기본적 방법으로...잊었으면 복습
 * 2. 명령어는 앞의 두글자 판별로 파악후 행동
 * 3. 버!!!퍼!!!드!!!
 * */
public class KTW_BOJ_10828_S4 {
	public static int[] stack = new int[10000];
	public static int top = -1;
	
	public static void push(int value) {
		stack[++top] = value;
	}
	
	public static int pop() {
		if(isEmpty() == 1) {
			return -1;
		}
		return stack[top--];
	}
	
	public static int size() {
		return top+1;
	}
	
	public static int isEmpty() {
		if(top==-1) return 1;
		else return 0;
	}
	
	public static int top() {
		if(isEmpty() == 1) {
			return -1;
		}
		return stack[top];
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//명령어 숫자 입력
		for(int i = 0; i < N; i++) {
			String command = br.readLine();
			String cmd = command.substring(0,2);//명령어 앞 2글자 추출
			if(cmd.equals("pu")) {//push
				push(Integer.parseInt(command.split(" ")[1]));//뒤의 숫자를 push
			} else if(cmd.equals("po")) {//pop
				System.out.println(pop());
			} else if(cmd.equals("si")) {//size
				System.out.println(size());
			} else if(cmd.equals("em")) {//empty
				System.out.println(isEmpty());
			} else {//top
				System.out.println(top());
			}
		}
		br.close();//닫아두자^^
		
		
	}
}
