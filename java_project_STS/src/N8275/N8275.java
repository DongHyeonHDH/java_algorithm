package N8275;
import java.io.*;
import java.util.*;
public class N8275 {
	static int[] cage;
	static int[] answer;
	//l,r,s 모음
	static int[] lCase, rCase, sCase;
	static boolean[] hamCase;
	static boolean[] visited;
	static boolean flag = false;
	static int N,X,M;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			cage = new int[N+1];
			//m의 조건식이 저장된 것만 수행
			visited = new boolean[N+1];
					
			lCase = new int[M];
			rCase = new int[M];
			sCase = new int[M];
			
			for(int m=0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				lCase[m] = l;				
				rCase[m] = r;
				sCase[m] = s;
			}
			
			dfs(0,N);
									
			sb.append("#")
			.append(tc)
			.append(" ");
				
			if(!flag) {
				sb.append(-1);
			}
			else {
				for(int j = 1; j<= N; j++) {
					sb.append(answer[j])
					.append(" ");
				}
			}
			sb.append("\n");
		}
	}
	static void dfs(int idx, int n) {
		if(idx == N) {
			return;
		}
		
		//m개의 식을 보고 판단
		int sum = 0;
		for(int i = 0; i<M; i++) {
			for(int j = lCase[i]; j< rCase[i]; j++) {
				sum += cage[j]; 
			}
			
			if(sum == sCase[i]) {
				return;
			}
			
			//다음 dfs로 이동
			visited[idx] = true;
			dfs(idx +1, n+1);
			visited[idx] = false;
		}		
		
	}
}
