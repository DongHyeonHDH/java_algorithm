package swea.역검대비.나무높이;
import java.io.*;
import java.util.*;
public class N14510 {
    static int N;
    static int[] tree;
    static int maxHeight;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            tree = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            maxHeight = 0;

            for(int i = 0; i<N; i++){
                tree[i] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, tree[i]);            
            }

            int left = 0;
            int right = 1;

            while(!check(right)){
                right *=2;
            }
            while(left <right){
                int mid = (left +right) /2;
                if(check(mid)){
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

                sb.append("#")
              .append(tc)
              .append(" ")
              .append(left)
              .append("\n");
        }
        System.out.println(sb);
    }

    static boolean check(int day){
        long odd = (day+ 1L) /2L;        
        long even = day /2L;

        long requiredOdd = 0;
        for(int h : tree){
            long diff = maxHeight - h;
            requiredOdd += diff %2;
        }

        if(requiredOdd > odd){
            return false;
        }

        long requiredEven = 0;
        for(int h: tree){
            long diff = maxHeight - h;
            requiredEven += diff /2;
        }

        long remainingOdd = odd - requiredOdd;
        long replacedEven = remainingOdd / 2;

        return requiredEven <= even + replacedEven;
    }
}
