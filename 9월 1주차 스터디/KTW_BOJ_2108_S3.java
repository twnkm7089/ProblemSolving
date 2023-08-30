package codetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*알고리즘 설명
 * 1. 중앙값, 범위는 배열 정렬해서 구함.
 * 2. 산술평균은 반복문 돌며 값 더한뒤 나누고 round를 취함
 * 3. 최빈값은 산술 평균을 위해 반복문을 돌 때, 빈도수 저장 배열을 따로 만들어 구했음.
 * */
public class KTW_BOJ_2108_S3 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		
		//정렬
		Arrays.sort(nums);
		
		//최빈값 배열
		int[] freq = new int[8001];
		int sum = 0;//산술 평균을 위한 합
		
		int max_freq = 0;//최대 빈도수
		for(int i = 0; i < N; i++) {//순회
			sum+= nums[i];//값 더하기
			freq[nums[i]+4000]++;//빈도수 배열 더하기(-4000~4000이니 보정값으로 인덱스 선언)
			if(freq[nums[i]+4000] > max_freq) {//최대 빈도수 갱신
				max_freq = freq[nums[i]+4000];
			}
		}
		
		System.out.println(Math.round((sum / (double)N)));//산술평균
		System.out.println(nums[N/2]);//중앙값
		//최빈값
		int cnt = 0;
		int idx = 0;
		for(int i = 0; i < 8001; i++) {
			if(freq[i] == max_freq) {//최빈값 찾기.
				cnt++;
				idx = i;
			}
			
			if(cnt == 2) {//최빈값이 여럿일 때 2번째로 작은 값 출력
				System.out.println(idx - 4000);
				break;
			}
		}
		if(cnt == 1) {//하나뿐인 경우 그냥 출력
			System.out.println(idx - 4000);
		}
		
		System.out.println(nums[N-1] - nums[0]);//범위

	}

}//end of class
