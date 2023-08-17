import java.util.Scanner;

public class KTW_SWEA_4698_D3 {
	/*알고리즘 설명
	 * 1. 에라토스테네스의 채 이용
	 * 2. 배열을 B+1만큼의 크기로 만들고, 2부터 시작해 p[i]==0이면 소수.
	 * 3. 소수를 찾으면 그 배수에 해당하는 2i, 3i, ...은 모두 p[i]=1로 바꾸어 소수 아님 표시.
	 * 4. 이를 끝까지 반복하는게 에라토스테네스의 채
	 * 5. answer라는 변수를 이용
	 * 6. A이상이니, 소수이면서 A이상인 경우, 그리고, String형태로 바꾸어, D라는 문자열을 포함하면 answer++
	 * 7. 이런 식으로 개수를 세어 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//입력
            String D = sc.next();
            int A = sc.nextInt();
            int B = sc.nextInt();
            //에라토스테네스 채 이용 위한 배열
            int[] p = new int[B+1];
            int idx = 0;//의미 없음
            int answer = 0;//정답 변수
            //0,1은 소수 아니니 1로 설정
            p[0] = 1;
            p[1] = 1;
            for(int i = 2; i <= B; i++){//소수 찾기
            	if(p[i] == 0){//소수이면
                    if(i >= A){//A이상일 경우
                    	String temp = String.valueOf(i);//문자열화
                		if(temp.contains(D)){//D를 포함하면
                			answer++;//정답 +1
                		}
                    }
                    p[i] = 1;//탐색 완료 표시
                    
                	for(int temp = 2*i; temp <= B; temp += i){//i의 배수 모조리 소수 아님 표시.
                		p[temp] = 1;
                	}
                }
            }//end of search(소수 찾기 끝)
            //결과출력
            System.out.println("#" + test_case + " " + answer);

		}//end of test_case
	}
}
