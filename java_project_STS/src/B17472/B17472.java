/*
 * 백준 17472 다리만들기2 
 */
package B17472;
import java.io.*;
import java.util.*;

public class B17472 {
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};
	
	static int islandCount = 1;
	static int N, M;
	static List<Edge> edge;
	
	
	//kruskal 용 
	static int[] parent;
	static int[] rank;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int answer = -1;
		
		edge = new ArrayList<>();
		
		map = new int[N][M];
		int temp = 0;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					// 1인 경우가 섬이 있다는 의미
					map[i][j] = temp;
				}
			}
		}
		
		//1. 섬구분 bfs 적용
		for(int i = 0; i<N; i++) {		
			for(int j = 0; j<M; j++) {				
				if(map[i][j] == 1) {
					// 1인 경우가 섬이 있다는 의미
					map[i][j] = ++islandCount;
					bfs(i,j, islandCount);
				}
			}
		}
		
//		for(int i = 0; i<N; i++) {		
//			for(int j = 0; j<M; j++) {				
//				System.out.print(" "+ map[i][j]);				
//			}
//			System.out.println();
//		}	
		
		//2. 섬끼리 edge 생성 길이 2이상인 것으로 판단
		makeEdge();
		
//		for(Edge e : edge) {	
//			System.out.println("from : "+ e.from + " to : "+ e.to + " weight : "+ e.weight);			
//		}
//		System.out.println();
		//3. 만들어진 edge로 mst 만들어보기
		
		parent = new int[islandCount+1];
		for(int i = 2; i < parent.length; i++) {
			parent[i] = i;
		}
		rank = new int[islandCount+1];	
		Collections.sort(edge);
		
//		for(Edge e : edge) {	
//			System.out.println("from : "+ e.from + " to : "+ e.to + " weight : "+ e.weight);			
//		}
		if(edge.size() >0) {
			//간선이 없는 경우는 -1
			answer = 0;		
			int selectEdges = 0;
			for(Edge e: edge) {			
				if(union(e.from, e.to)) {				
					answer += e.weight;
					selectEdges++;
					
					if(selectEdges == islandCount-2) {
						break;
					}
				}
			}
		}				
		
		System.out.println(answer);
	}
	
	static boolean checkingVisited(boolean[] visited) {
		int sum = 0;
		for(int i = 2; i < visited.length; i++) {
			if(visited[i]) sum++;
		}
		if(sum == visited.length-3) {
			for(int i = 0; i < visited.length; i++) {
				if(visited[i]) System.out.print(" "+ i);
			}
			System.out.println();
			
			return true;
		} 
		else {
			return false;
		} 
	}
	
	static void bfs(int row, int col, int cnt) {
		Node start = new Node(row, col);
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for(int i =0; i< 4; i++) {
				int r = cur.x + dr[i];
				int c = cur.y + dc[i];
				
				if(r <0 || r>= N || c <0 || c>= M) continue;
					
				if(map[r][c] == 1) {
					map[r][c] = cnt;
					q.add(new Node(r,c));
				}
			}
		}
		
	}
	static void makeEdge() {
		//가로줄에서의 edge 판단하기
		for(int i = 0; i< N; i++) {
			int startPoint = 0;
			int endPoint = 0;
			int weight = 0;			
			//edge 세는 것 체크하는 flag
			boolean flag = false;
			
			
			for(int j = 0; j< M-1; j++) {
				if(map[i][j] > 0 && map[i][j+1] == 0) {
					startPoint = map[i][j];
					flag = true;
				}
				
				if(map[i][j] == 0 && map[i][j+1] > 0) {
					
					endPoint = map[i][j+1];
					//일단 양방향으로 설계
					if(startPoint >0 && endPoint >0 && weight > 1) {
						edge.add(new Edge(startPoint, endPoint, weight));						
					}					
					//초기화
					startPoint = 0;
					endPoint = 0;
					weight = 0;
					flag = false;
					
				}
				
				if(flag) {
					weight++;
				}
			}
		}

		//세로줄에서의 edge 판단하기
		for(int j = 0; j< M; j++) {
			int startPoint = 0;
			int endPoint = 0;
			int weight = 0;			
			//edge 세는 것 체크하는 flag
			boolean flag = false;
			
			
			for(int i = 0; i< N-1; i++) {
				if(map[i][j] > 0 && map[i+1][j] == 0) {
					startPoint = map[i][j];
					flag = true;
				}
				
				if(map[i][j] == 0 && map[i+1][j] > 0) {					
					endPoint = map[i+1][j];
					//일단 양방향으로 설계
					if(startPoint >0 && endPoint >0 && weight > 1) {
						edge.add(new Edge(startPoint, endPoint, weight));						
					}					
					//초기화
					startPoint = 0;
					endPoint = 0;
					weight = 0;
					flag = false;
					
				}
				
				if(flag) {
					weight++;
				}
			}
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
		
		if(rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA;
		}
		else if(rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		}
		
		else{
			parent[rootB] = rootA;
			rank[rootA]++;
		}
		
		return true;
	}	
	
	static class Node{
		int x;
		int y;
		
		Node(int x, int y){
			this.x = x;
			this.y = y;
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
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}		
	}
	
}
