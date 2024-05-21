import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011_G5_암호코드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		int N = str.length();
		
		int[][] dP = new int[N][2];
		
		int MOD = 1000000;
		boolean able = true;
		//이번 것이... [0] : 한자리수일때, [1]: 두자리수일 때의 경우의 수
		
		if(str.charAt(0) == '0') {
			//0은 단독 불가
			able = false;
		} else {	
			dP[0][0] = 1;
			for(int i = 1; i < N; i++) {
				char before = str.charAt(i-1);
				char now = str.charAt(i);
				
				//0이 단독으로 나오는 경우는 불가능
				if(now == '0' && (before != '1' && before != '2')) {
					able = false;
					break;
				}
				//두자리수 가능
				if(before == '1' || (before == '2' && (now >= '0' && now <= '6'))) {
					if(now != '0') {
						//단독으로 0은 불가능, 고로, now == '0'면 dP[i][0] = 0
						dP[i][0] = (dP[i-1][0]%MOD + dP[i-1][1]%MOD)%MOD;
					}
					//앞의 수와 함께 두자리로 구성
					dP[i][1] = dP[i-1][0]%MOD;
				} else {
					dP[i][0] = (dP[i-1][0]%MOD + dP[i-1][1]%MOD)%MOD;
				}
				
			}
		}
		
		//가능, 불가능 여부에 따른 표시
		if(able) {
			System.out.println((dP[N-1][0] + dP[N-1][1])%MOD);
		} else {
			System.out.println(0);
		}
		
	}
}
