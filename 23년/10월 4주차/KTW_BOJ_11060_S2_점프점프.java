import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KTW_BOJ_11060_S2_점프점프 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //input
        int N = Integer.parseInt(br.readLine());
        
        int[] map = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	map[i] = Integer.parseInt(st.nextToken());
        }
        
        //dP배열 초기화
        int[] dP = new int[N];
        Arrays.fill(dP, Integer.MAX_VALUE);
        dP[0] = 0;
        
        for(int i = 0; i < N; i++) {
        	//도달 불가능 위치였다면 continue
        	if(dP[i] == Integer.MAX_VALUE) {
        		continue;
        	}
        	
        	int num = map[i];
        	//점프 가능한 위치면 현재 위치서 점프 vs 기존 값 중 작은 값으로...
        	for(int j = 1; j <= num; j++) {
        		if((i + j) < N) {
        			dP[i+j] = Math.min(dP[i+j], dP[i] + 1);
        		} else {
        			break;
        		}
        	}
        }
        //도달 못하면 -1로
        if(dP[N-1] == Integer.MAX_VALUE) {
        	dP[N-1] = -1;
        }

        //결과 출력
        System.out.println(dP[N-1]);
    }//end of main method
    
    
}