import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class KTW_BOJ_2493_탑 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input N
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		//input height
		StringTokenizer st = new StringTokenizer(br.readLine());
		//스택 사용
		int[] height = new int[N];
		for(int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<int[]> stack = new Stack<>();
		int[] ans = new int[N];
		//(우 -> 좌)
		for(int i = N-1; i >= 0; i--) {
			//스택이 비어있거나 현재 스택의 맨 위에 있는 높이가 지금 높이 이상인 경우, 즉 지금 높이가 오른쪽에 있던 높이보다 낮은 경우
			if(stack.isEmpty() || stack.peek()[0] >= height[i]) {
				//스택에 높이, index정보 담은 배열 추가
				stack.push(new int[] {height[i], i});
			} else {
				//아닌 경우, 즉, 지금(왼쪽)의 높이가 오른쪽보다 높은 경우(충돌)
				//스택이 비거나 지금 높이가 오른쪽보다 낮을 때까지
				while(!stack.isEmpty() && stack.peek()[0] < height[i]) {
					//뽑아서 해당 index값 갱신
					int[] temp = stack.pop();
					ans[temp[1]] = i+1;
				}
				//push
				stack.push(new int[] {height[i], i});
			}
		}

		
		//result output
		for(int i = 0; i < N; i++) {
			System.out.print(ans[i] + " ");
		}
		System.out.println();
		
		
	}
}
