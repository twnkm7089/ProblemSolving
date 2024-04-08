import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_BOJ_1011_G5_AlphaCentauri {
	//알고리즘 설명
	//중요한 것은 감속, 가속은 1씩만 가능하다는 것. 점진적으로 속도 증가하다 떨어져야 함.
	//양 끝에서 jump씩 감소하며 left_dist 0일 때까지 한다.
	//자세한 것은 그림 참고
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	//대칭을 만드는 법
        	//k가 (x+y)/2까지 증가하다 초과하면 감소하는 방식
        	int jump = 1;
        	int cnt = 0;
        	int left_dist = y - x; //남은 거리
        	while(left_dist > 0) {
        		if(left_dist > 2*jump) {//남은 거리가 jump 2배 초과
        			cnt += 2;
        			left_dist -= 2*jump;
        		} else {
        			//jump 2배 이하(최대 2번으로 끝)
        			if(left_dist <= jump) {
        				//한번에 jump 가능 거리
        				cnt += 1;
        			} else {
        				//두번은 jump해야
        				cnt += 2;
        			}
        			left_dist = 0;
        		}
        		//jump 거리 1씩 증가
        		jump++;
        	}
        	
        	//결과 출력
        	System.out.println(cnt);
        }
    }//end of main method
    
    
    
    
}