package swea.D2.파리퇴치;
import java.util.*;
import java.io.*;

// Scanner 임포트 추가

public class Solution {
    public static void main(String[] args) throws IOException { 
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++)
		{
            //배열 세팅
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());;		
			
            int[][] arr_n = new int[n][n];           
            
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    arr_n[i][j] = Integer.parseInt(st.nextToken());
                    
                }
            }
            
            //구현 시작
            int res_max = 0;
            int res_temp = 0;

            for(int i = 0; i < n-m+1; i++){
                for(int j = 0; j < n-m+1; j++){
                    res_temp = 0;

                    for(int k =i; k< i+m; k++){
                        for(int l =j; l< j+m; l++){
                            res_temp += arr_n[k][l];
                            res_max = Math.max(res_temp, res_max);
                        }    
                    }
                }         
            }

            System.out.println("#"+t+" "+res_max);
            
		}        
        
        
    }
}