package swea.그래프연습;
import java.io.*;
import java.util.*;

public class Solution {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for(int i = 0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n+1];

        for(int i = 0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for(int i = 1; i<= n; i++){
            Collections.sort(graph.get(i));
        }

        sb= new StringBuilder();

        visited = new boolean[n+1];
        dfs(start);

        sb.append('\n');


        visited = new boolean[n+1];
        bfs(start);

        System.out.println(sb);

    }

    static void bfs(int start){
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.println(current + " ");

            for(int next: graph.get(current)){
                if(!visited[next]){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    static void dfs(int current){
        visited[current] = true;
        sb.append(current).append(' ');

        for(int next: graph.get(current)){
            if(!visited[next]){
                dfs(next);
            }
        }
    }
    
}
