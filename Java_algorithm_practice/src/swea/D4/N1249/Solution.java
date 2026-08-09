package swea.D4.N1249;
import java.io.*;
import java.util.*;

public class Solution {
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,1,-1};
    
    static int[][] warMap;
    static List<List<Node>> graph;
    static int[][] distance;
    static int N;
    static int INF = Integer.MAX_VALUE;    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int res;

        //출발점은 (0,0) 도착점은 (n-1, n-1)

        for(int t=1; t<=T; t++){
            //입력값 입력
            N = Integer.parseInt(br.readLine());
            distance = new int[N][N];
            warMap = new int[N][N];
            for(int i = 0; i<N; i++){
                String line = br.readLine();
                for(int j = 0; j< N; j++){
                    //char int형으로 치환해주기
                    warMap[i][j] = line.charAt(j)  - '0';
                }                
            }
            res = dijkstra();

            sb.append("#")
              .append(t)
              .append(" ")
              .append(res)
              .append("\n")
              ;
        }
        System.out.println(sb);
    }
    static int dijkstra(){
        PriorityQueue <Node> pq = new PriorityQueue<>();        
        for(int i = 0; i < N; i++){
            Arrays.fill(distance[i], INF);
        }           
        distance[0][0] = 0;
        pq.offer(new Node(0,0, 0));

        while(!pq.isEmpty()){
            Node current = pq.poll();

            int row = current.row;
            int col = current.col;
            int cost = current.cost;

            if(cost > distance[row][col]){
                continue;
            }
            if(row == N-1 && col == N-1){
                return cost;
            }
            for (int direction = 0; direction < 4; direction++) {
                int nextRow = row + dx[direction];
                int nextCol = col + dy[direction];

                if (!isOk(nextRow, nextCol)) {
                    continue;
                }

                int nextCost = cost + warMap[nextRow][nextCol];

                if (nextCost < distance[nextRow][nextCol]) {
                    distance[nextRow][nextCol] = nextCost;

                    pq.offer(new Node(nextRow, nextCol, nextCost));
                }
            }
        }

        return -1;
    }

    static boolean isOk(int row, int col){
        if(row>=0 && row<N && col>=0 && col<N){
            return true;
        }
        else{
            return false;
        }
    }

    static class Node implements Comparable<Node>{
        int row;
        int col;
        int cost;
        Node(int row, int col, int cost){
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other){
            return Integer.compare(this.cost, other.cost);
        }
    }

    static class Edge{
        int to;
        int weight;

        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }   
        
    }
}
