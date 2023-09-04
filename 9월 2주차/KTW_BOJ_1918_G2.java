package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/*알고리즘 설명
 * 1. 후위 표기식 제조
 * 2. 스택에 연산자 쌓기. 여는 괄호는 무조건 넣는다.
 * 3. 스택 내 우선 순위는 '*,/' > '+,-' > '('
 * 4. 현재 문자가 일반 문자면 결과 문자열에 바로 추가
 * 5. 연산자의 경우 이 연산자와 같거나 우선순위가 높은 연산자를 스택에서 모두 빼고 집어넣음. 뺀 것은 결과 문자열에 추가
 * 6. 닫는 괄호는 여는 괄호 나올때까지 모두 빼서 결과 문자열에 넣음. 그 후, 좌괄호도 빼서 제거.
 * 7. 연산 종료후 스택에 남은 것 모두 추가.
 * */
public class KTW_BOJ_1918_G2 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		String str = br.readLine();
		
		
		Stack<Character> stack = new Stack<>();
		String result = "";
		for(int i = 0; i < str.length(); i++) {
			char temp = str.charAt(i);
			if(temp == '(') {//여는 괄호는 그냥 스택에 추가
				stack.push(temp);
			} else if(temp == ')') {//닫는건 여는 괄호 나올 때까지 pop해서 결과 문자열에 추가
				while(!(stack.peek()=='(')) {
					result += stack.pop();
				}
				//여는 괄호 제거
				stack.pop();
			} else if(temp == '*' || temp == '/') {//*, /는 맨 위가 *, /만 아니면 됨. 그때까지 뽑고 넣기
				while(!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
					result += stack.pop();
				}
				stack.push(temp);//추가
			} else if(temp == '+' || temp == '-') {//+, -는 비어있거나 맨 위가 (인 경우만 추가 가능. 그렇게 만들기
				while(!stack.isEmpty() && stack.peek() != '(') {
					result += stack.pop();
				}
				stack.push(temp);//추가
			} else {//연산자 아니면 결과 문자열에 그냥 추가
				result += temp;
			}
		}
		
		while(!stack.isEmpty()) {//스택에 남은 것 제거해 더해주기
			result += stack.pop();
		}
		//출력
		System.out.println(result);
		
	}//end of main method
	
	
}//end of class
