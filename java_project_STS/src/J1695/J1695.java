/*
 * 정올 1695번 단지번호 붙이기
 * */
package J1695;

import java.io.*;
import java.util.*;
public class J1695 {
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	static boolean[][] visited;
	static int[][] map;
	static int N;	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N= Integer.parseInt(br.readLine());		
		map = new int[N][N];
		visited = new boolean[N][N];
		ArrayList<Integer> answer = new ArrayList<>();
		
		for(int i =0; i<N; i++) {
			String temp = br.readLine();
			for(int j =0; j<N; j++) {
				map[i][j] = Integer.parseInt(temp.charAt(j)+"");
			}
		}
	
		
		
		int cnt = 0;
		for(int i =0; i<N; i++) {		
			for(int j =0; j<N; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					answer.add(bfs(i,j));
					cnt++;				
		
				}
			}
		}
				
		System.out.println(cnt);
		Collections.sort(answer);
		for(int n : answer) {
			System.out.println(n);
		}
		
	}
	static int bfs(int r, int c) {
		Queue<Node> q = new ArrayDeque<Node>();
		int sum = 0;
		q.add(new Node(r,c));
		
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			
			for(int i=0; i<4; i++) {
				int row = cur.x + dr[i];
				int col = cur.y + dc[i];
				
				if(row <0 || row>= N || col <0 || col>= N) {
					continue;
				}
				
				if(map[row][col] == 1 && !visited[row][col]) {
					q.add(new Node(row,col));
					visited[row][col] = true;
					sum++;
				}
					
			}
			
		}
		
		if(sum == 0) {
			sum =1;
		}
		return sum;
	}
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
