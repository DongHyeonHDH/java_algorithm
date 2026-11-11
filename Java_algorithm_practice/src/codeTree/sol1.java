//아름다운수 
package codeTree;
import java.util.*;
public class sol1 {
    static int answer, n;
    static ArrayList<Integer> seq = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        answer = 0;

        // Please write your code here.
        dfs(0);

        System.out.println(answer);
        sc.close();
    }
    static void dfs(int idx){
        if(idx == n){
            if(isBeautiful())
                answer++;
            return;
        }

        for(int i = 1; i<= 4; i++){
            seq.add(i);
            dfs(idx+1);
            seq.remove(seq.size()-1);       
        }
    }

    static boolean isBeautiful(){        
        int check = 0;
        int start = seq.get(0);
        
        for(int i = 0; i< seq.size(); i++){
            if(start > i){
                continue;
            }

            if(start == i){
                start += seq.get(i);
                check = seq.get(i);                
                // System.out.println(start );

                for(int j =i; j< (i + check-1); j++){
                    if(seq.get(j) != check){
                        return false;
                    }
                }
            }               
        }
        return true; 
    }
}
