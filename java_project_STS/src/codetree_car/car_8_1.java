/*
 * 현대차 8차 1번 기출
 * 
 */
package codetree_car;

import java.io.*;
import java.util.*;

public class car_8_1 {
	static int n;
	static boolean[][] edges;
	static boolean[] visited;
	static int[][] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
//		1~n		
		edges = new boolean[n+1][n+1];		
		int[] starts = new int[k];
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			edges[from][to] = true;
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<k; i++) {
			starts[i]= Integer.parseInt(st.nextToken());
		}
		
		//코드 구현		
		//각 지점에서 경로 저장
		dist = new int[k][n+1];
		int maxN = 0;
		for(int i: starts) {
			maxN = Math.max(bfs(i),maxN);
		}
		
		System.out.println(maxN);
				
	}
	
	static int bfs(int start){
		int max = 0;
		Queue<Node> q = new ArrayDeque<>();
		visited = new boolean[n];
		for(int i = 1; i<= n; i++) {
			//갈수 있는 장소 인 경우
			if(edges[start][i] && !visited[i]) {
				q.add(new Node(i,1));
			}
		}
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			 
			for(int i = 1; i<= n; i++) {
				//갈수 있는 장소 인 경우
				if(edges[cur.to][i] && !visited[i]) {
					visited[i] = true;
					q.add(new Node(i, cur.dist +1));
					
					max = Math.max(max, cur.dist+1);				
				}
			}	 
			 
		}
		
		
		return max;
	}
	
	//bfs용 layer 저장
	static class Node{
		int to;
		int dist;
		
		Node(int to, int dist){
			this.to = to;
			this.dist = dist;
		}
	}
	
	
	
	
}
