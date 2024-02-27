package Feb5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10799_S2_쇠막대기 {
	public static void main(String[] args) throws IOException {
		//() : laser, +stack의 크기
		//)) : end of stick,  덩어리 +1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		//stack 이용
		Stack<Character> stack = new Stack<>();
		int answer = 0;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c == '(') {
				stack.add(c);
			} else {
				if(str.charAt(i-1) == '(') {
					stack.pop();
					answer += stack.size();
				} else {
					stack.pop();
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
}
