package N1868;
import java.io.*;
import java.util.*;
public class N1868 {
	// 0번 인덱스 "지뢰 없음" 1번 인덱스 "지뢰 있음"	
	static char[][] cMap;
	static boolean[][] checked;
	static int[][] map;
	static int[] dr = {1,1,1,-1,-1,-1,0,0};
	static int[] dc = {1,0,-1,-1,0,1,1,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int click = 0;
			cMap = new char[N][N];
			map = new int[N][N];
			checked = new boolean[N][N];
			List<Node> startPoint = new ArrayList<>();
			
			for(int i = 0; i< N; i++) {
				String temp = br.readLine();
				for(int j = 0; j< N; j++) {
					cMap[i][j] = temp.charAt(j);
				}
			}
			
			for(int i = 0; i< N; i++) {				
				for(int j = 0; j< N; j++) {
					int tempNum = 0;
					if(cMap[i][j] =='*') {
						map[i][j] = -1;
						continue;
					}
					for(int k = 0; k< 8; k++) {
						int r = i+dr[k];
						int c = j+dc[k];
						
						if(r <0 || r>=N || c <0 || c>=N) continue;
						
						if(cMap[r][c] == '*') tempNum++;
					}
					map[i][j] = tempNum;
					if(map[i][j] == 0) {
						startPoint.add(new Node(i,j));
					}
				}
			}					
			
			//시작지점에서 0의 터짐을 수행
			click += boom(startPoint, N);
			
			//터짐이 끝나면 빈곳을 체크한다.
			click += checking(N);
			
			sb.append("#").append(tc).append(" ").append(click).append("\n");
		}
		System.out.println(sb);
	}
	
	//터짐을 수행, 0에서는 다 적용되어 있어야 한다.
	static int boom(List<Node> st, int N) {		
		int cnt = st.size();		
		int res =0;				
		
		for(int i =0; i< cnt; i++) {			
			//팔방향 탐색을 통해 0을 찾고 시행한다.
			Node cur = st.get(i);									
			
			//이미 터진 0인 경우 continue
			if (checked[cur.x][cur.y]){
				continue;
			}
			//안 터진 0에 관해 bfs 수행
			else {
				Queue<Node> q = new ArrayDeque<>();
				res += 1;
				checked[cur.x][cur.y] = true; 
				q.add(cur);			
			
			 
			
				while(!q.isEmpty()) {
					Node nd = q.poll();
					
					//BFS에서 꺼낼때 방문처리하고 넣을때 방문해주는 것의 차이가 이렇게 심하게 나는 이유가 뭘까?
					//그 노드를 꺼내기 전에 노드를 중복으로 들어가서 체킹을 시도하기 때문이다.
					for(int k = 0; k< 8; k++) {
						int r = nd.x+dr[k];
						int c = nd.y+dc[k];
						
						if(r <0 || r>=N || c <0 || c>=N) continue;					
						
												
						
						if(map[r][c] == 0 && !checked[r][c]) {
							q.add(new Node(r,c));
							checked[r][c] = true;
						}
						else {
							checked[r][c] = true;
						}					
					}
				}
			}
		}
		
		
			
		return res;
	}
	
	static int checking(int N) {
		int res = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] != -1 && !checked[i][j]) {
					checked[i][j] = true;
					res += 1;
				}
			}
		}	

		
		return res;
	}
	
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
