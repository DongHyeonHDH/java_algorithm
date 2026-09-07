package N1949;
import java.io.*;
import java.util.*;

public class N1949 {
	static int[][] gameMap;
	static boolean[][] visited;
	static Node[] startNode = new Node[5];	
	static int N,K,distanceMax;
	static boolean kflag;
		
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			distanceMax = 0;
			kflag = true;
			
			int maxHeight = 0;
			int cnt = 0;
			gameMap = new int[N][N];
			visited = new boolean[N][N];		
			
			//입력값 삽입 및 최대높이 저장
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j<N; j++) {
					gameMap[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, gameMap[i][j]);
				}
			}
			
			//최대높이 시작점 저장			
			for(int i = 0; i<N; i++) {				
				for(int j = 0; j<N; j++) {
					if(gameMap[i][j] == maxHeight) {
						startNode[cnt++] = new Node(i,j, gameMap[i][j]); 
					}
				}
			}			
			
			
			for(int i = 0; i<cnt; i++) {
				dfs(startNode[i], 0);
			}
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(distanceMax)
			;
		}
		System.out.println(sb);
	}	
	
	//
	static void dfs(Node node, int distance) {
		//언제 원복을 해야할까? 해야할 시점을 잡지 못한다면 안하자
		//k 시점을 어떻게 해?
		
		if(distance > distanceMax) {
			distanceMax = distance;
		}
		
		//길찾기 및 접근
		for(int i =0; i< 4; i++) {
			int x = node.x+dx[i];
			int y = node.y+dy[i];
			
			if(x<0 || x>=N || y<0 || y>=N) {
				continue;
			}
			
			//k 적용, 함수로 나눌지 생각
			//이거 언제 원복시켜주지?
			//--------------------------
			if((gameMap[x][y] - K) < gameMap[node.x][node.y] 
				&& gameMap[x][y] >= gameMap[node.x][node.y]
				&& kflag
			) {				
				kflag = false;
				int kMax = 0;
				//얼만큼 깎을지 판단
				for(int k = 1; k<=K; k++) {
					int kpoint = 0;
					for(int l =0; l< 4; l++){
						int kx = x+dx[l];
						int ky = y+dy[l];
						
						if(gameMap[x][y] - k < gameMap[kx][ky]) {
							kpoint++;
						}
					}
					//어느정도 줄여야 할까?
					if(kMax < k) {
						kMax = k;
					}
				}				
				gameMap[x][y] -= kMax;
			}
				
//			----------------------
			if(gameMap[x][y] < node.height && !visited[x][y] ) {
				Node cur = new Node(x,y,gameMap[x][y]);
				visited[x][y] = true;
				dfs(cur, distance + 1);
				visited[x][y] = false;
			}
		}
				
		
	}
	
	static class Node{
		int x;
		int y;
		int height;
		
		Node(int x, int y, int height){
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
}
