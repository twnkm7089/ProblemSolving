import java.util.Scanner;

public class KTW_SWEA_7087_D3 {
	/*알고리즘 설명
	 * 1. A~Z에 해당하는 26칸의 int형 배열을 만듭니다.
	 * 2. 문자열을 입력받은 후, 제목 맨 앞자리 - 'A'의  index의 값을 1 추가합니다.
	 * 3. 그 후, 26칸 배열을 처음부터 순회하며 0이 나올 때까지 answer의 값을 더합니다.
	 * 4. 그럼 답이 나옵니다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{//test case start
			//입력
            int N = sc.nextInt();
            String[] s = new String[N];
            for(int i = 0; i < N; i++){
            	s[i] = sc.next();
            }
            //26칸 알파벳 배열
            int[] alphabet = new int[26];
			for(int i = 0; i < N; i++){//제목 맨 앞에 해당하는 알파벳의 번호를 인덱스로 값 추가
            	alphabet[(s[i].charAt(0) - 'A')]++;
            }//index 추가 종료
            //정답찾기
            int answer = 0;
            for(int i = 0; i < 26; i++){//배열 순회
            	if(alphabet[i] == 0){//중간에 끊기는 곳에서 break
                	break;
                }
                answer++;//값 추가
            }//순회 종료
            //출력
            System.out.println("#" + test_case + " " + answer);
		}//test case end
	}
}
