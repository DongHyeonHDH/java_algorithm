package N1767;
import java.io.*;
import java.util.*;
public class N1767 {

	static int[][] map;
	static boolean[] enableCore;
	static int[] coreX = new int[12];
	static int[] coreY= new int[12];
	static int coreCount;
	static int N;	
	
	
	static int maxCore;
	static int minLength;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			coreCount = 0;
			
			//입력 값 받기
			for(int i =0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j =0; j<N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					if(temp == 1) {
						map[i][j] = 2;

						// 외곽이 아닌 코어만 DFS 대상으로 해야한다.
					    if(i != 0 && i != N-1 && j != 0 && j != N-1) {
					        coreX[coreCount] = i;
					        coreY[coreCount] = j;
					        coreCount++;
					    }
					}
				}
			}
			enableCore = new boolean[coreCount+1];
			maxCore = 0;
			minLength = N*N;
			
			dfs(0, 0, 0);

			sb.append("#")
			.append(tc)
			.append(" ")
			.append(minLength)
			.append("\n")
			;
		}
		System.out.println(sb);
	}
	
	static void dfs(int cnt, int connectedCore, int wireLength) {
		if(cnt == coreCount) {
			if(connectedCore > maxCore) {
		        maxCore = connectedCore;
		        minLength = wireLength;
		    }
		    else if(connectedCore == maxCore) {
		        minLength = Math.min(minLength, wireLength);
		    }

		    return;
		}
		
		for(int i = 0; i<4; i++) {
			//4가지 방향에 관해 접근 시도, 같은 라인에 다른 core나 visited 된 것 있으면 안된다.
			//여기서 오류날 수도 있다.
			List<Integer> tempX = new ArrayList<>();
			List<Integer> tempY = new ArrayList<>();
			
			boolean possible = true;
			int x = coreX[cnt];
	        int y = coreY[cnt];

	        while(true) {
	        	x+= dx[i];
	        	y+= dy[i];
	        	
	        	if(x<0 || x>=N || y<0 || y>=N) {
	        		break;
	        	}
	        	
	        	if(map[x][y] != 0) {
	        		possible = false;
	        		break;
	        	}
	        	
	        	tempX.add(x);
	        	tempY.add(y);
	        }
	        
	        if(!possible) {
	        	continue;
	        }
	        
	        enableCore[cnt] = true;
	        for(int j = 0; j < tempX.size(); j++) {
	            map[tempX.get(j)][tempY.get(j)] = 1;
	        }

	        dfs(cnt + 1, connectedCore+1, wireLength+tempX.size());

	        enableCore[cnt] = false;

	        for(int j = 0; j < tempX.size(); j++) {
	            map[tempX.get(j)][tempY.get(j)] = 0;
	        }
	        
		}
		enableCore[cnt] = false;
		dfs(cnt + 1, connectedCore, wireLength);
	}
//			//순회를 했는데 갈 수 있는 것이 없는 경우
//			for(int j=1; j<N;j++) {
//				int x =coreX[cnt] + dx[i]*j;
//				int y =coreY[cnt] + dy[i]*j;				
//				if(x>=0 && x<N && y>=0 && y<N) {
//					//map에서 core나 visited 만나면 오면서 기록했던것 초기화 시도
//					if(map[x][y] > 0) {
//						tempX.clear();
//						tempY.clear();
//						break;						
//					}
//					else if(map[x][y] == 0) {
//						tempX.add(x);
//						tempY.add(y);
//					}
//				}
//			}					
//			
//			if(tempX.size() > 0) {				
//				enableCore[cnt] = true;
//				//된 경우 직선화 진행
//				for(int j = 0; j< tempX.size(); j++) {
//					map[tempX.get(j)][tempY.get(j)] = 1;
//				}		
//			}			
//			//다음으로의 dfs 실행
//			dfs(cnt+1);
//			
//			//백트래킹
//			//직선화 된 것 백트래킹
//			if(tempX.size() > 0) {
//				enableCore[cnt] = false;
//				for(int j = 0; j< tempX.size(); j++) {
//					map[tempX.get(j)][tempY.get(j)] = 0;
//				}
//			}			
//			

		
		
	
 
}
