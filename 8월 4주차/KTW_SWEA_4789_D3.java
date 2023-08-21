package August_4;

import java.util.Scanner;

/*알고리즘 설명
 * 1. sum을 통해 기립 박수 하는 사람(고용인 제외)의 누적합을 구함
 * 2. cnt로 기립박수 고용 알바 수 구함.
 * 3. i번째 사람을 일으켜 세우기 위해서는 i명이 박수를 칠 필요 존재, i - sum - cnt가 0보다 크면(누적 인원+알바 수가 부족하면) 해당 인원만큼 cnt 추가(추가 고용)
 * 4. 그 후 sum에 해당 i번째에 있던 사람 구해 이들이 기립박수 인원 추가됨 반영하고 반복
 * */

public class KTW_SWEA_4789_D3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            String str = sc.next();
            
            int cnt = 0;//알바생
            int sum = 0;//그냥 관객(누적 기립박수 인원수)
            sum += str.charAt(0) - '0';//아무도 안 일어나도 박수치는 사람 수
            for(int i = 1; i < str.length(); i++){//그 다음부터 비교
            	if((i - sum - cnt) > 0){//인원수 부족 시
                	cnt += i - sum - cnt;//그 만큼 알바 추가 고용
                }
                sum += str.charAt(i) - '0';//이제 기립한 인원도 추가
            }
            System.out.println("#" + test_case + " " + cnt);//결과 출력

		}
	}
}
