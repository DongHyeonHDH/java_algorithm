package swea.비상탈출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EmergencyEscape {

    static int[] speed;
    static int[] height;
    public static void main(String[] args) throws IOException{
    
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());

      StringTokenizer st = new StringTokenizer(br.readLine());
      StringTokenizer st2 = new StringTokenizer(br.readLine());
      speed = new int[n+1];
      height = new int[n+1];

      for(int i =1; i<=n; i++){
        speed[i] = Integer.parseInt(st.nextToken());
        height[i] = Integer.parseInt(st2.nextToken());
      }

      int answer = -1;      

      outer:
      for(int i =1; i<= n; i++){
        //첫번째 경우
        for(int j =i+1; j<= n; j++){                    
          if(j-i == 2){
            if(height_change(i, j) >= 2000 && speed_avg(i,j) >= 1100){
              answer = j;
              
              break outer;
            }            

          }
          if(j-i == 3){
            if(height_change(i, j) >= 3000 && speed_avg(i,j) >= 1000){
              answer = j;
              
              break outer;
            }            

          }
          if(j-i == 4){
            if(height_change(i, j) >= 4000 && speed_avg(i,j) >= 900){
              answer = j;
              
              break outer;
            }            

          }        
        }
        
      }
      System.out.println(answer);
    }
    static int height_change(int a, int b){      
      return height[a] - (height[a]-height[b]) ;            
    }
    //a는 현재시점 //b는 이전시점
    static double speed_avg(int a, int b){      
      int avg = 0;
      for(int i = a; i<=b-1; i++){
        avg += speed[i];
      }
      
      return (double)avg / (b-a);
    }
}