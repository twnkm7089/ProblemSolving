import java.util.Scanner;

public class KTW_SWEA_2005_D2 {
	/*알고리즘 설명
	 * 1. old_arr, new_arr 선언
	 * 2. new_arr의 첫번째, 마지막 원소는 1로
	 * 3. 나머지 부분은 old_arr의 [ㅓ-1] + [ㅓ-2]로.
	 * 4. new_arr 다 만들었으면 출력 후 new_arr을 old_arr에 복사.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            System.out.println("#"+test_case);
            int[] old_arr = new int[N];
            int[] new_arr = new int[N];
            for(int i = 1; i <= N; i++){
            	for(int j = 1; j <= i; j++){
                	if(j == 1 || j == i){ //처음, 마지막 원소
                    	new_arr[j-1] = 1;
                    } else { //그외
                    	new_arr[j-1] = old_arr[j-2] + old_arr[j-1];
                    }
                }
                for(int j = 0; j < i; j++){ //출력
                	System.out.print(new_arr[j] + " ");
                }
                System.out.println();
                old_arr = new_arr.clone(); //복사
            }

		}
	}
}
