import java.util.Scanner;

public class KTW_SWEA_2805_D3 {
	/*알고리즘 설명
	 * 1. isIncrease를 이용해 다음 턴에 it_num을 증가시킬지, 감소시킬지 판단
	 * 2. blank를 이용해 생략할 숫자 개수를 정하고, blank + j에 해당하는 숫자(로 된 문자) 불러옴.
	 * 3. 해당 문자를 숫자로 변환 후 더하기. 이걸 it_num만큼 반복
	 * 4. 한 줄이 완료되면 다음 줄로... isIncrease 여부 보고 다음 줄의 탐색 범위 조절.
	 * 5. 결과 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();//농장 크기 입력
            String[] s = new String[N];//인자 입력
            for(int i = 0; i < N; i++){
            	s[i] = sc.next();
            }
            
            boolean isIncrease = true;//isIncrease를 true로 설정
            int blank = N / 2; //blank개수 설정
            int it_num = 1;//반복 횟수 설정
            int sum = 0;//sum설정
            for(int i = 0; i < N; i++){//각 줄별(행 우선)
            	for(int j = 0; j < it_num; j++){//반복 횟수만큼 반복하며
                	sum += (int)(s[i].charAt(blank + j) - '0');//숫자로 만들어 더하기
                }
                //최대치 도달시 감소 시작
                if(it_num == N){
                	isIncrease = false;
                }
                
                if(isIncrease == true){//다음 인자 조절
                	blank--;
                    it_num+=2;
                } else{
                	blank++;
                    it_num-=2;
                }
            }
			//결과 출력
            System.out.println("#" + test_case + " " + sum);
		}
	}
}

