import java.util.Scanner;

public class KTW_BOJ_2941_S5 {
	/*알고리즘 설명
	 * 1. 조건문 이용, 문자열 특정 인덱스에 접근해 =, -, j로 끝나는 경우 특수문자인지 확인
	 * 2. 맞으면 특수문자 1개니 count를 추가 안하거나 3글자면 하나 빼고, 아니면 count 추가
	 * 3. else구문 사용에 주의! 이중 if문에서의 예외처리 주의!
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		
		int count = 0;
		//조건 판별
		for(int i = 0; i < s.length(); i++) {
			if(i >= 1 && s.charAt(i) == '=') { // = 으로 끝
				if(i >= 2 && s.charAt(i-2) == 'd' && s.charAt(i-1) == 'z') {
					count--;
				} else if(s.charAt(i-1) == 'c' || s.charAt(i-1) == 's' || s.charAt(i-1) == 'z') {
					continue;
				} else {
					count++;
				}
			} else if(i >= 1 && s.charAt(i) == '-') { // - 으로 끝
				if(s.charAt(i-1) == 'c' || s.charAt(i-1) == 'd') {
					continue;
				} else {
					count++;
				}
			} else if(i >= 1 && s.charAt(i) == 'j') { // j 으로 끝
				if(s.charAt(i-1) == 'l' || s.charAt(i-1) == 'n') {
					continue;
				} else {
					count++;
				}
			} else {
				count++;
			}
		}
		
		System.out.println(count); //출력
	}
}
