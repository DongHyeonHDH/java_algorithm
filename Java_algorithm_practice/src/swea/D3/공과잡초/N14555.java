package swea.D3.공과잡초;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N14555 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for(int tc = 1; tc <= T; tc++){
            String weedMap =  br.readLine();
            int count = 0;
            for(int i = 0; i< weedMap.length()-1; i++){
                if(weedMap.charAt(i) == '('){
                    if(weedMap.charAt(i+1) == ')' || weedMap.charAt(i+1) == '|' ){
                        count +=1;
                    }
                }

                if(weedMap.charAt(i) == '|'){
                     if(weedMap.charAt(i+1) == ')'){
                        count +=1;
                    }
                }
            }
            sb.append('#')
            .append(tc)
            .append(' ')
            .append(count)
            .append('\n')
            ;
        }
        System.out.println(sb);
    }
}
