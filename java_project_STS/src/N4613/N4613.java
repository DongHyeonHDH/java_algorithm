package N4613;
import java.io.*;
import java.util.*;
public class N4613 {
	static int answer;
	static int[][] map;
	static int N,M;	
	static int[] comb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T= Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			
			//W = 1, B = 2, R = 3
			for(int i = 0; i< N; i++) {
				String temp = br.readLine();
				for(int j = 0; j< M; j++) {
					if(temp.charAt(j) == 'W') {
						map[i][j] =1;
					}
					else if(temp.charAt(j) == 'B') {
						map[i][j] =2;
					}
					else {
						map[i][j] = 3;
					}
				}
			}
			
			comb = new int[4];			
			Arrays.fill(comb, 1);
			comb[0] = 0;
			answer = Integer.MAX_VALUE;
			//어디까지 흰색, 그 아래는 파란색, 그 아래는 빨간색
			//2,1,1, 3,2,1  세가지에 대해 수행 거기에 대해 최솟값 수행		
			dfs(0,0);
		
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n")
			;
		}
		System.out.println(sb);
	}
	
	static void dfs(int idx, int sum) {
		if(idx == 3) {
		   if(sum == N) {
		        int temp = calc();
		        answer = Math.min(answer, temp);
		    }
			return;
		}
		
		int remain = 3 - idx - 1;
		int temp = 0;
		for(int i = 1; i<=N; i++) {
			if(sum +i +remain >N) {
				break;
			}
			comb[idx] = i;
			dfs(idx+1, sum+i);
		}
		
	}
	
	static int calc() {
	    int res = 0;
	    int row = 0;

	    for(int color = 0; color < 3; color++) {
	        for(int j = row; j < row + comb[color]; j++) {
	            for(int k = 0; k < M; k++) {
	                if(map[j][k] != color + 1) {
	                    res++;
	                }
	            }
	        }

	        row += comb[color];
	    }

	    return res;
	}	
}
