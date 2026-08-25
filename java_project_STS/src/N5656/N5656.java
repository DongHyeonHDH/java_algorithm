package N5656;
import java.io.*;
import java.util.*;
public class N5656 {	
	static boolean[][] visited;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int N,W,H;
	static int minNum;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		
		for(int tc =1; tc<=T; tc++) {
			
			minNum = Integer.MAX_VALUE;
			StringTokenizer st  = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[][] gameMap = new int[H][W];
			visited = new boolean[H][W];
			
			for(int i = 0; i< H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j< W; j++) {
					gameMap[i][j] = Integer.parseInt(st.nextToken());					
				}
			}
			
			System.out.println();
			
//			Node nd = new Node(0,0, gameMap[0][0]);
//			breakBricks(nd);
			findBricks(0,gameMap);

			
			
			sb.append('#')
			.append(tc)
			.append(' ')
			.append(minNum)
			.append('\n')
			;
		}		
		
		System.out.println(sb);
	}
	static void breakBricks(Node start, int[][] map) {

	    Deque<Node> queue = new ArrayDeque<>();

	    queue.add(start);

	    // ★ 최초 벽돌 반드시 제거
	    map[start.x][start.y] = 0;

	    while (!queue.isEmpty()) {

	        Node node = queue.poll();

	        for (int dist = 1; dist < node.weight; dist++) {

	            for (int d = 0; d < 4; d++) {

	                int nx = node.x + dx[d] * dist;
	                int ny = node.y + dy[d] * dist;

	                if (nx < 0 || nx >= H ||
	                    ny < 0 || ny >= W) {
	                    continue;
	                }

	                if (map[nx][ny] == 0) {
	                    continue;
	                }

	                int weight = map[nx][ny];

	                map[nx][ny] = 0;

	                if (weight > 1) {
	                    queue.add(new Node(nx, ny, weight));
	                }
	            }
	        }
	    }
	}
//	//bfs로 벽돌을 없애는 함수
//	static int[][] breakBricks(Node nd, int[][] map) {
//		Deque<Node> queue = new ArrayDeque<Node>();
//		queue.add(nd);
//		
//		while(!queue.isEmpty()) {
//			Node node = queue.poll();
//			int stride = node.weight;
//			
//			for(int i =1; i<stride; i++) {
//				for(int j= 0; j<4; j++) {
//					int x = node.x + (dx[j]*i);
//					int y = node.y + (dy[j]*i);
//					
//					//범위 내인 경우 bfs 실행
//					if(x >=0 && x<H && y >=0 && y<W) {
//						if(visited[x][y] == false) {
////							System.out.println("x: " +x+" y: "+y);
//							
//							if(map[x][y] == 1 || map[x][y] == 0) {
//								map[x][y] = 0;
//								visited[x][y] = true;
//							}
//							
//							else if (map[x][y] > 1 ){								
//								Node newNode = new Node(x,y, map[x][y]);
//								visited[x][y] = true;
//								map[x][y] = 0;
////								System.out.println();
////								
////								for(int k = 0; k< H; k++) {
////									for(int l = 0; l< W; l++) {
////										System.out.print(" "+gameMap[k][l]);
////									}
////									System.out.println();
////								}
////								System.out.println();
//								
//								
//								queue.add(newNode);
//							}
//						}
//						
//						
//					}
//				}				
//			}
//			
//		}
//		
//
//		
//		//폭발이 끝나고 끌어당기기 실행
//		//map=gravity(map);
//		return map;
//	}
	
	//dfs로 n개의 벽돌을 리턴하는 함수
//	static void findBricks(int idx, int[][] map) {	
//		//N개 충족하는 경우
//		if(idx == N) {			
//			int res = sumBricks(map);
//			minNum = Math.min(minNum,res);
//			return;
//		}
//				
//		
//		for(int j = 0; j< W; j++) {
//			for(int i = 0; i< H; i++) {	
//				
//				int[][] copy = copyMap(map);
//				
//				if(map[i][j] == 1) {
////					System.out.println();
////					System.out.println("idx:" +idx);
////					System.out.println();
//					
//					copy[i][j] = 0;
//					findBricks(idx+1, copy);
//				}
//				//크기가 1이상인 폭탄을 만나는 경우
//				else if(map[i][j] > 1) {
////					System.out.println();
////					System.out.println("idx:" +idx);
////					System.out.println();
//					
//					Node nd = new Node(i,j, copy[i][j]);
//					visited = new boolean[H][W];
//					copy = breakBricks(nd,copy);	
//					findBricks(idx+1,copy);
//			
//					
//				}
//				else {
//					continue;
//				}
//			}
//		}
//		
//	}
	
//	static void findBricks(int idx, int[][] map) {
//
//	    if (idx == N) {
//	        int res = sumBricks(map);
//	        minNum = Math.min(minNum, res);
//	        return;
//	    }
//
//	    for (int j = 0; j < W; j++) {
//
//	        // 현재 상태를 각 분기마다 새로 복사
//	        int[][] copy = copyMap(map);
//
//	        // 해당 열의 가장 위 벽돌 찾기
//	        int row = -1;
//
//	        for (int i = 0; i < H; i++) {
//	            if (copy[i][j] > 0) {
//	                row = i;
//	                break;
//	            }
//	        }
//
//	        // 해당 열에 벽돌이 없는 경우
//	        if (row == -1) {
//	            findBricks(idx + 1, copy);
//	            continue;
//	        }
//
//	        // 벽돌이 1인 경우
//	        if (copy[row][j] == 1) {
//	            copy[row][j] = 0;
//	        }
//
//	        // 벽돌이 2 이상인 경우
//	        else {
//	            visited = new boolean[H][W];
//	            Node nd = new Node(row, j, copy[row][j]);
//	            copy = breakBricks(nd, copy);
//	        }
//
//	        copy = gravity(copy);
//	        findBricks(idx + 1, copy);
//	    }
//	}
	
	static void findBricks(int count, int[][] map) {

	    int remain = sumBricks(map);

	    if (remain == 0) {
	        minNum = 0;
	        return;
	    }

	    if (count == N) {
	        minNum = Math.min(minNum, remain);
	        return;
	    }

	    for (int col = 0; col < W; col++) {

	        int row = -1;

	        // 현재 열의 가장 위 벽돌 찾기
	        for (int i = 0; i < H; i++) {
	            if (map[i][col] > 0) {
	                row = i;
	                break;
	            }
	        }

	        // 빈 열은 탐색하지 않음
	        if (row == -1) {
	            continue;
	        }

	        // 각 분기마다 현재 상태 복사
	        int[][] nextMap = copyMap(map);

	        visited = new boolean[H][W];

	        Node start = new Node(
	            row,
	            col,
	            nextMap[row][col]
	        );

	        // 1. 벽돌 폭발
	        breakBricks(start, nextMap);

	        // 2. 중력 적용
	        gravity(nextMap);

	        // 3. 다음 구슬
	        findBricks(count + 1, nextMap);
	    }
	}
	
	//비워져있는 벽돌을 아래로 당기는 함수
	//단순하게 반복문 돌려서 0을 만나면 위에 있는 1과 swap  
//	static int[][] gravity(int[][] map) {
//	    int[][] copy = new int[map.length][map[0].length];
//	    
//	    for(int i = H-1; i>= 0; i--) {
//			for(int j = 0; j< W; j++) {				
//				if(map[i][j] == 0){
//					for(int k = i; k>=0; k--) {
//						if(map[k][j] >= 1) {
//							int temp = map[k][j]; 
//							//swap
//							map[k][j] = 0;
//							map[i][j] = temp;
//							break;
//						}
//					}
//				}				
//			}
//		}
//		
//	    
//	    for (int i = 0; i < map.length; i++) {
//	        copy[i] = map[i].clone();
//	    }
//	    
////		for(int i = 0; i< H; i++) {
////			for(int j = 0; j< W; j++) {
////				System.out.print(" "+map[i][j]);
////			}
////			System.out.println();
////		}
////		System.out.println();
//		
//		
//		return copy;
//	}
	
	static void gravity(int[][] map) {

	    for (int col = 0; col < W; col++) {

	        int bottom = H - 1;

	        for (int row = H - 1; row >= 0; row--) {

	            if (map[row][col] > 0) {

	                int temp = map[row][col];
	                map[row][col] = 0;

	                map[bottom][col] = temp;

	                bottom--;
	            }
	        }
	    }
	}
	
	static int sumBricks(int[][] map) {
		int res = 0;
		for(int j = 0; j< W; j++) {
			for(int i = 0; i< H; i++) {
				if(map[i][j] >= 1) {
					res += 1;
				}
			}
		}
		return res;
	}
	
	static class Node{
		int x;
		int y;
		int weight;
		Node(int x, int y, int weight){
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
	}
	
	static int[][] copyMap(int[][] map) {

	    int[][] copy = new int[H][W];

	    for (int i = 0; i < H; i++) {
	        copy[i] = map[i].clone();
	    }

	    return copy;
	}
}
