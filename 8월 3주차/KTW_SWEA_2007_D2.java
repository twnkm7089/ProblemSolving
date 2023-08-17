import java.util.Scanner;

public class KTW_SWEA_2007_D2 {
	/*알고리즘 설명
	 * 1. 길이 1부터 10까지 실험
	 * 2. 우선 각 길이별로 test라는 boolean값 true로 설정.
	 * 3. for문을 돌며 s.charAt(i)값이 s.charAt(i+k)와 다르면 다른 문자열, false로 전환
	 * 4. test가 true면 정답으로 설정 후 반환, 아니면 k값 늘려서 검사
	 * 5. 결과 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//문자열 입력
            String s = sc.next();
            int answer = -1;
            for(int k = 1; k <= 10; k++){ //문자열 길이별 탐색
                boolean test = true; //boolean값 설정
            	for(int i = 0; i < k; i++){ //0부터 k-1까지
                	if(s.charAt(i) != s.charAt(i+k)){ //index i와 i+k가 다르면 다른 문자열
                    	test = false; //false
                    }
                }
                if(test == true){//문자열 단위 이거 맞음.
                	answer = k; //answer로 설정 후 break
                    break;
                } else{ //아니면 다음으로
                	continue;
                }
            }
			//결과 출력
            System.out.println("#" + test_case + " " + answer);
		}
	}
}
