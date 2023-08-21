package August_4;

import java.util.Scanner;
/*알고리즘 설명
 * 1. Queue 구현해서 풀었음
 * 2. 수를 모두 enqueue함.
 * 3. rear-front를 계산해 queue에 원소가 하나 남을 때까지 1번 dequeue, 1번 dequeue한 값 다시 enqueue
 * 4. 하나 남으면 남은 값 출력.
 * */
public class KTW_BOJ_2164_S4 {
	//큐 구현
	public static int[] queue = new int[10000000];
	public static int front = -1, rear = -1;
	
	public static void enQueue(int value) {
		queue[++rear] = value;
	}
	
	public static int deQueue() {
		return queue[++front];
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for(int i = 1; i <= N; i++) {//1부터 N까지 enqueue
			enQueue(i);
		}
		
		while((rear - front) > 1) {//queue안의 값이 한 개가 될 때까지
			deQueue();//dequeue 1번
			if(rear - front == 1) {//다시 확인, queue안의 값이 한개면 break
				break;
			}
			//dequeue후 다시 enqueue
			int temp = deQueue();
			enQueue(temp);
		}
		
		System.out.println(deQueue());//결과 출력
	}//end of main method
}//end of class
