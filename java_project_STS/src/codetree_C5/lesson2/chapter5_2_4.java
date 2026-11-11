package codetree_C5.lesson2;

import java.io.*;
import java.util.*;
public class chapter5_2_4 {
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static long[][] grid;
	static int[][] memo;
	static boolean[][] visited;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		grid = new long[N][N];
		memo = new int[N][N];
		
		int max = 0;
		
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j< N; j++) {
				grid[i][j] = Long.parseLong(st.nextToken());
			}
		}
		
		for(int i = 0; i<N; i++) {			
			for(int j = 0; j< N; j++) {
				max = Math.max(bfs(new Node(i,j)), max);
			}
		}
		
		System.out.println(max);
	}
	
	
	static int bfs(Node node) {
		int max = 1; 
		Queue<Node> q =  new ArrayDeque<>();
		visited = new boolean[N][N];
		q.add(node);
		boolean flag = false;
		boolean layerFlag = false;
		
		while(!q.isEmpty() && !flag) {
			Node cur = q.poll();
			
			for(int i = 0; i< 4; i++) {
				int r = cur.row + dr[i];
				int c = cur.col + dc[i];
				
				if(r <0 || r >= N || c <0 || c >= N) continue;
				
				if(grid[r][c] > grid[cur.row][cur.col] && !visited[r][c]) {					
					
					//메모이제이션 된 값을 가지고 있는 경우
					if(memo[r][c] != 0) {
						max += memo[r][c];
						flag = true;
					}else {
						 layerFlag = true;	
						 q.add(new Node(r,c));
						 visited[r][c] = true;
					}
				}
			}
			
			//다음 길이로 갔으면 넣을 수 있는 layer 값 높여준다.  
			if(layerFlag) {
				max += 1;
				layerFlag = false;
			}
			
		}
		
		memo[node.row][node.col] = max;
		return max;
	}
	
	static class Node{
		int row;
		int col;
		
		Node(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
		
}
