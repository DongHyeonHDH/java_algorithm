package N3307;
import java.io.*;
import java.util.*;
public class N3307 {
	static int[] memo;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());		
		
		for(int tc = 1; tc<= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			memo = new int[N];
			Arrays.fill(memo, 1);
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i< N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int answer = 1;
			for(int i =0; i<N; i++) {
				for(int j =0; j<i; j++) {
					if(arr[j] < arr[i]) {
						memo[i] = Math.max(memo[i], memo[j]+1);
					}
				}
				answer = Math.max(answer, memo[i]);
			}
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n")
			;
		}	
		System.out.println(sb);
	}
}

