package swea.D4.N5643;
import java.io.*;
import java.util.*;

public class N5643 {
	static int N;
	static int M;	
	static StringBuilder sb = new StringBuilder();
	static int[][] matrix = new int[500][500];
	static int[][] visited = new int[500][500];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		boolean flag = false;

		for(int tc =1; tc<= T; tc ++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());									
			
			for(int i = 0; i< M; i++) {				
				StringTokenizer st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());	
				
				//인접리스트 기록, 재활용
				matrix[from][to] = tc;				
			}			
			
			for(int student = 0; student < N; student++){
				//자기보다 아래에 있는 것 dfs
				flag = true;
				dfs(student,flag, tc);

				//자기보다 위에 있는 것에 대해 dfs
				flag = true;
				dfs(student,flag, tc);

			}

			sb.append('#')
			.append(tc)
			.append(' ')
			;			

			
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int idx, boolean flag, int tc){
		
		
		


	}		
	//걍 방향은 저장되어 있으니까 각 점에서 dfs 찍어서 지나간 vertex의 개수인 경우에 정답일 것 같다.
}
