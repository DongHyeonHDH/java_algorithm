package swea.D3.규영이와인영이의카드게임;
import java.io.*;
import java.util.*;
public class Solution {
    static StringBuilder br = new StringBuilder();
    static int[] kyuCard;
    static int[] inCard;
    static boolean[] visited = new boolean[9] ;
    static int[] temp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
       
        //규영이 카드
        kyuCard = new int[9];
        //전체 카드
        LinkedList<Integer> wholeCard = new LinkedList<>();

        for(int t = 1; t<=T; t++){
            // StringTokenizer st = new StringTokenizer(br.readLine()); 
            for(int i =1; i<=18; i++){
               wholeCard.add(i);
            }
            System.out.println(wholeCard);
            
            // for(int i =0; i<9; i++){
            //     kyuCard[i] = Integer.parseInt(st.nextToken()); 
            //     kyuCard[i]   ;            
            // }  
           
             
            inCard = wholeCard.stream().mapToInt(i->i).toArray();
            System.out.println(Arrays.toString(inCard));
            // temp = new int[9];     

            // for(int i =0; i<9; i++){                
            //     permutation(0);
            //     System.out.println(Arrays.toString(temp));      
            // }
        }
        
    }

    //9! 경우의 수 구하기
    static void permutation(int idx){
        visited[idx] = true;        
        for(int i = idx; i < 9; i++){
            if(!visited[i]){
                temp[i] = inCard[i];
            }
                
        }        
        visited[idx] = false;
        permutation(idx + 1);
    }

    static int factorial(int num){
        int res=1;
        for(int i = num; num>=1; i--){
            res *= i;
        }
        return res;
    }
    static boolean compute(int[] kyuCard, int[] inCard){
        int in = 0;
        int kyu = 0;
        for(int i=0; i<9; i++){
            if(inCard[i] > kyuCard[i]){
                in += inCard[i] + kyuCard[i];
            }
            else{
                kyu += inCard[i] + kyuCard[i];
            }
        }

        if(kyu > in){
            return true;
        }
        else{
            return false;
        }
    }
}
