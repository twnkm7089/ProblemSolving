import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_6603_S2_로또 {
	static int K;
	static int[] sel = new int[6];
	static int[] whole;
	static StringBuilder sb;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//간단한 조합 문제
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		whole = new int[K];
		while(K != 0) {
			for(int i = 0; i < K; i++) {
				whole[i] = Integer.parseInt(st.nextToken());
			}
			select(0, 0);
			sb.append("\n");
			
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			whole = new int[K];
		}
		
		System.out.println(sb.toString());
		
	}
	
	public static void select(int idx, int sidx) {
		if(sidx == 6) {
			for(int i = 0; i < 6; i++) {
				sb.append(sel[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = idx; i <= K - 6 + sidx; i++) {
			sel[sidx] = whole[i];
			select(i + 1, sidx + 1);
		}
	}
}
