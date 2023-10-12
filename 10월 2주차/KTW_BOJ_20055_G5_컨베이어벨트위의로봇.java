import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class KTW_BOJ_20055_G5_컨베이어벨트위의로봇 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int zero = 0;
		//내구도 배열
		int[] conveir = new int[2*N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2*N; i++) {
			conveir[i] = Integer.parseInt(st.nextToken());
			if(conveir[i] == 0) zero++;
		}
		
		//로봇 위치 저장, true면 해당 칸에 존재
		boolean[] robot = new boolean[2*N];
		int stage = 0;
		while(zero < K) {
			stage++;
			
			//벨트 회전
			int temp = conveir[2*N-1];
			for(int i = 2*N-1; i > 0; i--) {
				conveir[i] = conveir[i-1];
				robot[i] = robot[i-1];
			}
			conveir[0] = temp;
			robot[0] = false;
			
			//로봇 하차
			if(robot[N-1] == true) robot[N-1] = false;
			
			//로봇 이동
			for(int i = N-1; i >= 0; i--) {
				if(robot[i] == true && robot[i+1] == false && conveir[i+1] > 0) {
					robot[i+1] = true;
					robot[i] = false;
					conveir[i+1]--;
					if(conveir[i+1] == 0) zero++;
				}
			}
			
			//로봇 하차
			if(robot[N-1] == true) {
				robot[N-1] = false;
			}
			
			
			//로봇 승차
			if(conveir[0] > 0) {
				robot[0] = true;
				conveir[0]--;
				if(conveir[0] == 0) zero++;
			}
			
		}
		//결과 출력
		System.out.println(stage);
	}//end of main method
	
}//end of class
