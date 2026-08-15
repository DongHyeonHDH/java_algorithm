package swea.D4.N1232;
import java.io.*;
import java.util.*;
public class Solution{   
    
    static Node[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    
        StringBuilder sb =new StringBuilder();

        for(int t=1; t<=10; t++){
            int N = Integer.parseInt(br.readLine());
            tree = new Node[N+1];
            for(int i = 0; i<N; i++){                
                StringTokenizer st = new StringTokenizer(br.readLine());

                int idx = Integer.parseInt(st.nextToken());
                String value = st.nextToken();

                if(st.countTokens() == 0){
                    int num = Integer.parseInt(value);
                    tree[idx] = new Node(num);
                }
                else{
                    int left = Integer.parseInt(st.nextToken());
                    int right = Integer.parseInt(st.nextToken());

                    tree[idx] = new Node(value.charAt(0),left, right);
                }
                
                
            }

            double result = calculate(1);

            sb.append("#")
              .append(t)
              .append(" ")
              .append((int) result)
              .append("\n");
    
        }
        System.out.println(sb);
    }
    static double calculate(int idx){
        Node node = tree[idx];
        
        if(node.isNumber){
            return node.number;
        }

        double left = calculate(node.left);
        double right = calculate(node.right);

        switch(node.operator){
            case '+': return left+right;
            case '-': return left-right;
            case '*': return left*right;
            case '/': return left/right;
        }

        return 0;
    }

    static class Node{
        boolean isNumber;
        double number;
        char operator;
        int left;
        int right;

        Node(double number){
            this.isNumber = true;
            this.number = number;
        }

        Node(char operator, int left, int right){
            this.isNumber = false;
            this.operator = operator;
            this.left = left;
            this.right = right;
        }
    }
}