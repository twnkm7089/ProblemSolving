package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*알고리즘 설명
 * 1. 계산기 문제 응용. 스택을 이용.
 * 2. 식의 덧셈 부분에 모두 괄호를 치면 최소 완성. 고로, + > - 의 우선순위 가지는 계산기 구현
 * 3. 계산식을 문자열로 받고, 임시 문자열에 숫자를 추가하다 +,-,연산식 끝이 나오면 nums stack에 해당 임시 문자열 변환한 정수 push해 parsing 구현.
 * 4. +, -가 나오면 연산, -는 연산 우선순위 최하위니 ch stack빌 때까지 pop하면서 연산 수행.
 * 5. +가 나오면 -가 최상단에 나오거나 stack이 빌 때까지 pop하며 연산 수행.
 * 6. 마치고 ch에 남은 연산자로 연산 수행. 결과를 출력.
 * */
public class KTW_BOJ_1541_S2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		//후위표기식 이용 연산 응용, 스택 사용
		Stack<Integer> nums = new Stack<>();//숫자 저장 스택
		Stack<Character> ch = new Stack<>();//연산자 저장 스택
		String temp = "";//임시 문자열
		for(int i = 0; i < str.length(); i++) {//탐색
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {//숫자 나오면
				temp += str.charAt(i);//임시 문자열에 추가
			}
			if (str.charAt(i) == '+' || str.charAt(i) == '-' || i == str.length()-1) {//연산자나 문장의 끝이면
				nums.push(Integer.parseInt(temp));//임시 문자열 int로 바꾸어 nums에 push
				temp = "";//문자열 초기화
				
				//연산자의 경우 연산 수행
				if(str.charAt(i) == '-') {// - 연산자면
					while(!ch.empty()) {//우선순위 최하위, push하려면 ch stack을 모두 비워야함.
						char c = ch.pop();//연산자 pop
						//숫자 pop
						int b = nums.pop();
						int a = nums.pop();
						//연산해서 결과 nums에 push
						if(c == '+') {
							nums.push(a+b);
						} else {
							nums.push(a-b);
						}
					}
					ch.push(str.charAt(i));//다 끝나면 stack에 -를 push
				} else if(str.charAt(i) == '+') {//+ 연산자면
					while(!ch.empty() && ch.peek() == '+') {//ch stack이 비거나 peek이 -여야 넣을 수 있음, 연산자는 +, - 두개니 peek이 +면 안됨.
						//위와 동일, +만 pop이 될 수 있으니 덧셈만 수행
						char c = ch.pop();
						int b = nums.pop();
						int a = nums.pop();
						if(c == '+') {
							nums.push(a+b);
						}
					}
					ch.push(str.charAt(i));//+를 push해두기.
				}
			}
		}
		//탐색 종료 후 stack 비우기
		while(!ch.empty()) {//stack 빌 때까지 연산 수행
			char c = ch.pop();
			int b = nums.pop();
			int a = nums.pop();
			if(c == '+') {
				nums.push(a+b);
			} else {
				nums.push(a-b);
			}
		}
		//결과 출력, 결과는 nums stack에 남은 유일한 값.
		System.out.println(nums.pop());
	
		
	}//end of main method
}//end of class
