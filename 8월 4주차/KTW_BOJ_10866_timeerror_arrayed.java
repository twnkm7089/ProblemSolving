package August_4;

import java.util.Scanner;

public class KTW_BOJ_10866_timeerror_arrayed {
	public static int[] deque = new int[10000000];
	public static int front = -1, back = -1;
	
	public static int empty() {
		if(front == back) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static boolean full() {
		return back == deque.length - 1 ;
	}
	
	public static void push_front(int value) {
		if(full()) {
			System.out.println("full");
			return;
		}
		
		for(int i = back; i > front; i--) {
			deque[i+1] = deque[i];
		}
		back++;
		deque[front+1] = value;
	}
	
	public static void push_back(int value) {
		if(full()) {
			System.out.println("full");
			return;
		}
		
		deque[++back] = value;
	}
	
	public static int pop_front() {
		if(empty()==1) {
			return -1;
		}
		return deque[++front];
	}
	
	public static int pop_back() {
		if(empty()==1) {
			return -1;
		}
		return deque[back--];
	}
	
	public static int size() {
		return back - front;
	}
	
	public static int fPeek() {
		if(empty()==1) {
			return -1;
		}
		return deque[front + 1];
	}
	
	public static int bPeek() {
		if(empty()==1) {
			return -1;
		}
		return deque[back];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i = 0; i < N; i++) {
			String inst = sc.next();
			if(inst.equals("push_back")) {
				int val = sc.nextInt();
				push_back(val);
			} else if (inst.equals("push_front")) {
				int val = sc.nextInt();
				push_front(val);
			} else if (inst.equals("pop_front")) {
				System.out.println(pop_front());
			} else if (inst.equals("pop_back")) {
				System.out.println(pop_back());
			} else if (inst.equals("front")) {
				System.out.println(fPeek());
			} else if (inst.equals("back")) {
				System.out.println(bPeek());
			} else if (inst.equals("size")) {
				System.out.println(size());
			} else {
				System.out.println(empty());
			}
		}
		
	}//end of main method
}//end of class
