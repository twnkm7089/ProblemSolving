import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*알고리즘 설명
 * 1. 비교 문자열 생성
 * 2. 문자열 분석, 불일치 시 불일치 칸에 맞추어 점프(보이어 무어 알고리즘 응용)
 * 3. 일치 시 카운트 추가 후 2칸씩 비교. 불일치 시 해당 칸에 맞추어 점프
 * 4. 결과 출력
 * */

public class KTW_BOJ_5525_S1_IOIOI {
	
	public static void main(String[] args) throws IOException {
		//input, 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//input
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String str = br.readLine();
		//비교 문자열 생성
		StringBuilder temp = new StringBuilder();
		for(int i = 0; i < N; i++) {
			temp.append("IO");
		}
		temp.append("I");
		
		String P = temp.toString();
		int K = P.length();
		int cnt = 0;//정답
		int startidx = 0;//시작 인덱스
		while(startidx <= M - K) {//탐색
			boolean flag = true;//일치 여부
			int jump = 0;//다음 칸
			for(int i = 0; i < K; i++) {//탐색
				if(P.charAt(i) != str.charAt(startidx+i)) {//불일치 시 break(jump위치, flag 설정)
					if(P.charAt(i) == 'O') {//O가 나와야 하는데 I가 나옴
						jump = i;
						flag=false;
						break;
					} else {//I가 나와야 하는데 O가 나옴.
						jump = i+1;
						flag=false;
						break;
					}
				}
			}
			if(flag == true) {//일치시
				cnt++;//카운트 추가
				startidx+=2;//2칸 추가
				while(startidx <= M-K) {//2칸씩 추가 비교
					if(str.substring(startidx+K-2, startidx+K).equals("OI")) {//일치시
						cnt++;//카운트 추가
						startidx+=2;//2칸 뒤로
					} else if(str.charAt(startidx+K-2) == 'I') {//불일치시, 첫 칸이 I면(II, IO)
						//그에 맞추어 점프
						startidx+=(K-2);
						break;
					} else {//OO의 경우
						//점프
						startidx+=(K-1);
						break;
					}
				}
			}
			startidx += jump;//다음 위치로
		}
		System.out.println(cnt);//결과 출력
		
		
	}//end of main method

}//end of class
