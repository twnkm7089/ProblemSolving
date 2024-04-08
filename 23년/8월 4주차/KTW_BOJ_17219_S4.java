package August_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class KTW_BOJ_17219_S4 {
	/*알고리즘 설명
	 * 1. 해시맵 사용
	 * 2. 줄 단위로 읽어와 사이트명을 key로 하고 비밀번호를 value로 지정.
	 * 3. 그 뒤부터는 불러와 버퍼에 저장후 flush로 출력
	 * */
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//버퍼드 리더, 라이터
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//숫자 N, K입력
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		HashMap<String, String> list = new HashMap<>();//해시맵
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			list.put(str.split(" ")[0], str.split(" ")[1]);//앞은 key, 뒤는 value
		}
		for(int i = 0; i < K; i++) {//key 바탕으로 value 출력
			String str = list.get(br.readLine());
			bf.write(str+"\n");
		}
		br.close();
		bf.flush();//flush
		bf.close();
	}//end of main method
}//end of class
