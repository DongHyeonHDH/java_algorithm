/*
 * 백준 토마토 7576 
 */

package B7576;
import java.io.*;
import java.util.*;

public class B7576 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int[][] farm = new int[N][M];
		StringTokenizer st = null;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				farm[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
	}
}


/*
 * 0은 익지 않은 토마토, 1 익은 토마토, -1 토마토가 하나 있는 경우
 * 
 * 
 */