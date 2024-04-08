
public class KTW_programmers_L2_뒤에있는큰수 {
	 public int[] solution(int[] numbers) {
	        int N = numbers.length;
	        int[] answer = new int[N];
	        //마지막 원소에 뒷 큰수는 없다.
	        for(int i = N-1; i >= 0; i--){
	            int pivot = numbers[i];
	            for(int j = i-1; j >= 0; j--){
	                if(pivot <= numbers[j]){
	                    break;
	                } else{
	                    answer[j] = pivot;
	                }
	            }
	        }
	        
	        for(int i = 0; i < N; i++){
	            if(answer[i] == 0) answer[i] = -1;
	        }
	        return answer;
	    }
}
