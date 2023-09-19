import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 다이나믹 프로그래밍
 * 2. 삼각형을 2차원 배열로 저장해 바로위와 대각선 위(그림에서는 대각선 위 두방향)중 큰값이랑 원래 수 더하며 누적합 도출
 * 3. 마지막 줄 비교해 그 중 최대값 출력
 * */
public class KTW_BOJ_1932_S1_정수삼각형 {
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][] tri = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j <= i; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//누적합
		for(int i = 1; i < N; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0) {//왼쪽 줄은 그냥
					tri[i][j] += tri[i-1][j];
				} else {//그 외는 대각선 왼쪽 위랑 그냥 위 비교
					tri[i][j] += Math.max(tri[i-1][j-1], tri[i-1][j]);
				}
			}
		}
		
		//최대 찾기
		int max = 0;
		for(int j = 0; j < N; j++) {//마지막 줄 중 최대값 탐색
			max = Math.max(max, tri[N-1][j]);
		}
		//결과 출력
		System.out.println(max);
		
		br.close();
	}//end of main method

}//end of class
