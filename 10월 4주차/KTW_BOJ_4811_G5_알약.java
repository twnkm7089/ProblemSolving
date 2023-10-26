import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KTW_BOJ_4811_G5_알약 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //input
        int N = Integer.parseInt(br.readLine());
        
        //미리 계산한 배열
        //dP[w][h]는 W가 w개, H가 h개인 문자열의 개수
        //dP[w][h]는 dP[w-1][h]의 맨 뒤에 'W'를 더하거나 dP[w][h-1] 뒤에 'H'를 더해서 만들어진다.
        //고로, dP[w][h] = dP[w-1][h] + dP[w][h-1]
        long[][] dP = new long[31][31];
        for(int i = 1; i <= 30; i++) {
        	dP[i][0] = 1;
        }
        dP[1][1] = 1;
        for(int w = 2; w <= 30; w++) {
        	for(int h = 1; h <= w; h++) {
        		dP[w][h] = dP[w-1][h] + dP[w][h-1];
        	}
        }
        //결과 출력
        while(N != 0) {
        	System.out.println(dP[N][N]);
        	N = Integer.parseInt(br.readLine());
        }
    }//end of main method
    
    
}