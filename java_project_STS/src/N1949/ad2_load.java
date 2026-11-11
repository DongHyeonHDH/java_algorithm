/*
 * codetree 보충 문제
 * 실전연습 2번 등산로 공사 
 * 
 */
package N1949;
import java.util.*;
import java.io.*;

public class ad2_load {
	static int[][] gameMap;
	static boolean[][] visited;
	static List<Point> startPoints;
	static int N,K, longest, maxValue;	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
				
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		gameMap = new int[N][N];
		visited = new boolean[N][N];
		startPoints = new ArrayList<>();
		maxValue = 0;
		for(int i = 0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j< N; j++) {
				gameMap[i][j] = Integer.parseInt(st.nextToken());
				if(gameMap[i][j] > maxValue) maxValue = gameMap[i][j];
				
			}
		}
		
		longest = 0;
		for(int i = 0; i< N; i++) {			
			for(int j = 0; j< N; j++) {
				if(maxValue == gameMap[i][j]) {
					startPoints.add(new Point(i, j, gameMap[i][j]));
				}						
			}
		}
		
		//시뮬레이션 수행공간			
		for(Point pos: startPoints) {
			for(int i = 0; i<N; i++) {
				Arrays.fill(visited[i], false);
			}
			visited[pos.r][pos.c] = true;
			
			simul(pos, 1, K);
			visited[pos.r][pos.c] = false;
			System.out.println("-------------------");
		}
		System.out.println(longest);
	}
	
	static void simul(Point pos, int load, int numK) {
		System.out.println(pos.r+" "+ pos.c + " gameMap: "+gameMap[pos.r][pos.c]+" load: "+load+ " numK: "+numK);
		if(load > longest) {
			longest = load;
		}		
		
		for(int i = 0; i<4; i++) {
			int r = pos.r + dr[i];
			int c = pos.c + dc[i];
			
			if(r <0 || r >= N ||c <0 ||c >= N) continue;
					
			if(!visited[r][c] && (gameMap[r][c] < gameMap[pos.r][pos.c])) {				
				visited[r][c] = true;
				simul(new Point(r,c,gameMap[r][c]), load+1, numK);
				visited[r][c] = false;
			}
			
			//k 줄이는 경우 이동 가능한 경우
			else if(!visited[r][c] && gameMap[r][c] >= gameMap[pos.r][pos.c]
					&& (gameMap[r][c] - numK < gameMap[pos.r][pos.c]) 
					&& gameMap[r][c] != maxValue
					&&  gameMap[pos.r][pos.c] >= 2 && numK > 0){				
				
				
				// 남아 있는 K값이 지형을 못깎는 경우
				if(numK <(gameMap[r][c] - (gameMap[pos.r][pos.c] -1))){
					continue;
				}
								
				
				int temp = gameMap[r][c];
				int newHeight = gameMap[pos.r][pos.c] - 1;
				int cost  = gameMap[r][c] - newHeight;
				
				gameMap[r][c] = newHeight;
				visited[r][c] = true;
				simul(new Point(r,c,gameMap[r][c]), load+1, numK - cost);
				
				gameMap[r][c] = temp;
				visited[r][c] = false;
								
			}
		}
	}
		
	static class Point{
		int r;
		int c;
		int height;
		
		Point(int x, int y, int height){
			this.r = x;
			this.c = y;
			this.height = height;
		}
	}
}
