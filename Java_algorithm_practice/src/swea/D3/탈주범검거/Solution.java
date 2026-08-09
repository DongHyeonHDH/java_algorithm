package swea.D3.탈주범검거;

import java.io.*;
import java.util.*;

public class Solution {
	
	//입력 값
	static int[][] baseMap;
	//처리가 된 map
	static boolean[][] spot;
	static List<Integer> dx;
	static List<Integer> dy;
	static int N;
	static int M;
	
	static int R;
	static int C;
	static int L;
	
	static int answer;
	static int moveCnt;
	static Deque<Node> q;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());		
		for(int t = 1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			baseMap = new int[N][M];
			spot = new boolean[N][M];
			
			for(int i = 0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j =0; j<M; j++) {
					baseMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}			
			
			moveCnt =0;
			answer = 0;
			
			
			bfs();
			
			
			System.out.println("#"+t+" " +answer);
		}
	}
	//direct로 갈 수 있는 길에 관해 bfs 적용하기
	public static void bfs() {		
		q =  new ArrayDeque<>();
		
		q.offer(new Node(R, C, 0));
		System.out.println("R: "+R+ "C: "+C);		
		
		while(!q.isEmpty()) {			
			
			Node cur = q.poll();			
			
			direct(cur.x, cur.y, cur.level,baseMap[cur.x][cur.y]);			
			System.out.println(cur);			

		}
	}
	
	//map을 받아서 갈 수 있는길 없는 길 판단해주는 함수
	public static void direct(int row, int col, int level, int res) {
		int x;
		int y;
		Node node;
		if(isOk(row,col)) {			
			spot[row][col] = true;			
			if(level < L) {
				answer+=1;
			}
			
			
		}
		switch(res){
		case 1:			
			dx = List.of(0,0,1,-1);
			dy = List.of(1,-1,0,0);
			for(int i=0; i<4; i++) {
				x = row+dx.get(i);
				y = col+dy.get(i);				
				if(isOk(x,y)) {					
					node = new Node(x,y, level+1);
					q.offer(node);					
				}
				
			}
			
			break;
		case 2:			
			dx = List.of(1,-1);
			dy = List.of(0,0);
			for(int i=0; i<2; i++) {
				x = row+dx.get(i);
				y = col+dy.get(i);				
				if(isOk(x,y)) {					
					node = new Node(x,y,level+1);
					q.offer(node);					
				}
				
			}
			
			break;
		case 3:			
			dx = List.of(0,0);
			dy = List.of(1,-1);
			for(int i=0; i<2; i++) {
				x = row+dx.get(i);
				y = col+dy.get(i);				
				if(isOk(x,y)) {					
					node = new Node(x,y,level+1);
					q.offer(node);					
				}
				
			}
			
			break;
		case 4:			
			dx = List.of(0,-1);
			dy = List.of(1,0);
			for(int i=0; i<2; i++) {
				x = row+dx.get(i);
				y = col+dy.get(i);				
				if(isOk(x,y)) {					
					node = new Node(x,y,level+1);
					q.offer(node);					
				}
				
			}
			
			break;
		case 5:			
			dx = List.of(0,1);
			dy = List.of(1,0);
			for(int i=0; i<2; i++) {
				x = row+dx.get(i);
				y = col+dy.get(i);				
				if(isOk(x,y)) {					
					node = new Node(x,y,level+1);
					q.offer(node);					
				}
				
			}
			
			break;
		case 6:			
			dx = List.of(-1,0);
			dy = List.of(0,-1);
			for(int i=0; i<2; i++) {
				x = row+dx.get(i);
				y = col+dy.get(i);				
				if(isOk(x,y)) {					
					node = new Node(x,y,level+1);
					q.offer(node);					
				}
				
			}
			
			break;
		case 7:			
			dx = List.of(1,0);
			dy = List.of(0,-1);
			for(int i=0; i<2; i++) {
				x = row+dx.get(i);
				y = col+dy.get(i);				
				if(isOk(x,y) ) {					
					node = new Node(x,y,level+1);
					q.offer(node);					
				}
			}
			
			break;
		}	
	}	
	
	public static boolean isOk(int row, int col) {
		if(row>=0 && row< N && col>=0 && col< M && baseMap[row][col] >0 && spot[row][col] == false) {
			return true;
		}
		else {
			return false;
		}
	}

	//반대인 경우 고려
	public static boolean isOpp(int row, int col , int res) {
		if(row>=0 && row< N && col>=0 && col< M && baseMap[row][col] >0 && spot[row][col] == false) {
			return true;
		}
		else {
			return false;
		}
	}

	public static class Node{
		int x;
		int y;
		int level;
		Node(int x,int  y, int level) {
			this.x = x;
			this.y = y;		
			this.level = level;		
		}		
		public String toString() {
			return ("x: "+this.x+" y: "+this.y+" level: "+this.level);
		}
	}
	 
}

/*
해야할 리스트
1. opposite 값 도입하여 문제 해결
2. switch 남발 줄이기
3. bit masking 도입한 최적화 시도하기

*/