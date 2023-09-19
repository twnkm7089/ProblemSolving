import java.io.IOException;
import java.util.Scanner;

/*알고리즘 설명
 * 1. 전위 탐색 결과 순서로 BST에 넣으면 원본 트리 복원 가능
 * 2. 해당 트리로 후위탐색 진행
 * */

public class KTW_BOJ_5639_G5_이진검색트리 {
	//tree 위한 노드 생성
	static class Node {
		int value;
		Node left;
		Node right;
		Node parent;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//input
		Scanner sc = new Scanner(System.in);
		Node rootNode = null;
		
		while(sc.hasNext()) {
			int data = sc.nextInt();
			
			//트리 생성, 전위 탐색 순서로 insert하면 트리 복원 가능
			if(rootNode == null) {
				rootNode = new Node(data);
			} else {
				Node now = rootNode;
				while(true) {
					if(data < now.value) {//데이터가 노드보다 작다
						if(now.left == null) {//왼쪽 자식 없으면 추가
							now.left = new Node(data);
							break;
						} else {//있으면 이동
							now = now.left;
						}
					} else {//데이터가 노드보다 크다
						if(now.right == null) {//오른쪽 자식 없으면 추가
							now.right = new Node(data);
							break;
						} else {//있으면 이동
							now = now.right;
						}
					}
				}
			}
		}
		
		//후위 순회(LRV)
		postorder(rootNode);

		sc.close();
	}//end of main method
	
	public static void postorder(Node node) {
		if(node.left != null) {//L
			postorder(node.left);
		}
		if(node.right != null) {//R
			postorder(node.right);
		}
		System.out.println(node.value);//V
		return;
	}

}//end of class
