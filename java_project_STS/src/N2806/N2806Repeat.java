package N2806;
import java.io.*;
import java.util.*;
public class N2806Repeat {	
	static int[] visited;
	static int N, answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			answer = 0;			
			visited = new int[N];
			Arrays.fill(visited, -1);
			
			dfs(0);
			
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n")
			;
		}
		System.out.println(sb);
	}
	
	static void dfs(int idx) {
		if(idx == N) {
			answer += 1;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(checked(idx, i)) {
				visited[idx] = i;
				dfs(idx+1);
				visited[idx] = -1;
			}
		}
		
	}
	static boolean checked(int idx, int k) {		
		for(int i = 0; i<idx; i++) {
			int pC = visited[i];
			
			if(k == pC) {
				return false;
			}
			if(Math.abs(idx- i) == Math.abs(k-pC)) {
				return false;
			}
		}
		
		return true;
	}
	
	
}
