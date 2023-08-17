import java.util.Scanner;

public class KTW_BOJ_3052_B2 {
	/*알고리즘 설명
	 * 1. 나머지를 담은 배열을 생성
	 * 2. 탐색을 돌려 같은 원소가 있을 시 outer로 빠져나오고, 아니면 count에 1더하기.
	 * */
	public static void main(String[] args) {
		int count = 0;
		int[] arr = new int[10];
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt() % 42;
		}
		
		count++; //정답 1은 일단 기본
		outer : for(int i = 1; i < 10; i++) { //탐색
			for(int j = 0; j < i; j++) { //이전 원소 분석
				if(arr[i] == arr[j]) { //중복 있으면 빠져나오고
					continue outer;
				}
			}
			count++; //없으면 추가
		}
		
		System.out.println(count);
	}
}
