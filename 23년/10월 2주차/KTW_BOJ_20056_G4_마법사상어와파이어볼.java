import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class KTW_BOJ_20056_G4_마법사상어와파이어볼 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//input of first line
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		//direction
		int[] dr = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dc = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
		
		//fireball queue
		List<int[]> fireball = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int ri = Integer.parseInt(st.nextToken());
			int ci = Integer.parseInt(st.nextToken());
			int mi = Integer.parseInt(st.nextToken());
			int si = Integer.parseInt(st.nextToken());
			int di = Integer.parseInt(st.nextToken());
			fireball.add(new int[] {ri, ci, mi, si, di});
		}
		
		//이동 시작
		for(int i = 0; i < K; i++) {
			int size = fireball.size();
			//count after position
			int[][] temp = new int[N][N];
			for(int j = 0; j < size; j++) {
				int[] f = fireball.remove(0);
				int ri = f[0]-1;
				int ci = f[1]-1;
				int mi = f[2];
				int si = f[3];
				int di = f[4];
				
				//이동
				int nr = (ri + si*dr[di])%N;
				int nc = (ci + si*dc[di])%N;
				if(nr < 0) {
					nr += N;
				}
				if(nc < 0) {
					nc += N;
				}
				
				temp[nr][nc]++;
				fireball.add(new int[] {nr+1, nc+1, mi, si, di});
			}//end of move fireball
			
			//이동 후의 firball 합치기
			for(int r = 1; r <= N; r++) {
				for(int c = 1; c <= N; c++) {
					//fireball개수가 여러개인 지역
					if(temp[r-1][c-1] > 1) {
						int cnt = 0;
						int[] newfb = new int[5];
						newfb[0] = r;
						newfb[1] = c;
						int[] delidx = new int[temp[r-1][c-1]];
						//모든 종류 합치기
						for(int k = 0; k < fireball.size(); k++) {
							int[] fb = fireball.get(k);
							//fireball 발견
							if(fb[0] == r && fb[1] == c) {
								newfb[2] += fb[2];
								newfb[3] += fb[3];
								//방향 만약 같은 종류면 그대로, 다르면 -1로 변경.
								if(cnt == 0) {//첫 파이어볼
									newfb[4] = fb[4];
								} else if(newfb[4] != -1) {
									//다른 방향
									if(newfb[4]%2 != fb[4]%2) {
										newfb[4] = -1;
									}
								}
								
								//cnt, removelist 추가
								delidx[cnt] = k;
								cnt++;
								//다 찾으면 탐색 종료
								if(cnt == temp[r-1][c-1]) break;
								
							}
						}
						
						newfb[2] /= 5;
						newfb[3] /= temp[r-1][c-1];

						//리스트에서 fireball 삭제
						for(int k = 0; k < temp[r-1][c-1]; k++) {
							//지울 때마다 변화
							fireball.remove(delidx[k] - k);
						}
						
						//질량 0 아닌 경우
						if(newfb[2] > 0) {
							for(int d = 0; d < 4; d++) {
								if(newfb[4] == -1) {
									fireball.add(new int[] {newfb[0], newfb[1], newfb[2], newfb[3], d*2+1});
								} else {
									fireball.add(new int[] {newfb[0], newfb[1], newfb[2], newfb[3], d*2});
								}
							}
						}
					}
				}
			} //end of add fireball
		}//end of move
		
		int size = fireball.size();
		int answer = 0;
		for(int i = 0; i < size; i++) {
			answer += fireball.get(i)[2];
		}
		
		System.out.println(answer);
	}//end of main method
}//end of class
