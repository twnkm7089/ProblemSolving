import java.util.Scanner;

public class KTW_BOJ_2609_B1 {
	/*알고리즘 설명
	 * 1. A=Qa, B=Qb면(Q는 최대공약수) A*B/Q = Q*a*b니 최소공배수가 됨.
	 * 2. 이를 이용해 최대공약수만 구하면 최소공배수 구할 수 있다고 생각.
	 * 3. 처음에는 무식하게 최대공약수 구했는데(반복문으로 둘 다 나머지 0되는 경우 파악), 비효율적이라 생각해 추가적으로 찾아봄.
	 * 4. 유클리드 호제법이 있었음.
	 * 5. A%B=R이라 할 때, A, B의 최대공약수는 B, R의 최대공약수.
	 * 6. 이를 이용해 구함.
	 * */
	public static void main(String[] args) {
		//입력
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int gcd = 0;//최대공약수
		int lcm = 0;//최소공배수
		//최대공약수 구하는 식
		if(a > b) {
			gcd = GCD(a, b);
		} else {
			gcd = GCD(b, a);
		}
		//최소공배수 구하는 색
		lcm = a * b / gcd;
		//출력
		System.out.println(gcd);
		System.out.println(lcm);
	}
	//최대 공약수 공식(유클리드 호제법, 재귀함수)
	public static int GCD(int div, int r) {
		if(r == 0) {
			return div;
		}
		return GCD(r, div%r);
		
	}
	/*증명
	* A=aG, B=bG (G: A와 B의 최대공약수)
	* A = Bq + R이라 할 때, R = A - Bq = G(a - bq), 고로, B와 R의 최대공약수도 G(a, b가 서로수가 아니면 위의 식도 성립 불가)
	* A <- B, B <- R 해서 반복
	* R = 0이 되었다 치자, G는 존재하니 a = bq, A = Bq에서 Ga = Gbq니, 양 변 소거시, G = G, 즉, B = G였어야 가능.
	* 고로, 이 때의 B가 최대공약수. 이제 리턴 시작.
	*/
}
