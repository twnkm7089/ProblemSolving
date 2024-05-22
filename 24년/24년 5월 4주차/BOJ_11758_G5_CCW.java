import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11758_G5_CCW {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		//input
		int[][] coord = new int[3][2];
		
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 2; j++) {
				coord[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//vectors
		int a1 = coord[1][0] - coord[0][0];
		int a2 = coord[1][1] - coord[0][1];
		int b1 = coord[2][0] - coord[1][0];
		int b2 = coord[2][1] - coord[1][1];
		
		//vector cross product
		if((a1*b2 - a2*b1) < 0) {
			System.out.println(-1); //CW(ClockWise)
		} else if((a1*b2 - a2*b1) > 0) {
			System.out.println(1); //CCW(Counter ClockWise)
		} else {
			System.out.println(0); //same line
		}
		
		br.close();
		
	}
}
