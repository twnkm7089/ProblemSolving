import java.util.Scanner;

public class KTW_BOJ_1259_B1 {
	/*알고리즘 설명
	 * 1. 대표적인 회문 문제
	 * 2. 끝과 끝부터 비교, flag로 회문여부 파악
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();//문자열로 입력 받기
		while(!str.equals("0")) {//0이 아니면
			int N = str.length();
			boolean flag = true;//회문여부 파악
			for(int i = 0; i < N/2; i++) {//탐색, 절반만 해도 됨
				if(str.charAt(i) != str.charAt(N-1-i)) {//불일치시 회문 아님
					flag = false;//표시
					break;//탐색 중단
				}
			}
			//결과 출력
			if(flag == true) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
			//다음 입력
			str = sc.next();
		}
		
	}
}
