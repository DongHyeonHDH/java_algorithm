package J8229;
import java.io.*;
import java.util.*;

public class J8229 {
	static int[] precedure;
	static long[] concentrate;
	
	static int N;
	static long X;		
		
	static long[] memo;
	
	// visited와 비슷 0은 방문 x, 1은 dfs 탐색, 2는 계산완료
	static int[] state;
	static final long INF = Long.MAX_VALUE / 4;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Long.parseLong(st.nextToken());
		
		
		//생성
		precedure = new int[N+1];		
		concentrate = new long[N+1];	
				
		//집중력 수
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<= N; i++) {
			concentrate[i] = Long.parseLong(st.nextToken());			
		}
		
		memo = new long[N+1];
		state = new int[N+1];
		
		//이 공이 오기 위해 선행되어야 하는 공 넘버
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<= N; i++) {
			precedure[i] = Integer.parseInt(st.nextToken());		
		}		
		
		for(int i =1; i<= N; i++) {
			if(state[i] == 0) {
				dfs(i);
			}
		}
		
		
        for (int i = N; i >= 1; i--) {
            if (memo[i] <= X) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
		
	}
	
	static void dfs(int idx) {
		List<Integer> path = new ArrayList<>();
		int cur = idx;
		
		// 선행공을 따라간다. 재귀대신 반복문으로 구현 200,000의 stack을 쌓을 수 있기 때문
		while(cur != -1 && state[cur] == 0) {
			state[cur] = -1;
			path.add(cur);
			cur = precedure[cur];
		}
		
		  /*
         * 여기까지 왔을 때 경우는 3가지이다.
         *
         * 1. cur == -1
         *    → 선행 조건이 없는 공까지 도착
         *
         * 2. state[cur] == 2
         *    → 이미 계산된 노드를 만남
         *
         * 3. state[cur] == 1
         *    → 사이클 발견
         */
		long base;
		
		if(cur == -1) {
			base = 0;
		}else if(state[cur] ==2) {
			base= memo[cur];
		}else {
			base = INF;
		}
		
		
		for(int i = path.size()-1; i>=0; i--) {
			
			int node = path.get(i);
			if(base == INF) {
				memo[node] = INF;
			}
			else {
				memo[node] = base + concentrate[node];
				
				//의미적으로 INF보다 커지는 것 방지
				if(memo[node]>= INF) {
					memo[node] = INF;
				}
			}
			base = memo[node];
			state[node] = 2;
		}
		
		
	}
	
	
	
	
//	//dfs로 풀어보기
//	static void find(int idx, long concent) {
//		//집중력이 부족한경우 return		
//		
//		if(concent - concentrate[idx] < 0) {
//			flag = false;
//			System.out.println("checking");
//		}
//		
//		if(flag == false) {
//			if(layerFlag == true) {
//				answer = -1;
//			}
//			return;
//		}
//		
//		//성공 조건
//		if(precedure[idx] == -1 && (concent - concentrate[idx]) >= 0) {
//			System.out.println("concent: " + concent);
//			System.out.println("concentrate[idx]: " + concentrate[idx]);
//			success = true;
//			if(layerFlag == true) {
//				answer = idx;
//			}
//			return;
//		}	
//		
//		if(memo[idx] != -1)
//		
//		layerFlag = false;
//		find(precedure[idx], concent - concentrate[idx]);
//		
//		if(success == true) {
//			answer = idx;
//		}
//		//끝까지 접근했을 때 -1이 되어야 한다.
//	}
	
	
	
	
	
}		 