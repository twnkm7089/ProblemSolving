import java.io.IOException;
import java.util.Scanner;

/*알고리즘 설명
 * 1. f(N)=f(N/2)*f(N/2)(if N%2==0)
 * 2. f(N)=f((N-1)/2)*f((N-1)/2)*f(1)(if N%2==1)
 * 3. 을 이용해 재귀함수로 구현.
 * 4. B가 int범위 넘어가는 것,
 * 5. //   2    1
 * 6. //1000 1000
 * 7. //1000 1000 
 * 8. 의 경우도 중요
 * */
public class KTW_BOJ_10830_G4_행렬제곱 {
	public static int[][] matrix;
	public static int[][] answer;
	public static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//input
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		long B = sc.nextLong();
		//matrix 생성 및 입력
		matrix = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				matrix[i][j] = sc.nextInt();
			}
		}
		//결과 출력
		int[][] answer = find_ans(B);//재귀함수
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(answer[i][j] + " ");
			}
			System.out.println();
		}

	}
	//재귀 함수 구현
	public static int[][] find_ans(long B){
		if(B == 1) {//B==1일 경우, 위의 예외 처리 위해 1000으로 나눈 나머지 복제 후 반환.
			int[][] temp = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0;j < N; j++) {
					temp[i][j] = matrix[i][j]%1000;
				}
			}
			return temp;
		}
		//재귀부
		if(B % 2 == 0) {//짝수면 f(B/2)의 행렬곱
			int[][] temp = find_ans(B/2);
			return matMul(temp, temp);
		} else {//홀수면 f((B-1)/2)의 행렬곱에 f(1) 행렬곱
			int[][] temp = find_ans((B-1)/2);
			return matMul(matMul(temp, temp),matrix);
		}
	}
	//행렬 곱 구현
	public static int[][] matMul(int[][] a, int[][] b) {
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int sum = 0;
				for(int k = 0; k < N; k++) {//주기적으로 나머지 구해 범위 초과 방지
					sum += a[i][k]*b[k][j]%1000;
					sum %= 1000;
				}
				temp[i][j] = sum % 1000;
			}
		}
		return temp;//반환
	}
}
