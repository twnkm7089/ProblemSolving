package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*알고리즘 설명
 * 1. 의상의 종류를 key로 하고 의상을 담은 list를 value로 하는 hashmap 생성
 * 2. 입력 완료 후 (종류별 옷개수+1(안입음))씩 곱해줌
 * 3. 모두 안입은 경우 -1.
 * 4. 결과 출력
 * */
public class KTW_BOJ_9375_S3 {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//input
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			//해시맵, key는 종류, value는 옷
			Map<String, List<String>> clothes = new HashMap<>();
			//추가
			for(int i = 0; i < N; i++) {
				String[] str = br.readLine().split(" ");
				String a = str[0];
				String b = str[1];
				if(!clothes.containsKey(b)) {//아직 리스트 미생성
					List<String> temp = new ArrayList<>();
					temp.add(a);
					clothes.put(b, temp);
				} else {//리스트 이미 존재
					clothes.get(b).add(a);
				}
			}
			//정답
			int answer = 1;
			for(String key : clothes.keySet()) {
				answer *= (clothes.get(key).size()+1);//종류별 옷 + 1(안 입음)
			}
			answer--;//모두 안입는 경우
			
			System.out.println(answer);//결과 출력
		}
		
	}//end of main method
	
	
}//end of class
