import java.util.TreeSet;

class UserSolution
{
	//병사의 mID, mTeam, mScore이 기록되는 테이블(배열)을 만든다.
	//각 팀에 소속된 병사를 관리하는 리스트를 만든다. Treeset으로 구성된 array다.
	
	static int[][] database;
	static TreeSet<Integer>[] teamList;
	
	
	public void init()
	{
		//db, teamlist 초기화
		//행 번호  : mID, [0]: 팀 번호, [1] : 평판
		database = new int[100001][2];
		teamList = new TreeSet[6];
		for(int i = 1; i <= 5; i++) {
			teamList[i] = new TreeSet<Integer>();
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		//db 업데이트
		database[mID][0] = mTeam;
		database[mID][1] = mScore;
		
		//teamlist 업데이트
		teamList[mTeam].add(mID);
	}
	
	public void fire(int mID)
	{
		//db 업데이트
		int mTeam = database[mID][0];
		database[mID][0] = 0;
		//teamlist 업데이트
		teamList[mTeam].remove(mID);
	}

	public void updateSoldier(int mID, int mScore)
	{
		//db 업데이트
		database[mID][1] = mScore;
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		//teamlist보고 소속 인원 전원 mScore 업데이트
		for(int a : teamList[mTeam]) {
			database[a][1] += mChangeScore;
			if(database[a][1] > 5) {
				database[a][1] = 5;
			} else if(database[a][1] < 1) {
				database[a][1] = 1;
			}
		}
	}
	
	public int bestSoldier(int mTeam)
	{
		int maxScore = 0; //최대 평판점수
		int maxNum = 0; //최대번호
		for(int a : teamList[mTeam]) {
			if(database[a][1] > maxScore) {
				maxScore = database[a][1];
				maxNum = a;
			} else if (database[a][1] == maxScore && a > maxNum) {
				maxNum = a;
			}
		}
		
		
		return maxNum;
	}
}