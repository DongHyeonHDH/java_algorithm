package N5643;
import java.io.*;
import java.util.*;

public class N5643 {
	static int N;
	static int M;
	static int[] isDegree;
	static List<Integer>[] graph;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc =1; tc<= T; tc ++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			graph = new ArrayList[N+1];
			isDegree = new int[N+1];
			
			for(int i = 1; i<=N; i++) {
				graph[i] = new ArrayList<Integer>();
			}			
			
			for(int i = 0; i< M; i++) {				
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());				
				
				graph[from].add(to);
				isDegree[to]++;
			}			
			
			sb.append('#')
			.append(tc)
			.append(' ')
			;
			
			topologySort();
			
		}
		
		System.out.println(sb);
	}
	
	static public void topologySort() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		
		for(int i = 1; i<=N; i++) {
			if(isDegree[i] == 0) {
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			result.add(current);
			
			for(int next: graph[current]) {
				isDegree[next]--;
				
				if(isDegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		if(result.size() != N) {
			System.out.println("사이클이 존재하여 위상 정렬 x");				
			return;
		}
		
		for (int node : result) {
            sb.append(node)
            .append(' ')
            ;
        }
		sb.append('\n');
	}
	
	//걍 방향은 저장되어 있으니까 각 점에서 dfs 찍어서 지나간 vertex의 개수인 경우에 정답일 것 같다.
}
