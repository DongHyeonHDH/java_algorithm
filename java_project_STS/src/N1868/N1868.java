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
			
			for(int i = 0; i< N; i++) {				
				for(int j = 0; j< N; j++) {
					System.out.print(" "+ map[i][j]);
				}
				System.out.println();
			}
			
			//시작지점에서 0의 터짐을 수행
			click += boom(startPoint, N);
			
			//터짐이 끝나면 빈곳을 체크한다.
			click += checking();
			
			sb.append("#").append(tc).append(" ").append(click).append("\n");
		}
		System.out.println(sb);
	}
	
	//터짐을 수행
	static int boom(List<Node> st, int N) {		
		int cnt = st.size();
		
		for(int i =0; i< cnt; i++) {
			//팔방향 탐색을 통해 0을 찾고 시행한다.
			Node cur = st.get(i);
			
			//이미 터진 0인 경우 continue
			if(checked[cur.x][cur.y]) continue;
			
			for(int k = 0; k< 8; k++) {
				int r = cur.x+dr[k];
				int c = cur.y+dc[k];
				
				if(r <0 || r>=N || c <0 || c>=N) continue;
				
				checked[r][c] = true;
				
				if(map[r][c] == 0) {} 
			}
		}
		
		return 0;
	}
	
	static int checking() {
		
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
