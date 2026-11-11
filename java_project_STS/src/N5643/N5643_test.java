/*
 * 키순서 1
 */
package N5643;
import java.io.*;
import java.util.*;

public class N5643_test {
	static int N, M, adj[][];
	static int cnt = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adj = new int[N+1][N+1];
			StringTokenizer st = null;
			
			for(int i = 0; i< M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a][b] = 1; // a가 b보다 큰 경우
				
			}
			
			for(int i = 1; i<=N; i++) {				
				if(adj[i][0] == -1) {//미탐색 정점이면 탐색
					gtDFS(i);		
				}			
			}
			for(int i = 1; i<= N; i++) {
				for(int j =1; j<=N; j++) {
					adj[0][j] += adj[i][j];
				}
			}
			
			int ans = 0;
			for(int i = 1; i<=N; i++) {				
				if(adj[i][0]+ adj[0][i]== N-1) {//미탐색 정점이면 탐색
					++ans;		
				}			
			}
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	static void gtDFS(int cur) {
		//cur : 현재 학생 번호,	
		for(int i = 1; i<=N; i++) {
			//인접정점이면
			if(adj[cur][i] == 1) {
				//탐색된 정점이 아니면
				if(adj[i][0] == -1) gtDFS(i);
				
				// 정점이 탐색되었으면
				
				// 탐색된 정점의 결과를 나와의 관계로 매핑 //cur <i, i<j ==> cur < j
				if(adj[i][0] > 0) {
					for(int j = 1; j<= N; j++) {
						if(adj[i][j] == 1) adj[cur][j] =1;
					}
				}
			}					
		}
		
		int cnt = 0;
		for(int i = 1; i<= N; i++) {
			cnt += adj[cur][i];
		}
		
		adj[cur][0] = cnt; // 탐색이 완료되었고 나보다 큰 학생수가 몇인지 메모
	}	
	
	
}
