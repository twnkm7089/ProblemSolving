package August_4;

import java.util.Scanner;
import java.util.Stack;
/*알고리즘 설명
 * 1. 괄호 일치 문제는 스택으로 풀면 상당히 좋다.
 * 2. 여는 괄호가 나오면 stack에 push.
 * 3. 닫는 괄호가 나왔는데 stack이 비어 있으면 answer = "no", pop을 한 값이 종류가 다르면 "no"
 * 4. 탐색 완료후 값이 남아 있어도 "no". 스택 비우자.
 * 5. 결과 출력
 * */
public class KTW_BOJ_4949_S4 {
	public static void main(String[] args){
		Stack<Character> stack = new Stack<>();//스택
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();//input
		while(!str.equals(".")) {//input iteration
			String answer = "yes";//답(기본은 yes)
			for(int i = 0; i < str.length(); i++) {//탐색 시작
				if(str.charAt(i) == '(' || str.charAt(i) == '[') {//여는 괄호 push
					stack.push(str.charAt(i));
				} else if(str.charAt(i) == ')') {//닫는 소괄호
					if(stack.isEmpty()) {//스택 비어 있으면 no
						answer = "no";
						break;
					} else {
						char temp = stack.pop();
						if(temp == '[') {//pop했는데 괄호 종류 달라도 no
							answer = "no";
							break;
						}
					}
				} else if(str.charAt(i) == ']') {//닫는 대괄호
					if(stack.isEmpty()) {//스택 비어 있으면 no
						answer = "no";
						break;
					} else {
						char temp = stack.pop();
						if(temp == '(') {//pop했는데 괄호 종류 달라도 no
							answer = "no";
							break;
						}
					}
				}
				
			}//탐색 종료
			if(!stack.isEmpty()) {//탐색 종료 후 stack이 안 비어 있어도 no
				answer = "no";
				stack.clear();
			}
			
			System.out.println(answer);//정답 출력
			
			
			
			str = sc.nextLine();
		}//end of input
		
	}//end of main method
}//end of class
