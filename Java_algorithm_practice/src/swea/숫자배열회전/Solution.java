package swea.숫자배열회전;
import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(st.nextToken());

            String[][] arr = new String[n][n];
            String[][] arr_res = new String[n][3];
            //배열 세팅
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    arr[i][j] = st.nextToken();
                }
            }
            
            //90도 회전            
            for(int i = 0; i< n; i++){
                sb.setLength(0);
                for(int j = 0; j< n; j++){
                    sb.append(arr[n-1-j][i]);
                }
                arr_res[i][0] =sb.toString();
            }          
            
            //180도 회전            
            for(int i = 0; i< n; i++){
                sb.setLength(0);
                for(int j = 0; j< n; j++){
                    sb.append(arr[n-i-1][n-j-1]);
                }
                arr_res[i][1] =sb.toString();
            }
            
            
            for(int i = 0; i< n; i++){
                sb.setLength(0);
                for(int j = 0; j< n; j++){
                    sb.append(arr[j][i]);
                }
                arr_res[n-i-1][2] =sb.toString();
            }
            
            System.out.println("#"+t);
            for(int i = 0; i< n; i++){
                for(int j = 0; j< 3; j++){
                    System.out.print(arr_res[i][j]);
                    if (j < 2) {
                        System.out.print(" ");
                    }
                }                
                System.out.println();
            }

        }

    }
}
