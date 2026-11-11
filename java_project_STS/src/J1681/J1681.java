package J1681;
import java.io.*;
import java.util.*;

public class J1681 {
	static int[][] gameMap;
	static int selected;
	static int N, temp, minNum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		gameMap = new int[N+1][N+1];		
		selected = 0;
		
		int cnt = 0;
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				gameMap[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		temp = 0;
		minNum = 100 * N;
		dfs(1, selected , 1);
		
		System.out.println(minNum);
	}
	
	//check에 방문한 것을 저장할 것
	static void dfs(int idx, int check, int prePoint){
		
		if(temp >= minNum){
			return;
		}		
		
		//다 고른 경우
		if (idx == N) {
	        // 출발점으로 복귀 가능한지 확인
	        if (gameMap[prePoint][1] != 0) {
	            minNum = Math.min(minNum, temp + gameMap[prePoint][1]);
	        }

	        return;
	    }
		
		for(int i = 2; i<= N; i++) {
			if((check & (1 << i)) == 0 && gameMap[prePoint][i] != 0)  {
				temp += gameMap[prePoint][i];
				dfs(idx + 1, (check | (1<<i)), i);
				temp -= gameMap[prePoint][i];
			}
			
		}
		
	}	
}
