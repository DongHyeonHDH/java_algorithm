package swea.D4.장훈이의높은선반;
import java.io.*;
import java.util.*;

public class Solution{
    static StringTokenizer st;
    static StringBuilder sb;
    static int[] arr;
    static boolean[] selected;
    static ArrayList<Integer> res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());         
        sb = new StringBuilder();       

        int n;
        int b;

        for(int t = 1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            n =  Integer.parseInt(st.nextToken());
            b =  Integer.parseInt(st.nextToken());
            
            arr = new int[n];            
            selected = new boolean[n];
            res = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for(int i =0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);            
            //구현하기            
            
            dfs(0);
            // for(int i =0; i<n; i++){
            //     dfs(i);    
            // }

            Collections.sort(res);
            // System.out.println(res);
            

            for(int i = 0; i< res.size(); i++){                
                if(res.get(i) >= b){
                   sb.append("#"+t+" "+(res.get(i)-b) + "\n");
                    break;
                }
            }
                
        }
        System.out.print(sb);    
        
    }
    
    //부분집합을 구하는 dfs
    static void dfs(int idx){
        int num_res = 0;

        if(idx == arr.length){
            for(int i =0;i<arr.length; i++){
                if(selected[i]){
                    num_res += arr[i];                        
                    // System.out.print(arr[i]+" ");
                }                    
            }
            // System.out.println();
            res.add(num_res);
            
            return;
        }        
    

        selected[idx] = true;
        dfs(idx+1);

        selected[idx] = false;
        dfs(idx+1);
    }
}