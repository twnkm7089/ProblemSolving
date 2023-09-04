import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class KTW_BOJ_2078_S2 {
	/*알고리즘 설명
	 * 1. 부모노드 추적
	 * 2. 현재 노드가 c,d라고 하자. c>d면 왼쪽자식, c<d면 오른쪽 자식이다. 이 여부는 카운팅한다.
	 * 3. 그럼 부모 노드를 계산한다. c>d였으면 부모 노드는 (c-d, d). d>c였으면 (c, d-c)
	 * 4. 이걸 1,1이 될 때까지 반복한다.
	 * */
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int l = 0;
		int r = 0;
		while(A != 1 || B != 1) {
			//부모노드 추적
			if(A > B) {//왼쪽 자식이었으면
				l++;//카운팅
				int c = A;
				int d = B;
				//부모노드 갱신
				A = c-d;
				B = d;
			} else if(A < B) {//오른쪽 자식이었음
				r++;//카운팅
				int c = A;
				int d = B;
				//부모노드 갱신
				A = c;
				B = d-c;
			}
		}
		//출력
		System.out.println(l + " " + r);
		
	}
	
}
