package J4947;
import java.util.*;
import java.io.*;

public class J4947 {
	static Node[] tree;
	static List<Integer> visited;
	
	static int firstFarDistance;
	static int firstMaxNode;
	
	static int secondFarDistance;
//	static int secondMaxNode;
	
	static int answerDistance;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		tree = new Node[N+1];
		visited = new ArrayList<>();
		
		for(int i = 1; i<= N; i++){
			tree[i] = new Node(i);
		}
		
		for(int i = 1; i<= N-1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int pid = Integer.parseInt(st.nextToken());
			int cid = Integer.parseInt(st.nextToken());
			
			// 부모노드 설정
			tree[cid].pid = pid;
			// 자식노드 설정
			tree[pid].cids.add(cid);
		}		
		
		//가장 먼거리의 노드를 구한 다음 거기서 가장 먼 노드의 거리를 구하면 될 것같다.
		FindFarthestNode(1, 0);
		System.out.println("firstMaxNode: " + firstMaxNode + " firstFarDistance : " + firstFarDistance);
		
		//visited 했는 경로 선택
		distanceRoot(firstMaxNode);
		System.out.println("visited: "+visited);
		
		//node에서의 최댓값 선택
		distanceNode(firstMaxNode);		
		
		//거리 구하기
		System.out.println(answerDistance);
		
	}
	
	static class Node {
		int idx;
		int pid;
		List<Integer> cids;
		
		Node(int idx){
			this.idx= idx;
			this.cids = new LinkedList<Integer>(); 
		}
	}
	
	//처음 가장 먼거리의 자손노드 구하기
	static void FindFarthestNode(int x, int distance){
		if(firstFarDistance < distance) {
			firstFarDistance = distance;
			firstMaxNode = x;
		}		
		
		if(tree[x].cids.size() == 0) {
			return;
		}
		
		for(int c: tree[x].cids) {
			FindFarthestNode(c, distance+1);
		}
	}
	
	//루트노드까지 갈때 방문하는 노드 선택
	static void distanceRoot(int x) {
		visited.add(x);
		
		if(x == 1) {	
			return;
		}
		int p = tree[x].pid;				
				
		distanceRoot(p);
	}
	
	//visited 노드에서 최대거리 구하기
	static void distanceNode(int x) {		
		for(int cid : visited) {		
			secondFarDistance = 0;
			FindFarthestNode(cid, 0);
			
			int res = secondFarDistance + visited.indexOf(cid);
			System.out.println(cid + "의 최대거리 : " + res);
			
			answerDistance = Math.max(answerDistance , secondFarDistance + visited.indexOf(cid));
		}		
		
	}
	
	static void distanceFarthestNode(int x, int distance) {
		if(tree[x].cids.size() == 0) {
			secondFarDistance = Math.max(distance, secondFarDistance);
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
