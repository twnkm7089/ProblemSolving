import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 중복조합 만들기
 * 2. 더 할 말이 없다. 
 * */
public class KTW_BOJ_15652_S3_N과M4 {
	public static int[] sel;
	public static int N, M;
	public static int idx, sidx;
	public static StringBuilder str = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sel = new int[M];
		comb(0,0);//조합
		//output
		bw.write(str.toString());
		bw.flush();
		bw.close();
		br.close();

	}
	//중복 조합 제조 함수
	public static void comb(int idx, int sidx) {
		if(sidx == M) {//다 뽑으면 StringBuilder에 저장
			for(int i = 0; i < M; i++) {
				str.append(sel[i]);
				str.append(" ");
			}
			str.append("\n");
			return;
		}
		//중복조합 만들기
		for(int i = idx; i < N; i++) {//중복조합이라 일부 변경
			sel[sidx] = i+1;
			comb(i, sidx+1);//중복조합이라 일부 변경
		}
		
	}
	
}
