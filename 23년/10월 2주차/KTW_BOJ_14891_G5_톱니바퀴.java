import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class KTW_BOJ_14891_G5_톱니바퀴 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		String[] sawtooth = new String[5];
		//톱니 상태 input
		for(int i = 1; i < 5; i++) {
			sawtooth[i] = br.readLine();
		}
		
		int K = Integer.parseInt(br.readLine());
		//회전 개시
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			//톱니바퀴별 회전 방향
			int[] rotate = new int[5];
			rotate[num] = dir;
			//왼쪽으로 돌릴 방향 설정
			for(int a = num; a > 1; a--) {
				//N극과 S극이 맞닿아 있는 경우
				if(sawtooth[a].charAt(6) != sawtooth[a-1].charAt(2)) {
					//반대 방향으로
					rotate[a-1] = rotate[a]*(-1);
				} else {
					//아니면 더이상 탐색 불필요, break
					break;
				}
			}
			//오른쪽으로 돌릴 방향 설정
			for(int a = num; a < 4; a++) {
				//N극 S극 맞닿을 시 반대 방향
				if(sawtooth[a].charAt(2) != sawtooth[a+1].charAt(6)) {
					rotate[a+1] = rotate[a]*(-1);
				} else {
					//아니면 break
					break;
				}
			}
			
			//돌리기
			for(int a = 1; a < 5; a++) {
				//시계, 반시계에 따라 문자열 조정
				if(rotate[a] == -1) {
					sawtooth[a] = sawtooth[a].substring(1,8) + sawtooth[a].charAt(0);
				}
				if(rotate[a] == 1) {
					sawtooth[a] = sawtooth[a].charAt(7) + sawtooth[a].substring(0,7);
				}
			}
		}
		
		//결과
		int answer = 0;
		for(int i = 1; i < 5; i++) {
			//(톱니 첫 숫자)*2^(톱니번호-1)
			answer += Math.pow(2, i-1)*(sawtooth[i].charAt(0) - '0');
		}
		//결과 출력
		System.out.println(answer);
	}//end of main method
	
	
}//end of class
