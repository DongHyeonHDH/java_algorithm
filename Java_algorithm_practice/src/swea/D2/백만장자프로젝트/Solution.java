package swea.D2.백만장자프로젝트;
import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        long [] answer = new long[T];
        for(int t= 0; t< T; t++){
            // 배열 입력
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int[] arr = new int[n];            
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(st.nextToken()); 
            }
           
            // 구현
            long max_num = 0;
            long profit = 0;

            for(int i = n-1; i>=0; i--){
                if(arr[i]>max_num){
                    max_num = arr[i];
                }
                else{
                    profit += max_num - arr[i];
                }
            }
            
            answer[t] = profit;
            
        }    
        for(int i =0; i<T; i++){
            System.out.println("#"+ (i+1) + " "+ answer[i]);
        }
    }
    
    
}
