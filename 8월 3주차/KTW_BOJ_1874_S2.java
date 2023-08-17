import java.util.Scanner;
import java.util.Stack;

public class KTW_BOJ_1874_S2 {
	/*알고리즘 설명
	 * 1. 스택에는 1부터 n까지 push된다.
	 * 2. 목표 숫자가 stack의 top에 든 숫자보다 크면 push를 한다.
	 * 3. 목표 숫자가 stack의 top에 든 숫자와 일치하면 pop을 한다. 그 후 다음 목표 숫자로 넘어간다.
	 * 4. 목표 숫자가 stack의 top에 든 숫자보다 작으면 stack이 오름차순으로 들어가는 이상 해당 수열은 구현 불가능하다. NO를 출력하게 한다.
	 * */
	public static void main(String[] args) {
		//input
		Scanner sc = new Scanner(System.in);
		Stack<Integer> stack = new Stack<>();//스택 선언
		
		
		int n = sc.nextInt();//숫자 입력
		char[] record = new char[2*n];//push, pop 기록 입력, push n번, pop n번 도합 2*n번이니 그만큼 할당
		int record_idx = 0;//기록할 index
		int num = 1;//stack에 넣을 숫자, 1부터 시작
		int[] target = new int[n];//목표 숫자 배열
		for(int i = 0; i < n; i++) {//목표 숫자 입력
			target[i] = sc.nextInt();
		}
		int target_idx = 0;//찾을 목표 숫자의 index, 0부터 시작
		
		
		while(true) {
			if(stack.isEmpty() || stack.peek() < target[target_idx]) {//스택이 비어 있거나 목표 숫자가 현재 top의 숫자보다 큼.
				stack.push(num++);//push를 해주며 다음에 넣어줄 숫자(num) 1 증가
				record[record_idx++] = '+';//기록
			} else if(stack.peek() == target[target_idx]) {//목표 숫자 발견
				stack.pop();//pop해줌
				record[record_idx++] = '-';//기록
				target_idx++;//다음 목표 숫자로 넘어감
				if(target_idx == n) {//모든 숫자를 탐색했다면
					break;//빠져나감
				}
			} else {//불가능한 수열(목표 숫자가 현재 top의 숫자보다 작음)
				record_idx = -1;//불가능하다는 표시
				break;//빠져나감
			}
		}
		
		if(record_idx != -1) {//불가능하지 않은 수열
			for(char c : record) {//기록 출력
				System.out.println(c);
			}
		} else {//불가능한 수열
			System.out.println("NO");//결과 출력
		}
		
	}
}
