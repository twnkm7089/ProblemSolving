import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//input
			int N = sc.nextInt();
			int M = sc.nextInt();
			int C = sc.nextInt();
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			//각 경우의 수 별 최대 제곱수 저장 배열 생성
			int arrSize = N * (N-M+1);
			int[] result = new int[arrSize];
			int ridx = 0;
			//각 map 분석
			for(int i = 0; i < N; i++) {
				for(int j = 0; j <= N-M; j++) {
					int tempA = 0;
					for(int k = 0; k < M; k++) {
						result[ridx] += map[i][j+k] * map[i][j+k];
						tempA += map[i][j+k];
					}
					//유효성 검사
					if(tempA > C) {//더 큰 경우
						result[ridx] = 0;//초기화
						//배열 복사
						int[] tempArr = new int[M];
						for(int k = 0; k < M; k++) {
							tempArr[k] = map[i][j+k];
						}
						
						//M=1일 경우. 방법없음
						//M=2일 경우, 비교 후.
						if(M == 2) {
							Arrays.sort(tempArr);
							result[ridx] = tempArr[1] * tempArr[1];
							//C는 10이상이니 최대값의 제곱으로...
						}
						
						//M>=3일 경우
						//1. 정렬
						//2. index 0 부터 시작, C를 넘지않는 최대 제곱수 찾기.
						//3. 완전 탐색으로 모든 경우의수 찾기
						if(M >= 3) {
							Arrays.sort(tempArr);
							int maxsquare = 0;
							for(int k = 0; k < M; k++) {
								int temp = tempArr[k] * tempArr[k];
								int plusNum = tempArr[k];
								for(int t = M-1; t > k; t--) {//뒤부터 해서 최대값부터 곱함
									if(plusNum + tempArr[t] <= C) {
										plusNum += tempArr[t];
										temp += tempArr[t] * tempArr[t];
									}
								}
								
								if(temp > maxsquare) {//값 갱신
									maxsquare = temp;
								}
							}
							
							result[ridx] = maxsquare;

						}
						
					}//end of 유효성검사
					
					ridx++;//다음 인덱스로
					
				}//end of row search			
			}//end of search

			

			//최대값 찾기
			int max = 0;
			//결과 배열 탐색, 행, 열이 중복되지 않는 경우만.
			for(int i = 0; i < arrSize - 1; i++) {
				for(int j = i + 1; j < arrSize; j++) {
					//다른행, 또는 같은 행이어도 중복 안됨
					int eachRow = N - M + 1;
					//각 행별 차지하는 칸 수의 몫이 다르면 다른 행
					if(i / eachRow != j / eachRow) {//서로 다른 행
						if(result[i] + result[j] > max) {
							max = result[i]+result[j];
						}
					} else if((j % eachRow) - (i % eachRow) >= M) {//같은 행이어도 중복 안됨
						if(result[i] + result[j] > max) {
							max = result[i]+result[j];
						}
					}
				}
			}
			//결과 출력
			System.out.println("#" + test_case + " " + max);
			


		}//end of test case
	}//end of main method
}//end of class