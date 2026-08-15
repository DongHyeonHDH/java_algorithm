package swea.D2.풍선보너스2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N20230
 */
public class N20230 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine());
            int[][] gameMap = new int[N][N];
            int[][] resSum = new int[N][N];
            int[] rowSum = new int[N];
            int[] colSum = new int[N];
            int max = 0;
            
            for (int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j<N; j++){
                    gameMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //Max값 구하기
            for (int i = 0; i<N; i++){
                for (int j = 0; j<N; j++){
                    rowSum[i] += gameMap[i][j];
                    colSum[j] += gameMap[i][j];  
                }                 
            }

            for (int i = 0; i<N; i++){
                for (int j = 0; j<N; j++){                    
                    resSum[i][j] = rowSum[i] + colSum[j] - gameMap[i][j];
                }                 
            }

            for(int i = 0; i<N; i++){
                Arrays.sort(resSum[i]);
                max = Math.max(max,resSum[i][N-1]);
            }


            sb.append('#')            
                .append(tc)
                .append(' ')
                .append(max)
                .append('\n')
                ;
        }
        System.out.println(sb);
    }
}