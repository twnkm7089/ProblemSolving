import java.util.Scanner;

public class KTW_SWEA_4047_D3 {
	/*알고리즘 설명
	 * 1. 문자열을 3개 단위로 파악, 앞자리 보고 종류 파악, 뒷자리 보고 숫자 파악
	 * 2. 4*13 배열 생성, 각 행은 카드 종류(S,D,H,C 순), 각 열은 카드 번호
	 * 3. index는 0부터 시작이니 카드번호-1에 해당하는 인덱스의 값을 증가시켜 카운팅, 2개 나오면 ERROR(중복)
	 * 4. 종료 후, 중복 있었으면 에러 출력, 아니면 각 행별 행 우선 순회 돌며 값이 0인 칸 수 세서 필요한 카드 장수 파악, 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            String str = sc.next();
            int[][] cards = new int[4][13];//카드 배열 S, D, H, C 각 13칸
            

            int answer = 0;//0:no error 1 : error
            for(int i = 0; i < str.length(); i+=3){//문자열 순회, 3칸 단위
            	if(str.charAt(i) == 'S'){//맨 앞이 S
                	String tmp = str.substring(i+1, i+3);//뒤의 2칸 분리
                    int a = Integer.parseInt(tmp);//정수화
                    cards[0][a - 1]++;//해당 인덱스 추가
                    if(cards[0][a - 1] > 1){//즁복판별
                    	answer = 1;//중복 시 error
                        break;//순회 종료
                    }//나머지 모두 이런 방식
                } else if(str.charAt(i) == 'D'){//D
                	String tmp = str.substring(i+1, i+3);
                    int a = Integer.parseInt(tmp);
                    cards[1][a - 1]++;
                    if(cards[1][a - 1] > 1){
                    	answer = 1;
                        break;
                    }
                } else if(str.charAt(i) == 'H'){//H
                	String tmp = str.substring(i+1, i+3);
                    int a = Integer.parseInt(tmp);
                    cards[2][a - 1]++;
                    if(cards[2][a - 1] > 1){
                    	answer = 1;
                        break;
                    }
                } else if(str.charAt(i) == 'C'){//C
                	String tmp = str.substring(i+1, i+3);
                    int a = Integer.parseInt(tmp);
                    cards[3][a - 1]++;
                    if(cards[3][a - 1] > 1){
                    	answer = 1;
                        break;
                    }
                } 
            }//end of array 추가
            
            //output
            if(answer == 1){//error 발생(중복)
            	System.out.println("#" + test_case + " ERROR");
            } else {//그 외
            	//조회 후 빈칸 출력 S,D,H,C순
                System.out.print("#" + test_case + " ");
                for(int i = 0; i < 4; i++){//순회 돌기
                	int cnt = 0;
                    for(int j = 0; j < 13; j++){//빈칸 갯수 세기
                    	if(cards[i][j] == 0){
                        	cnt++;
                        }
                    }
                    System.out.print(cnt + " ");
                }
                System.out.println();//줄바꿈
            }//end of output

		}//end of test case
	}//end of main
}//end of class
