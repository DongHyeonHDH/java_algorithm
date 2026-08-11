package J9659;
import java.util.*;
import java.io.*;

public class J9659 {
	
	static Node[] tree;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cid = 0;
		int pid = 0;
		tree = new Node[N+1];
		
		for(int i = 1; i<=N-1; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			cid = Integer.parseInt(st.nextToken());
			pid = Integer.parseInt(st.nextToken());			
			
			tree[cid] = new Node(cid, pid);
			
			
		}
		
		
		
	}
	
	static class Node{
		int idx;
		int pid;		
		
		Node(int cid, int pid){
			this.idx = cid;
			this.pid = pid;
		}
	}
	
//	1번 요구사항 루트와의 거리
	static void distanceRoot() {
		
	}
//	2번 요구사항 본인포함 자손노드 개수
	static void countChildNode() {
		
	}
//	3번 요구사항 가장 먼 자손노드와의 거리
	static void distanceFarthestChild() {
		
	}
//	4번 요구사항 가장 먼 노드와의 거리
	static void distanceFarthestNode() {
		
	}
	
}
