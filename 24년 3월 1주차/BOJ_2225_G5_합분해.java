import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

//kHn 중복 조합 이용
//BigInteger이용

//나중에 dP이용해 다시 풀 것
public class BOJ_2225_G5_합분해 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		BigInteger ans = new BigInteger("1");
		for(int i = (N + K - 1); i > N; i--) {
			ans = ans.multiply(new BigInteger(String.valueOf(i)));
		}
		
		BigInteger div = new BigInteger("1");
		for(int i = 2; i < K; i++) {
			div = div.multiply(new BigInteger(String.valueOf(i)));
		}

		ans = ans.divide(div);
		ans = ans.remainder(new BigInteger("1000000000"));
		
		System.out.println(ans);
	}
}
