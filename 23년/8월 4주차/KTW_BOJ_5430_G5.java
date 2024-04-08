package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*알고리즘 설명
 * 1. 계속 뒤집으면 시간 초과 나니 시작, 끝 인덱스를 관리하며 출력만 하는 방식으로 함.
 * 2. 그래도 시간 초과나서 버퍼드, 스트링빌더 사용.
 * */
public class KTW_BOJ_5430_G5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());//테케 입력
		for(int tc = 1; tc <= T; tc++) {
			//입력
			StringBuilder sb = new StringBuilder();
			String p = br.readLine();
			int n = Integer.parseInt(br.readLine());
			
			String answer = "";
			
			String str = br.readLine();
			str = str.substring(1,str.length()-1);
			String[] starr = str.split(",");
			//입력 받은 배열 정수형 배열로 변환
			int[] arr = new int[n];
			int arrSize = n;
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(starr[i]);
			}
			
			boolean dir = true;//true 정방향 false 역방향
			int start = 0;//시작점
			int end = n - 1;//끝점
			int removecnt = 0;//제거한 개수
			boolean err = false;//에러 여부
			
			for(int i = 0; i < p.length(); i++) {//탐색 시작
				if(p.charAt(i) == 'R') {
					//reverse는 start, end값 swap으로 구현, 방향 반대로
					//swap
					int temp = start;
					start = end;
					end = temp;
					//방향 반대
					dir = !dir;
				} else {//D의 경우
					if(arrSize == removecnt) {//크기 0에서 실행시 에러.
						sb.append("error");
						err = true;
						break;
					} else {//아니면
						removecnt++;//개수 추가
						if(dir) {//정방향이면 시작점 1칸 뒤로
							start++;
						} else {//역방향이면 한칸 앞으로
							start--;
						}
					}
				}
			}//탐색 종료
			

			//출력
			if(err) {
				System.out.println(sb);
			}else {
				
				sb.append("[");
				//방향에 따른 출력 변화
				if(dir) {
					for(int i = start; i <= end; i++) {
						sb.append(arr[i]);
						if (i != end){
							sb.append(",");
						}
					}
				} else {
					for(int i = start; i >= end; i--) {
						sb.append(arr[i]);
						if (i != end){
							sb.append(",");
						}
					}
				}
				
				sb.append("]");
				System.out.println(sb);//출력
			}
			
			
		}
		
		br.close();
		
		
		
	}//end of main method

}//end of class
