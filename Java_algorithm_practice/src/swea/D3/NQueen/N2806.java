package N2806;
import java.util.*;
import java.io.*;

public class N2806 {
	
	static int N;
	static int answer = 0;
	
	//이전에 방문했던 위치를 저장하기 위한 배열
	static int[] visited;
	
	
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++){
			N = Integer.parseInt(br.readLine());			
			visited = new int[N];			
			Arrays.fill(visited, -1);
			
			answer = 0;
					
			DFS(0);			
			sb.append('#')
				.append(tc)
				.append(' ')
				.append(answer)
				.append('\n')
				;
		}
		System.out.println(sb);
		
	}	
	
	static void DFS(int row) {
		
		if(row == N) {			
			answer ++;
			return;
		}		
		
		for(int col = 0; col< N; col++) {
			//방문한 것 체킹
			if(check(row, col)) {				
				visited[row] = col;			
				
//				System.out.println("방문: "+ visited[col]);
				DFS(row+1);
				
				//백트래킹
				visited[row] = -1;
			}			
			
		}		
		
	}	
	
	static boolean check(int row, int col) {		
		for(int previousR = 0; previousR<row; previousR++) {
			
			int previousC = visited[previousR]; 
			
			//같은 열에 있는지 체크
			if(col == previousC) {
				return false;
			} 
			
			//대각선 관계인지 체크
			if(Math.abs(row-previousR) == Math.abs(col-previousC)) {
				return false;
			}		
		}
		return true;
	}
		

}

/*
 * 10칸에 아래로 내려갈때 dfs를 적용하면 가능할 것 같다
 * 재귀로 내려간 후 return 할 때 방문했던 노드랑 상관없는 위치에 있으면 배치 
 * 
 */