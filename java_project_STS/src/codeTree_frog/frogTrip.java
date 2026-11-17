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
		
		minT = djkstra(r1, c1, r2, c2);
		
		return minT;
	}
	
	static int djkstra(int r1, int c1, int r2, int c2) {
		
		
		
		while(!q.isEmpty()) {
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
		}
			
		
			
		
		return 0;
	}
	//점프하는 길이를 반환
	static int jumping(int row, int col, int dir) {
		int prac = 0;
		switch(dir) {
			// 남쪽
			case 0 :{				
				for(int i = row; i<=N; i++) {
					if(gameMap[i][col] == '.') {
						break;						
					}			
					else {
						prac++;
					}
					
				}				
				break;
			}
			// 북쪽
			case 1 :{
				for(int i = row; i>=0; i--) {
					if(gameMap[i][col] == '.') {
						break;						
					}			
					else {
						prac++;
					}
					
				}				
				break;				
			}
			// 서쪽
			case 2 :{
				for(int i = col; i>=0; i--) {
					if(gameMap[row][i] == '.') {
						break;						
					}
					else {
						prac++;
					}
				}				
				break;
			}
			// 동쪽
			case 3 :{
				for(int i = 1; i<=N; i++) {
					if(gameMap[row][i] == '.') {
						break;						
					}
					else {
						prac++;
					}
				}				
				break;
			}
		}
		
		return prac;
	}
}
