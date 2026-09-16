/*
 * N7699 수지의 수지 맞는 여행
 * */
import java.io.*;
import java.util.*;

public class N7699 {
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int[][] map;
	static int R,C;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			map = new int[R+1][C+1];			
			
			//알파벳 A의 아스키 : 65						
			//알파벳 Z의 아스키 : 90
			
			for(int i = 1; i<= R; i++) {
				String temp =br.readLine();
				for(int j = 1; j<= C; j++) {
					//visited는 0부터
					map[i][j] = temp.charAt(j-1) - 65;
				}
			}
			
			int answer = bfs(1,1);
			sb.append("#")
			.append(tc)
			.append(" ")			
			.append(answer)
			.append("\n")
			;
		}
		System.out.println(sb);
	}
	
	static int bfs(int row, int col) {
		Queue<Node> q = new ArrayDeque<>();
		
		HashSet<Integer>[][] visited =
                new HashSet[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                visited[r][c] = new HashSet<>();
            }
        }
        
		int startMask = 1 << map[row][col];
		Node start = new Node(row,col,startMask, 1);		
		q.add(start);
		
		visited[row][col].add(startMask);
		int length = 1;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			length = Math.max(length, cur.cnt);			
						
			
			for(int i = 0; i<4; i++) {
				int r = cur.x + dr[i];
				int c = cur.y + dc[i];
				
				if(r<1 || r>R || c<1 || c>C) {
					continue;
				}
				
				int nextAlphabet = map[r][c];
				if((cur.mask & (1 << nextAlphabet)) != 0)
					continue;
	    
				int newMask = cur.mask | (1 << nextAlphabet);
				if (visited[r][c].contains(newMask)) {
                    continue;
                }
				// 5. 새로운 상태 등록
                visited[r][c].add(newMask);
				
				q.offer(new Node(r,c,newMask,cur.cnt+1));
			}
		}
		return length;
				
	}
	static class Node{
		int x;
		int y;
		int mask;
		int cnt;	
		
		Node(int x, int y, int mask, int cnt){
			this.x = x;
			this.y = y;
			this.mask = mask;
			this.cnt = cnt;
			
		}
	}
	
	
}
