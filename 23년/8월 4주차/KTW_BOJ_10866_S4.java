package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class KTW_BOJ_10866_S4 {
	/*알고리즘 설명
	 * 1. 링크드 리스트 이용해서 구현(배열은 시간 초과 나옴)
	 * 2. 주요 메서드 구현
	 * 3. 버퍼드리더로 줄 단위로 읽어오고, 그에 해당하는 명령 수행.
	 * */
	public static LinkedList<Integer> deque = new LinkedList<>();
	
	public static int empty() {
		if(deque.isEmpty()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static void push_front(int value) {
		deque.addFirst(value);
	}
	
	public static void push_back(int value) {
		deque.addLast(value);
	}
	
	public static int pop_front() {
		if(empty()==1) {
			return -1;
		}
		return deque.remove();
	}
	
	public static int pop_back() {
		if(empty()==1) {
			return -1;
		}
		return deque.remove(deque.size()-1);
	}
	
	public static int size() {
		return deque.size();
	}
	
	public static int fPeek() {
		if(empty()==1) {
			return -1;
		}
		return deque.peekFirst();
	}
	
	public static int bPeek() {
		if(empty()==1) {
			return -1;
		}
		return deque.peekLast();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());//명령어 개수
		for(int i = 0; i < N; i++) {
			String inst = br.readLine();//명령어 읽기.
			//명령어에 따른 처리
			if(inst.contains("push_back")) {
				int val = Integer.parseInt(inst.split(" ")[1]);
				push_back(val);
			} else if (inst.contains("push_front")) {
				int val = Integer.parseInt(inst.split(" ")[1]);
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
