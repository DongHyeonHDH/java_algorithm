/*
 * swea 1267 작업순서
 * */

import java.io.*;
import java.util.*;

public class N1267_2{
	static int V,E;
	static List<Integer>[] edge;
	static int[] indegree;
	static List<Integer> answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edge = new ArrayList[V+1];
			for(int i =1; i<= V; i++) {
				edge[i] = new ArrayList<>();
			}
			
			answer = new ArrayList<>();
			indegree = new int[V+1];
			st = new StringTokenizer(br.readLine());
			for(int i =0; i<E; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				edge[from].add(to);
				indegree[to]++;
			}
			
		
			topologySort();
			
			sb.append("#").append(tc).append(" ");
			for(int ed : answer) {
				sb.append(ed).append(" ");
			}
			sb.append("\n");
		}		
		System.out.println(sb);
	}
	
	static void topologySort() {
		Queue<Integer> q = new ArrayDeque<Integer>();
		for(int i = 1; i<= V; i++) {
			if(indegree[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int temp = q.poll();
			answer.add(temp);
			
			for(int eg : edge[temp]) {
				indegree[eg]--;
				
				if(indegree[eg] == 0) {
					q.add(eg);
				}
			}
		}
	}

	
}
