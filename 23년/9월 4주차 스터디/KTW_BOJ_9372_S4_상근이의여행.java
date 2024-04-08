import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 사실 인접리스트 생성하고 뭐하고 할 필요 없었다.
 * 2. 모든 섬을 방문해야 한다. -> 최소한의 경우의 수는 두 섬을 잇는 edge 하나씩만 필요하다. -> N-1이 최소개수
 * */
public class KTW_BOJ_9372_S4_상근이의여행 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[] visited = new int[N+1];
			//인접 리스트 생성 및 초기화
			List<Integer>[] adjArr = new ArrayList[N+1];
			for(int i = 1; i <= N; i++) {
				adjArr[i] = new ArrayList<>();
			}
			
			//인접 입력
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				adjArr[A].add(B);
				adjArr[B].add(A);
			}
			
			System.out.println(N-1);//다 필요없고 이것만 필요
			
			
		}
		
		
		
		
		
		br.close();
	}//end of main method
	
	
}//end of class
