package N3952;

import java.io.*;
import java.util.*;
public class N3952 {	 
	static int[] indegree;
	static List<Integer>[] graph;
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[N+1];
			for(int i =1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			indegree = new int[N+1]; 
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[start].add(to);
				
				indegree[to]++;
			}
			sb.append("#")
			.append(tc);
			
			topologySort();
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static public void topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i<=N; i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int current = q.poll();
			sb.append(" ")
			.append(current);
			
			for(int ed: graph[current]) {
				indegree[ed]--;
				if(indegree[ed] == 0) {
					q.add(ed);
				}
			}
		}
	}
}
