package N2112;
import java.io.*;
import java.util.*;
public class N2112 {

	static int D, W, K;
	static boolean[][] glass;
	static int answer;
		
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			answer = D+1;
			glass = new boolean[D][W];			
			for(int i =0; i< D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<W; j++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						glass[i][j] = true;
					}					
				}
			}
			
			dfs(0,0, false);
			
			//약물을 투입하지 않고 성능검사를 통과하는 경우
			if(answer == D+1) {
				answer = 0;
			}
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");			
		}
		System.out.println(sb);
		
	}
	
	static void dfs(int idx, int used, boolean clear) {
		//원복을 위한 배열
		boolean[] original = new boolean[W];
		//정답 케이스
		if (used >= answer) {
	        return;
	    }
		
		if(checking()) {						
			answer = Math.min(answer, used);
			return;
		}
		
		if(idx == D) {
			return;
		}		

		//현재 칸 약물 선택
		
		for(int i = 0; i< W; i++) {
			original[i] = glass[idx][i]; 
		}
		
		//약물 안키고 적용하는 경우		
		dfs(idx+1, used, clear);
		
		//다 특성A 적용
		for(int i = 0; i< W; i++) {
			glass[idx][i] = false;
		}			
		//0, 1에 관한 조합-> 부분집합처럼 수행
		dfs(idx+1, used+1, clear);			
		
		for(int i = 0; i< W; i++) {
			glass[idx][i] = original[i];
		}
		
		//다 특성B 적용
		for(int i = 0; i< W; i++) {
			glass[idx][i] = true;
		}			
		dfs(idx+1, used+1, clear);
		
		for(int i = 0; i< W; i++) {
			glass[idx][i] = original[i];
		}	
	}
	
	//열에 k개만큼의 것이 주어졌는지 확인하는 함수
	static boolean checking() {				
//		W는 열
		for(int col = 0; col<W; col++) {
			int count = 1;
			boolean pass= (K==1);
			
			for(int row = 1; row<D; row++) {
				if(glass[row][col] == glass[row-1][col]) {
					count++;
				}else {
					count =1;
				}
				
				if(count>= K) {
					pass = true;
					break;
				}
			}
			
			if(!pass) {
				return false;
			}
		}
		
		return true;
	}

}
