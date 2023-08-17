import java.util.Arrays;
import java.util.Scanner;

public class KTW_SWEA_1860_D3 {
	/*알고리즘 설명
	 * 1. 사람들의 도착 시간은 오름차순으로 정렬한다.
	 * 2. a초 후 시점에 만들어진 총 붕어빵의 개수는 (a/M)*K개다.
	 * 3. 그 때까지 도착한 사람의 개수를 빼었을 때, 음수가 나오면 불가능하다.
	 * 4. 이러한 원리를 이용해 문제를 계산했다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//N, M, K 입력
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            //도착시간 입력
            int[] arrive = new int[N];
            for(int i = 0; i < N; i++){
            	arrive[i] = sc.nextInt();
            }
            
            Arrays.sort(arrive);//도착시간 오름차순 정렬
            //정답값, 기본은 가능.
            int answer = 0;//0:possible 1:impossible
            for(int i = 0; i < N; i++){
            	if((((arrive[i]/M)*K) - (i+1)) < 0){//(arrive[i]/M)*K는 arrive[i]초까지 만든 총 붕어빵 개수, i+1은 그 때까지 방문한 사람 명수, 음수면 문제 발생
                	answer = 1;//불가능
                    break;//탐색 종료
                }
            }
            //정답 출력
            if(answer == 0){
            	System.out.println("#" + test_case + " Possible");
            } else{
            	System.out.println("#" + test_case + " Impossible");
            }

		}//test case end
	}//main end
}//class end
