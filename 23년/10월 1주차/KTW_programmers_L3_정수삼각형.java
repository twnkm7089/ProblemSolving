import java.util.*;
/*알고리즘 설명
 * 1. 대표적 dP문제. 한칸씩 내려가며 윗 칸에서 추가할 수 비교해 추가
 * 2. 코드 참고
 * */
public class KTW_programmers_L3_정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;
        //반복문
        for(int i = 1; i < N; i++){
            int M = triangle[i].length;
            //첫 칸과 마지막 칸은 그냥 추가
            triangle[i][0] += triangle[i-1][0];
            //중간은 비교해서 추가
            for(int j = 1; j < M-1; j++){
                triangle[i][j] += Math.max(triangle[i-1][j-1], triangle[i-1][j]);
            }
            triangle[i][M-1] += triangle[i-1][M-2];
        }
        
        //마지막 줄 정렬 후 제일 큰거 출력
        int M = triangle[N-1].length;
        Arrays.sort(triangle[N-1]);
        answer = triangle[N-1][M-1];
        return answer;
    }
}
