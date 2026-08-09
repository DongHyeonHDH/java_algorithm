package swea.D2.두개의문자열;
import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());        

        for(int t = 1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());            
            
            int[] arr_n = new int[n];
            int[] arr_m = new int[m];           

            //배열 입력값 저장
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                arr_n[i] = Integer.parseInt(st.nextToken()); 
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++){
                arr_m[i] = Integer.parseInt(st.nextToken());
            }

            if (n>m){
                int[] temp_a = arr_n;
                arr_n = arr_m;
                arr_m = temp_a;

                int temp = n;
                n = m;
                m = temp;
            }
            
            //구현
            int[] arr_temp = new int[m];
            int[] arr_res = new int[m];
            int temp_sum = 0;
            int max_res = 0;

            for(int i = 0; i<=m-n;i++){
                temp_sum = 0;
                arr_temp = new int[m];
                arr_res = new int[m];

                for(int j = 0; j < n; j++){
                    arr_temp[i+j] = arr_n[j]; 
                    //System.out.print(arr_temp[j] + " ");                   
                }
                // System.out.println();

                for(int j = 0; j < m; j++){
                    arr_res[j] = arr_m[j] * arr_temp[j];
                    temp_sum += arr_res[j];                    
                }
                // System.out.println("temp_sum: " + temp_sum);
                max_res = Math.max(max_res,temp_sum);
            }
            System.out.println("#"+t +" "+max_res);

        }
    }
}
