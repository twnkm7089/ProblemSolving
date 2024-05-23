import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11049_G3_행렬곱셈순서 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] matSize = new long[N][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			matSize[i][0] = Long.parseLong(st.nextToken());
			matSize[i][1] = Long.parseLong(st.nextToken());
		}
		
		//행렬을 곱한 개수를 하나씩 늘려가며 최소값 갱신
		//초기는 곱하는 개수가 없으니 0이다.
		//행렬 크기에 따라 점점 늘어난다.
		long[][] dP = new long[N][N];
		//dP[][1] 은 2개씩 합친 결과
		for(int i = 0; i < N-1; i++) {
			dP[1][i] = matSize[i][0]*matSize[i+1][0]*matSize[i+1][1];
		}
		
		for(int i = 2; i < N; i++) {
			//합쳐진 행렬 수 i+1개
			for(int j = 0; j < N-i; j++) {
				long min = Long.MAX_VALUE;
				for(int k = 0; k < i; k++) {
					long temp = dP[i-1-k][j] + dP[k][j+i-k] + matSize[j][0]*matSize[j+i-k][0]*matSize[j+i][1];
					min = Math.min(min, temp);
				}
				dP[i][j] = min;
			}
		}
		
		
		System.out.println(dP[N-1][0]);
		
		br.close();
		
	}
		
}
