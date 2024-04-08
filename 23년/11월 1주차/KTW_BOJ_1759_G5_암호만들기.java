import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

//위상정렬때 썼던 테크닉 응용
public class KTW_BOJ_1759_G5_암호만들기 {
	static int L, C;
	static char[] sel, c;
	static List<Character> vowels;
	
	
	
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //input
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        c = new char[C];
        for(int i = 0; i < C; i++) {
        	c[i] = st.nextToken().charAt(0);
        }
        
        //알파벳순 정렬
        Arrays.sort(c);
        
        //모음 추가
        vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        
        //완탐
        sel = new char[L];
        comb(0, 0);
        
        
    }
    
    //조합
    public static void comb(int idx, int sidx) {
    	if(sidx == L) {
    		int numVowels = 0;
    		int numConso = 0;
    		StringBuilder str = new StringBuilder();
    		//문자열 생성 및 자음 모음 파악
    		for(int i = 0; i < L; i++) {
    			str.append(sel[i]);
    			if(vowels.contains(sel[i])) {
    				numVowels++;
    			} else {
    				numConso++;
    			}
    		}
    		//결과 출력
    		if(numVowels >= 1 && numConso >= 2) {    			
    			System.out.println(str.toString());
    		}
    		return;
    	}
    	
    	//재귀로 문자열 제조
    	for(int i = idx; i <= C - L + sidx; i++) {
    		sel[sidx] = c[i];
    		comb(i+1, sidx+1);
    	}
    }
}