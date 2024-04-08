package August_4;

import java.util.Scanner;
import java.util.Stack;
/*알고리즘 설명
 * 1. 스택을 이용해 해결
 * 2. 여는 괄호가 나오면 stack에 추가
 * 3. 닫는 괄호가 나왔는데 stack에 아무 값도 없으면 VPS 아님. answer NO로 바꾸고 탐색 종료
 * 4. 탐색 완료 후 stack에 뭐 나오면 닫는 괄호 부족, answer NO
 * */
public class KTW_BOJ_9012_S4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack<>();//stack
		
		int N = sc.nextInt();
		//N개 단어 탐색 시작
		for(int i = 0; i < N; i++) {
			String str = sc.next();//단어 입력
			String answer = "YES";//정답값, 기본 VPS
			for(int j = 0; j < str.length(); j++) {//각 단어별 탐색
				if(str.charAt(j) == '(') {//여는 괄호는 push
					stack.add('(');
				} else if (str.charAt(j) == ')') {//닫는괄호
					if(stack.isEmpty()) {//스택 비어있으면
						answer = "NO";//VPS 아님
						break;//탐색 종료
					} else {//있으면
						stack.pop();//pop
					}
				}
			}//탐색 종료
			if(!stack.empty()) {//탐색 완료 후에도 남아있으면
				answer = "NO";//VPS 아님
				stack.clear();//스택 비우기
			}
			
			System.out.println(answer);//정답 출력
		}//N개 단어 탐색 종료
	}//end of main method
}//end of class
