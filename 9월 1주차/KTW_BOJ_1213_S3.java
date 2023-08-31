package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 회문 만들기
 * 2. 각 문자별 개수 count
 * 3. 개수가 홀수인 문자가 2개 이상이면 안됨.
 * 4. 나중에 A부터 검사하며 개수/2만큼 추가, 문자열 전반부
 * 5. 홀수개인 경우는 mid에도 따로 저장.
 * 6. 문자열 전반부 + mid 출력 후, 전반부를 순서 반대로 해 출력.
 * */

public class KTW_BOJ_1213_S3 {
	public static int[][] map;
	public static int[][] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int[] alpha = new int[26];
		//문자 개수 카운팅
		for(int i = 0; i < str.length(); i++) {
			alpha[str.charAt(i)-'A']++;
		}
		//홀수개 가진 문자 카운팅
		int cnt_odd = 0;
		for(int i = 0; i < 26; i++) {
			if(alpha[i] % 2 == 1) {
				cnt_odd++;
			}
		}
		
		if(cnt_odd > 1) {//2개 이상이면 회문 불가
			System.out.println("I'm Sorry Hansoo");
		} else {//그 외
			char mid = 'a';//가운데 초기화
			String res = "";//문자열 전반부
			for(int i = 0; i < 26; i++) {
				//문자열 전반부에 값 추가
				char a = (char)(i + 'A');
				for(int j = 0; j < alpha[i]/2; j++) {
					res += a;
				}
				if(alpha[i] % 2 == 1) {//홀수개면 가운데에도 추가해야 하니 mid에 저장
					mid = a;
				}
			}
			
			
			System.out.print(res);//전반부 출력
			if(mid != 'a') {//mid 존재시 출력
				System.out.print(mid);
			}
			for(int i = res.length()-1; i >= 0; i--) {//전반부 거꾸로 해서 후반부 출력
				System.out.print(res.charAt(i));
			}
			System.out.println();//줄바꿈
		}
	}//end of main method
	
}//end of class
