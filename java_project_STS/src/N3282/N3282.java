package N3282;
import java.io.*;
import java.util.*;

public class N3282 {
	static Node[] arr;
	static int N;
	static int K;
	static int sumC;
	static int sumV;
	static int answer;
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N =  Integer.parseInt(st.nextToken());
			K =  Integer.parseInt(st.nextToken());
			
			//가방 V부피,C가치
			answer =0;
			arr = new Node[N];
			visited = new boolean[N];
			
			sumC = 0;
			sumV = 0;
			for(int i =0; i< N; i++) {
				st = new StringTokenizer(br.readLine());
				int V = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				arr[i] = new Node(V,C);
			}
			
			//가치의 최대합, 부피합은 k이하여야 한다.
			knapsack(0);
			
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append('\n')
			;
		}
		System.out.println(sb);
		
		
	}
	
	static class Node{
		int V;
		int C;
		
		Node(int V, int C){
			this.V = V;
			this.C = C;
		}
	}
	
	static void knapsack(int idx) {		
		if(sumV > K) {			
			return;
		}
		
		if(visited[idx] == false) {
			visited[idx] = true;
			sumV += arr[idx].V;
			sumC += arr[idx].C;
			
			knapsack(idx+1);
			answer = Math.max(answer, sumC);
			visited[idx] = false;
		}				
		
	}
}
