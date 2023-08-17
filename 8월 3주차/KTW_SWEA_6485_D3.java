import java.util.Scanner;

public class KTW_SWEA_6485_D3 {
	/*알고리즘 설명
	 * 1. 버스정류장을 5001 크기의 배열로 만들어 버스정류장 번호를 인덱스에 일치시킵니다.
	 * 2. A, B를 입력 받은 후 A이상 B이하의 인덱스에 위치한 배열의 값을 1씩 늘려줍니다.
	 * 3. 입력 받은 정류장 번호에 해당하는 인덱스의 값을 출력합니다.
	 * 4. 주의사항은 버스정류장 배열 크기를 5001로 해야 오류가 안 납니다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//노선 개수 입력 및 A, B 저장 배열 생성
            int N = sc.nextInt();
            int[] A = new int[N];
            int[] B = new int[N];
            
            for(int i = 0; i <N; i++){//input, Ai, Bi 값 입력
            	A[i] = sc.nextInt();
                B[i] = sc.nextInt();
            }//end of input
            
            int[] bus_stop = new int[5001];//버스정류장 배열, 인덱스가 정류장 번호
            for(int i = 0; i < N; i++){//정류장 지나는 버스 표시
            	for(int j = A[i]; j <= B[i]; j++){//A이상 B이하 노선에 버스가 다니니 값 1씩 추가
                	bus_stop[j]++;
                }
            }//정류장 지나는 버스 표시
            
            //출력
            int P = sc.nextInt();//출력 갯수 입력
            System.out.print("#" + test_case + " ");
            for(int i = 0; i < P; i++){//해당 정류장 결과 출력
            	int idx = sc.nextInt();//입력받은 인덱스에 해당하는 값 출력
                System.out.print(bus_stop[idx] + " ");
            }//해당 정류장 결과 출력
            System.out.println();//줄 바꿈

		}//end of test case
	}
}
