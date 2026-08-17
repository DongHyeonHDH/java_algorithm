package swea.D3.신뢰;

import java.io.*;
import java.util.*;

public class N10761 {
    static List<Integer> blue;
    static List<Integer> orange;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            
            for(int i = 0; i< N; i++){
                String check = st.nextToken();
                if(check.equals("B")){
                    blue.add(Integer.parseInt(st.nextToken()));
                }else{
                    orange.add(Integer.parseInt(st.nextToken()));
                }
            }

        }
    }
    
}
