package jungol.gold.오리무전기;
import java.io.*;
import java.util.*;

public class J5402 {

    static List<Edge>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];

        graph = new ArrayList[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {

            StringTokenizer st =
                    new StringTokenizer(br.readLine());

            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {

            for (int j = i + 1; j < N; j++) {

                int dx = x[i] - x[j];
                int dy = y[i] - y[j];

                int weight = dx * dx + dy * dy;

                graph[i].add(new Edge(j, weight));
                graph[j].add(new Edge(i, weight));
            }
        }

        System.out.println(prim(0));
    }

    static int prim(int start) {

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        int answer = 0;

        while (!pq.isEmpty()) {

            Edge current = pq.poll();

            int edgeTo = current.to;
            int edgeWeight = current.weight;

            if (visited[edgeTo]) {
                continue;
            }
            
            visited[edgeTo] = true;

            // MST 간선들 중 최대값
            answer = Math.max(answer, edgeWeight);

            // 현재 정점과 연결된 간선 탐색
            for (Edge edge : graph[edgeTo]) {

                if (!visited[edge.to]) {
                    pq.offer(edge);
                }
            }
        }

        return answer;
    }

    static class Edge implements Comparable<Edge> {

        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}