package N1868;

import java.io.*;
import java.util.*;

public class N1868_test{

    static int N;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());

            map = new char[N][N];
            visited = new boolean[N][N];

            for (int r = 0; r < N; r++) {
                map[r] = br.readLine().toCharArray();
            }

            int click = 0;

            // 1. 주변 지뢰가 0개인 칸부터 클릭
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '.' &&
                        !visited[r][c] &&
                        isZero(r, c)) {

                        bfs(r, c);
                        click++;
                    }
                }
            }

            // 2. BFS로 열리지 않은 일반 칸 직접 클릭
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {

                    if (map[r][c] == '.' && !visited[r][c]) {
                        click++;
                    }
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(click)
              .append("\n");
        }

        System.out.print(sb);
    }

    static void bfs(int sr, int sc) {

        Queue<Integer> q = new ArrayDeque<>();

        visited[sr][sc] = true;
        q.offer(sr * N + sc);

        while (!q.isEmpty()) {

            int pos = q.poll();

            int r = pos / N;
            int c = pos % N;

            for (int d = 0; d < 8; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;

                if (map[nr][nc] == '*')
                    continue;

                if (visited[nr][nc])
                    continue;

                // 큐에 넣기 전에 방문 처리
                visited[nr][nc] = true;

                // 0인 칸만 추가 전파
                if (isZero(nr, nc)) {
                    q.offer(nr * N + nc);
                }
            }
        }
    }

    static boolean isZero(int r, int c) {

        for (int d = 0; d < 8; d++) {

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;

            if (map[nr][nc] == '*')
                return false;
        }

        return true;
    }
}