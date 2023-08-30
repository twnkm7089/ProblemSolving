package codetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*알고리즘 설명
 * 1. 문자열이 27 길이라고 가정하자. 각 인덱스를 9로 나누고 3으로 나눈 나머지를 구하면 0~8은 0, 9~17은 1, 18~26은 2가 된다.
 * 2. 1에 해당하는 부분을 모두 빈칸으로 대체한다. 그 후, 각 인덱스를 3으로 나눈 후, 3으로 모듈러 계산을 수행한다.
 * 3. 그 결과가 1이 되는 부분은 3~5, 12~14, 21~23의 세 부분이다. 이곳도 빈칸으로 대체한다.
 * 4. 1로 나눈 후 3으로 모듈러 계산을 해 1이 되는 부분도 마찬가지다.
 * 5. 반복문을 이용해 이를 구현했다.
 * */
public class KTW_BOJ_4779_S3 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder st = new StringBuilder();
		String val = "";
		while((val = br.readLine()) != null) {//스캐너로 치면 sc.hasNext()
			int N = Integer.parseInt(val);//입력
			int size = (int)Math.pow(3, N);//길이 구하기
			//char형 배열에 문자열 저장
			char[] answer = new char[size];
			for(int i = 0; i < size; i++) {
				answer[i] = '-';
			}
			//스트링빌더 초기화
			st = new StringBuilder();
			
			//대체
			int temp = size;
			while(temp > 1) {//while문 사용
				for(int i = 0; i < size; i++) {
					if((i/(temp/3))%3 == 1) {//인덱스 / (temp/3) % 3 이 1이 되는 부분 빈칸 대체
						answer[i] = ' ';
					}
				}
				temp /= 3;//크기 3으로 나누기
			}
			//결과 String으로
			for(int i = 0; i < size; i++) {
				st.append(answer[i]);
			}
			
			//출력
			bw.write(st + "\n");
			bw.flush();
		}
		//닫기
		br.close();
		bw.close();
	}
	
	

}//end of class
