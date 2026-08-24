package J3560;

import java.io.*;
import java.util.*;

public class J3560 {
	
	static boolean[][] gameMap;
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int temp = 0;
		gameMap = new boolean[N][N];
		
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					gameMap[i][j] = true; 
				}		
			}
			
		}
		dfs(0,N,0,N);
		
		System.out.println(sb);
	}
	
	static boolean req(int rowStart, int colStart, int rowEnd, int colEnd) {
		int res = 0;
		for(int i = rowStart; i< rowEnd; i++) {
			for(int j = colStart; j< colEnd; j++) {
				if(gameMap[i][j] == true) {
					res += 1;
				}
			}
		}
		
		//비교군
		int comp = (colStart-colEnd) * (rowStart-rowEnd);
		
		if(comp == res) {
			sb.append(1);
			return true;
		}
		if(res == 0) {
			sb.append(0);
			return true;
		}
		
		sb.append('X');
		return false;
	}
	
	static void dfs(int rowStart, int rowEnd, int colStart, int colEnd) {
		if (req(rowStart, colStart, rowEnd, colEnd)) {
	        return;
	    }

	    int rowMid = (rowStart + rowEnd) / 2;
	    int colMid = (colStart + colEnd) / 2;

	    dfs(rowStart, rowMid, colStart, colMid);
	    dfs(rowStart, rowMid, colMid, colEnd);
	    dfs(rowMid, rowEnd, colStart, colMid);
	    dfs(rowMid, rowEnd, colMid, colEnd);
	}
}
