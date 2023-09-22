import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class KTW_BOJ_1991_S1_트리순회 {
	public static char[][] tree;
	public static StringBuilder str = new StringBuilder();
	/*알고리즘 설명
	 * 1. 재귀를 이용해 구현했다.
	 * 2. index는 해당 char - 'A'로 구현
	 * 3. 아래 코드 참조
	 * */
	
	public static void main(String[] args) throws IOException {
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new char[N][3];
		//tree A~Z순 표시
		for(int i = 0; i < N; i++) {
			tree[i][0] = (char) ('A' + i);
		}
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			//input
			char vertex = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			int idx = vertex - 'A';//index 접근
			//left, right 저장
			tree[idx][1] = left;
			tree[idx][2] = right;
		}
		//돌리기
		preorder(0);
		str.append("\n");
		inorder(0);
		str.append("\n");
		postorder(0);
		System.out.println(str.toString());
		br.close();
	}//end of main method

	//preorder
	public static void preorder(int idx) {
		str.append(tree[idx][0]);//출력(V)
		if(tree[idx][1] != '.') {//L
			preorder(tree[idx][1] - 'A');
		}
		if(tree[idx][2] != '.') {//R
			preorder(tree[idx][2] - 'A');
		}
	}
	
	//inorder
	public static void inorder(int idx) {
		if(tree[idx][1] != '.') {//L
			inorder(tree[idx][1] - 'A');
		}
		str.append(tree[idx][0]);//V
		if(tree[idx][2] != '.') {//R
			inorder(tree[idx][2] - 'A');
		}
	}

	//postorder
	public static void postorder(int idx) {
		if(tree[idx][1] != '.') {//L
			postorder(tree[idx][1] - 'A');
		}
		if(tree[idx][2] != '.') {//R
			postorder(tree[idx][2] - 'A');
		}
		str.append(tree[idx][0]);//V
	}	
	
}//end of class
