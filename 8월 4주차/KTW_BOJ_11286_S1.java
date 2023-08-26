import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class KTW_BOJ_11286_S1 {
	public static int[] heap = new int[1000000];
	public static int heapSize = 0;
	
	public static void insert(int value) {
		heap[++heapSize] = value;
		//swap
		int ch = heapSize;
		int p = heapSize / 2;
		while(p > 0 && (Math.abs(heap[p]) > Math.abs(heap[ch]) || (Math.abs(heap[p]) == Math.abs(heap[ch]) && heap[p] > heap[ch]))) {
			int temp = heap[p];
			heap[p] = heap[ch];
			heap[ch] = temp;
			
			ch = p;
			p = ch/2;
		}
		
	}
	
	public static int delete() {
		if(heapSize == 0) return 0;
		else {
			int data = heap[1];
			//delete
			heap[1] = heap[heapSize--];
			
			//initialize
			int p = 1;
			int ch = 2 * p;
			if(ch + 1 <= heapSize && (Math.abs(heap[ch]) > Math.abs(heap[ch+1]) || (Math.abs(heap[ch]) == Math.abs(heap[ch+1]) && heap[ch] > heap[ch+1]))) {
				ch+=1;
			}
			
			while(ch <= heapSize && (Math.abs(heap[p]) > Math.abs(heap[ch]) || (Math.abs(heap[p]) == Math.abs(heap[ch]) && heap[p] > heap[ch]))) {
				//swap
				int temp = heap[p];
				heap[p] = heap[ch];
				heap[ch] = temp;
				
				//p, ch 갱신
				p = ch;
				ch = 2 * p;
				if(ch + 1 <= heapSize && Math.abs(heap[ch]) > Math.abs(heap[ch+1]) || (Math.abs(heap[ch]) == Math.abs(heap[ch+1]) && heap[ch] > heap[ch+1])) {
					ch+=1;
				}
			}
			
			return data;//data return
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			int inst = Integer.parseInt(br.readLine());
			if(inst == 0) {
				System.out.println(delete());
			} else {
				insert(inst);
			}
		}
		
	}//end of main

}//end of class

