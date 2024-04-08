import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class KTW_BOJ_10158_S4 {
	/*알고리즘 설명
	 * - 문제 풀이보다 최적화 위해 BuffredReader, StringTokenizer, BufferedWriter, StringBuilder 쓰는게 더 어려웠음.
	 * 1. p, q에 t만큼 더한다.
	 * 2. 반사를 2번 하면 0부터 시작하는 원상복구니, 2*w, 2*h를 나눈 나머지를 구한다.
	 * 3. w, h를 넘은 경우 1번 반사한 결과니 대칭으로 구해준다.
	 * */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		
		p += t;
		q += t;
		//2회 이상 반사(사실상 0부터 시작)
		p %= (2*w);
		q %= (2*h);
		if(p > w) { //반사시 대칭
			p = 2*w - p;
		}
		if(q > h) { //반사시 대칭
			q = 2*h - q;
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		sb.append(p);
		sb.append(" ");
		sb.append(q);
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
		
	}
}
