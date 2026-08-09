package swea.D3.탈주범검거;

import java.io.*;
import java.util.*;

public class Solution2 {

    static int[][] baseMap;
    static boolean[][] spot;

    static int N;
    static int M;
    static int R;
    static int C;
    static int L;

    static int answer;

    
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static final int[][] Pdir = {
        {},             // 0번: 터널 없음
        {0, 1, 2, 3},   // 1번
        {0, 2},         // 2번
        {1, 3},         // 3번
        {0, 1},         // 4번
        {1, 2},         // 5번
        {2, 3},         // 6번
        {0, 3}          // 7번
    };

    public static void main(String[] args) throws IOException {

        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            StringTokenizer st =
                new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            baseMap = new int[N][M];
            spot = new boolean[N][M];

            for (int i = 0; i < N; i++) {

                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < M; j++) {
                    baseMap[i][j] =
                        Integer.parseInt(st.nextToken());
                }
            }

            answer = 0;

            bfs();

            sb.append("#")
              .append(t)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.print(sb);
    }

    public static void bfs() {

        Deque<Node> q = new ArrayDeque<>();

        
        q.offer(new Node(R, C, 1));
        spot[R][C] = true;
        answer = 1;

        while (!q.isEmpty()) {

            Node cur = q.poll();

           
            if (cur.level == L) {
                continue;
            }

            int currentTunnel = baseMap[cur.x][cur.y];

           
            for (int dir : Pdir[currentTunnel]) {

                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

           
                if (!isOk(nx, ny)) {
                    continue;
                }

                int nextTunnel = baseMap[nx][ny];

           
                if (!isConnected(dir, nextTunnel)) {
                    continue;
                }

           
                spot[nx][ny] = true;
                answer++;

                q.offer(
                    new Node(nx, ny, cur.level + 1)
                );
            }
        }
    }

    public static boolean isOk(int row, int col) {

        return row >= 0
            && row < N
            && col >= 0
            && col < M
            && baseMap[row][col] > 0
            && !spot[row][col];
    }

    //터널 연결된 것인지 검사
    public static boolean isConnected(
        int dir,
        int nextTunnel
    ) {        
        int opposite = (dir + 2) % 4;

        for (int nextDir : Pdir[nextTunnel]) {

            if (nextDir == opposite) {
                return true;
            }
        }

        return false;
    }

    static class Node {

        int x;
        int y;
        int level;

        Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }

        @Override
        public String toString() {
            return "x: " + x
                + " y: " + y
                + " level: " + level;
        }
    }
}