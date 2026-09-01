package N5643;
import java.io.*;
import java.util.*;
public class N5643 {
	
	static int[] indegree;
	static int[] level;
	static List<Integer>[] edge;
	static List<Integer>[] reverseEdge;
	static List<Integer>[] levelEdge;
	static boolean[] visited;
	static int N;
	static int isOK;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			int answer = 0;
		
			level= new int[N+1];
			indegree = new int[N+1];
			edge = new ArrayList[N+1];			
			for(int i =1; i<=N; i++) {
				edge[i] = new ArrayList<>();
			}		
			
			reverseEdge = new ArrayList[N+1];
			for(int i =1; i<=N; i++) {
				reverseEdge[i] = new ArrayList<>();
			}
			
			levelEdge = new ArrayList[N+1];
            for(int i =1; i<=N; i++) {
                levelEdge[i] = new ArrayList<>();
            }
			
			for(int i =0; i< M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				edge[a].add(b);
				reverseEdge[b].add(a);
				indegree[b]++;
			}
			
		    topologySort();
			for (int i = 1; i<=N; i++) {             
                levelEdge[level[i]].add(i);
            }
			
			for (int i = 1; i<=N; i++) {             
                System.out.println(levelEdge[i]);
            }
			
			//동일한 계층인 경우 판별작업 시행
			for(int i= 1; i<= N; i++) {				
				if(levelEdge[i].size() == 1) {
					int student = levelEdge[i].get(0);
					
					visited = new boolean[N+1];
					
					downBfs(student);
					upBfs(student);
					
					isOK = 0;
					for(int j=1; j<=N; j++) {
						if(j != student && visited[j]) {
							isOK++;
						}
					}
					if (isOK == N - 1) {
					    answer++;
					}
				}
				
			}
			sb.append("#")
			.append(tc)
			.append(" ")
			.append(answer)
			.append("\n")
			;
		}
		System.out.println(sb);
		
	}
	//현재 레벨에서 아래까지 bfs를 시행
	static void downBfs(int idx) {		
		Queue<Integer> q = new ArrayDeque<>();		
		q.offer(idx);
		visited[idx] = true;		
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: reverseEdge[current]) {				
			  if(!visited[next]) {
	                visited[next] = true;
	                q.offer(next);	                
	            }
			}
		}
	}
	
	//현재 레벨에서 위로 bfs를 시행
	static void upBfs(int idx) {
		Queue<Integer> q = new ArrayDeque<>();		
		q.offer(idx);				
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for(int next: edge[current]) {				
			  if(!visited[next]) {
	                visited[next] = true;
	                q.offer(next);

	            }
			}
		}
	}
	
	static void topologySort() {
        Queue<Integer> q = new ArrayDeque<>();      
        for(int i = 1; i<=N; i++) {
            if(indegree[i] == 0) {
                q.offer(i);
                level[i] = 1;
            }
        }
        while(!q.isEmpty()) {
            int current = q.poll();
             
            for(int next: edge[current]) {
                level[next] = Math.max(level[next], level[current]+1);
                indegree[next]--;
                 
                if(indegree[next] == 0) {
                    q.offer(next);
                }
            }            
             
        }
         
    }
	
	
}

//2. DAG를 사용한 그래프인지 판별
//그래프가 어디까지 들어가나 고려
//DAG인데 첫 노드가 아니고 중간에 있는 노드인데 존재하면 가능
//같은 계층인 것을 어떻게 알 수가 있을까?
//레벨의 유효성을 확인하는 함수 추가
//이전 레벨에 있는 것들을 다 거치긴 해야 한다.