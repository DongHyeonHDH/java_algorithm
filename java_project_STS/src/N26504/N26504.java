package N26504;
import java.io.*;
import java.util.*;

public class N26504 {
	static List<Long> edgeWeight;
	static List<Edge> mst;
	static int[] parent;
	static int[] rank;	
	
	static int count = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());
			int edgeNum = N * (N-1) /2 ;
			
			edgeWeight = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i< edgeNum; i++) {				
				Long weight = Long.parseLong(st.nextToken());
				edgeWeight.add(weight);
			}
			
//			//MST에서 최소비용구하기			
//			parent = new int[N];
//			rank = new int[N];			
//			mst = new ArrayList<>();
//			
//			int edgeCount = 0;
//			for(int i =0; i<N; i++) {
//				parent[i]=i;
//			}
//			//edge 생성
//			for(int i = 0; i< N-1; i++) {
//				for(int j = i+1; j< N; j++) {
//					Edge edge = new Edge();
//					edge.start = i;
//					edge.to = j;
//					edge.weight = edgeWeight.get(edgeCount++);
//					
//					mst.add(edge);
//				}
//			}
//			
//			Collections.sort(mst);
//			// edge의 weight를 바꾸고 sort를 적용해서 이때의 mst를 구하고
//			// 거기서 max와 min을 구해서 출력하면 될 것 같다.		
//
//			edgeCount = 0;
//			long costMin = 0;
//			for(Edge ed: mst) {
//				if(union(ed.start,ed.to)) {
//					costMin += ed.weight;
//					edgeCount++;			
//					if(edgeCount == N-1) {
//						break;
//					}					
//					
//				}			
//			
//			}			

			long costMin = 0;
			long costMax = 0;
			Collections.sort(edgeWeight);
			for(int k = 1; k<=N-1; k++) {
				costMin += edgeWeight.get(k-1);
				int index = k*(k-1) /2;
				costMax += edgeWeight.get(index);
			}
			
			
			//MST에서 최대비용구하기	
			sb.append(costMin)
				.append(" ")
				.append(costMax)
				.append('\n')
				;
			
		}
		System.out.println(sb);
	}	
	
	
	static class Edge implements Comparable<Edge>{
		int start;
		int to;
		long weight;
		Edge(){
			
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.weight, o.weight);
		}
	}
	
	static int find(int idx) {
		if(parent[idx] == idx) {
			return idx;
		}
		return parent[idx] = find(parent[idx]);
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
