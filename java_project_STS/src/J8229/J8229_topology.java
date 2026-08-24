package J8229;
import java.io.*;
import java.util.*;

public class J8229_topology {
	static int N;
    static long X;

    static long[] A;
    static int[] P;

    static List<Integer>[] graph;
    static int[] indegree;
    
    // dp[i] = i번 공까지 떨어뜨리기 위해 필요한 총 집중력
    static long[] dp;
	
    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Long.parseLong(st.nextToken());

        A = new long[N + 1];
        P = new int[N + 1];
        graph = new ArrayList[N + 1];
        indegree = new int[N + 1];
        dp = new long[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {

            P[i] = Integer.parseInt(st.nextToken());

            if (P[i] != -1) {
                // 선행 공 -> 현재 공
                graph[P[i]].add(i);
                indegree[i]++;
            }
        }
        
        topology();
    }
    static void topology() {
    	Queue<Integer> queue = new ArrayDeque<>();

        /*
         * 선행 조건이 없는 공부터 시작
         */
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                // 이 공 하나만 떨어뜨리면 됨
                dp[i] = A[i];
            }            
        }	
        /*
         * 위상정렬 + DP
         */
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                /*
                 * next를 떨어뜨리기 위해서는
                 * cur까지 모두 떨어뜨리고
                 * next도 떨어뜨려야 한다.
                 */
                dp[next] = dp[cur] + A[next];
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        /*
         * 번호가 큰 공부터 확인
         */
        for (int i = N; i >= 1; i--) {

            /*
             * indegree[i] == 0
             *
             * 위상정렬이 정상적으로 끝난 노드
             *
             * 사이클에 포함된 노드는 indegree가 남아 있음
             */
            if (indegree[i] == 0 && dp[i] <= X) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }
}
