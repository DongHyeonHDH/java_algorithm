package N1949;
import java.io.*;
import java.util.*;
public class N1949_practice {
	static int[][] gameMap;
	static boolean[][] visited;
	static List<Point> startPoints;
	static int N,K, longest;	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc<= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			gameMap = new int[N][N];
			visited = new boolean[N][N];
			startPoints = new ArrayList<>();
			int maxValue = 0;
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
						startPoints.add(new Point(i,j, gameMap[i][j]));
					}						
				}
			}
			
			//시뮬레이션 수행공간			
			for(Point pos: startPoints) {
				for(int i = 0; i<N; i++) {
					Arrays.fill(visited[i], false);
				}
				visited[pos.r][pos.c] = true;
				
				simul(pos,1, true);
				visited[pos.r][pos.c] = false;
//				System.out.println("-------------------");
			}
		
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(longest)
			.append("\n")
			;
		}
		System.out.println(sb);
	}
	static void simul(Point pos, int load, boolean usedK) {
//		System.out.println(pos.r+" "+ pos.c + " load: "+load);
		if(load > longest) {
			longest = load;
		}		
		
		for(int i = 0; i<4; i++) {
			int r = pos.r + dr[i];
			int c = pos.c + dc[i];
			
			if(r <0 || r >= N ||c <0 ||c >= N) continue;
					
			if(!visited[r][c] && (gameMap[r][c] < gameMap[pos.r][pos.c])) {				
				visited[r][c] = true;
				simul(new Point(r,c,gameMap[r][c]), load+1, usedK);
				visited[r][c] = false;
			}
			
			//k 줄이는 경우 이동 가능한 경우
			else if(!visited[r][c] && gameMap[r][c] >= gameMap[pos.r][pos.c]
					&& (gameMap[r][c] - K < gameMap[pos.r][pos.c]) && usedK){
				visited[r][c] = true;
				int temp = gameMap[r][c]; 
				gameMap[r][c] = gameMap[pos.r][pos.c] - 1;
				usedK = false;
				
				simul(new Point(r,c,gameMap[r][c]), load+1, usedK);
				
				gameMap[r][c] = temp;
				visited[r][c] = false;
				usedK = true;
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

/*
 * 가장 높은곳들에서 시작해서 아래로 간다고 하면 중간에 한번씩 교체를 한다.
 * 한 경로에서 시작할 때 백트래킹 걸고 비교 수행
 * 
 */
 