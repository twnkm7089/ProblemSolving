import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2166_G5_다각형의면적 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//신발끈 공식
		//모르면 찾아보길
		int N = Integer.parseInt(br.readLine());
		
		long[][] pos = new long[N][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pos[i][0] = Long.parseLong(st.nextToken());
			pos[i][1] = Long.parseLong(st.nextToken());
		}
	
		//신발끈
		double ans = 0;
		long plus = 0;
		long minus = 0;
		for(int i = 0; i < N; i++) {
			plus += pos[i%N][0]*pos[(i+1)%N][1];
			minus += pos[(i+1)%N][0]*pos[i%N][1];
		}
		
		ans = 0.5 * Math.abs(plus - minus);

		
		double num = Math.round(ans*10)/10.0;
		System.out.println(String.format("%.1f", num));
		
		br.close();
		
	}
		
}
