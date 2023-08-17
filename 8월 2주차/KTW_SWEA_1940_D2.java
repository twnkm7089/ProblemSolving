import java.util.Scanner;

public class KTW_SWEA_1940_D2 {
	/*알고리즘 설명
	 * 1. 모드를 입력받음
	 * 2. 모드에 따라 0이면 그대로 dist(거리)에 speed(현재 속도) 더하기.
	 * 3. 1, 2면 입력 받은 value만큼 speed 증감 한 뒤, dist에 speed 추가.
	 * 4. 0보다 작으면 0으로 선언.
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int speed = 0;
            int dist = 0;
            int mode, value;
            for(int i = 0; i < N; i++){
            	mode = sc.nextInt(); //mode input
                if(mode == 0){ //maintain
                	dist += speed;
                } else if (mode == 1){ //accelerate
                	value = sc.nextInt();
                    speed += value;
                    dist += speed;
                } else{ //decelerate
                	value = sc.nextInt();
                    speed -= value;
                    if(speed < 0) //if speed is minus
                        speed = 0;
                    dist += speed;
                }
            } //input for loop
            //출력
            System.out.println("#" + test_case + " " + dist);
		}
	}
}
