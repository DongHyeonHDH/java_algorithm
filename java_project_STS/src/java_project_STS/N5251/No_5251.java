package java_project_STS.N5251;
import java.util.*;
import java.io.*;

public class No_5251 {
	
	
	static List<List<Edge>> graph;
	static int[] distance;
	static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			distance = new int[N+1];
			graph = new ArrayList<>();
			
			for(int i=0; i<N+1; i++) {
				graph.add(new ArrayList<Edge>());
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				graph.get(start).add(new Edge(end,dist) );
			}
			
			Arrays.fill(distance, INF);
			dijkstra(0);
			
			sb.append("#").append(t).append(" ").append(distance[N]).append("\n");
		}
		System.out.println(sb);
	}
	static void dijkstra(int start) {	
		PriorityQueue<Node> pq = new PriorityQueue<>();		
		
		distance[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			int currentVertex = current.vertex;
			int currentDistance = current.distance;
			
			//가지치기, 더 짧은 경로가 있으면 무시
			if(currentDistance > distance[currentVertex]){
				continue;
			}
			
			for(Edge edge : graph.get(currentVertex)) {
				int nextVertex = edge.to;
				int newDistance = currentDistance + edge.weight;
				
				if(newDistance < distance[nextVertex]) {
					distance[nextVertex] = newDistance;
					pq.offer(new Node(nextVertex, newDistance));
				}
			}
		}
				
	}
	static class Node implements Comparable<Node>{
		int vertex;
		int distance;
		
		Node(int vertex, int distance){
			this.vertex = vertex;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Node other){
			return Integer.compare(this.distance, other.distance);
		}
	}
	
	static class Edge{		
		int to;
		int weight;
		Edge(int to, int weight){			
			this.to = to;
			this.weight = weight;
		}
	}
}
