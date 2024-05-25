import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1038_G5_감소하는수 {
	static long cnt;
	static int N;
	static boolean isFound;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//최대치 : 9876543210 -> long
		//brute force
		
		cnt = -1;
		isFound = false;
		
		outer : for(int i = 1; i <= 10; i++) {
			//자리수
			for(long n = 0; n <= 9; n++) {
				findNum(n, n, 1, i);
				
				if(isFound) break outer;
			}
			
		}
		
		//못찾으면 -1 출력
		if(!isFound) {
			System.out.println(-1);
		}
		
		br.close();
		
	}
	
	public static void findNum(long num, long lastNum, int now, int target) {
		//num = 현재 수, lastNum = 마지막 추가한 자리수, now = 현재 num의 자리수, target = 목표 자리수
		
		if(isFound) return; //찾았으면 종료
		
		if(now == target) { //줄어드는 수
			//카운트
			cnt++;
			if(cnt == N) { //목표 발견
				//출력 후 끝
				System.out.println(num);
				isFound = true;
				return;
			}
		}
		
		//현재 수*10 후 줄어드는 수 만들기
		long temp = num * 10;
		for(long i = 0; i < lastNum; i++) {
			findNum(temp+i, i, now+1, target);
		}

	}
	
	
}
