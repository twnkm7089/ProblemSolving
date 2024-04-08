import java.util.Scanner;

public class KTW_SWEA_1493_D3 {
	/*알고리즘 설명
	 * 1. 우선 &를 구했습니다. 대각선의 경우 x와 y의 값을 더한 값이 일정하다는 점을 이용하였습니다.
	 * 2. num이 p나 q에 도달하면 해당 수를 저장하고, 다음 인덱스로 가며 num을 더하고, i의 값을 1 더하고, j의 값은 N-x로 하였습니다.
	 * 3. j가 0이 되면 해당 대각선은 끝났다고 생각하고 N을 1 더해 다음 대각선으로 이동, 그 후, i, j값을 새로 초기화했습니다.
	 * 4. 구한 &값을 이용해 덧셈을 수행하고, 나온 값을 이용해 위와 같은 방식으로 이번에는 i, j값이 일치할 때의 num을 이용해 # 연산을 수행했습니다.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int p = sc.nextInt();
            int q = sc.nextInt();
            //x, y of p, q 초기화
            int xp = -1;
            int xq = -1;
            int yp = -1;
            int yq = -1;
            
            int N = 2;//처음 시작 때 i + j = 2
            int i = 1; //i = 1로 초기화
            int j = N - i;//j = N - i(일정한 더한 값 유지)
            int num = 1; //num은 해당 좌표에 들어갈 수
            if(p > q){//q가 p보다 작아 p까지 돌아야 함.
            	while(num <= p){ //최대 p까지 돌기
                	if(num == q){ //&q
                    	xq = i;
                        yq = j;
                    }
                    if(num == p){ //&p
                    	xp = i;
                        yp = j;
                        break;
                    }
                    //next index
                    num++;
                    i ++; //i 증가
                    j = N - i; //j 새로 계산
                    if(j == 0){ //다음 대각선으로 이동
                    	N++;
                        i = 1;
                        j = N - i;
                    }
                }//& search
            } else{//p가 q보다 작아 q까지 돌아야 함.
            	while(num <= q){//최대 q 까지 돌기
                    if(num == p){ //&p
                    	xp = i;
                        yp = j;
                    }
                	if(num == q){ //&q
                    	xq = i;
                        yq = j;
                        break;
                    }
                    //next index
                    num++;
                    i ++; //i 증가
                    j = N - i; //j 새로 계산
                    if(j == 0){ //다음 대각선 이동
                    	N++;
                        i = 1;
                        j = N - i;
                    }
            	}//& search
			}//& search finish
            //(&(p) + &(q))
            int x = xp + xq;
            int y = yp + yq;
            //#(&(p) + &(q)) 구하기
            //초기화
            N = 2;
            i = 1;
            j = N - i;
            num = 1;
            //# search
            while(!(i == x && j == y)){ //좌표 일치 시까지
            	//인덱스 변환
                num++;
                i++;
                j = N - i;
                if(j == 0){
                    N ++;
                    i = 1;
                    j = N - i;
                }
            }
            //최종 결과 출력
            System.out.println("#"+ test_case + " " + num);
		}//end of test case
	}
}
