import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 그리디 응용
 * 2. 5원으로 거슬러 줄 수 있는 최대 동전 개수를 구한다(fiveNum).
 * 3. 그리고 5원을 그만큼 거슬러 주었을 경우 짝수원이 남으면(2원짜리로 가능) 2원으로 거슬러 줄 경우까지 더해 출력.
 * 4. 아니면 fiveNum--한 후, 다시 실행(fiveNum이 음수가 되면 불가능)
 * */
public class KTW_BOJ_14916_S5_거스름돈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		//초기화
		int answer = -1;
		//5원으로 거슬러 줄 수 있는 최대 동전 수
		int fiveNum = N / 5;
		while(fiveNum >= 0) {
			//5원을 fiveNum만큼 거슬러주고 남는 금액
			int left = N - fiveNum*5;
			
			if(left%2 == 0) {//짝수면 2원으로 거슬러 주고 탐색 종료
				answer = fiveNum + (left / 2);
				break;
			} else {//아니면 fiveNum--하고 한번 더
				fiveNum--;
			}
		}
		//결과 출력
		System.out.println(answer);
		
	}
}
