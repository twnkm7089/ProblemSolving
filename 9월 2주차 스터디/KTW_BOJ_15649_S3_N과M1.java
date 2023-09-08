import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class KTW_BOJ_15649_S3_N과M1 {
	public static int[] sel;
	public static int N, M;
	public static StringBuilder str = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//input
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		//결과 함수 초기화
		sel = new int[M];
		comb(0,0);//조합 생성
		//결과 출력
		bw.write(str.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb(int idx, int visited) {
		if(idx == M) {//조합 완성시 StringBuilder에 추가
			for(int i: sel) {
				str.append(i + " ");
			}
			str.append("\n");
			return;
		}
		//조합 생성
		for(int i = 1; i <= N; i++) {
			if((visited & (1<<i)) == 0) {//없는 숫자면
				sel[idx] = i;//추가
				comb(idx+1, visited | (1<<i));//비트마스킹 방식으로 방문 표시 및 재귀 호출
			}
		}
	}
	
}
