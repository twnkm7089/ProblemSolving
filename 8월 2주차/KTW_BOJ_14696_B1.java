import java.util.Scanner;

public class KTW_BOJ_14696_B1 {
	public static void main(String[] args) {
		/*알고리즘 설명
		 * 1. 입력 받은 후 if-else if-else로 별, 동그라미, 네모, 세모 수 비교 반복
		 * 2. 결과 출력
		 * */
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 0; i < T; i++) {
			//A 입력
			int na = sc.nextInt();
			int[] a = new int[na];
			for(int j = 0; j < na; j++) {
				a[j] = sc.nextInt();
			}
			//B 입력
			int nb = sc.nextInt();
			int[] b = new int[nb];
			for(int j = 0; j < nb; j++) {
				b[j] = sc.nextInt();
			}
			//A, B 별 개수 비교
			int star_a = 0;
			int star_b = 0;
			for(int j = 0; j < na; j++) {
				if(a[j] == 4) {
					star_a++;
				}
			}
			for(int j = 0; j < nb; j++) {
				if(b[j] == 4) {
					star_b++;
				}
			}
			if(star_a > star_b) { //별 개수 A가 더 많음
				System.out.println("A");
				continue;
			} else if (star_b > star_a) { //B가 더 많음
				System.out.println("B");
				continue;
			} else { //같아서 동그라미 수 비교
				int round_a = 0;
				int round_b = 0;
				for(int j = 0; j < na; j++) {
					if(a[j] == 3) {
						round_a++;
					}
				}
				for(int j = 0; j < nb; j++) {
					if(b[j] == 3) {
						round_b++;
					}
				}
				
				if(round_a > round_b) { //동그라미 A가 더 많음
					System.out.println("A");
					continue;
				} else if (round_b > round_a) { //B가 더 많음
					System.out.println("B");
					continue;
				} else { //같아서 네모 개수 비교
					int square_a = 0;
					int square_b = 0;
					for(int j = 0; j < na; j++) {
						if(a[j] == 2) {
							square_a++;
						}
					}
					for(int j = 0; j < nb; j++) {
						if(b[j] == 2) {
							square_b++;
						}
					}
					
					if(square_a>square_b) { //네모 A가 더 많음
						System.out.println("A");
						continue;
					} else if(square_b > square_a) { //B가 더 많음
						System.out.println("B");
						continue;
					} else { //같아서 삼각형 개수 비교
						int tri_a = 0;
						int tri_b = 0;
						for(int j = 0; j < na; j++) {
							if(a[j] == 1) {
								tri_a++;
							}
						}
						for(int j = 0; j < nb; j++) {
							if(b[j] == 1) {
								tri_b++;
							}
						}
						
						
						if(tri_a > tri_b) { //삼각형 A가 더 많음
							System.out.println("A");
							continue;
						} else if(tri_b > tri_a) { //B가 더 많음
							System.out.println("B");
							continue;
						} else { //같은 개수
							System.out.println("D");
							continue;
						}
					}
					
					
				}
			}
		}
	}
}
