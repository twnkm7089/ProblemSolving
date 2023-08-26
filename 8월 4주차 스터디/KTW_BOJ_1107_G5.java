package August_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KTW_BOJ_1107_G5 {
	/*알고리즘 설명
	 * 1. 브루트포스 사용
	 * 2. 입력을 받은 후 숫자 패드를 아얘 사용하지 않을 경우 횟수 파악
	 * 3. 숫자 패드를 사용할 경우 파악. 우선 채널 번호를 높여가며 가장 가까이 있는 입력 가능한 채널 번호 획득.
	 * 4. 이후에는 채널번호 낮춰가며 반복.
	 * 5. 둘을 비교해 누르는 횟수가 낮은 것을 선정
	 * 6. 숫자패드 미사용시와 비교, 최소값을 출력
	 * */
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] nums = new int[10]; //숫자버튼 0:정상 1:고장
		if(M != 0) {//고장난 버튼 존재 시
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				int broken = Integer.parseInt(st.nextToken());
				nums[broken] = 1;
			}
		}
		
		//숫자 패드 미사용시
		int onlypm = 0;
		onlypm = Math.abs(N - 100);//차이만큼 입력
		
		//숫자패드 사용 시 입력 횟수
		int numuse = -1;
		if(M == 10) {//숫자패드 모두 고장
			numuse = -1;//이용 불가
		} else {//숫자패드 사용 가능
			String ch = String.valueOf(N);//문자열화
			
			//완탐으로 가불가 탐색
			//가장 가까운 채널 찾기
			//채널 올리면서, 본인 포함
			int now = N;
			int max_pos = -1;
			
			//버튼이 0만 사용 가능할 경우 무한 루프로 빠지는 문제 해결
			boolean onlyzero = true;
			for(int i = 1; i <= 9; i++) {
				if(nums[i] == 0) {
					onlyzero = false;
					break;
				}
			}
			
			while(now >= 0 && !onlyzero) {//0만 입력 가능한게 아닌경우
				String temp = String.valueOf(now);//현재 채널 문자열화
				//flag로 판별, 해당 채널이 입력 가능한지 파악
				boolean flag = true;
				for(int i = 0; i < temp.length(); i++) {
					if(nums[temp.charAt(i) - '0'] == 1) {
						flag = false;
						break;
					}
				}
				if(flag) {//입력 가능하면 max_pos에 저장 후 탐색 종료
					max_pos = now;
					break;
				}
				now++;//아니면 다음 채널로
			}
			//채널 내리면서 위와 동일
			int min_pos = -1;
			now = N;
			while(now >= 0) {
				String temp = String.valueOf(now);
				
				boolean flag = true;
				for(int i = 0; i < temp.length(); i++) {
					if(nums[temp.charAt(i) - '0'] == 1) {
						flag = false;
						break;
					}
				}
				if(flag) {
					min_pos = now;
					break;
				}
				now--;//채널 내림
			}
			
			//차이가 최소인 채널 기준
			if(max_pos != -1 && min_pos != -1) {//올리고 내리면서 값을 찾았으면, 누르는 횟수 최소로
				int maxDiff = max_pos - N;
				int minDiff = N - min_pos;
				
				numuse = String.valueOf(max_pos).length() + maxDiff;
				if(String.valueOf(min_pos).length() + minDiff < numuse) {
					numuse = String.valueOf(min_pos).length() + minDiff;
				}
			} else if(max_pos == -1) {//올리면서 못 찾은 경우
				int minDiff = N - min_pos;
				numuse = String.valueOf(min_pos).length() + minDiff;
			} else if(min_pos == -1) {//내리면서 못 찾은 경우
				int maxDiff = max_pos - N;
				numuse = String.valueOf(max_pos).length() + maxDiff;
			}

		}//numuse 탐색 종료
		
		if(numuse == -1) {//숫자키 입력 불가
			System.out.println(onlypm);
		} else {//둘 다 가능하면 최소값으로
			System.out.println(Math.min(onlypm, numuse));
		}
		
		
		
	}//end of main method
}//end of class
