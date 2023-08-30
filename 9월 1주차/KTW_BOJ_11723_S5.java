package codetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 총평 : 온갖 최적화 기법의 향연. 이게 실버5냐.
 * 2. 비트마스크 이용. 1<<21로 10000000...의 비트열 형성
 * 3. 맨 뒤 자리(0번 자리)는 없는 걸로 간주
 * 4. add 연산. 1<<x를 or. 오른쪽 맨 끝에서 x번 간 곳에 1 비트 나옴.
 * 5. remove 연산. andval = 11111101111... 여기 and취함.
 * 6. check 연산. 1<<x와 and해준 값 >>x 해서 출력
 * 7. toggle 연산. 1<<x와 xor
 * 8. all 연산. Integer.MAX_VALUE인 1111...1111과 or.
 * 9. empty 연산. 0과 and
 * */
public class KTW_BOJ_11723_S5 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int set = 1 << 21;//bit 0:부재, 1:존재
		int M = Integer.parseInt(br.readLine());
		int x = 0;
		for(int i = 0; i < M; i++) {//시작
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			if(command.equals("add")) {//add (1<<x)와 or
				x = Integer.parseInt(st.nextToken());
				set = set | (1<<x);
			} else if(command.equals("remove")) {//remove 1110111...와 and
				x = Integer.parseInt(st.nextToken());
				int andval = Integer.MAX_VALUE - (1<<x);
				set = set & andval;
			} else if(command.equals("check")) {//check 1<<x와 and한 값 >>x
				x = Integer.parseInt(st.nextToken());
				String ans = String.valueOf(((set & (1 << x)) >> x));
				sb.append(ans);
				sb.append('\n');

			} else if(command.equals("toggle")) {//toggle 1<<x와 xor
				x = Integer.parseInt(st.nextToken());
				set = set ^ (1 << x);
			} else if(command.equals("all")) {//111....1111와 or
				set = set | Integer.MAX_VALUE;
			} else {//0000...0000와 and
				set = set & 0;
			}
		}
		//결과 한방에 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	

}//end of class
