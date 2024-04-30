import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14719_G5_빗물 {	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] height = new int[W];
		for(int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int ans = 0;
		//한줄씩 검사, 채워진 칸에서 다음 칸까지
		for(int i = 1; i <= H; i++) { //각 height
			boolean flag = false;
			int temp = 0;
			for(int j = 0; j < W; j++) {
				//더 높으면(벽을 만나다) flag 활성화
				if(height[j] >= i) {
					flag = true;
					
					ans += temp;
					temp = 0;
				} else if(flag) {
					//앞에 블록을 만난 뒤에 빈칸이 나오면 카운트
					temp++;
				}
			}
		}
		
		System.out.println(ans);
		
		br.close();
	}
	

}
