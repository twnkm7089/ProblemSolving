import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 조합문제
 * 2. 설명할게 없다.
 * */
public class KTW_BOJ_15650_S3_N과M2 {
	public static int[] sel;
	public static int N, M;
	public static StringBuilder str = new StringBuilder();
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//선택 행렬 선언
		sel = new int[M];
		
		comb(0, 0);//조합함수
		//output
		bw.write(str.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void comb(int idx, int sidx) {//조합함수
		if(sidx == M) {//다 뽑으면 StringBuilder에 한다.
			for(int i = 0; i < M; i++) {
				str.append(sel[i] + " ");
			}
			str.append("\n");
			return;
		}
		//아니면 반복문 이용 뽑기.
		for(int i = idx; i <= (N - M + sidx); i++) {
			sel[sidx] = i+1;
			comb(i+1, sidx+1);
		}
	}
	
	
}
