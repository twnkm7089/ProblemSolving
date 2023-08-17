import java.util.Scanner;

public class KTW_SWEA_7964_D3 {
	/*알고리즘 설명
	 * 1. 다음 차원관문까지의 거리를 세는 cnt와 정답 answer 변수 선언
	 * 2. 반복문으로 순회 돌며 map[i]가 1일 경우 cnt를 0으로 초기화
	 * 3. 아니면 cnt를 1씩 증가, 만약 cnt가 D에 도달했다면 answer을 1 늘리고 cnt를 0으로 초기화(해당 위치에 차원 관문 설치했다고 가정)
	 * 4. 결과 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//N, D 입력
            int N = sc.nextInt();
            int D = sc.nextInt();
            //map 입력 받기
            int[] map = new int[N];
            for(int i = 0; i < N; i++){
            	map[i] = sc.nextInt();
            }
            //cnt, answer 선언
            int cnt = 0;//다음 차원 관문까지 누적 거리
            int answer = 0;//정답
            for(int i = 0; i < N; i++){//순회 시작
            	if(map[i]==1){//관문 설치 시
                	cnt = 0;//누적 거리 초기화
                } else{//없을 시
                	cnt++;//거리 증가
                    if(cnt == D){//거리가 D에 도달시, 설치
                    	answer++;//정답 증가
                        cnt = 0;//누적거리 초기화
                    }
                }
            }//순회 종료
            //정답 출력
            System.out.println("#" + test_case + " " + answer);

		}//테스트케이스 종료
	}//end of main
}//end of class
