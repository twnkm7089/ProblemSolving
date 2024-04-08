import java.util.Scanner;

public class KTW_SWEA_1289_D3 {
	/*알고리즘 설명
	 * 1. 첫 문자 1이면 cnt 1 증가
	 * 2. 문자가 변하는 지점마다 cnt증가.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			String s = sc.next();
            int cnt = 0;
            if(s.charAt(0) == '1'){ //첫문자 1이면 cnt 증가
            	cnt++;
            }
            for(int i = 1; i < s.length(); i++){ //문자 변하는 지점마다 증가
            	if(s.charAt(i) != s.charAt(i-1)){
                	cnt++;
                }
            }
            
            System.out.println("#" + test_case + " " + cnt); //출력

		}
	}
}
