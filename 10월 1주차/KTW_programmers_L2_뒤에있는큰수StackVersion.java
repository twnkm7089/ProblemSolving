import java.util.*;

public class KTW_programmers_L2_뒤에있는큰수StackVersion {
	public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];
        Stack<int[]> stack = new Stack<>();
        
        for(int i = 0; i < N; i++){
            int num = numbers[i];
            if(stack.isEmpty() || stack.peek()[0] >= num){
                stack.push(new int[]{num, i});
            } else{
                while(!stack.isEmpty() && stack.peek()[0] < num){
                    int[] data = stack.pop();
                    answer[data[1]] = num;
                }
                stack.push(new int[]{num, i});
            }
        }
        
        for(int i = 0; i < N; i++){
            if(answer[i] == 0) answer[i] = -1;
        }
        return answer;
    }
}
