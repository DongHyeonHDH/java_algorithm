package N27021;
import java.io.*;
import java.util.*;
public class N27021 {
	
	static int[] dp;
	static int[] box;
	static int N;
	static int C;
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			box = new int[C];
			//1<=C<=5
			//상자 종류, 종류별 상자가 얼마나 쓰였는지 저장하는 배열
			dp = new int[N+1];
//			answer = 0;
			
//			Arrays.fill(dp, -1);
			
			st = new StringTokenizer(br.readLine());	
			for(int i = 0; i<C; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}
			dp[0]=1;
	
			for(int i =0; i<C; i++) {				
				int currentBox = box[i];
				System.out.println("box[i] " + box[i]);
				for(int sum = currentBox; sum<=N; sum++) {
					dp[sum] += dp[sum - currentBox];
					System.out.println("dp[sum] " + dp[sum] + " dp[sum-currentBox] " + dp[sum-currentBox] );
				}
			}
			
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(dp[N])
			.append('\n')
			;
		}	
		
		System.out.println(sb);
		
		
	}	
	
	
}

/*
 * 
 * 첫번째 케이스
 * (1,2),(1,1,1)
 * 
 * 1
 *  1+1
 *  2 
 *     1+1+1
 *     2+1
 *          1+1+1+1
 *          2+2
 *          1+1+2
 * 두번째 케이스
 * (1,1,1,1), (2,2), (1,1,2)
 * */
