package swea.D2.과일등급분류;
import java.io.*;
import java.util.*;

public class N26059 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int lo = Integer.parseInt(st.nextToken());
            int hi = Integer.parseInt(st.nextToken());

            int[] w = new int[N];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                w[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(w);

            int answer = Integer.MAX_VALUE;

            
            for (int i = 1; i < N; i++) {
                
                if (w[i - 1] == w[i]) {
                    continue;
                }

                for (int j = i + 1; j < N; j++){ 
                    if (w[j - 1] == w[j]) {
                        continue;
                    }

                    int economy = i;
                    int standard = j - i;
                    int premium = N - j;

                    //범위확인
                    if (economy < lo || economy > hi) {
                        continue;
                    }

                    if (standard < lo || standard > hi) {
                        continue;
                    }

                    if (premium < lo || premium > hi) {
                        continue;
                    }

                    int max = Math.max(
                            economy,
                            Math.max(standard, premium)
                    );

                    int min = Math.min(
                            economy,
                            Math.min(standard, premium)
                    );

                    int diff = max - min;

                    answer = Math.min(answer, diff);
                }
            }

            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.println(sb);
    }
}

