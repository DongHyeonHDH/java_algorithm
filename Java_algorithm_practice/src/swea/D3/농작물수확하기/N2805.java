package swea.D3.농작물수확하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc<=T; tc++){
            int N = Integer.parseInt(br.readLine());
            int mid = N/2;
            int res = 0;
            int[][] farm = new int[N][N];
            for(int i = 0; i< N; i++){
                String temp = br.readLine();
                for(int j = 0; j< N; j++){
                    farm[i][j] = (int)(temp.charAt(j)) - 48;
                }
            }

            //중간값 이전
            int upCount = 1;
            for(int i = mid-1; i>=0; i--){
                for(int j = upCount; j< N-upCount; j++){
                    res += farm[i][j];
                }
                upCount +=1;
            }

            //중간값
            for(int j = 0; j< N; j++){
                res += farm[mid][j];
            }

            //중간값 이후
            int downCount =1;
            for(int i = mid + 1; i<N; i++){
                for(int j = downCount; j< N-downCount; j++){
                    res += farm[i][j];
                }
                downCount +=1;
            }

            sb.append('#')
            .append(tc)
            .append(' ')
            .append(res)
            .append('\n')
            ;
        }
        System.out.println(sb);
    }
    
}