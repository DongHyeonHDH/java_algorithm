package N27022;
import java.io.*;
import java.util.*;

public class N27022 {
	static int N;
	static int[] tree;
	static int[] mem;
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			tree = new int[N];
			mem = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i< N; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}
			
			
		}
	}
	
	static int func(int idx) {
		for(int i= 0; i< N; i++) {			
			mem[i] = Math.max(mem[i-1]+ tree[i], ); 
			
		}
	}
}
