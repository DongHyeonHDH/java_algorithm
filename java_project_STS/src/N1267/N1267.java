package N1267;
import java.io.*;
import java.util.*;
public class N1267 {
	
	static int N;
	static int M;
	static List<Integer>[] graph;
	static int[] indegree;	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
		
			//edge 저장
			graph = new ArrayList[N+1];
			//각 vertex별 들어오는 간선 저장
			indegree = new int[N+1];
			
			for(int i =1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			//방향 그래프 저장
			st = new StringTokenizer(br.readLine());
			for(int i =0; i<M; i++) {			
								
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
				indegree[to]++;
			}
			
			sb.append('#')
			.append(tc)
			.append(' ')
			;
			
			topologicalSort();
			
		}
		System.out.println(sb);
	}
	
	static void topologicalSort() {
		Queue<Integer> queue = new ArrayDeque<>();
		List<Integer> result = new ArrayList<>();
		
		//진입 차수가 0인 정점을 큐에 삽입
		for(int i = 1; i<=N; i++) {
			if(indegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			//처리된 정점을 결과로 저장
			result.add(current);
			
			//current에서 출발하는 모든 간선 확인
			for(int next: graph[current]) {
				//current -> next 간선을 제거한 것처럼 처리
				indegree[next]--;
				
				//next의 선행작업이 모두 끝난 경우
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}		
		}
		
		if(result.size() != N) {
			System.out.println("사이클이 존재하여 위상 정렬 x");				
			return;
		}
		
		for (int node : result) {
            sb.append(node)
            .append(' ')
            ;
        }
		sb.append('\n');
		
	}
}
