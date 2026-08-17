package jungol.gold;

import java.util.*;
import java.io.*;

public class J1060{
    static List<Edge>[] graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); 
        int v = 0;
        int e = 0;
        int[][] schools = new int[N][N];
        
        graph = new ArrayList[N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i< N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j< N; j++){
                schools[i][j] = Integer.parseInt(st.nextToken());
                if(i != j){
                    graph[i].add(new Edge(j, schools[i][j]));    
                    graph[j].add(new Edge(i, schools[i][j]));
                }                
                
            }
        }
        System.out.println(prim(0));
    }

    static int prim(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        int totalCost =0;

        while(!pq.isEmpty()){
            Edge current = pq.poll();

            int node = current.to;
            int weight = current.weight;

            if(visited[node]){
                continue;
            }

            visited[node] = true;
            totalCost += weight;

            for(Edge next: graph[node]){
                if(!visited[next.to]){
                    pq.offer(next);
                }
            }
            
        }

        return totalCost;
    }

    static class Edge implements Comparable<Edge>{
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.weight, o.weight);
        }        
    }


}