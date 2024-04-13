import java.util.Arrays;
import java.util.Scanner;

public class CodeTree_1502_01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//restaurant size
		int N = sc.nextInt();
		//고객 수
		int[] restaurant = new int[N];
		for(int i = 0; i < N; i++) {
			restaurant[i] = sc.nextInt();
		}
		//팀장, 팀원
		int[] ability = new int[2];
		ability[0] = sc.nextInt(); //팀장
		ability[1] = sc.nextInt(); //팀원
		
		long ans = 0;
		for(int i = 0; i < N; i++) {
			if(restaurant[i] <= ability[0]) {
				//팀장으로 충분
				ans++;
				continue;
			} else {
				
				ans += ((restaurant[i] - ability[0] -1) / ability[1])+2;
			}
		}
		
		System.out.println(ans);
	}
}
