package swea.D3.정곤이의단조증가하는수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N6190 {    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <=T; tc++){
            int N = Integer.parseInt(br.readLine());
            int[] numArr = new int[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine()); 
            for(int i = 1; i<=N; i++){
                numArr[i] = Integer.parseInt(st.nextToken());
            }

            long temp = 0;
            long max = -1;
            for(int i = 1; i<=N-1; i++){
                for(int j = i+1; j<=N; j++){
                    temp = numArr[i] * numArr[j];
                    temp = judge(temp);
                    max = Math.max(temp, max);
                }
            }
            sb.append('#')
            .append(tc)
            .append(' ')
            .append(max)
            .append('\n')
            ;
        }
        System.out.println(sb);        
    }
    //문자열화 해서 단조하는지 확인하는 함수
    static long judge(long num){
        String temp = String.valueOf(num);
        for(int i = 0; i< temp.length()-1; i++){
            if( temp.charAt(i) > temp.charAt(i+1)){
                return -1;
            }
        }

        return num;
    }
}
