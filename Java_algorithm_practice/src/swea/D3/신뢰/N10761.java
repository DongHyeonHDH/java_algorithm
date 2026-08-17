package swea.D3.신뢰;

import java.io.*;
import java.util.*;

public class N10761 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            char[] robot = new char[N];
            int[] button = new int[N];

            for (int i = 0; i < N; i++) {
                robot[i] = st.nextToken().charAt(0);
                button[i] = Integer.parseInt(st.nextToken());
            }

            //시작 위치
            int orangePos = 1;
            int bluePos = 1;

            // 각 로봇이 마지막으로 버튼을 누른 전체 시간
            int orangeTime = 0;
            int blueTime = 0;

            // 현재 시간
            int totalTime = 0;


            int passedTime = 0;
            int distance = 0;
            int remainDistance=0;
            for (int i = 0; i < N; i++) {

                char currentRobot = robot[i];
                int target = button[i];
                

                if (currentRobot == 'O') {

                    //오랜지 처리시 블루의 처리시간
                    passedTime = totalTime - orangeTime;

                    // 현재 위치에서 목표까지 필요한 거리
                    distance = Math.abs(target - orangePos);
                    
                    // 이미 이동할 수 있었던 시간을 제외
                    remainDistance = Math.max(0, distance - passedTime);

                    // 남은 이동 시간 + 버튼 누르는 시간
                    totalTime += remainDistance + 1;

                    orangePos = target;
                    orangeTime = totalTime;

                }
                else {

                    passedTime = totalTime - blueTime;
                    distance = Math.abs(target - bluePos);
                    remainDistance = Math.max(0, distance - passedTime);

                    totalTime += remainDistance + 1;

                    bluePos = target;
                    blueTime = totalTime;
                }
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(totalTime)
              .append("\n");
        }

        System.out.print(sb);
    }
    
}
