package sfytest;

public class Java_test2 {
	package java_test;

	import java.io.*;
	import java.util.*;

	public class javaTest2 {

	    static int N, M;

	    // true = 농지(0)
	    // false = 산(1)
	    static boolean[][] farm;

	    /*
	     * planted[r][c]
	     *
	     * 0 : 아무것도 심어져 있지 않음
	     * 양수 : 씨앗을 심은 날짜
	     */
	    static int[][] planted;

	    /*
	     * 방향
	     * 0 : 우
	     * 1 : 상
	     * 2 : 좌
	     * 3 : 하
	     *
	     * 따라서 현재 방향 dir부터
	     * dir, dir+1, dir+2, dir+3 순서로 보면
	     * 우 → 상 → 좌 → 하 방향으로 회전하게 됨
	     */
	    static int[] dr = {0, -1, 0, 1};
	    static int[] dc = {1, 0, -1, 0};

	    public static void main(String[] args) throws Exception {

	        BufferedReader br =
	                new BufferedReader(new InputStreamReader(System.in));

	        StringBuilder sb = new StringBuilder();

	        int T = Integer.parseInt(br.readLine());

	        for (int tc = 1; tc <= T; tc++) {

	            StringTokenizer st =
	                    new StringTokenizer(br.readLine());

	            N = Integer.parseInt(st.nextToken());
	            M = Integer.parseInt(st.nextToken());

	            farm = new boolean[N][N];

	            // ==========================
	            // 맵 입력
	            // ==========================

	            for (int r = 0; r < N; r++) {

	                st = new StringTokenizer(br.readLine());

	                for (int c = 0; c < N; c++) {

	                    int value =
	                            Integer.parseInt(st.nextToken());

	                    // 0 = 농지
	                    // 1 = 산
	                    farm[r][c] = (value == 0);
	                }
	            }

	            int answer = 0;

	            /*
	             * ==========================
	             * 모든 시작 위치 확인
	             * ==========================
	             */

	            for (int r = 0; r < N; r++) {

	                for (int c = 0; c < N; c++) {

	                    // 산에서는 시작할 수 없음
	                    if (!farm[r][c]) {
	                        continue;
	                    }

	                    /*
	                     * ==========================
	                     * 초기 방향 4개 확인
	                     * ==========================
	                     */
	                    for (int dir = 0; dir < 4; dir++) {

	                        // 각각의 시뮬레이션은 독립적
	                        planted = new int[N][N];

	                        int harvest =
	                                simulate(r, c, dir);

	                        answer =
	                                Math.max(answer, harvest);
	                    }
	                }
	            }

	            sb.append("#")
	              .append(tc)
	              .append(" ")
	              .append(answer)
	              .append("\n");
	        }

	        System.out.print(sb);
	    }


	    /*
	     * ==========================================
	     * M일 동안 로봇 시뮬레이션
	     * ==========================================
	     */
	    static int simulate(
	            int startR,
	            int startC,
	            int startDir) {

	        int row = startR;
	        int col = startC;

	        // 현재 로봇이 바라보는 방향
	        int dir = startDir;

	        // 현재 수확량
	        int harvest = 0;


	        /*
	         * 1일부터 M일까지 진행
	         */
	        for (int day = 1; day <= M; day++) {

	            /*
	             * ======================================
	             * 1. 이동 가능한 곳 탐색
	             * ======================================
	             */

	            int nextR = -1;
	            int nextC = -1;
	            int nextDir = -1;

	            boolean canGo = false;


	            /*
	             * 현재 바라보는 방향부터
	             * 4방향 순서대로 탐색
	             */
	            for (int i = 0; i < 4; i++) {

	                int nd = (dir + i) % 4;

	                int nr = row + dr[nd];
	                int nc = col + dc[nd];


	                /*
	                 * 이동 불가능하면
	                 * 다음 방향 확인
	                 */
	                if (!canMove(nr, nc, day)) {
	                    continue;
	                }


	                /*
	                 * 가장 먼저 발견한
	                 * 이동 가능한 위치 선택
	                 */
	                nextR = nr;
	                nextC = nc;
	                nextDir = nd;

	                canGo = true;

	                break;
	            }


	            /*
	             * ======================================
	             * 2. 이동할 수 없는 경우
	             * ======================================
	             *
	             * 문제 조건:
	             *
	             * 이동 X
	             * → 가만히 있음
	             *
	             * 따라서 씨앗도 심지 않는다.
	             */
	            if (!canGo) {
	                continue;
	            }


	            /*
	             * ======================================
	             * 3. 이동 가능
	             * ======================================
	             *
	             * 현재 위치에 씨앗을 심고 이동
	             */

	            if (planted[row][col] == 0) {

	                // 현재 날짜에 씨앗을 심음
	                planted[row][col] = day;
	            }


	            /*
	             * ======================================
	             * 4. 다음 위치로 이동
	             * ======================================
	             */

	            row = nextR;
	            col = nextC;

	            /*
	             * 이동한 방향을
	             * 새로운 현재 방향으로 사용
	             */
	            dir = nextDir;


	            /*
	             * ======================================
	             * 5. 도착한 위치에서 수확 확인
	             * ======================================
	             */

	            if (planted[row][col] > 0) {

	                int plantedDay =
	                        planted[row][col];


	                /*
	                 * 예:
	                 *
	                 * 1일 : 씨앗 심음
	                 *
	                 * 2일 : 싹 1일차
	                 * 3일 : 싹 2일차
	                 * 4일 : 싹 3일차
	                 *
	                 * 5일 : 수확 가능
	                 *
	                 * 따라서
	                 *
	                 * 현재날짜 - 심은날짜 >= 4
	                 */
	                if (day - plantedDay >= 4) {

	                    harvest++;

	                    /*
	                     * 수확 완료
	                     *
	                     * 다시 빈 농지로 변경
	                     */
	                    planted[row][col] = 0;
	                }
	            }
	        }


	        return harvest;
	    }


	    /*
	     * ==========================================
	     * 해당 위치로 이동 가능한지 확인
	     * ==========================================
	     */
	    static boolean canMove(
	            int row,
	            int col,
	            int day) {


	        /*
	         * 1. 맵 범위 확인
	         */
	        if (row < 0 ||
	            row >= N ||
	            col < 0 ||
	            col >= N) {

	            return false;
	        }


	        /*
	         * 2. 산인지 확인
	         */
	        if (!farm[row][col]) {

	            return false;
	        }


	        /*
	         * 3. 아무것도 심어져 있지 않으면
	         *    이동 가능
	         */
	        if (planted[row][col] == 0) {

	            return true;
	        }


	        /*
	         * 4. 작물이 심어져 있는 경우
	         */
	        int plantedDay =
	                planted[row][col];


	        /*
	         * 심은 다음날부터 3일 동안 싹
	         *
	         * 예)
	         *
	         * plantedDay = 1
	         *
	         * day = 2 → 차이 1 → X
	         * day = 3 → 차이 2 → X
	         * day = 4 → 차이 3 → X
	         * day = 5 → 차이 4 → O
	         */
	        if (day - plantedDay < 4) {

	            return false;
	        }


	        /*
	         * 4일 이상 지났으므로
	         * 수확 가능한 농지
	         *
	         * 이동 가능
	         */
	        return true;
	    }
	}
}
