package codetree_C5.lesson2;

import java.io.*;
import java.util.*;

public class chapter5_2_5_2 {

    static int[][] grid;
    static int N;

    static int[] dx = {0, 1};
    static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        N = Integer.parseInt(br.readLine());
        grid = new int[N][N];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(min, grid[i][j]);
                max = Math.max(max, grid[i][j]);
            }
        }

        // 최댓값 - 최솟값의 최소 차이를 이분 탐색
        int left = 0;
        int right = max - min;
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canMove(mid, min, max)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean canMove(int diff, int min, int max) {

        // 가능한 최솟값 범위를 순회
        for (int low = min; low <= max - diff; low++) {
            int high = low + diff;

            if (bfs(low, high)) {
                return true;
            }
        }

        return false;
    }

    static boolean bfs(int low, int high) {

        // 시작점과 도착점이 범위 밖이면 불가능
        if (grid[0][0] < low || grid[0][0] > high ||
            grid[N-1][N-1] < low || grid[N-1][N-1] > high) {
            return false;
        }

        boolean[][] visited = new boolean[N][N];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            int x = cur[0];
            int y = cur[1];

            if (x == N - 1 && y == N - 1) {
                return true;
            }

            for (int d = 0; d < 2; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;

                if (grid[nx][ny] < low || grid[nx][ny] > high) {
                    continue;
                }

                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});
            }
        }

        return false;
    }
}