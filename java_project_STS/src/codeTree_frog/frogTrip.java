package codeTree_frog;

import java.io.*;
import java.util.*;
public class frogTrip {
	static char[][] gameMap;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int N;
	
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		gameMap = new char[N+1][N+1];
		
		for(int i = 1; i<=N; i++) {
			String temp = br.readLine();
			for(int j = 1; j<=N; j++) {
				gameMap[i][j] = temp.charAt(i-1);
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		
		
		
		for(int i = 1; i<=Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			
			
			int minTime = simulation(r1,c1, r2,c2);
		}
		
		
	}
	
	static int simulation(int r1, int c1, int r2, int c2) {
		int minT= 0;		
		visited = new boolean[N+1][N+1];
		
		
		
		minT = dfs(r1, c1, r2, c2, 0);
		
		
		
		return minT;
	}
	
	static int dfs(int r1, int c1, int r2, int c2, int preTime) {
		int time = preTime;
		if(r1 == r2 && c1 == c2) {		
			time = Math.min(time, );
			return time;
		}
		
		for(int i = 0; i<4; i++) {
			int r = r1 + dr[i];
			int c = c1 + dc[i];
			
			if(r <1 || r>N || c <1|| c>N) continue;
			
			if(visited[r][c] && gameMap[r][c] != '#') {
				if(gameMap[r][c] == 'S') {
					
				}
				else {
					
				}
				
			}
			
		}
		
		
		
		
		
		return 0;
	}
	
	
}
