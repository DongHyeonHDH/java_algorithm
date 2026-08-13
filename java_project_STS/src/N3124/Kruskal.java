package N3124;
import java.io.*;
import java.util.*;

public class Kruskal {
	
	static int[] parent;
	static int[] rank;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
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
		
		long totalCost = 0;
		int selectedEdges = 0;
		
		for(Edge edge: edges) {
			if(union(edge.from, edge.to)) {
				totalCost += edge.weight;
				selectedEdges++;
				
				if(selectedEdges == V-1) {
					break;
				}
			}	
			
		}
		if (selectedEdges == V - 1) {
            System.out.println(totalCost);
        } else {
            System.out.println("모든 정점을 연결할 수 없습니다.");
        }
		
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
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA == rootB) {
			return false;
		}
		
		if(rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		}else if(rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA;
		}else {
			parent[rootB] = rootA;
			rank[rootA]++;
		}		
		
		return true;
	}
	
}
