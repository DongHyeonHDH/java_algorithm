package J2469;
import java.util.*;
import java.io.*;
public class J2469 {
	static int[] heights;	
	static int[] answer;
	static int[] S;
    static boolean[] used;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());	
		heights = new int[N];
		S = new int[N];		
		
		answer = new int[N];
		used = new boolean[N];
		
		for(int cnt = 0; cnt<N; cnt++) {
			heights[cnt] = Integer.parseInt(br.readLine());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int cnt = 0; cnt < N; cnt++) {
			S[cnt] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(heights);		
		
		Sorting();

		for(int i = 0; i<N; i++) {
			sb.append(answer[i])
			.append('\n')
			;
		}
		System.out.println(sb);
	}
	
	//끝에서부터 접근
	static void Sorting(){
		for(int i= S.length -1; i>=0; i--) {
			int count = 0;
			for(int j= 0; j< S.length; j++) {
				if(!used[j]) {			
					if(S[i] == count) {
						answer[i] = heights[j];
						used[j] = true;
					}
					count++;
				}
				
			}
		}
	}
		
	
}
/*
 * 
 * 자기보다 키가 작거나 같은 사람들 수를 표시하는 수열 S
 * S에 맞게 출력하는 것이 중요
 * 
 * 모든 경우의 수를 구하고 조건을 만족하는 지 확인?
 * 
 * 어떻게 이 관계를 확정할 수가 있을까?
 * 
 * 앞에 자기보다 작은 것이 4개인 것을 배치
 * 
 * 이것을 제외하고 다음 조건 선택
 * 
 * 뒤에서 부터 선택해서 배치
 * */
 