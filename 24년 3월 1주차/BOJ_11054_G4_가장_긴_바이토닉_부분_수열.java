import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_11054_G4_가장_긴_바이토닉_부분_수열 {	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//LIS
		//좌측에서 한번, 우측에서 한번
		//양쪽으로 증가하는 최대 길이 더해주고 -1해주면 완료
		int[] dP1 = new int[N];
		int[] dP2 = new int[N];
		for(int i = 0; i < N; i++) {
			int max1 = dP1[i];
			int max2 = dP2[N-1-i];
			
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && dP1[j] > max1) {
					max1 = dP1[j];
				}
				
				if(arr[N-1-j] < arr[N-1-i] && dP2[N-1-j] > max2) {
					max2 = dP2[N-1-j];
				}
			}
			
			dP1[i] = max1 + 1;
			dP2[N-1-i] = max2 + 1;
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			ans = Math.max(ans, dP1[i] + dP2[i] - 1);
		}
		
		//output
		System.out.println(ans);
	}
}
