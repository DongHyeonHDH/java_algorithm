package swea.파리퇴치3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    // + 모양 방향: 상, 하, 좌, 우
    static int[] plusRow = {-1, 1, 0, 0};
    static int[] plusCol = {0, 0, -1, 1};

    // X 모양 방향: 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래
    static int[] crossRow = {-1, -1, 1, 1};
    static int[] crossCol = {-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int max = 0;

            //중심점 이동
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    // 중심 칸 포함
                    int plusSum = arr[i][j];
                    int crossSum = arr[i][j];

                    // 중심에서 각 방향으로 m - 1칸 이동
                    for (int distance = 1; distance < m; distance++) {

                        // + 모양 계산
                        for (int direction = 0; direction < 4; direction++) {

                            int nextRow =
                                    i + plusRow[direction] * distance;

                            int nextCol =
                                    j + plusCol[direction] * distance;

                            // 배열 내부에 있는 칸만 더한다.
                            if (nextRow >= 0 && nextRow < n
                                    && nextCol >= 0 && nextCol < n) {

                                plusSum += arr[nextRow][nextCol];
                            }
                        }

                        // X 모양 계산
                        for (int direction = 0; direction < 4; direction++) {

                            int nextRow =
                                    i + crossRow[direction] * distance;

                            int nextCol =
                                    j + crossCol[direction] * distance;

                            // 배열 내부에 있는 칸만 더한다.
                            if (nextRow >= 0 && nextRow < n
                                    && nextCol >= 0 && nextCol < n) {

                                crossSum += arr[nextRow][nextCol];
                            }
                        }
                    }

                    max = Math.max(max, Math.max(plusSum, crossSum));
                }
            }

            System.out.println("#" + t + " " + max);
        }
    }
}
