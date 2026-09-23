package N2115;
import java.io.*;
import java.util.*;
public class N2115 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			//선택 가능한 벌통의 개수
			int M = Integer.parseInt(st.nextToken());
			//채취 가능한 벌꿀의 양
			int C = Integer.parseInt(st.nextToken());
			
			int[][] honeyBox = new int[N][N]; 
			boolean[][] visited = new boolean[N][N];
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					honeyBox[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			
		}
	}

}
