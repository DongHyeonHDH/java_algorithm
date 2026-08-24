package J1335;
import java.io.*;
import java.util.*;

public class J1335 {
	static boolean[][] checked;
	static int[][] gameMap;
	static int blue = 0;
	static int white = 0;
	static int N;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		//0이 하얀색
		//1이 파란색
		gameMap = new int[N][N];
		for(int i = 0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				gameMap[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		sumPaper(0,N,0,N);
		
		System.out.println(blue);
		System.out.println(white);
	}
	
	//구간별 blue, white 판별하는 함수
	static boolean req(int rowStart, int colStart, int rowEnd, int colEnd) {
		int res = 0;
		for(int i = rowStart; i< rowEnd; i++) {
			for(int j = colStart; j< colEnd; j++) {
				if(gameMap[i][j] == 1) {
					res += 1;
				}
			}
		}
		
		//비교군
		int comp = (colStart-colEnd) * (rowStart-rowEnd);
		
		if(comp == res) {
			blue += 1;
			return true;
		}
		if(res == 0) {
			white += 1;
			return true;
		}
		
		return false;
	}	
	
	static void sumPaper(int rowStart, int rowEnd, int colStart, int colEnd) {
		if (req(rowStart, colStart, rowEnd, colEnd)) {
	        return;
	    }

	    int rowMid = (rowStart + rowEnd) / 2;
	    int colMid = (colStart + colEnd) / 2;

	    sumPaper(rowStart, rowMid, colStart, colMid);
	    sumPaper(rowStart, rowMid, colMid, colEnd);
	    sumPaper(rowMid, rowEnd, colStart, colMid);
	    sumPaper(rowMid, rowEnd, colMid, colEnd);
		
	}
}
