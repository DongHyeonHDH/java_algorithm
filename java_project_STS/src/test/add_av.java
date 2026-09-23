package test;
import java.util.*;
import java.io.*;
public class add_av {
    static int spotCount = 2;
    static List<Integer> answer;
    
    static int[][] grid;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
                
        // Please write your code here.
//         for (int i = 0; i < n; i++){
//             for (int j = 0; j < n; j++){
//                 System.out.print(" "+grid[i][j]);
//             }
//             System.out.println();
//         }
                
        answer = new ArrayList<>();
        //구역 찾기
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){                
                if(grid[i][j] == 1){
                    int temp = bfs(i,j,spotCount++,n);
                    answer.add(temp);
                }
            }
        }
        
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < n; j++){
        		System.out.print(" "+grid[i][j]);
            }
            System.out.println();
        }
        
        System.out.println(spotCount - 2);
        Collections.sort(answer);
        for(int as: answer){
            System.out.println(as);
        }
    }
    
    static int bfs(int row, int col, int cnt, int n){
        int res = 0;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(row,col));
        grid[row][col] = cnt;
        res += 1;
        

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int i = 0; i<4; i++){
                int r = cur.r + dr[i];
                int c = cur.c + dc[i];

                if(r<0 || r>= n || c<0 || c>= n ) continue;

                if(grid[r][c] == 1){
                    grid[r][c] = cnt;
                    q.add(new Node(r,c));
                    res++;
                }
                
            }    
        }        
    
        return res;
    }
    
    static class Node{
        int r; 
        int c;
        Node(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
