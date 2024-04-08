package August_4;

import java.util.Scanner;
import java.io.FileInputStream;
/*알고리즘 설명
 * 1. 문제가 도저히 안풀려서 다른 사람들 것 봤음. 나중에 다시 풀어볼 것.
 * 2. Dynamic Programming 이용
 * 3. 우선 월간 이용권과 일간 이용권을 비교. 각 달별로 1일 이용권 샀을 경우와 월간 이용권 샀을 경우 중 최소 비용을 저장.
 * 4. 3달 이용권, dP사용. n-1월 까지의 비용에 price[n]더하기 vs n-3월 까지의 비용에 quarter 더하기.
 * 5. dP[11]에 있는 누적 최소 비용과 1년 이용권 비교해 답 출력.
 * */
class KTW_SWEA_1952
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//가격 저장
            int[] price = new int[12];
            int daily = sc.nextInt();
            int monthly = sc.nextInt();
            int quarter = sc.nextInt();
            int annually = sc.nextInt();
            
            int[] day = new int[12];
            for(int i = 0; i < 12; i++) {//매 달 가는 일 수 저장
            	day[i] = sc.nextInt();
            }
            
            //일간권일 경우 매달 가격
            for(int i = 0; i < 12; i++) {
            	price[i] = day[i] * daily;
            }
            
            //월간권으로 이득은?
            for(int i = 0; i < 12; i++) {
            	if(price[i] > monthly) {
            		price[i] = monthly;
            	}
            }
            
            //3개월 권으로 이득은?
            //DP사용
            int[] dP = new int[12];
           	dP[0] = price[0];
            dP[1] = dP[0] + price[1];
            dP[2] = Math.min(dP[1] + price[2], quarter);
            for(int i = 3; i < 12; i++){//3개월 전 최소 + 3개월 이용권 vs 1개월 전 최소비용 + 이번달 최소 비용
            	dP[i] = Math.min(dP[i-1] + price[i], dP[i-3] + quarter);
            }
            
            //결과 출력, dP[11]과 1년 이용권 비교
            System.out.println("#" + test_case + " " + Math.min(dP[11], annually));
            
            

		}
	}
}