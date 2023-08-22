package August_4;

import java.util.Scanner;
import java.util.Stack;
/*알고리즘 설명
 * 1. 가장 최근에 쓴 수를 지우게 한다 -> stack
 * 2. 0이 아닌 수는 stack에 넣고, 0이 나오면 pop한다.
 * 3. 모든 입력이 끝난 후 stack에 남아 있는 값을 모두 pop하면서 sum에 더한다.
 * 4. 정답 생성
 * */
public class KTW_BOJ_10773_S4 {
	public static void main(String[] args){
		Stack<Integer> stack = new Stack<>();//스택
		
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		for(int i = 0; i < K; i++) {//입력 받기
			int price = sc.nextInt();
			if(price == 0 && !stack.isEmpty()) {//0이면서 stack이 안 비어있음
				stack.pop();//pop
			} else {//0이 아닌 수
				stack.push(price);//push
			}
		}
		int sum = 0;
		while(!stack.isEmpty()) {//stack에 있던 값 모조리 pop
			sum += stack.pop();//더해주기
		}
		System.out.println(sum);//정답 출력
		
	}//end of main method
}//end of class
