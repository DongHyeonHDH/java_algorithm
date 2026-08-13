package N1251;

import java.io.*;
import java.util.*;
public class N1251 {
	static Spot[] island;
	static int[] parent;
	static int[] rank;
	static List<Edge> edge;	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		
		int T = Integer.parseInt(br.readLine());
		for(int t =1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			island = new Spot[N];
			
			for(int i = 0; i<N; i++) {
				island[i] = new Spot();
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {		
				int x = Integer.parseInt(st.nextToken());						
				island[i].x = x;
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {	
				int y = Integer.parseInt(st.nextToken());
				island[i].y = y;
				
			}
			//환경부담 세율 실수 E
			double E = Double.parseDouble(br.readLine());			
			
			//모든 구간의 edge를 구해서 E* L^2을 가중치로 두고 저장할 필요가 있다.
			edge = new ArrayList<>();
			findEdge(N,E);		
						
			//크루스칼 적용해서 최소신장 트리 만들기
			
			//크루스칼은 정렬을 먼저 적용해야 한다.
			Collections.sort(edge);

			parent = new int[N];
			rank = new int[N];
			
			for(int i = 0; i<N; i++) {
				parent[i] = i;
			}
			
			//정답 저장할 변수들
			double countWeight = 0;
			double countEdges = 0;
			
			for(int i = 0; i< edge.size(); i++) {
				if(union(edge.get(i).start, edge.get(i).to)) {
					countWeight += edge.get(i).weight;
					countEdges++;
					
					if(countEdges == N-1) {
						break;
					}

				}				
			}
			
			if (countEdges == N-1) {
				sb.append("#").append(t).append(" ").append(Math.round(countWeight)).append("\n");
			}
			else {
				sb.append("모든 정점이 연결 안되었네요");
			}
		}
		System.out.println(sb);
	}
	
	static class Spot{
		int x;
		int y;
		
		Spot(){			
		}
	}
	static void findEdge(int N, double E) {
		for(int i = 0; i< N-1; i++) {
			for(int j = i+1; j< N; j++) {
				//start와 to 넣기
				long lengthX= (long)(island[i].x - island[j].x);
				long lengthY= (long)(island[i].y - island[j].y);
				double weight = E * ((lengthX * lengthX)+ (lengthY * lengthY));						 
				
				Edge ed = new Edge(i,j,weight);				
				edge.add(ed);				
				
//				System.out.println("ed start: "+ed.start + " ed : "+ed.to +  " ed weight: "+ed.weight );
			}			
		}
		System.out.println();
	}
	
	static class Edge implements Comparable<Edge>{
		int start;
		int to;
		double weight;
		
		Edge(int start, int to, double weight){
			this.start = start;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Double.compare(this.weight, o.weight);
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
		}
		else if(rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA;
		}
		else {	
			parent[rootB] = rootA;
			rank[rootA]++;
		}
			
		return true;
	}
	
	
}
