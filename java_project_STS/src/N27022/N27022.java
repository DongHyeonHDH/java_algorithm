package N27022;
import java.io.*;
import java.util.*;

public class N27022 {
	static int N;
	static int[] tree;
	static int[] mem;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N+1];
			mem = new int[N+1];
			int half = N/2;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i<= N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i= 1; i<= half; i++) {			
				mem[i] = mem[i-1]+Math.max(tree[2*i-1], tree[2*i]); 
				
			}
			
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(mem[half])
			.append("\n")
			;
		}
		System.out.println(sb);
	}	
	
}
