package swea.D2.부분수열판별;

import java.io.*;
import java.util.*;
public class N26045 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] arrA = new int[N];
            int[] arrB = new int[M];
            String res;

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i< N; i++){
                arrA[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i< M; i++){
                arrB[i] = Integer.parseInt(st.nextToken());
            }

            // A의 위치및 개수 카운트해주는 함수
            int cnt = 0;
            for(int i = 0; i< N; i++){           
                if(arrA[i] == arrB[cnt]){
                    cnt++;                    

                    if(cnt == M-1){
                        break;
                    }
                }               
                
            }

            if(cnt == M-1){
                res = "YES";
            }
            else{
                res = "NO";
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
