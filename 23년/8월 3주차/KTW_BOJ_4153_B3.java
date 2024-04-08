import java.util.Arrays;
import java.util.Scanner;

public class KTW_BOJ_4153_B3 {
	/*알고리즘 설명
	 * 1. 피타고라스 법칙 이용
	 * 2. 입력이 오름차순 되도록 항상 정렬
	 * 
	 * */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] input = new int[3];
		for(int i = 0; i < 3; i++) {//입력
			input[i] = sc.nextInt();
		}
		Arrays.sort(input);//오름차순 정렬
		
		while(input[2] != 0) {//input[2]==0이면 입력 종료이므로(제일 큰 숫자가 0이려면 0 0 0)
			//피타고라스의 법칙으로 판별
			if((input[0]*input[0] + input[1]*input[1]) == input[2]*input[2]) {
				System.out.println("right");
			} else {
				System.out.println("wrong");
			}
			//재입력
			for(int i = 0; i < 3; i++) {
				input[i] = sc.nextInt();
			}
			Arrays.sort(input);
		}
		
	}
}
