import java.util.*;
/*알고리즘 설명
 * 1. 순열 만들어 완전탐색
 * 2. 코드 참조
 * */
public class KTW_programmers_L2_피로도 {
    static List<int[]> selArray = new ArrayList<>();
    static int[] sel;
    static int N;
    
    
    public int solution(int k, int[][] dungeons) {
        int answer = -1;
        N = dungeons.length;
        sel = new int[N];
        //순열 생성
        perm(0, 0);
        //탐색
        for(int i = 0; i < selArray.size(); i++){
        	//순서를 꺼내
            int[] order = selArray.get(i);
            int temp = 0;
            int now = k;
            //탐색 가능한 최대 던전 수 구하기
            for(int j = 0; j < N; j++){
                if(now >= dungeons[order[j]][0]){
                    temp++;
                    now -= dungeons[order[j]][1];
                } else{
                    break;
                }
            }
            //갱신
            answer = Math.max(answer, temp);
        }
        return answer;
    }
    
    //순열 생성
    public void perm(int sidx, int visited){
    	//기저부, 다 뽑았으면
        if(sidx == N){
            //복제
            int[] temp = new int[N];
            for(int i = 0; i < N; i++){
                temp[i] = sel[i];
            }
            //selArray에 추가
            selArray.add(temp);
            return;
        }
        
        //비트마스킹 이용, 재귀
        for(int i = 0; i < N; i++){
            if(((1<<i)&visited) == 0){
                sel[sidx] = i;
                perm(sidx+1, visited|(1<<i));
            }
        }
    }
}
