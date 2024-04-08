import java.util.Scanner;

public class CodeTree_2302_after01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//input
		int N = sc.nextInt();
		int M = sc.nextInt();
		int P = sc.nextInt();
		int C = sc.nextInt();
		int D = sc.nextInt();
		
		//initialize
		//map
		//-1: rudolff, 0: nothing, 1~P : santa
		int[][] map = new int[N][N];
		
		//앞으로의 pos는 1,1을 0,0으로 만들기 위해 입력 좌표 -1
		//rudolff position
		int[] rudPos = new int[2];
		rudPos[0] = sc.nextInt() - 1;
		rudPos[1] = sc.nextInt() - 1;
		map[rudPos[0]][rudPos[1]] = -1;
		
		
		//santa position, status
		
		//santa game over?
		boolean[] gameOver = new boolean[P+1];
		//santa fainted? 0 : no, 1,2 : turn left
		int[] faint = new int[P+1];
		//santa score
		int[] score = new int[P+1];
		
		//get santa position
		int[][] santaPos = new int[P+1][2];
		for(int i = 1; i <= P; i++) {
			int santaNum = sc.nextInt();
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			//position record
			santaPos[santaNum][0] = r;
			santaPos[santaNum][1] = c;
			//add map
			map[r][c] = santaNum;
		}
		
		//rudolff move dist
		int[] drR = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dcR = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
		
		//santa move dist
		int[] drS = new int[] {-1, 0, 1, 0};
		int[] dcS = new int[] {0, 1, 0, -1};
		
		//move
		for(int tc = 1; tc <= M; tc++) {
			
			//1. rudolff is trying to move
			
			//finding target santa
			int targetSanta = -1;
			int maxDist = Integer.MAX_VALUE;
			for(int i = 1; i <= P; i++) {
				//gameover? no!
				if(gameOver[i]) continue;
				
				
				int dist = calcDist(rudPos[0], rudPos[1], santaPos[i][0], santaPos[i][1]);
				if(dist < maxDist) {
					maxDist = dist;
					targetSanta = i;
				} else if(dist == maxDist) {
					int[] oldPos = santaPos[targetSanta];
					int[] newPos = santaPos[i];
					if(oldPos[0] < newPos[0] || (oldPos[0] == newPos[0] && oldPos[1] < newPos[1])) {
						maxDist = dist;
						targetSanta = i;
					}
				}
			}
			
			//start of rudolff move
			if(targetSanta == -1) {
				//no santa left
				break;
			} else {
				int dir = rudolffDir(rudPos[0], rudPos[1], santaPos[targetSanta][0], santaPos[targetSanta][1]);
				
				//move rud
				int nr = rudPos[0] + drR[dir];
				int nc = rudPos[1] + dcR[dir];
				if(isInside(nr, nc, N)) { //check inside
					if(map[nr][nc] == 0) {
						//비어 있으면 이동
						map[rudPos[0]][rudPos[1]] = 0;
						map[nr][nc] = -1;
						rudPos[0] = nr;
						rudPos[1] = nc;
					} else {
						//충돌!!!
						int hitSanta = map[nr][nc];
						//santa get score
						score[hitSanta] += C;
						//santa faint
						faint[hitSanta] = 2;
						//santa 밀려나다
						int nrS = nr + C*drR[dir];
						int ncS = nc + C*dcR[dir];
						//상호작용과 밀려나기
						while(true) {
							if(isInside(nrS, ncS, N)) {
								//빈 곳
								if(map[nrS][ncS] == 0) {
									//santa 이동
									map[nrS][ncS] = hitSanta;
									santaPos[hitSanta][0] = nrS;
									santaPos[hitSanta][1] = ncS;
									break; //이제 그만 연쇄 충돌
								} else {
									//연쇄 충돌 로직
									int newSanta = map[nrS][ncS];
									
									//기존santa 이동
									map[nrS][ncS] = hitSanta;
									santaPos[hitSanta][0] = nrS;
									santaPos[hitSanta][1] = ncS;
									
									//다음 santa
									nrS += drR[dir];
									ncS += dcR[dir];
									hitSanta = newSanta;
								}
							} else {
								//밀려났으니 game over
								gameOver[hitSanta] = true;
								santaPos[hitSanta][0] = Integer.MAX_VALUE;
								santaPos[hitSanta][1] = Integer.MAX_VALUE;
								break;
							}
						}
						
						//rudolff 이동
						map[rudPos[0]][rudPos[1]] = 0;
						map[nr][nc] = -1;
						rudPos[0] = nr;
						rudPos[1] = nc;
					}
				} 
			}//end of rudolff move
			
			
			//2. santa is trying to move
			
			//santa start moving
			for(int i = 1; i <= P; i++) {
				if(gameOver[i] || faint[i] > 0) {
					//faint of game over? continue
					continue;
				} else {
					//possible to move
					
					int r = santaPos[i][0];
					int c = santaPos[i][1];
					
					//find direction
					int newDist = calcDist(r, c, rudPos[0], rudPos[1]);
					int dir = -1;
					for(int d = 0; d < 4; d++) {
						int nr = r + drS[d];
						int nc = c + dcS[d];
						//이동 가능?
						if(isInside(nr, nc, N) && map[nr][nc] <= 0) {
							//거리는 줄어드는가?
							int calculated = calcDist(nr, nc, rudPos[0], rudPos[1]);
							if (newDist > calculated) {
								newDist = calculated;
								dir = d;
							}
						}
					}
					
					//이동 시작
					if(dir != -1) {
						int nr = r + drS[dir];
						int nc = c + dcS[dir];
						
						//그냥 이동 가능
						if(map[nr][nc] == 0) {
							//이동
							map[r][c] = 0;
							map[nr][nc] = i;
							santaPos[i][0] = nr;
							santaPos[i][1] = nc;
						} else {
							//이동
							map[r][c] = 0;
							
							//루돌프와 충돌
							int hitSanta = i;
							//점수 증가
							score[hitSanta] += D;
							//santa faint
							faint[hitSanta] = 2;
							//산타 밀려나다
							int nrS = nr - D*drS[dir];
							int ncS = nc - D*dcS[dir];
							//상호작용과 밀려나기
							while(true) {
								if(isInside(nrS, ncS, N)) {
									//빈 곳
									if(map[nrS][ncS] == 0) {
										//santa 이동
										map[nrS][ncS] = hitSanta;
										santaPos[hitSanta][0] = nrS;
										santaPos[hitSanta][1] = ncS;
										break; //이제 그만 연쇄 충돌
									} else {
										//연쇄 충돌 로직
										int newSanta = map[nrS][ncS];
										
										//기존santa 이동
										map[nrS][ncS] = hitSanta;
										santaPos[hitSanta][0] = nrS;
										santaPos[hitSanta][1] = ncS;
										
										//다음 santa
										nrS -= drS[dir];
										ncS -= dcS[dir];
										hitSanta = newSanta;
									}
								} else {
									//밀려났으니 game over
									gameOver[hitSanta] = true;
									santaPos[hitSanta][0] = Integer.MAX_VALUE;
									santaPos[hitSanta][1] = Integer.MAX_VALUE;
									break;
								}
							}
						}
					}
				}
			} //end of santa move
			
			//show map
//			for(int[] arr : map) {
//				System.out.println(Arrays.toString(arr));
//			}
//			Arrays.toString(score);
//			Arrays.toString(faint);
//			Arrays.toString(gameOver);
//			System.out.println("----------------------------");
			
			
			
			//faint turn 감소, gameover 안하면 점수 추가
			for(int i = 1; i <= P; i++) {
				if(faint[i] > 0) {
					faint[i]--;
				}
				
				if(!gameOver[i]) {
					score[i]++;
				}
			}
			
			
		} // end of total moving
		
		for(int i = 1; i <= P; i++) {
			System.out.print(score[i] + " ");
		}
		
		//show map
//		for(int[] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}
		
	}
	
	//calculate distance
	public static int calcDist(int r1, int c1, int r2, int c2) {
		return ((r1 - r2)*(r1 - r2) + (c1 - c2) * (c1 - c2));
	}
	
	//calculate rudolff moving direction
	public static int rudolffDir (int r1, int c1, int r2, int c2) {
		if(r1 < r2) {
			if(c1 < c2) {
				return 3;
			} else if(c1 == c2) {
				return 4;
			} else {
				return 5;
			}
		} else if(r1 == r2) {
			if(c1 < c2) {
				return 2;
			} else if(c1 > c2) {
				return 6;
			}
		} else {
			if(c1 < c2) {
				return 1;
			} else if(c1 == c2) {
				return 0;
			} else {
				return 7;
			}
		}
		return -1;
	}
	
	//find is inside map?
	public static boolean isInside(int r, int c, int N) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
}
