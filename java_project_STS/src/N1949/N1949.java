package N1949;
import java.io.*;
import java.util.*;

public class N1949 {
	static int[][] gameMap;
	static boolean[][] visited;
	static boolean[][] kvisited;
	static int[] startX = new int[5];
	static int[] startY = new int[5];
	static int N,K,distanceMax;	
		
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			distanceMax = 0;			
			
			int maxHeight = 0;
			int cnt = 0;
			gameMap = new int[N][N];
			visited = new boolean[N][N];		
			kvisited = new boolean[N][N];
			//입력값 삽입 및 최대높이 저장
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					gameMap[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, gameMap[i][j]);
				}
			}
			
			//최대높이 시작점 저장			
			for(int i = 0; i<N; i++) {				
				for(int j = 0; j<N; j++) {
					if(gameMap[i][j] == maxHeight) {
						startX[cnt] = i;
						startY[cnt++] = j;
					}
				}
			}			
			
			
			for(int i = 0; i<cnt; i++) {
				visited[startX[i]][startY[i]] = true;
				dfs(startX[i], startY[i], 1, false);
				visited[startX[i]][startY[i]] = false;
			}
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(distanceMax)
			.append("\n")
			;
		}
		System.out.println(sb);
	}	
	
	
	

	static void dfs(int x, int y, int length, boolean usedK) {
		distanceMax = Math.max(distanceMax, length);
		
		for(int d = 0; d< 4; d++) {
			int nx = x  + dx[d];
			int ny = y  + dy[d];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) {
				continue;
			}
			// 이동
			if (gameMap[nx][ny] < gameMap[x][y]) {
	            visited[nx][ny] = true;
	            dfs(nx, ny, length + 1, usedK);
	            visited[nx][ny] = false;
	        }
			
			else if(!usedK) {
				//K로 깎아서 이동 가능한지 판단
				if(gameMap[nx][ny] - gameMap[x][y] +1 <= K) {
					int original = gameMap[nx][ny];
					gameMap[nx][ny] = gameMap[x][y] - 1;
					
					visited[nx][ny] = true;
					dfs(nx, ny , length +1, true);
					visited[nx][ny] = false;
					
					gameMap[nx][ny] = original;
				}				
			}
		}
	}
	
	static class Node{
		int x;
		int y;
		int height;
		
		Node(int x, int y, int height){
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
}
