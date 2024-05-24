import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023_G5_신기한소수 {
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		

		//backtracking + dfs
		//한자리수 소수는 그냥 구하기
		//2,3,5,7

		check(2, 1);
		check(3, 1);
		check(5, 1);
		check(7, 1);

		br.close();
		
	}
	
	public static void check(int num, int i) {
		//N번째 자리수 도달
		if(i == N) {
			System.out.println(num);
			return;
		}
		
		else {
			//root N까지 판별해 소수 파악
			int temp = num * 10;
			for(int j = temp; j < temp+10; j++) {
				//prime
				boolean isPrime = true;
				int sqrt = (int)Math.sqrt(j);
				for(int k = 2; k <= sqrt+1; k++) {
					if(j % k == 0) {
						isPrime = false;
						break;
					}
				}
				
				//소수면 dfs
				if(isPrime) {
					check(j, i+1);
				}
			}
		}
	}

		
}
