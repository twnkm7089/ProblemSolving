import java.util.Scanner;

public class KTW_SWEA_3499_D3 {
	/*알고리즘 설명
	 * 1. for문에 여러 초기 식을 대입할 수 있음을 이용.
	 * 2. 앞 절반은 결과 배열 index의 0부터 시작해 2씩 증가하며 넣기.
	 * 3. 뒤 절반은 결과 배열 index의 1부터 시작해 2씩 증가하며 넣기.
	 * 4. 절반 나누는 과정의 문제 때문에 길이가 짝수, 홀수인 배열 나누어 계산
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            //입력
            String[] s = new String[N];
            for(int i = 0; i < N; i++){
            	s[i] = sc.next();
            }
            //결과 배열
            String[] result = new String[N];
            
            if(N % 2 == 0){//짝수 길이
            	for(int i = 0, idx = 0; i < N/2; i++, idx+=2){//앞 절반
                	result[idx] = s[i];
                }
                for(int i = N/2, idx = 1; i < N; i++, idx+=2){//뒤 절반
                	result[idx] = s[i];
                }
            } else{//홀수 길이
            	for(int i = 0, idx = 0; i <= N/2; i++, idx+=2){//앞 절반
                	result[idx] = s[i];
                }
                for(int i = (N/2)+1, idx = 1; i < N; i++, idx+=2){//뒤 절반
                	result[idx] = s[i];
                }
            }
			//출력
            System.out.print("#" + test_case + " ");
            for(int i = 0; i < N; i++){
            	System.out.print(result[i] + " ");
            }
            System.out.println();
		}
	}
}
