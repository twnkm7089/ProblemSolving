import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*알고리즘 설명
 * 1. 선형 큐 직접 구현
 * 2. front, rear 둬서 size, enqueue, dequeue 등 작업 수행
 * 3. StringBuilder, Buffered 등으로 시간 절약
 * */
public class KTW_BOJ_18258_S4_큐2 {
	//큐 구현
	public static int[] queue = new int[10000000];
	public static int front = -1;
	public static int rear = -1;
	
	public static void enqueue(int value) {
		queue[++rear] = value;
	}
	
	public static int isEmpty() {
		if(front == rear) return 1;
		else return 0;
	}
	
	public static int dequeue() {
		if(isEmpty() == 1) {
			return -1;
		} else {
			return queue[++front];
		}
	}
	
	public static int size() {
		return rear - front;
	}
	
	public static int front() {
		if(isEmpty() == 1) {
			return -1;
		} else {
			return queue[front+1];
		}
	}
	
	public static int back() {
		if(isEmpty() == 1) {
			return -1;
		} else {
			return queue[rear];
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder st = new StringBuilder();
		
		
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {//명령어 구분 및 실행
			String str = br.readLine();
			if(str.split(" ").length > 1) {
				enqueue(Integer.parseInt(str.split(" ")[1]));
			} else {
				if(str.equals("pop")) {
					st.append(dequeue() + "\n");
				} else if(str.equals("size")) {
					st.append(size() + "\n");
				} else if(str.equals("empty")) {
					st.append(isEmpty() + "\n");
				} else if(str.equals("front")) {
					st.append(front() + "\n");
				} else if(str.equals("back")) {
					st.append(back() + "\n");
				}
			}
		}
		//결과 출력
		bw.write(st.toString());
		bw.flush();
		bw.close();
		br.close();
		
		
		
	}
	
}
