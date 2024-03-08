import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_2565_G5_전깃줄_다시풀것 {
	//다시 풀어볼 것
	//생각의 전환이 중요하다.
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		//줄 정보 저장
		int[][] lines = new int[N][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(lines, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
			
		});
		
		//좌측을 위에서부터 아래로 내려오면서 도착하는 곳의 최장 수열의 길이를 구하면
		//정답은 N-(LIS 길이)
		//왜냐하면 제거해야되는 선의 개수의 최소값은 역으로 보면 제거하지 않아도 되는 선의 최대 개수
		int[] dP = new int[N];
		for(int i = 0; i < N; i++) {
			int max = 0;
			for(int j = 0; j < i; j++) {
				if(lines[i][1] > lines[j][1] && max < dP[j]) {
					max = dP[j];
				}
			}
			
			dP[i] = max + 1;
		}
		
		int LIS = 0;
		for(int i = 0; i < N; i++) {
			if(dP[i] > LIS) {
				LIS = dP[i];
			}
		}
		
		int ans = N - LIS;
		System.out.println(ans);
	}	
}
