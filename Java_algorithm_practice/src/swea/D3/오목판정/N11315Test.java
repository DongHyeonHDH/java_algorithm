package swea.D3.오목판정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N11315Test {

    static char[][] gameMap;
    static int N;

    // 가로, 세로, 오른쪽 아래 대각선, 왼쪽 아래 대각선
    static final int[] dx = {0, 1, 1, 1};
    static final int[] dy = {1, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(br.readLine());
            gameMap = new char[N][N];

            // 테스트 케이스마다 초기화
            String answer = "NO";

            // 맵 입력
            for (int i = 0; i < N; i++) {

                String temp = br.readLine();

                for (int j = 0; j < N; j++) {
                    gameMap[i][j] = temp.charAt(j);
                }
            }

            boolean found = false;

            // 모든 칸 검사
            for (int row = 0; row < N && !found; row++) {

                for (int col = 0; col < N; col++) {

                    // 돌이 없는 곳은 검사할 필요 없음
                    if (gameMap[row][col] != 'o') {
                        continue;
                    }

                    if (checkMap(row, col)) {
                        answer = "YES";
                        found = true;
                        break;
                    }
                }
            }

            sb.append('#')
              .append(tc)
              .append(' ')
              .append(answer)
              .append('\n');
        }

        System.out.print(sb);
    }


    // 현재 위치를 시작점으로 4방향 검사
    static boolean checkMap(int row, int col) {

        for (int dir = 0; dir < 4; dir++) {

            // 현재 위치의 돌 포함
            int count = 1;

            // 현재 위치 다음부터 4칸 검사
            for (int i = 1; i < 5; i++) {

                int nextRow = row + dx[dir] * i;
                int nextCol = col + dy[dir] * i;

                if (isOk(nextRow, nextCol)) {
                    count++;
                } else {
                    // 중간에 끊기면 이 방향은 실패
                    break;
                }
            }

            if (count >= 5) {
                return true;
            }
        }

        return false;
    }


    // 범위 안에 있고 돌이 있는지 검사
    static boolean isOk(int row, int col) {

        if (row < 0 || row >= N ||
            col < 0 || col >= N) {
            return false;
        }

        return gameMap[row][col] == 'o';
    }
}