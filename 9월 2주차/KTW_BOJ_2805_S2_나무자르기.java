import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*알고리즘 설명
 * 1. 이진탐색 이용
 * 2. 0~최대높이를 범위로 두고 이진탐색 실시
 * 3. 자르는 높이를 탐색하며 조건 만족(M이상 가져감)인 경우면서 최대높이면 갱신 
 * */
public class KTW_BOJ_2805_S2_나무자르기 {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//input
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] tree = new int[N];
		int max=Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			//입력 및 최대높이 찾기.
			tree[i] = Integer.parseInt(st.nextToken());
			
			if(max < tree[i]) {
				max = tree[i];
			}
		}
		
		//이진탐색
		int answer = 0;
		int start = 0;
		int end = max;
		int cut = (start+end)/2;
		while(start <= end) {
			long cut_res = 0;//자른 결과 누적
			for(int i = 0; i < N; i++) {
				if(tree[i] > cut) {
					cut_res += (tree[i]-cut);
				}
			}
			//가져가는 높이가 M 이상인 경우
			if(cut_res >= M) {
				start = cut+1;//범위 갱신
				answer = Math.max(cut, answer);//최대값 갱신
			} else {//그 외
				end = cut-1;//범위 갱신
			}
			
			cut = (start+end)/2;//다음 탐색높이
		}
		//결과 출력
		System.out.println(answer);
		br.close();
		
		
		
	}
	
}
