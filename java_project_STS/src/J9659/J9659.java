package J9659;
import java.util.*;
import java.io.*;

public class J9659 {
	
	static Node[] tree;	
	static List<Integer> visited;
	
	//1번용
	static int distanceOne;	
	static int N;
	
	//3번용
	static int X;	
	static int farthestD;
	
	//4번용	
	static int farthest4D;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int cid = 0;
		int pid = 0;
		tree = new Node[N+1];		
		visited = new ArrayList<>();
		
		
		for(int i = 1; i<=N; i++){
			tree[i] = new Node(i);
		}	
		
		for(int i = 1; i<=N-1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			cid = Integer.parseInt(st.nextToken());
			pid = Integer.parseInt(st.nextToken());			
			
			tree[pid].cids.add(cid);
			tree[cid].pid = pid;
			
		}
		X = Integer.parseInt(br.readLine());
		
		//1번문제
		distanceRoot(X);
		int ans1 = distanceOne;
		System.out.println(ans1);	
		
		//2번문제
		int ans2 = countChildNode(X);
		System.out.println(ans2);
		
		//3번문제
		distanceFarthestChild(X,0);
		int ans3 = farthestD;
		System.out.println(ans3);
		
		//4번문제	
		distanceNode(X);
		int ans4 = farthest4D;
		System.out.println(ans4);
	}
	
	static class Node{
		//자기 노드 번호
		int idx;
		//부모 노드
		int pid;
		//자식 노드들
		List<Integer> cids;		
		
		Node(int idx){
			this.idx = idx;
			this.cids = new LinkedList<Integer>(); 
		}	
		
	}	
	
//	1번 요구사항 루트와의 거리 재귀 사용
	static void distanceRoot(int x) {
		visited.add(x);
		
		if(x == 1) {	
			return;
		}
		int p = tree[x].pid;	
				
		distanceOne = distanceOne +1;		
		distanceRoot(p);	
		
	}
	
//	2번 요구사항 본인포함 자손노드 개수, bfs 사용
	static int countChildNode(int x) {
		int answer = 1;
		Deque<Integer> q = new ArrayDeque<>();
		for(int cid : tree[x].cids) {
			q.add(cid);
		}		
		while(!q.isEmpty()) {
			int c = q.poll();
			answer +=1 ;
//			System.out.println("c: "+ c + "answer: "+ answer);
			for(int cid : tree[c].cids) {
				q.add(cid);				
			}
		}
		
		return answer;
	}
//	3번 요구사항 가장 먼 자손노드와의 거리
	static void distanceFarthestChild(int x, int distance) {
		if(tree[x].cids.size() == 0) {
			farthestD = Math.max(distance, farthestD);
			return;
		}
		
		for(int cid : tree[x].cids) {							
			distanceFarthestChild(cid, distance+1);
//			System.out.println("cid: "+ cid + " distance:" + distance);
		}
				
	
	}
//	4번 요구사항 가장 먼 노드와의 거리
//	루트에서 자기자신 포함하고 올라가서의 dfs 구하기
	static void distanceNode(int x) {		
		for(int cid : visited) {		
			farthestD = 0;
			distanceFarthestNode(cid, 0);
			
			int res = farthestD + visited.indexOf(cid);
//			System.out.println(cid + "의 최대거리 : " + res);
			
			farthest4D = Math.max(farthest4D , farthestD + visited.indexOf(cid));
		}		
		
	}
/*
 * 그 노드까지 최단거리로 간다는 기준으로 젤 먼것을 구하는 것이라서 dfs를 고려할 필요가 있는 거 같다.
 * 한칸 위로 올라갈 때 마다 dfs 적용해주고 visited 인 경우는 방문하지 않도록 한다. 
 * */	
	static void distanceFarthestNode(int x, int distance) {
		if(tree[x].cids.size() == 0) {
			farthestD = Math.max(distance, farthestD);
			return;
		}

		for(int cid : tree[x].cids) {
			if(visited.contains(cid)) {
				continue;
			}
			distanceFarthestNode(cid, distance+1);
		}				
		
	}
}
