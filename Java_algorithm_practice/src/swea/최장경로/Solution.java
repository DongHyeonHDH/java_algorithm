package swea.최장경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    static List<List<Integer>> graph;
    static boolean[] visited;
    static int maxLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // 무방향 그래프
                graph.get(x).add(y);
                graph.get(y).add(x);
            }

            visited = new boolean[n + 1];
            maxLength = 1;

            for (int start = 1; start <= n; start++) {
                dfs(start, 1);
            }

            sb.append('#')
              .append(t)
              .append(' ')
              .append(maxLength)
              .append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int current, int length) {
        visited[current] = true;

        maxLength = Math.max(maxLength, length);

        for (int next : graph.get(current)) {
            if (!visited[next]) {
                dfs(next, length + 1);
            }
        }

        visited[current] = false;
    }
}