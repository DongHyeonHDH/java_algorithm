package N3124;
import java.io.*;
import java.util.*;

public class Prim {
	
	static List<Edge>[] graph;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[V+1];
			
			for(int i =1; i<=V; i++) {
				graph[i] = new ArrayList<>();					
			}
			
		    for (int i = 0; i < E; i++) {
	            st = new StringTokenizer(br.readLine());

	            int from = Integer.parseInt(st.nextToken());
	            int to = Integer.parseInt(st.nextToken());
	            int weight = Integer.parseInt(st.nextToken());

	            // 무방향 그래프이므로 양쪽에 저장
	            graph[from].add(new Edge(to, weight));
	            graph[to].add(new Edge(from, weight));
	        }
		    
		    long result =  prim(1,V);
		    if(result == -1) {
		    	System.out.println("연결불가");
		    }
		    else {
		    	System.out.println(result);
		    }
		}
		

	}
	static long prim(int start, int V) {
		boolean[] visited = new boolean[V+1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(start,0));
		
		long totalCost = 0;
		int visitedCount =0;
		
		while(!pq.isEmpty()) {
			Edge current = pq.poll();
			
			int node = current.to;
			int weight = current.weight;
			
			if(visited[node]) {
				continue;
			}
			visited[node] = true;
			totalCost += weight;
			visitedCount++;
			
			for(Edge next: graph[node]) {
				if(!visited[next.to]) {
					pq.offer(next);
				}
			}
		}
		if(visitedCount != V) {
			return -1;
		}
		
		return totalCost;
	}
	
	
	
	static class Edge implements Comparable<Edge>{
		int to;
		int weight;
		
		Edge(int to, int weight){
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
	}
	
}
