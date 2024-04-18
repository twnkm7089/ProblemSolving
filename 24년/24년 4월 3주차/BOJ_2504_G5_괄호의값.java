import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504_G5_괄호의값 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		//stack 이용
		//(: -1, [: -2
		
		Stack<Integer> stack = new Stack<>();
		int ans = 0;
		boolean isValid = true; //올바른 문자열인가?
		
		
		outer : for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if(c == '(') { //여는 소괄호
				stack.add(-1);
			} else if(c == '[') { //여는 대괄호
				stack.add(-2);
			} else if(c == ')') { //닫는 소괄호
				
				int temp = 0;
				
				while(true) {
					//문자열이 안 끝났는데 스택이 비었다?
					//잘못된 문자열
					if(stack.isEmpty()) {
						isValid = false;
						break outer;
					}

					int val = stack.pop();
					if(val == -2) { //잘못된 문자열
						isValid = false;
						break outer;
					} else if(val == -1) { //문자열 종료
						if(temp == 0) temp = 1;
						stack.add(temp*2);
						break;
					} else {
						//아니면 괄호 안의 숫자, 더해주기
						temp += val;
					}
				}
			} else { //닫는 대괄호
				
				int temp = 0;
				
				while(true) {
					//문자열이 안 끝났는데 스택이 비었다?
					//잘못된 문자열
					if(stack.isEmpty()) {
						isValid = false;
						break outer;
					}

					int val = stack.pop();
					if(val == -1) { //잘못된 문자열
						isValid = false;
						break outer;
					} else if(val == -2) { //문자열 종료
						if(temp == 0) temp = 1;
						stack.add(temp*3);
						break;
					} else {
						//아니면 괄호 안의 숫자, 더해주기
						temp += val;
					}
				}
				
			}
		}
		
		if(isValid) {
			while(!stack.isEmpty()) {
				//문자열 문제 있음?(아직 안 닫힘) -> 망함
				if(stack.peek() < 0) {
					ans = 0;
					break;
				}
				
				ans += stack.pop();
			}
		}
		
		
		System.out.println(ans);
		
		br.close();
	}
}
