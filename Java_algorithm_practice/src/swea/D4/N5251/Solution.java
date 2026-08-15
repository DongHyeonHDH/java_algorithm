package swea.D4.N5251;
import java.io.*;
import java.util.*;

/*
다익스트라를 떠올리는 조건
간선 비용 모두 0이상
음수 가중치가 없는 경우
*/


/*
우선순위 큐 사용이유
다익스트라는 아직 처리하지 않은 정점 중 시작점으로부터 거리가 가장 짧은 정점을 선택
가장 작은 값을 빠르게 꺼내는 것이 가능하다.
*/
public class Solution{
    static int[] distance;
    static int INF = Integer.MAX_VALUE;
    static List<List<Edge>> graph;

    static class Edge{
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node>{
        int vertex;
        int distance;

        Node(int vertex, int distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other){
            return Integer.compare(this.distance, other.distance);
        }
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(distance, INF);

        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentDistance = current.distance;

            if(currentDistance > distance[currentVertex]){
                continue;
            }

            for(Edge edge: graph.get(currentVertex)){
                int nextVertex = edge.to;
                int newDistance = currentDistance + edge.weight;

                if(newDistance < distance[nextVertex]){
                    distance[nextVertex] = newDistance;
                    pq.offer(new Node(nextVertex, newDistance));                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();

        long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for(int i =0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                //Map을 그래프로 입력
                for(int j =0; j<N; j++){
                    int s = Integer.parseInt(st.nextToken());
                    int e = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());
                    graph.get(s).add(new Edge(e,w));
                }
            }

            dijkstra(0);

        }

        long endTime = System.nanoTime();

        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();

        System.out.println("실행 시간: " + (endTime - startTime) / 1_000_000.0 + " ms");
        System.out.println("메모리 사용량: " + (memoryAfter - memoryBefore) / 1024.0 / 1024.0 + " MB");
    }
}