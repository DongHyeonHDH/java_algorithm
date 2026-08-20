package J6097;

import java.io.*;
import java.util.*;

public class J6097 {
	static int N;
	static int M;	
	static List<Integer>[] graph;
	static int[] indegree;
	static int[][] label;
	
	static int[] answerLength;
	static int[] answerLabel;
	
	//진행과정을 비교하면서 진행	 
	static int[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		graph = new ArrayList[N+1];
		label = new int[N+1][N+1];
		indegree = new int[N+1];
		visited = new int[N+1];
		answerLength = new int[N+1];
		answerLabel = new int[N+1];
		
		for(int i = 0; i<= N; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			//도로 라벨
			int l = Integer.parseInt(st.nextToken());
			
			label[a][b]  = l;
			graph[a].add(b);
			indegree[b]++;
			
						
		}
		//처음 visited 초기화
		Arrays.fill(visited, 1);
		
		for(int start = 1; start <= N; start++) {			
			sort(start);
		}
		
		
		
	}
	
	static void sort(int start) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> line = new ArrayList<>();
		
		int length = 0;
		int label =0;
		//갈림길에서 작은 라벨을 선택해야 해서 비교값 저장하는 변수
		int crossMin = Integer.MAX_VALUE;		
		
		if(!graph[start].isEmpty()) {
			line.add(start);
		}
		for(int node: graph[start]) {			
			q.offer(node);						
		}	
		
		while(!q.isEmpty()) {
			int num = q.poll();
			line.add(num);
			length++;
			
			for(int next: graph[num]) {
				//visited 재활용
				if(visited[num] < start){				
					visited[num] = start;		
					
					for(int node: graph[num]) {
						q.add(node);
						result.add(line);
					}			
				}
			}					
			
		}			
		
		for(ArrayList<Integer> ln : result) {
			ln.toArray();
			for(int l : ln) {
				System.out.print(" "+ l);
			}
			System.out.println();
		}
		System.out.println();
		
		answerLength[start] = length;
		answerLabel[start] = label;		
	}
	
	static void dfs(int start) {
		int[] result = new int[N+1];
		
		if(!graph[start].isEmpty()) {
			for(int node: graph[start]) {
				dfs(node);
			}
		}
	}
}

/*
 * 
 * 단방향 도로가 존재
 * 
 * 가장 긴 여행 일정
 * -> 도로 라벨 수열이 작은 일정 선호 * 
 * 
 * 길이가 같은 경우 
 * 사전순으로 작은 라벨 -> 같은 값으로 진행할 때 더 작은 라벨을 가지는 경우 더 작은 수열
 * 
 * 1. dfs로 다음 길이를 선택할 때 더 작은 라벨을 선택하여 진행
 * 2. 라벨까지의 합을 구한다.
 * 
 * 가는 경로를 리스트에 다 기록해두자
 * 리스트의 길이가 가장 긴 경우 따로 비교하는 수행 
 * 
 * 
 * 완전탐색 필요, 
 * */
