import java.util.Scanner;

public class KTW_BOJ_2839_S4 {
	/*알고리즘 설명
	 * 1. 우선 5킬로 봉투에 0개, 1개, 2개...식으로 넣는것 시도
	 * 2. 남은 설탕을 3킬로 봉투에 넣으면 나누어 떨어지는지 검사
	 * 3. 만약 그렇다면 봉투 개수 더해서 min보다 작은지 구함(-1일 때 제외, 그때는 그냥 더함)
	 * 4. min출력, 아무것도 만족 못 시켰으면 기본값인 -1, 만족 시킨 거 있으면 그 중 최소값 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int min = -1;
		for(int i = 0; i <= N/5; i++) { //5kg봉투 시도
			if((N-5*i) % 3 != 0) { //3kg봉투로 나누어 떨어지나 검사
				continue;
			} else { //나누어 떨어짐
				int temp = i + ((N-5*i)/3); //봉투 갯수 더하기
				if(min == -1) { //min과의 비교
					min = temp;
				} else if(min > temp) {
					min = temp;
				}
			}
		}
		System.out.println(min); //출력
	}
}
