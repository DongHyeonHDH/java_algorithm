package B17471;
import java.io.*;
import java.util.*;
public class B17471 {
	
	static int N;
	static int[] spot;
	static boolean[] selected;
	static int[][] edge;
	
	static List<List<Integer>> grA;
	static List<List<Integer>> grB;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		spot = new int[N+1];
		selected = new boolean[N+1];
		edge = new int[N+1][N+1];
		grA = new ArrayList<>();
		grB = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<= N; i++) {
			spot[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			int sg = Integer.parseInt(st.nextToken());
			for(int j = 1; j<= sg; j++) {
				int to = Integer.parseInt(st.nextToken());
				edge[i][to] = 1;
				edge[to][i] = 1;
			}
		}
		
		//1부터 시작하기 때문
		partJoin(1);
				
		//인구수 작은 순으로 sorting
		int size = grA.size();
		space2[] p = new space2[size];
		for(int i = 0; i< size; i++) {
			p[i] = new space2(grA.get(i),grB.get(i));
		}
		
		Arrays.sort(p);
		
		boolean find = false;
		//사이클 검증 되면 출력
		for(int i = 1; i<= N; i++) {
			if(checkCycle(p[i].a) && checkCycle(p[i].b)) {
				System.out.println(p[i].comp);
				find = true;
				break;
			}
		}
		if(!find) {
			System.out.println(-1);
		}
	}
	
	//두 부분집합으로 나누기
	static void partJoin(int idx) {
		if(idx == N+1) {
			List<Integer> a = new ArrayList<Integer>();
			List<Integer> b = new ArrayList<Integer>();
			for(int i = 1; i<= N; i++) {
				if(selected[i]) {
					a.add(i);
				}
				else {
					b.add(i);
				}
			}
			if(a.isEmpty() || b.isEmpty()) {
	            return;
	        }
			
			grA.add(a);
			grB.add(b);
			return;
		}
		
		selected[idx] = true;
		partJoin(idx + 1);
		
		selected[idx] = false;
		partJoin(idx + 1);
	}
	
	static class space2 implements Comparable<space2>{
		List<Integer> a;
		List<Integer> b;
		int comp;
		
		space2(List<Integer> a, List<Integer> b){
			this.a = a;
			this.b = b;
			this.comp = compute(a,b);
		}
		@Override
		public int compareTo(space2 other) {
			// TODO Auto-generated method stub
			return Integer.compare(this.comp, other.comp);
		}
	}
	
	static int compute(List<Integer> a, List<Integer> b) {
		int numA = 0;
		int numB = 0;
		
		for(int i = 0; i< a.size(); i++) {
			numA += spot[a.get(i)];
		}
		
		for(int i = 0; i< b.size(); i++) {
			numB += spot[b.get(i)];
		}
		
		return Math.abs(numA - numB);
	}
	
	static boolean checkCycle(List<Integer> a) {
		//각 선거구에 대해 방문가능한지 검증하는 함수, 한 지점이 다 체크가능하면 되는거 아닌가?		
		Queue<Integer> q = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N+1];
		int start = a.get(0);

		q.add(start);
		visited[start] = true;
		int count =1;
		while(!q.isEmpty()) {
			int sp = q.poll();
			
			for(int i = 1; i<= N; i++) {
				//연결되었는 경우
				if(edge[sp][i] == 1 && !visited[i] && a.contains(i)) {
					visited[i] = true;
					q.add(i);
					count++;
				}
			}
			
		}
		

		
		
		return count == a.size();
		
	}
}
