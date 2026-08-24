package N5656;
import java.io.*;
import java.util.*;
public class N5656 {
	static int[][] gameMap;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc =1; tc<=T; tc++) {
			StringTokenizer st  = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			
			
			
			for(int i = 0; i< H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j< W; j++) {
					gameMap[i][j] = Integer.parseInt(st.nextToken());					
				}
			}
			
			
			
			
			
		}		
		
	}
	
	//bfs로 벽돌을 없애는 함수
	static void breakBricks(Node nd) {
		Deque<Node> queue = new ArrayDeque<Node>();
		queue.add(nd);
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
		}
	}
	
	//dfs로 n개의 벽돌을 리턴하는 함수
	static void findBricks() {
		
	}
	
	//비워져있는 벽돌을 아래로 당기는 함수
	static void gravity() {
		
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
