package N3282;
import java.io.*;
import java.util.*;

public class N3282 {
	static int[] weight;
	static int[] value;
	static int[][] dp;
	static int N;
	static int K;
	
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N =  Integer.parseInt(st.nextToken());
			K =  Integer.parseInt(st.nextToken());
			
			//가방 V:부피,C:가치
			answer = 0;
			dp = new int[K+1][K+1];
			weight = new int[N];
			value = new int[N];
			int sumV = 0;
			int sumC = 0;
			for(int i =0; i< N; i++) {
				 Arrays.fill(dp[i], -1);
			}
			for(int i =0; i< N; i++) {
				st = new StringTokenizer(br.readLine());
				int V = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				weight[i] = V;
				value[i] = C;
			}			
			
			//가치의 최대합, 부피합은 k이하여야 한다.	
							
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(knapsack(0,0))
			.append('\n')
			;
		}
		System.out.println(sb);
		
		
	}	
	
	
	static int knapsack(int idx, int currentWeight) {
		if(idx == N) {			
			return 0;
		}
		
		if(dp[idx][currentWeight] != -1) {
			return dp[idx][currentWeight];
		}
		int result = knapsack(idx+1, currentWeight);
		
		if(currentWeight + weight[idx] <=K) {
			result= Math.max(value[idx]+ knapsack(idx+1, currentWeight+ weight[idx]), result);
		}
				
		return dp[idx][currentWeight] = result;
		
	}		

}

//for(int i = 0; i<N; i++) {
//	if(visited[i] == true) {
//		System.out.print("visited True ");
//	}
//	else {
//		System.out.print("visited false ");
//	}
//}
//System.out.println("                ");
//System.out.println("sumV : "+sumV);
//System.out.println("sumC : "+sumC);
//System.out.println("----------------");
//
//System.out.println("idx : "+idx);

