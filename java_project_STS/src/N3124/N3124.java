package N3124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;


public class N3124 {
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t =1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<Edge> edges = new ArrayList<>();
			
			for(int i = 0; i< E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				edges.add(new Edge(A,B,C));
			}
			
			//크루스칼은 정렬을 먼저 적용해야 한다.
			Collections.sort(edges);
			
			parent = new int[V+1];
			rank = new int[V+1];
			
			for(int i =1; i<=V; i++) {
				parent[i]=i;
			}
			long totalCost= 0;
			int selectEdges = 0;
			
			for(Edge edge: edges) {
				if(union(edge.from, edge.to)) {
					totalCost += edge.weight;
					selectEdges++;
					
					if(selectEdges == V-1) {
						break;
					}
				}
			}
			if(selectEdges == V-1) {
				sb.append("#").append(t).append(" ").append(totalCost).append("\n");
			}
			else {
				sb.append("모든 정점이 연결 안되었네요");
			}			
			
		}
		System.out.println(sb);
		
		
	}
	
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int weight;
		
		Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			// TODO Auto-generated method stub			
			return Integer.compare(this.weight, other.weight);
		}
		
	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int a, int b){
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) {
			return false;
		}
		
		if(rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA; 
		}
		else if(rank[rootA]< rank[rootB]) {
			parent[rootA] = rootB;
		}
		else {
			parent[rootB] = rootA;
			rank[rootA]++;
		}
		return true;
	}
	
	
	
}
