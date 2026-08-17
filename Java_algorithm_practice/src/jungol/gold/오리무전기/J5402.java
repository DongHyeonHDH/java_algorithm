package jungol.gold.오리무전기;

import java.io.*;
import java.util.*;

public class J5402 {
    
    static List<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i = 0; i<N; i++){
            graph[i] = new ArrayList<Edge>();
        }

        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = (start - to) * (start - to); 

            graph[start].add(new Edge(to,weight));
            graph[to].add(new Edge(start,weight));            
        }
        
        System.out.println(prim(1));
    }

    static int prim(int idx){
        int totalCost = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(idx,0));

        visited[idx] = true;

        while(!pq.isEmpty()){
            Edge current = pq.poll();
            int edgeTo = current.to;
            int edgeWeight = current.weight;
            
            if(visited[edgeTo]){
                continue;
            }

            visited[edgeTo] = true;
            totalCost += edgeWeight;

            for(Edge edge: graph[edgeTo]){
                if(!visited[edge.to]){
                    pq.offer(edge);
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
