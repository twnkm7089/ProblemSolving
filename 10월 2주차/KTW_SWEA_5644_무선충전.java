import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class KTW_SWEA_5644_무선충전
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int M = sc.nextInt();
			int A = sc.nextInt();
			//이동 입력
			int[] moveA = new int[M];
			int[] moveB = new int[M];
			for(int i = 0; i < M; i++) {
				moveA[i] = sc.nextInt();
			}
			for(int i = 0; i < M; i++) {
				moveB[i] = sc.nextInt();
			}
			//AP정보 저장
			List<int[]> AP = new ArrayList<>();
			for(int i = 0; i < A; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int c = sc.nextInt();
				int p = sc.nextInt();
				AP.add(new int[] {x, y, c, p});
			}
			
			//사용자 위치 초기화
			int Ax = 1;
			int Ay = 1;
			int Bx = 10;
			int By = 10;
			
			//delta
			int[] dx = {0, 0, 1, 0, -1};
			int[] dy = {0, -1, 0, 1, 0};
			
			//시작
			int total = 0;
			for(int i = 0; i <= M; i++) {
				//현 위치에서 접속 가능한 AP 등록
				List<Integer> possiA = new ArrayList<>();
				List<Integer> possiB = new ArrayList<>();
				
				//각 AP별 거리 측정
				for(int j = 0; j < A; j++) {
					int[] info = AP.get(j);
					int distA = Math.abs(Ax-info[0])+Math.abs(Ay-info[1]);
					int distB = Math.abs(Bx-info[0])+Math.abs(By-info[1]);
					//가능하면 추가
					if(distA <= info[2]) {
						possiA.add(j);
					}
					if(distB <= info[2]) {
						possiB.add(j);
					}
				}
				
				//충전량 측정
				int charge = 0;
				//B만 충전 가능
				if(possiA.size() == 0 && possiB.size() != 0) {
					for(int j = 0; j < possiB.size(); j++) {
						charge = Math.max(charge, AP.get(possiB.get(j))[3]);
					}
				} else if (possiB.size() == 0 && possiA.size() != 0) {
					//A만 충전 가능
					for(int j = 0; j < possiA.size(); j++) {
						charge = Math.max(charge, AP.get(possiA.get(j))[3]);
					}
				} else if(possiA.size() != 0 && possiB.size() != 0) {
					//둘 다 충전 가능함
					for(int j = 0; j < possiA.size(); j++) {
						for(int k = 0; k < possiB.size(); k++) {
							int aap = possiA.get(j);
							int bap = possiB.get(k);
							
							if(aap == bap) { //같은 AP 접속시 해당 AP의 처리량이 한계
								charge = Math.max(charge, AP.get(aap)[3]);
							} else {
								//그 외는 두 AP 처리량 모두 가능
								int chargeA = AP.get(aap)[3];
								int chargeB = AP.get(bap)[3];
								charge = Math.max(charge, chargeA + chargeB);
							}
						}
					}
				}
				
				//충전
				total += charge;
				
				//이동
				if(i == M) break;
				
				Ax += dx[moveA[i]];
				Ay += dy[moveA[i]];
				Bx += dx[moveB[i]];
				By += dy[moveB[i]];
			}//end of move
			
			//결과 출력
			System.out.println("#" + test_case + " " + total);
		}//end of test case
	}//end of main method
}//end of class