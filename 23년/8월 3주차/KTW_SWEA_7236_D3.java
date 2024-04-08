import java.util.Scanner;

public class KTW_SWEA_7236_D3 {
	/*알고리즘 설명
	 * 1. 델타를 이용
	 * 2. 입력 받은 값을 2차원 배열에 저장 후, 델타를 이용해 8방향 움직임을 표시
	 * 3. index 범위를 초과하지 않으면서 W타일이 나오면 카운트
	 * 4. 카운트 결과가 0(근처 모두 G)면 1로 설정
	 * 5. 최대값을 갱신해가며 결과 도출 및 출력
	 * */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			//map 크기 입력
            int N = sc.nextInt();
            char[][] map = new char[N][N];
            //map 입력, N*N 배열에 값 입력
            for(int i = 0; i < N; i++){
            	for(int j = 0; j < N; j++){
                	map[i][j] = sc.next().charAt(0);
                }
            }//end of input
            
            //델타, 8방향 상부터 시계방향
            int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
            int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
            int max = 0;//max값 저장 변수
            for(int r = 0; r < N; r++){//탐색 시작(행 우선)
            	for(int c = 0; c < N; c++){
            		if(map[r][c]=='W') {//물 구역
            			int temp = 0;//카운팅 변수
                        for(int i = 0; i < 8; i++){//direction
                        	//현재 행,열 표시
                        	int nr = r + dr[i];
                            int nc = c + dc[i];
                            //인덱스 내부 + 근처에 W
                            if(nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] == 'W'){
                            	temp++;//카운트 증가
                            }
                        }//end of direction
                        //근처 모두 G면 1로 설정
                        if(temp == 0){
                        	temp++;
                        }
                        //최대값 갱신
                        if(temp > max){
                        	max = temp;
                        }
            		}//end of if
                }
            }//search end
            //결과 출력
            System.out.println("#" + test_case + " " + max);

		}//end of test case
	}
}
