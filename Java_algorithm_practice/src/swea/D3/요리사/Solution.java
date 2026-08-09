package swea.D3.요리사;

import java.util.*;
import java.io.*;

public class Solution{

    static int[][] S;
    static boolean[] selected;
    static int N;
    static int minD;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            S = new int[N][N];
            selected = new boolean[N];
            minD = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                StringTokenizer st =
                        new StringTokenizer(br.readLine());

                for (int j = 0; j < N; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            
            selected[0] = true;
            combination(1, 1);

            sb.append("#").append(t)
                            .append(" ")
                            .append(minD)
                            .append("\n");
        }

        System.out.print(sb);
    }


    
    static void combination(int start, int count) {

        if (count == N / 2) {
            calculateDifference();
            return;
        }
        
        // 남은 것만 탐색                
            
        for (int i = start; i <= N - (N / 2 - count); i++) {
            selected[i] = true;

            combination(i + 1, count + 1);

            selected[i] = false;
        }
    }

    
    //  절반만 선택 A, 선택하지 않은 것은 B
     
    static void calculateDifference() {

        int aTaste = 0;
        int bTaste = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                
                if (selected[i] && selected[j]) {
                    aTaste += S[i][j] + S[j][i];
                }
                
                else if (!selected[i] && !selected[j]) {
                    bTaste += S[i][j] + S[j][i];
                }
            }
        }

        int difference = Math.abs(aTaste - bTaste);
        minD = Math.min(minD, difference);
    }
}
/*

*/