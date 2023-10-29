
public class KTW_Programmers_이모티콘할인행사 {
    static int[][] users;
    static int[] emoticons;
    static int N;
    static int[] max, sel;
    
    
    public int[] solution(int[][] users, int[] emoticons) {
        //static 전환
        this.users = users;
        this.emoticons = emoticons;
        
        //완전탐색
        N = users.length;
        max = new int[]{-1, Integer.MAX_VALUE};
        sel = new int[emoticons.length];
        comb(0);

        return max;
    }
    
    
    public static void comb(int sidx){
        if(sidx == emoticons.length){ 
            //배열 완성, 1마다 10% 할인
            //각 user들이 써야 하는 금액(플러스 미가입시)
            int[] price = new int[N];
            //각 유저별로
            for(int i = 0; i < N; i++){
                //각 이모티콘 탐색
                for(int j = 0; j < emoticons.length; j++){
                    //상정한 할인율 이상이면 구매
                    if(users[i][0] <= sel[j]){
                        price[i] += emoticons[j]*(100-sel[j])/100;
                    }
                }
            }
            
            //최종적으로 이모티콘 플러스 가입자 수와 판매액 집계
            int plus = 0;
            int total = 0;
            for(int i = 0; i < N; i++){
                //이모티콘 플러스 가입이 이득이면 가입
                if(users[i][1] <= price[i]){
                    plus++;
                } else{
                    total += price[i];
                }
            }
            
            
            //갱신
            if(plus > max[0] || ((plus == max[0]) && (total > max[1]))){
                max[0] = plus;
                max[1] = total;
            }
            
            return;
        }
        
        for(int i = 1; i <= 4; i++){
            //sel:할인율 저장 배열, sidx번 이모티콘은 i*10퍼 할인
            sel[sidx] = i*10;
            comb(sidx+1);
        }
    }
}
