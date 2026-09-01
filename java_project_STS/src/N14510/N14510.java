package N14510;
import java.io.*;
import java.util.*;
public class N14510 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] height = new int[N];
			int maxHeight = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());			
			for(int i = 0; i<N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
				if(height[i] > maxHeight) {
					maxHeight = height[i];
				}
			}
			

//			물을 안주는 것이 가능 홀수는 1자라고 짝수날은 2자란다
// 			끝에 1,2씩 오니 3으로 나누었을 때 나오는 것에 관한 부가 연산을 더해주면 될 것 같다.
			int answer = 0;
			//짝수번째가 와야 하는 날은 홀수가 와야 하는 날을 포함할 수가 있다.			
			int evenCount = 0;
			int oddCount = 0;
			
//			System.out.println("tree ");
			for(int tree : height) {			
				//최대 높이인 tree는 스킵
				if(maxHeight == tree) {
					continue;
				}				
				
				int diff = maxHeight - tree;				
				oddCount += diff % 2;
				evenCount += diff / 2;
				
				int branch = diff % 3;
				
			}
			//2가 많이 나오는 경우 최대한 1,1의 형태로 바꾸어줘야 한다. 
			while (evenCount > oddCount + 1) {
				evenCount--;
				oddCount += 2;
		  	}		

            if (oddCount > evenCount) {                
                answer = oddCount * 2 - 1;

            } 
            else {
            	answer = evenCount * 2;
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
