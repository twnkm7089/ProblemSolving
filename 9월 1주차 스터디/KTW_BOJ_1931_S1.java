package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 종료시간을 기준으로 오름차순 정렬(종료시간이 같으면 시작시간 기준)
 * 2. 시작시간이 이전 종료시간보다 크거나 같으면서 종료시간이 제일 작은 경우 cnt를 올리고 종료시간 갱신
 * 3. 이 작업을 반복해 수를 세면 됨.
 * */

public class KTW_BOJ_1931_S1 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int N = Integer.parseInt(br.readLine());
		//회의시간 저장 배열 생성 및 입력
		Conf[] time = new Conf[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			time[i] = new Conf(start, end);
		}
		//정렬
		Arrays.sort(time);
				
		int endtime = 0;//이전 종료시간(0으로 초기화)
		int cnt = 0;//회의 개수 초기화
		//종료시간 적은 것부터 비교, endtime(이전 종료시간)보다 시작시간이 뒤이면 됨.
		for(int i = 0; i < N; i++) {
			//정렬 했으니 처음부터 불러오기
			int st = time[i].start;
			int ed = time[i].end;
			
			if(st >= endtime) {//시작시간이 이전 종료시간 이후, 정렬 이후니 그러면서 종료시간이 제일 가까움.
				cnt++;//카운팅
				endtime = ed;//이전 종료시간 갱신
			}
		}
		
		System.out.println(cnt);//결과 출력
		
	}//end of main method
	
	//사용자 정의 class
	static class Conf implements Comparable<Conf>{
		int start; //시작시간
		int end; //종료시간
		//생성자
		public Conf(int start, int end) {
			this.start = start;
			this.end = end;
		}
		//확인용 toString()
		public String toString() {
			return "start= " + start + " end= " + end;
		}
		//정렬기준
		public int compareTo(Conf o) {
			if(this.end == o.end) {//종료시간 같으면 시작시간 기준
				return this.start - o.start;
			}
			return this.end - o.end;//기본은 종료시간 기준
		}
	}
	
}//end of class
