import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KTW_BOJ_10845_S4 {
	//선형큐 구현
	public static int[] queue = new int[100000];
	public static int front = -1, rear = -1;
	
	public static void push(int value) {
		queue[++rear] = value;
	}
	
	public static int pop() {
		if(empty() == 1) {
			return -1;
		} else {
			return queue[++front];
		}
	}
	
	public static int size() {
		return rear - front;
	}
	
	public static int empty() {
		if(front == rear) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static int front() {
		if(empty() == 1) {
			return -1;
		} else {
			return queue[front + 1];
		}
	}
	
	public static int back() {
		if(empty() == 1) {
			return -1;
		} else {
			return queue[rear];
		}
	}
	
	public static void main(String[] args) throws Exception {
		/*알고리즘
		 * 1. 선형큐랑 메서드 구현해서 풀었습니다.
		 * 2. 입력은 버퍼드로
		 * 3. 받은 명령어의 앞 2자리로 종류 구별해 메서드 쓰게 했습니다.
		 * */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N =Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String order = br.readLine();
			String inst = order.substring(0, 2);//명령어 앞 두자리
			if(inst.equals("pu")) {//명령어 처리
				int val = Integer.parseInt(order.split(" ")[1]);//뒤의 숫자 넣기
				push(val);
			} else if(inst.equals("po")) {
				System.out.println(pop());
			} else if(inst.equals("si")) {
				System.out.println(size());
			} else if(inst.equals("em")) {
				System.out.println(empty());
			} else if(inst.equals("fr")) {
				System.out.println(front());
			} else {
				System.out.println(back());
			}
		}
		
		br.close();//버퍼 닫기.
	}
	
	
}

