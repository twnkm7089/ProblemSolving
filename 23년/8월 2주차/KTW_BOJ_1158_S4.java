import java.util.Scanner;

public class KTW_BOJ_1158_S4 {
	/*알고리즘 설명
	 * 1. 인덱스를 나타내는 변수를 따로 설정
	 * 2. 순회를 돈다. idx늘리며 계산하고 idx가 n이면 0으로 초기화하고 사람 제거되었으면 제거 안된 곳 찾을 때까지 반복
	 * 3. 사람이 제거되면 people배열 1로 바꾸고 answer에 idx+1 저장
	 * 4. 결과 나오면 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		//사람 저장, 정답 저장
		int[] people = new int[n];
		int[] answer = new int[n];
		int idx = -1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < k; j++) {
				idx++;
				if(idx == n) {//넘어갈 때 원형
					idx-=n;
				}
				if(people[idx] == 1) { //제거 한 사람은 생략, 제거 안된 사람 찾을때까지
					while(people[idx] == 1) {
						idx++;
						if(idx == n) {
							idx -= n;
						}
					}
				}
			}
			people[idx] = 1; //제거
			answer[i] = idx+1; //인원 저장
			
		}
		//출력
		for(int i = 0; i < n; i++) {
			if(i == 0) {
				System.out.print("<");
			}
			System.out.print(answer[i]);
			if(i == (n-1)) {
				System.out.print(">");
			} else {
				System.out.print(", ");
			}
		}
	}
}
