import java.util.Scanner;

public class KTW_SWEA_7272_D3 {
	/*알고리즘 설명
	 * 1.한개의 구멍을 가진 알파벳 모아놓은 문자열 생성
	 * 2.문자열 개수 다르면 다른 문자열
	 * 3.같을 때, 처음부터 비교하며 두 문자열 각각 hole의 개수 저장, B면 2개, one_hole문자열 내에 있으면 1개, 나머지 0개
	 * 4.hole개수 비교 시 다르면 DIFF, 비교 종료까지 같으면 SAME
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//문자열 2개 입력
            String a = sc.next();
            String b = sc.next();
            
            //길이 다르면 DIFF출력 후 다음 test case로
            if(a.length() != b.length()){
            	System.out.println("#" + test_case + " DIFF");
                continue;
            }
            
            //길이 같은 경우
            String one_hole = "ADOPQR";//구멍 하나 알파벳 모아 놓은 문자열
            //hole개수 저장할 변수
            int holeA = -1;
            int holeB = -1;
            int answer = 0;//Same:0, Different:1
            for(int i = 0; i < a.length(); i++){//탐색 시작
            	//문자열에서 문자 추출
            	char tmp_a = a.charAt(i);
                char tmp_b = b.charAt(i);
                //hole A
                if(tmp_a == 'B'){//B면 2개
                	holeA = 2;
                } else if(one_hole.contains(tmp_a+"")){//one_hole문자열 내에 있으면(+""는 String 변환 위해 사용) 1개
                	holeA = 1;
                } else{//그외면 0개
                	holeA = 0;
                }
                //holeB
                if(tmp_b == 'B'){//B면 2개
                	holeB = 2;
                } else if(one_hole.contains(tmp_b+"")){//one_hole문자열 내에 있으면(+""는 String 변환 위해 사용) 1개
                	holeB = 1;
                } else{//그외면 0개
                	holeB = 0;
                }
                if(holeA != holeB){//hole개수 비교, 불일치시
                	answer = 1;//DIFF로 전환
                    break;//탐색 종료
                } 
            }
            //answer에 따른 답 출력
            if(answer == 0){//같으면
            	System.out.println("#" + test_case + " SAME");
            } else{//다르면
            	System.out.println("#" + test_case + " DIFF");
            }

		}//end of test case
	}//end of main
}//end of class
