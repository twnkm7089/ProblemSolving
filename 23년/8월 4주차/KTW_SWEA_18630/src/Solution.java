import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		//input
		int N = sc.nextInt();
		//switch 배열 입력
		int[] sw = new int[N];
		for(int i = 0; i < N; i++) {
			sw[i] = sc.nextInt();
		}
		//동작부, 사람 수 입력 후 스위치 번호 입력, 그 후 작업 시작
		int M = sc.nextInt();
		for(int i = 0; i < M; i++) {
			int st = sc.nextInt();
			int swNum = sc.nextInt();
			if(st == 1) {//남성
				int pos = swNum - 1;//스위치 번호는 1부터 시작하니 보정
				while(pos < N) {
					//switch 전환
					if(sw[pos] == 0) {//0이면 1로
						sw[pos] = 1;
					} else {//1이면 0으로
						sw[pos] = 0;
 					}
					pos += swNum;//보정
				}
			} else {//여성
				int center = swNum - 1;//중심
				int far = 0;//중심으로부터 떨어진 거리
				while(true) {
					//좌, 우 인덱스 지정
					int left = center - far;
					int right = center + far;
					
					if(left >= 0 && right < N && sw[left] == sw[right]) {//좌우대칭
						if(sw[left] == 0) {//0이면 1로
							sw[left] = 1;
							sw[right] = 1;
						} else {//1이면 0으로
							sw[left] = 0;
							sw[right] = 0;
						}
					} else {//범위초과, 좌우대칭 아님
						break;
					}
					
					far++; //거리 1 증가
				}
			}
		}
		
		//출력부
		for(int i : sw) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		
		
		
	}//end of main
}//end of class