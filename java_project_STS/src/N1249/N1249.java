package N1249;
import java.io.*;
import java.util.*;

public class N1249 {
	static int[][] gameMap;
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = null;
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					gameMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			
		}
	}
	static void dijkstra() {
		
	}
}