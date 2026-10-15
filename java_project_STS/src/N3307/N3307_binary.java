package N3307;
import java.io.*;
import java.util.*;

public class N3307_binary {
	static int[] memo;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());		
		
		for(int tc = 1; tc<= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			memo = new int[N];
			int len = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i< N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
						
			for(int num: arr) {
				int left = 0;
				int right = len;
				
				while(left < right) {
					int mid = (left+right)/2;
					
					if(memo[mid] < num) {
						left = mid +1;
					}
					else{
						right = mid;
					}
				}
				
				memo[left] = num;
				if(left == len){
					len++;
				}
			}
			
			
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(len)
			.append("\n")
			;
		}	
		System.out.println(sb);
	}
}
